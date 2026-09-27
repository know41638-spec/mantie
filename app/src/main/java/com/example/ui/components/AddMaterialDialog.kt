package com.example.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.CurriculumData
import com.example.ui.theme.AcademicBluePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMaterialDialog(
    initialSubjectId: String = "PHYSICS",
    initialChapterNumber: Int = 1,
    onDismiss: () -> Unit,
    onAddNote: (subjectId: String, chapterNumber: Int, chapterTitle: String, category: String, title: String, content: String, description: String) -> Unit,
    onImportFile: (uri: Uri, subjectId: String, chapterNumber: Int, chapterTitle: String, category: String, materialType: String, title: String, description: String) -> Unit
) {
    var selectedSubjectId by remember { mutableStateOf(initialSubjectId) }
    var selectedChapterNumber by remember { mutableIntStateOf(initialChapterNumber) }
    var selectedCategory by remember { mutableStateOf("School Notes") }
    var materialType by remember { mutableStateOf("NOTE") } // "NOTE", "PDF", "IMAGE"

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var noteContent by remember { mutableStateOf("") }

    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var selectedFileName by remember { mutableStateOf("") }

    var subjectMenuExpanded by remember { mutableStateOf(false) }
    var chapterMenuExpanded by remember { mutableStateOf(false) }

    val chapters = when (selectedSubjectId) {
        "PHYSICS" -> CurriculumData.physicsChapters
        "CHEMISTRY" -> CurriculumData.chemistryChapters
        "MATHS" -> CurriculumData.mathsChapters
        else -> emptyList()
    }

    val currentChapter = chapters.find { it.number == selectedChapterNumber }
        ?: chapters.firstOrNull()

    val currentChapterTitle = if (selectedSubjectId == "OTHER") {
        selectedCategory
    } else {
        currentChapter?.title ?: "Chapter $selectedChapterNumber"
    }

    // Launchers for picking PDF & Image
    val pdfPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedFileUri = uri
            selectedFileName = uri.lastPathSegment ?: "document.pdf"
            if (title.isBlank()) {
                title = selectedFileName.substringBeforeLast(".")
            }
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedFileUri = uri
            selectedFileName = uri.lastPathSegment ?: "image.jpg"
            if (title.isBlank()) {
                title = selectedFileName.substringBeforeLast(".")
            }
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .clip(RoundedCornerShape(24.dp))
                .testTag("add_material_dialog"),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Add Study Material",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Class 11 PCM Local Vault",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("close_add_material_dialog")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Material Type Selector
                Text(
                    text = "Material Type",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(
                        Triple("NOTE", "📝 Note", Icons.Default.Description),
                        Triple("PDF", "📄 PDF", Icons.Default.PictureAsPdf),
                        Triple("IMAGE", "🖼️ Image", Icons.Default.Image)
                    ).forEach { (type, label, icon) ->
                        val isSelected = materialType == type
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    materialType = type
                                    selectedFileUri = null
                                    selectedFileName = ""
                                },
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) AcademicBluePrimary else MaterialTheme.colorScheme.surfaceVariant,
                            border = BorderStroke(
                                1.dp,
                                if (isSelected) AcademicBluePrimary else MaterialTheme.colorScheme.outlineVariant
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = label,
                                    tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                    ),
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Subject Selector
                Text(
                    text = "Subject",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(6.dp))

                ExposedDropdownMenuBox(
                    expanded = subjectMenuExpanded,
                    onExpandedChange = { subjectMenuExpanded = it }
                ) {
                    OutlinedTextField(
                        value = when (selectedSubjectId) {
                            "PHYSICS" -> "📘 Physics"
                            "CHEMISTRY" -> "🧪 Chemistry"
                            "MATHS" -> "📐 Maths"
                            else -> "📚 Other Material"
                        },
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = subjectMenuExpanded) },
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = subjectMenuExpanded,
                        onDismissRequest = { subjectMenuExpanded = false }
                    ) {
                        listOf(
                            "PHYSICS" to "📘 Physics",
                            "CHEMISTRY" to "🧪 Chemistry",
                            "MATHS" to "📐 Maths",
                            "OTHER" to "📚 Other Material"
                        ).forEach { (id, name) ->
                            DropdownMenuItem(
                                text = { Text(name) },
                                onClick = {
                                    selectedSubjectId = id
                                    selectedChapterNumber = 1
                                    subjectMenuExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Chapter or Category Selector
                if (selectedSubjectId != "OTHER") {
                    Text(
                        text = "Chapter",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    ExposedDropdownMenuBox(
                        expanded = chapterMenuExpanded,
                        onExpandedChange = { chapterMenuExpanded = it }
                    ) {
                        OutlinedTextField(
                            value = "Ch $selectedChapterNumber: $currentChapterTitle",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = chapterMenuExpanded) },
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        ExposedDropdownMenu(
                            expanded = chapterMenuExpanded,
                            onDismissRequest = { chapterMenuExpanded = false }
                        ) {
                            chapters.forEach { ch ->
                                DropdownMenuItem(
                                    text = { Text("Ch ${ch.number}: ${ch.title}") },
                                    onClick = {
                                        selectedChapterNumber = ch.number
                                        chapterMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Category",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        CurriculumData.otherCategories.take(3).forEach { cat ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (selectedCategory == cat) AcademicBluePrimary else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier.clickable { selectedCategory = cat }
                            ) {
                                Text(
                                    text = cat,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (selectedCategory == cat) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // File Picker trigger if PDF or Image
                if (materialType == "PDF" || materialType == "IMAGE") {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .clickable {
                                if (materialType == "PDF") {
                                    pdfPickerLauncher.launch(arrayOf("application/pdf"))
                                } else {
                                    imagePickerLauncher.launch("image/*")
                                }
                            }
                            .testTag("pick_file_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        border = BorderStroke(1.dp, AcademicBluePrimary.copy(alpha = 0.4f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                modifier = Modifier.size(44.dp),
                                shape = RoundedCornerShape(10.dp),
                                color = AcademicBluePrimary.copy(alpha = 0.15f)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.FileUpload,
                                        contentDescription = "Pick file",
                                        tint = AcademicBluePrimary
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (selectedFileUri != null) "Selected: $selectedFileName" else "Choose $materialType from Device",
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = if (selectedFileUri != null) "Tap to change file" else "Stored locally in offline storage",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Title Input
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title *") },
                    placeholder = { Text("e.g. Kinematics Revision Sheet") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("material_title_input"),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Description Input
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Short Description") },
                    placeholder = { Text("Key formulas, derivation, or notes") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Note Content Input
                if (materialType == "NOTE") {
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = noteContent,
                        onValueChange = { noteContent = it },
                        label = { Text("Note Content / Text / Formulas *") },
                        placeholder = { Text("Write your notes, formulas, step-by-step problem solution here...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .testTag("material_content_input"),
                        shape = RoundedCornerShape(12.dp),
                        maxLines = 10
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("cancel_add_material_button")
                    ) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    val canSubmit = title.isNotBlank() && (
                        materialType != "NOTE" || noteContent.isNotBlank()
                    ) && (
                        materialType == "NOTE" || selectedFileUri != null
                    )

                    Button(
                        onClick = {
                            if (materialType == "NOTE") {
                                onAddNote(
                                    selectedSubjectId,
                                    if (selectedSubjectId == "OTHER") 0 else selectedChapterNumber,
                                    currentChapterTitle,
                                    selectedCategory,
                                    title.trim(),
                                    noteContent.trim(),
                                    description.trim()
                                )
                            } else {
                                selectedFileUri?.let { uri ->
                                    onImportFile(
                                        uri,
                                        selectedSubjectId,
                                        if (selectedSubjectId == "OTHER") 0 else selectedChapterNumber,
                                        currentChapterTitle,
                                        selectedCategory,
                                        materialType,
                                        title.trim(),
                                        description.trim()
                                    )
                                }
                            }
                            onDismiss()
                        },
                        enabled = canSubmit,
                        modifier = Modifier.testTag("save_material_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AcademicBluePrimary
                        )
                    ) {
                        Text("Save Material")
                    }
                }
            }
        }
    }
}
