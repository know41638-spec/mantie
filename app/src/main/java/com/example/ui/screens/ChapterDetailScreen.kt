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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PictureAsPdf
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
import com.example.ui.theme.AcademicBluePrimary
import com.example.ui.theme.ChemistryPurple
import com.example.ui.theme.MathsOrange
import com.example.ui.theme.PhysicsBlue
import com.example.ui.viewmodel.StudyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterDetailScreen(
    subjectId: String,
    chapterNumber: Int,
    viewModel: StudyViewModel,
    materials: List<StudyMaterial>,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.popBack()
    }

    var selectedFilter by remember { mutableStateOf("ALL") } // "ALL", "NOTE", "PDF", "IMAGE", "SUMMARY"
    var showAddDialog by remember { mutableStateOf(false) }

    val chapter = viewModel.getChapter(subjectId, chapterNumber)
    val accentColor = when (subjectId) {
        "PHYSICS" -> PhysicsBlue
        "CHEMISTRY" -> ChemistryPurple
        "MATHS" -> MathsOrange
        else -> AcademicBluePrimary
    }

    val chapterMaterials = materials.filter {
        it.subjectId == subjectId && it.chapterNumber == chapterNumber
    }

    val filteredMaterials = when (selectedFilter) {
        "NOTE" -> chapterMaterials.filter { it.materialType == "NOTE" }
        "PDF" -> chapterMaterials.filter { it.materialType == "PDF" }
        "IMAGE" -> chapterMaterials.filter { it.materialType == "IMAGE" }
        else -> chapterMaterials
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Chapter $chapterNumber: ${chapter?.title ?: "Chapter Details"}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            ),
                            maxLines = 1
                        )
                        Text(
                            text = "${subjectId.lowercase().replaceFirstChar { it.uppercase() }} • Class 11",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.popBack() },
                        modifier = Modifier.testTag("back_button_chapter_detail")
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
                        text = "Add Material",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                containerColor = accentColor,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.testTag("chapter_add_material_fab")
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
            // Chapter Summary Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = accentColor.copy(alpha = 0.08f)
                    ),
                    border = BorderStroke(1.dp, accentColor.copy(alpha = 0.25f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = CircleShape,
                                color = accentColor,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "$chapterNumber",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = chapter?.title ?: "Chapter Overview",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        if (!chapter?.description.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = chapter?.description ?: "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        if (!chapter?.keyTopics.isNullOrEmpty()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Key Topics:",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = accentColor
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            chapter?.keyTopics?.forEach { topic ->
                                Text(
                                    text = "• $topic",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            // Filter Chips Row
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedFilter == "ALL",
                        onClick = { selectedFilter = "ALL" },
                        label = { Text("All (${chapterMaterials.size})") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = accentColor,
                            selectedLabelColor = Color.White
                        )
                    )
                    FilterChip(
                        selected = selectedFilter == "NOTE",
                        onClick = { selectedFilter = "NOTE" },
                        label = { Text("📝 Notes") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = accentColor,
                            selectedLabelColor = Color.White
                        )
                    )
                    FilterChip(
                        selected = selectedFilter == "PDF",
                        onClick = { selectedFilter = "PDF" },
                        label = { Text("📄 PDFs") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = accentColor,
                            selectedLabelColor = Color.White
                        )
                    )
                    FilterChip(
                        selected = selectedFilter == "IMAGE",
                        onClick = { selectedFilter = "IMAGE" },
                        label = { Text("🖼️ Images") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = accentColor,
                            selectedLabelColor = Color.White
                        )
                    )
                    FilterChip(
                        selected = selectedFilter == "SUMMARY",
                        onClick = { selectedFilter = "SUMMARY" },
                        label = { Text("⚡ Formulas") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = accentColor,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            // Quick Formula Section when Formulas tab is active
            if (selectedFilter == "SUMMARY") {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = accentColor
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Essential Formula Sheet",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            val formulas = chapter?.quickFormulas ?: emptyList()
                            if (formulas.isNotEmpty()) {
                                formulas.forEach { formula ->
                                    Surface(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        shape = RoundedCornerShape(10.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant
                                    ) {
                                        Text(
                                            text = formula,
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                                                fontWeight = FontWeight.SemiBold
                                            ),
                                            color = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.padding(10.dp)
                                        )
                                    }
                                }
                            } else {
                                Text(
                                    text = "No standard formulas registered for this chapter. Add your own custom notes and formulas using the button below!",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            // Empty state if no materials under filter
            if (selectedFilter != "SUMMARY" && filteredMaterials.isEmpty()) {
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
                                text = "📂",
                                fontSize = 42.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "No $selectedFilter in this Chapter yet",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Tap '+ Add Material' below to add your own notes, import PDF documents, or diagrams.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }
            }

            // Materials list
            if (selectedFilter != "SUMMARY") {
                items(filteredMaterials) { material ->
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
            }

            item {
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }

    if (showAddDialog) {
        AddMaterialDialog(
            initialSubjectId = subjectId,
            initialChapterNumber = chapterNumber,
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
