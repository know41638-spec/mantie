package com.example.ui.navigation

sealed class Screen {
    data object Home : Screen()

    data class SubjectChapters(
        val subjectId: String // "PHYSICS", "CHEMISTRY", "MATHS"
    ) : Screen()

    data class ChapterDetail(
        val subjectId: String,
        val chapterNumber: Int
    ) : Screen()

    data class OtherMaterial(
        val selectedCategory: String = "📝 School Notes"
    ) : Screen()

    data class PdfViewer(
        val materialId: Long? = null,
        val filePath: String,
        val title: String
    ) : Screen()

    data class ImageViewer(
        val materialId: Long? = null,
        val filePath: String,
        val title: String
    ) : Screen()

    data class NoteEditor(
        val materialId: Long? = null,
        val subjectId: String,
        val chapterNumber: Int,
        val chapterTitle: String,
        val category: String,
        val initialTitle: String = "",
        val initialContent: String = "",
        val initialDescription: String = ""
    ) : Screen()

    data class NoteViewer(
        val materialId: Long
    ) : Screen()
}
