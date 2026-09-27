package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.navigation.Screen
import com.example.ui.screens.ChapterDetailScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ImageViewerScreen
import com.example.ui.screens.NoteViewerScreen
import com.example.ui.screens.OtherMaterialScreen
import com.example.ui.screens.PdfViewerScreen
import com.example.ui.screens.SubjectChaptersScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.StudyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent(
    viewModel: StudyViewModel = viewModel()
) {
    val allMaterials by viewModel.allMaterials.collectAsStateWithLifecycle()
    val totalCount by viewModel.totalCount.collectAsStateWithLifecycle()
    val pdfCount by viewModel.pdfCount.collectAsStateWithLifecycle()
    val noteCount by viewModel.noteCount.collectAsStateWithLifecycle()
    val userMessage by viewModel.userMessage.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(userMessage) {
        userMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearUserMessage()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        AnimatedContent(
            targetState = viewModel.currentScreen,
            transitionSpec = {
                fadeIn() togetherWith fadeOut()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            label = "ScreenTransition"
        ) { targetScreen ->
            when (targetScreen) {
                is Screen.Home -> {
                    HomeScreen(
                        viewModel = viewModel,
                        totalCount = totalCount,
                        pdfCount = pdfCount,
                        noteCount = noteCount
                    )
                }

                is Screen.SubjectChapters -> {
                    SubjectChaptersScreen(
                        subjectId = targetScreen.subjectId,
                        viewModel = viewModel,
                        materials = allMaterials
                    )
                }

                is Screen.ChapterDetail -> {
                    ChapterDetailScreen(
                        subjectId = targetScreen.subjectId,
                        chapterNumber = targetScreen.chapterNumber,
                        viewModel = viewModel,
                        materials = allMaterials
                    )
                }

                is Screen.OtherMaterial -> {
                    OtherMaterialScreen(
                        initialCategory = targetScreen.selectedCategory,
                        viewModel = viewModel,
                        materials = allMaterials
                    )
                }

                is Screen.PdfViewer -> {
                    PdfViewerScreen(
                        filePath = targetScreen.filePath,
                        title = targetScreen.title,
                        viewModel = viewModel
                    )
                }

                is Screen.ImageViewer -> {
                    ImageViewerScreen(
                        filePath = targetScreen.filePath,
                        title = targetScreen.title,
                        viewModel = viewModel
                    )
                }

                is Screen.NoteViewer -> {
                    NoteViewerScreen(
                        materialId = targetScreen.materialId,
                        viewModel = viewModel,
                        materials = allMaterials
                    )
                }

                is Screen.NoteEditor -> {
                    // Handled within dialog/viewer
                    HomeScreen(
                        viewModel = viewModel,
                        totalCount = totalCount,
                        pdfCount = pdfCount,
                        noteCount = noteCount
                    )
                }
            }
        }
    }
}
