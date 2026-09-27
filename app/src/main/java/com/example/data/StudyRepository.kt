package com.example.data

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class StudyRepository(
    private val context: Context,
    private val dao: StudyMaterialDao
) {
    val allMaterials: Flow<List<StudyMaterial>> = dao.getAllMaterials()
    val totalMaterialsCount: Flow<Int> = dao.getMaterialCount()
    val totalPdfsCount: Flow<Int> = dao.getPdfCount()
    val totalNotesCount: Flow<Int> = dao.getNoteCount()

    fun getMaterialsBySubject(subjectId: String): Flow<List<StudyMaterial>> =
        dao.getMaterialsBySubject(subjectId)

    fun getMaterialsByChapter(subjectId: String, chapterNumber: Int): Flow<List<StudyMaterial>> =
        dao.getMaterialsByChapter(subjectId, chapterNumber)

    fun getOtherMaterialsByCategory(category: String): Flow<List<StudyMaterial>> =
        dao.getOtherMaterialsByCategory(category)

    suspend fun getMaterialById(id: Long): StudyMaterial? = withContext(Dispatchers.IO) {
        dao.getMaterialById(id)
    }

    suspend fun checkAndSeedInitialData() = withContext(Dispatchers.IO) {
        if (dao.getCountSync() == 0) {
            val seedItems = SampleFileHelper.ensureSampleFilesAndGetSeedData(context)
            dao.insertAll(seedItems)
        }
    }

    suspend fun insertMaterial(material: StudyMaterial): Long = withContext(Dispatchers.IO) {
        dao.insertMaterial(material)
    }

    suspend fun updateMaterial(material: StudyMaterial) = withContext(Dispatchers.IO) {
        dao.updateMaterial(material)
    }

    suspend fun deleteMaterial(material: StudyMaterial) = withContext(Dispatchers.IO) {
        // Also remove local file if user-created
        if (material.filePath.isNotEmpty() && !material.isSample) {
            try {
                val file = File(material.filePath)
                if (file.exists()) {
                    file.delete()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        dao.deleteMaterial(material)
    }

    suspend fun saveImportedFile(
        uri: Uri,
        subjectId: String,
        chapterNumber: Int,
        chapterTitle: String,
        category: String,
        materialType: String,
        customTitle: String,
        description: String
    ): Long = withContext(Dispatchers.IO) {
        var originalName = "imported_file_${System.currentTimeMillis()}"
        var fileSize = 0L

        // Query file metadata from ContentResolver
        try {
            context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                if (cursor.moveToFirst()) {
                    if (nameIndex != -1) {
                        originalName = cursor.getString(nameIndex) ?: originalName
                    }
                    if (sizeIndex != -1) {
                        fileSize = cursor.getLong(sizeIndex)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Copy file into internal app directory
        val studyDir = File(context.filesDir, "study_files")
        if (!studyDir.exists()) {
            studyDir.mkdirs()
        }

        val destinationFile = File(studyDir, "${System.currentTimeMillis()}_$originalName")
        try {
            context.contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(destinationFile).use { output ->
                    input.copyTo(output)
                }
            }
            if (fileSize == 0L) {
                fileSize = destinationFile.length()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val item = StudyMaterial(
            subjectId = subjectId,
            chapterNumber = chapterNumber,
            chapterTitle = chapterTitle,
            category = category,
            materialType = materialType,
            title = if (customTitle.isNotBlank()) customTitle else originalName,
            description = description,
            content = "Imported File: $originalName",
            filePath = destinationFile.absolutePath,
            fileName = originalName,
            fileSize = fileSize,
            isSample = false
        )
        dao.insertMaterial(item)
    }
}
