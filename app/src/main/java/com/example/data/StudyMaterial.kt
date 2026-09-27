package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_materials")
data class StudyMaterial(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val subjectId: String,          // "PHYSICS", "CHEMISTRY", "MATHS", "OTHER"
    val chapterNumber: Int,         // 1..15, or 0 for Other
    val chapterTitle: String,
    val category: String,           // E.g. "School Notes", "Question Papers", "Reference Material", "Important Resources", "Other"
    val materialType: String,       // "NOTE", "PDF", "IMAGE", "RESOURCE"
    val title: String,
    val description: String = "",
    val content: String = "",       // Full note body / formula sheet text
    val filePath: String = "",      // Internal storage path or URI for local PDFs/Images
    val fileName: String = "",
    val fileSize: Long = 0L,
    val externalUrl: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val isSample: Boolean = false
)
