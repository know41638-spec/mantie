package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.ui.components.MaterialItemCard
import com.example.ui.navigation.Screen
import com.example.ui.theme.OtherGreen
import com.example.ui.viewmodel.StudyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherMaterialScreen(
    initialCategory: String,
    viewModel: StudyViewModel,
    materials: List<StudyMaterial>,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.popBack()
    }

    var selectedCategory by remember { mutableStateOf(initialCategory) }
    var showAddDialog by remember { mutableStateOf(false) }

    // Strip emoji for category matching if needed
    val cleanCategory = selectedCategory.replace(Regex("^[\\p{So}\\p{Sk}\\p{Sm}\\p{Sc}]+\\s*"), "")

    val categoryMaterials = materials.filter {
        it.subjectId == "OTHER" && (it.category.contains(cleanCategory, ignoreCase = true) || it.category == selectedCategory)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Other Material",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                        Text(
                            text = "School notes, question papers & reference vaults",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.popBack() },
                        modifier = Modifier.testTag("back_button_other_material")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
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
                        text = "Add to Category",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                containerColor = OtherGreen,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.testTag("other_add_material_fab")
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Category Chips Row (Horizontal Scroll)
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(CurriculumData.otherCategories) { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = { selectedCategory = cat },
                            label = { Text(cat) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = OtherGreen,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            // Info Card
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    color = OtherGreen.copy(alpha = 0.08f),
                    border = BorderStroke(1.dp, OtherGreen.copy(alpha = 0.2f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = selectedCategory,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = OtherGreen
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = when {
                                selectedCategory.contains("School Notes") -> "Teacher lectures, revision summaries, and practical exam viva questions."
                                selectedCategory.contains("Question Papers") -> "Mid-term papers, final exam question banks, and answer keys."
                                selectedCategory.contains("Reference Material") -> "Textbook solution guides, NCERT Exemplar notes, and formula compilations."
                                selectedCategory.contains("Important Resources") -> "Curated video lecture links, simulation tools, and digital libraries."
                                else -> "General study guides, daily revision routines, and personal notes."
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // Empty state if none
            if (categoryMaterials.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "🗂️",
                                fontSize = 42.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "No items in $selectedCategory yet",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Use '+ Add to Category' below to upload your own PDFs, photos, or type notes.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }
            }

            // Material cards list
            items(categoryMaterials) { material ->
                MaterialItemCard(
                    material = material,
                    onClick = {
                        when (material.materialType) {
                            "PDF" -> viewModel.navigateTo(
                                Screen.PdfViewer(
                                    materialId = material.id,
                                    filePath = material.filePath,
                                    title = material.title
                                )
                            )
                            "IMAGE" -> viewModel.navigateTo(
                                Screen.ImageViewer(
                                    materialId = material.id,
                                    filePath = material.filePath,
                                    title = material.title
                                )
                            )
                            else -> viewModel.navigateTo(
                                Screen.NoteViewer(materialId = material.id)
                            )
                        }
                    },
                    onDelete = {
                        viewModel.deleteMaterial(material)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }

    if (showAddDialog) {
        AddMaterialDialog(
            initialSubjectId = "OTHER",
            initialChapterNumber = 0,
            onDismiss = { showAddDialog = false },
            onAddNote = { sId, chNum, chTitle, cat, title, content, desc ->
                viewModel.saveNote(
                    materialId = null,
                    subjectId = sId,
                    chapterNumber = chNum,
                    chapterTitle = chTitle,
                    category = cleanCategory,
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
                    category = cleanCategory,
                    materialType = type,
                    title = title,
                    description = desc
                )
            }
        )
    }
}
