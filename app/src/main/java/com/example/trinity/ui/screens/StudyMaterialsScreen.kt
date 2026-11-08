package com.example.trinity.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trinity.data.local.StudyMaterialEntity
import com.example.trinity.model.SchoolSubject
import com.example.ui.theme.TrinityDanger
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityRoyalBlue
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary

@Composable
fun StudyMaterialsScreen(
    selectedClass: String,
    materialsList: List<StudyMaterialEntity>,
    selectedSubject: SchoolSubject,
    onSelectSubject: (SchoolSubject) -> Unit,
    onOpenDocumentViewer: (title: String, subtitle: String, content: String, author: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FC))
            .testTag("study_materials_screen")
    ) {
        // Sticky Header with Subject Filters
        Surface(
            color = Color.White,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = null,
                        tint = TrinityNavy,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "$selectedClass Study Materials",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TrinityNavy
                            )
                        )
                        Text(
                            text = "Downloadable revision guides, formula sheets & PDFs",
                            style = MaterialTheme.typography.labelSmall.copy(color = TrinityTextSecondary)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedSubject == SchoolSubject.ALL,
                        onClick = { onSelectSubject(SchoolSubject.ALL) },
                        label = { Text("All Subjects") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TrinityNavy,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.testTag("filter_study_all")
                    )

                    SchoolSubject.CORE_SUBJECTS.forEach { sub ->
                        val isSelected = selectedSubject == sub
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSelectSubject(sub) },
                            leadingIcon = {
                                Icon(
                                    imageVector = sub.icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = if (isSelected) Color.White else sub.color
                                )
                            },
                            label = { Text(sub.displayName) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = sub.color,
                                selectedLabelColor = Color.White
                            ),
                            modifier = Modifier.testTag("filter_study_${sub.name.lowercase()}")
                        )
                    }
                }
            }
        }

        // Materials List
        if (materialsList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No Study Materials Found",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TrinityNavy
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "No study notes available for $selectedClass under ${selectedSubject.displayName}.",
                        style = MaterialTheme.typography.bodyMedium.copy(color = TrinityTextSecondary)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(materialsList, key = { it.id }) { item ->
                    StudyMaterialCard(
                        material = item,
                        onOpen = {
                            onOpenDocumentViewer(
                                item.chapterTitle,
                                "${item.subject} • ${item.chapterNumber}",
                                item.documentContent,
                                item.teacherName
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StudyMaterialCard(
    material: StudyMaterialEntity,
    onOpen: () -> Unit
) {
    val subjectEnum = SchoolSubject.fromString(material.subject)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("study_material_card_${material.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    color = subjectEnum.color.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = subjectEnum.icon,
                            contentDescription = null,
                            tint = subjectEnum.color,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${material.subject} • ${material.chapterNumber}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = subjectEnum.color
                            )
                        )
                    }
                }

                // File type and size
                Surface(
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.PictureAsPdf,
                            contentDescription = null,
                            tint = TrinityDanger,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "${material.fileType} (${material.fileSize})",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = TrinityNavy
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = material.chapterTitle,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityTextPrimary
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = TrinityTextSecondary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${material.teacherName} • Uploaded ${material.uploadDate}",
                    style = MaterialTheme.typography.labelSmall.copy(color = TrinityTextSecondary)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onOpen,
                colors = ButtonDefaults.buttonColors(containerColor = TrinityNavy),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("read_study_doc_button_${material.id}")
            ) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Open & Read PDF Document")
            }
        }
    }
}
