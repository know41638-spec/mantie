package com.example.ui.viewmodel

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.StudyMaterial
import com.example.data.StudyRepository
import com.example.model.CurriculumData
import com.example.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StudyRepository

    val navigationStack = mutableStateListOf<Screen>(Screen.Home)

    val currentScreen: Screen
        get() = navigationStack.lastOrNull() ?: Screen.Home

    val allMaterials: StateFlow<List<StudyMaterial>>
    val totalCount: StateFlow<Int>
    val pdfCount: StateFlow<Int>
    val noteCount: StateFlow<Int>

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _userMessage = MutableStateFlow<String?>(null)
    val userMessage: StateFlow<String?> = _userMessage.asStateFlow()

    init {
        val database = AppDatabase.getDatabase(application)
        repository = StudyRepository(application, database.studyMaterialDao())

        allMaterials = repository.allMaterials.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        totalCount = repository.totalMaterialsCount.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

        pdfCount = repository.totalPdfsCount.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

        noteCount = repository.totalNotesCount.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

        viewModelScope.launch {
            repository.checkAndSeedInitialData()
        }
    }

    fun navigateTo(screen: Screen) {
        navigationStack.add(screen)
    }

    fun popBack(): Boolean {
        return if (navigationStack.size > 1) {
            navigationStack.removeAt(navigationStack.size - 1)
            true
        } else {
            false
        }
    }

    fun popToHome() {
        while (navigationStack.size > 1) {
            navigationStack.removeAt(navigationStack.size - 1)
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun clearUserMessage() {
        _userMessage.value = null
    }

    fun showMessage(msg: String) {
        _userMessage.value = msg
    }

    fun saveNote(
        materialId: Long?,
        subjectId: String,
        chapterNumber: Int,
        chapterTitle: String,
        category: String,
        title: String,
        content: String,
        description: String
    ) {
        viewModelScope.launch {
            if (materialId != null && materialId > 0) {
                val existing = repository.getMaterialById(materialId)
                if (existing != null) {
                    repository.updateMaterial(
                        existing.copy(
                            title = title,
                            content = content,
                            description = description,
                            category = category
                        )
                    )
                    showMessage("Note updated successfully")
                }
            } else {
                val newNote = StudyMaterial(
                    subjectId = subjectId,
                    chapterNumber = chapterNumber,
                    chapterTitle = chapterTitle,
                    category = category,
                    materialType = "NOTE",
                    title = title,
                    content = content,
                    description = description,
                    isSample = false
                )
                repository.insertMaterial(newNote)
                showMessage("Note saved to $chapterTitle")
            }
            popBack()
        }
    }

    fun importFile(
        uri: Uri,
        subjectId: String,
        chapterNumber: Int,
        chapterTitle: String,
        category: String,
        materialType: String,
        title: String,
        description: String
    ) {
        viewModelScope.launch {
            try {
                repository.saveImportedFile(
                    uri = uri,
                    subjectId = subjectId,
                    chapterNumber = chapterNumber,
                    chapterTitle = chapterTitle,
                    category = category,
                    materialType = materialType,
                    customTitle = title,
                    description = description
                )
                showMessage("Successfully imported $materialType")
            } catch (e: Exception) {
                showMessage("Failed to import file: ${e.localizedMessage}")
            }
        }
    }

    fun deleteMaterial(material: StudyMaterial) {
        viewModelScope.launch {
            repository.deleteMaterial(material)
            showMessage("Material deleted")
            if (currentScreen is Screen.NoteViewer) {
                popBack()
            }
        }
    }

    fun getChapter(subjectId: String, chapterNumber: Int) = when (subjectId) {
        "PHYSICS" -> CurriculumData.physicsChapters.find { it.number == chapterNumber }
        "CHEMISTRY" -> CurriculumData.chemistryChapters.find { it.number == chapterNumber }
        "MATHS" -> CurriculumData.mathsChapters.find { it.number == chapterNumber }
        else -> null
    }
}
