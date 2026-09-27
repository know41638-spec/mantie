package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.StudyMaterial
import com.example.model.CurriculumData
import com.example.ui.components.AddMaterialDialog
import com.example.ui.components.ChapterItemCard
import com.example.ui.navigation.Screen
import com.example.ui.theme.AcademicBluePrimary
import com.example.ui.theme.ChemistryPurple
import com.example.ui.theme.MathsOrange
import com.example.ui.theme.PhysicsBlue
import com.example.ui.viewmodel.StudyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectChaptersScreen(
    subjectId: String,
    viewModel: StudyViewModel,
    materials: List<StudyMaterial>,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.popBack()
    }

    var searchQuery by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }

    val (screenTitle, accentColor, chapters) = when (subjectId) {
        "PHYSICS" -> Triple("Physics – Class 11", PhysicsBlue, CurriculumData.physicsChapters)
        "CHEMISTRY" -> Triple("Chemistry – Class 11", ChemistryPurple, CurriculumData.chemistryChapters)
        "MATHS" -> Triple("Maths – Class 11", MathsOrange, CurriculumData.mathsChapters)
        else -> Triple("Study Material", AcademicBluePrimary, emptyList())
    }

    val filteredChapters = chapters.filter { chapter ->
        searchQuery.isBlank() ||
            chapter.title.contains(searchQuery, ignoreCase = true) ||
            chapter.description.contains(searchQuery, ignoreCase = true) ||
            chapter.keyTopics.any { it.contains(searchQuery, ignoreCase = true) } ||
            "Chapter ${chapter.number}".contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = screenTitle,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                        Text(
                            text = "${chapters.size} Chapters • NCERT Syllabus",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.popBack() },
                        modifier = Modifier.testTag("back_button_subject_screen")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to Home"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showAddDialog = true },
                icon = { Icon(Icons.Default.Add, contentDescription = "Add Material") },
                text = {
                    Text(
                        text = "Add to ${subjectId.lowercase().replaceFirstChar { it.uppercase() }}",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                containerColor = accentColor,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.testTag("add_to_subject_fab")
            )
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val isWideScreen = maxWidth >= 600.dp

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = if (isWideScreen) 32.dp else 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Search Bar
                item {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("search_chapters_input"),
                        placeholder = { Text("Search chapters or topics (e.g. Kinematics, Mole)...") },
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(Icons.Default.Close, contentDescription = "Clear")
                                }
                            }
                        },
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true
                    )
                }

                // Header Overview
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        color = accentColor.copy(alpha = 0.08f)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "💡",
                                fontSize = 24.sp
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Select any chapter below to view or add Notes, PDFs, and Diagrams.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // Chapter List
                items(filteredChapters) { chapter ->
                    val chapterMaterials = materials.filter {
                        it.subjectId == subjectId && it.chapterNumber == chapter.number
                    }
                    val pdfCount = chapterMaterials.count { it.materialType == "PDF" }

                    ChapterItemCard(
                        chapter = chapter,
                        materialCount = chapterMaterials.size,
                        pdfCount = pdfCount,
                        accentColor = accentColor,
                        onClick = {
                            viewModel.navigateTo(
                                Screen.ChapterDetail(subjectId = subjectId, chapterNumber = chapter.number)
                            )
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    }

    if (showAddDialog) {
        AddMaterialDialog(
            initialSubjectId = subjectId,
            initialChapterNumber = 1,
            onDismiss = { showAddDialog = false },
            onAddNote = { sId, chNum, chTitle, cat, title, content, desc ->
                viewModel.saveNote(
                    materialId = null,
                    subjectId = sId,
                    chapterNumber = chNum,
                    chapterTitle = chTitle,
                    category = cat,
                    title = title,
                    content = content,
                    description = desc
                )
            },
            onImportFile = { uri, sId, chNum, chTitle, cat, type, title, desc ->
                viewModel.importFile(
                    uri = uri,
                    subjectId = sId,
                    chapterNumber = chNum,
                    chapterTitle = chTitle,
                    category = cat,
                    materialType = type,
                    title = title,
                    description = desc
                )
            }
        )
    }
}
