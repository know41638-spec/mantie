package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.AboutSection
import com.example.ui.components.AddMaterialDialog
import com.example.ui.components.SubjectCard
import com.example.ui.navigation.Screen
import com.example.ui.theme.AcademicBlueLight
import com.example.ui.theme.AcademicBluePrimary
import com.example.ui.theme.ChemistryPurple
import com.example.ui.theme.MathsOrange
import com.example.ui.theme.OtherGreen
import com.example.ui.theme.PhysicsBlue
import com.example.ui.viewmodel.StudyViewModel

@Composable
fun HomeScreen(
    viewModel: StudyViewModel,
    totalCount: Int,
    pdfCount: Int,
    noteCount: Int,
    modifier: Modifier = Modifier
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showAddDialog = true },
                icon = { Icon(Icons.Default.Add, contentDescription = "Add Material") },
                text = {
                    Text(
                        text = "Add Material",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                containerColor = AcademicBluePrimary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.testTag("home_add_material_fab")
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
                contentPadding = PaddingValues(horizontal = if (isWideScreen) 32.dp else 20.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // 1. Header Banner & Identity
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Hero Illustration Banner
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(if (isWideScreen) 200.dp else 140.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            shape = RoundedCornerShape(20.dp),
                            tonalElevation = 2.dp
                        ) {
                            Box {
                                Image(
                                    painter = painterResource(id = R.drawable.study_hero_banner_1790472625543),
                                    contentDescription = "Class 11 PCM Study Portal",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(
                                                    Color.Black.copy(alpha = 0.25f),
                                                    Color.Black.copy(alpha = 0.65f)
                                                )
                                            )
                                        )
                                )
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(20.dp)
                                ) {
                                    // Required App Identity
                                    Text(
                                        text = "Naitik Jain",
                                        style = MaterialTheme.typography.displayMedium.copy(
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = if (isWideScreen) 34.sp else 28.sp
                                        ),
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Hi, Class 11 👋",
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = if (isWideScreen) 22.sp else 18.sp
                                        ),
                                        color = Color(0xFFE2E8F0)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Offline & Status Badges
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                                modifier = Modifier.weight(1f)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = null,
                                        tint = AcademicBluePrimary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "$totalCount Items",
                                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                        Text(
                                            text = "$noteCount Notes • $pdfCount PDFs",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                                        )
                                    }
                                }
                            }

                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFFE8F5E9),
                                modifier = Modifier.weight(1f)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = Color(0xFF2E7D32),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "100% Offline",
                                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                            color = Color(0xFF1B5E20)
                                        )
                                        Text(
                                            text = "No login required",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color(0xFF2E7D32)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // 2. Four Subject Cards Header
                item {
                    Text(
                        text = "Study Subjects",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // 3. Four Subject Cards (Physics, Chemistry, Maths, Other Material)
                item {
                    if (isWideScreen) {
                        // Wide screen / Android TV grid layout
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                SubjectCard(
                                    title = "📘 Physics",
                                    subtitle = "Mechanics, Waves, Thermodynamics",
                                    chapterCountText = "15 Chapters",
                                    iconEmoji = "📘",
                                    accentColor = PhysicsBlue,
                                    gradientColors = listOf(Color(0xFF1E88E5), Color(0xFF1565C0)),
                                    onClick = { viewModel.navigateTo(Screen.SubjectChapters("PHYSICS")) },
                                    testTag = "subject_card_physics"
                                )

                                SubjectCard(
                                    title = "📐 Maths",
                                    subtitle = "Algebra, Calculus, Coordinate",
                                    chapterCountText = "14 Chapters",
                                    iconEmoji = "📐",
                                    accentColor = MathsOrange,
                                    gradientColors = listOf(Color(0xFFFB8C00), Color(0xFFE65100)),
                                    onClick = { viewModel.navigateTo(Screen.SubjectChapters("MATHS")) },
                                    testTag = "subject_card_maths"
                                )
                            }

                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                SubjectCard(
                                    title = "🧪 Chemistry",
                                    subtitle = "Physical, Inorganic, Organic",
                                    chapterCountText = "10 Chapters",
                                    iconEmoji = "🧪",
                                    accentColor = ChemistryPurple,
                                    gradientColors = listOf(Color(0xFF8E24AA), Color(0xFF6A1B9A)),
                                    onClick = { viewModel.navigateTo(Screen.SubjectChapters("CHEMISTRY")) },
                                    testTag = "subject_card_chemistry"
                                )

                                SubjectCard(
                                    title = "📚 Other Material",
                                    subtitle = "Notes, Question Papers, Reference",
                                    chapterCountText = "5 Categories",
                                    iconEmoji = "📚",
                                    accentColor = OtherGreen,
                                    gradientColors = listOf(Color(0xFF43A047), Color(0xFF2E7D32)),
                                    onClick = { viewModel.navigateTo(Screen.OtherMaterial()) },
                                    testTag = "subject_card_other"
                                )
                            }
                        }
                    } else {
                        // Vertical column for phones
                        Column(
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            SubjectCard(
                                title = "📘 Physics",
                                subtitle = "Mechanics, Waves, Thermodynamics",
                                chapterCountText = "15 Chapters",
                                iconEmoji = "📘",
                                accentColor = PhysicsBlue,
                                gradientColors = listOf(Color(0xFF1E88E5), Color(0xFF1565C0)),
                                onClick = { viewModel.navigateTo(Screen.SubjectChapters("PHYSICS")) },
                                testTag = "subject_card_physics"
                            )

                            SubjectCard(
                                title = "🧪 Chemistry",
                                subtitle = "Physical, Inorganic, Organic",
                                chapterCountText = "10 Chapters",
                                iconEmoji = "🧪",
                                accentColor = ChemistryPurple,
                                gradientColors = listOf(Color(0xFF8E24AA), Color(0xFF6A1B9A)),
                                onClick = { viewModel.navigateTo(Screen.SubjectChapters("CHEMISTRY")) },
                                testTag = "subject_card_chemistry"
                            )

                            SubjectCard(
                                title = "📐 Maths",
                                subtitle = "Algebra, Calculus, Coordinate",
                                chapterCountText = "14 Chapters",
                                iconEmoji = "📐",
                                accentColor = MathsOrange,
                                gradientColors = listOf(Color(0xFFFB8C00), Color(0xFFE65100)),
                                onClick = { viewModel.navigateTo(Screen.SubjectChapters("MATHS")) },
                                testTag = "subject_card_maths"
                            )

                            SubjectCard(
                                title = "📚 Other Material",
                                subtitle = "School Notes, Papers, Resources",
                                chapterCountText = "5 Categories",
                                iconEmoji = "📚",
                                accentColor = OtherGreen,
                                gradientColors = listOf(Color(0xFF43A047), Color(0xFF2E7D32)),
                                onClick = { viewModel.navigateTo(Screen.OtherMaterial()) },
                                testTag = "subject_card_other"
                            )
                        }
                    }
                }

                // 4. About Section (Required)
                item {
                    AboutSection()
                }

                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }

    if (showAddDialog) {
        AddMaterialDialog(
            initialSubjectId = "PHYSICS",
            initialChapterNumber = 1,
            onDismiss = { showAddDialog = false },
            onAddNote = { subjectId, chapterNumber, chapterTitle, category, title, content, desc ->
                viewModel.saveNote(
                    materialId = null,
                    subjectId = subjectId,
                    chapterNumber = chapterNumber,
                    chapterTitle = chapterTitle,
                    category = category,
                    title = title,
                    content = content,
                    description = desc
                )
            },
            onImportFile = { uri, subjectId, chapterNumber, chapterTitle, category, type, title, desc ->
                viewModel.importFile(
                    uri = uri,
                    subjectId = subjectId,
                    chapterNumber = chapterNumber,
                    chapterTitle = chapterTitle,
                    category = category,
                    materialType = type,
                    title = title,
                    description = desc
                )
            }
        )
    }
}
