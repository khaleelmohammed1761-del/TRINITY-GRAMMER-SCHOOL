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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trinity.data.local.HomeworkEntity
import com.example.trinity.model.SchoolSubject
import com.example.ui.theme.TrinityDanger
import com.example.ui.theme.TrinityDangerContainer
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark
import com.example.ui.theme.TrinityRoyalBlue
import com.example.ui.theme.TrinitySuccess
import com.example.ui.theme.TrinitySuccessContainer
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary
import com.example.ui.theme.TrinityWarning

@Composable
fun HomeworkScreen(
    selectedClass: String,
    homeworkList: List<HomeworkEntity>,
    selectedSubject: SchoolSubject,
    onSelectSubject: (SchoolSubject) -> Unit,
    onToggleCompletion: (HomeworkEntity) -> Unit,
    onOpenSubmission: (HomeworkEntity) -> Unit,
    onOpenDocumentViewer: (title: String, subtitle: String, content: String, author: String) -> Unit,
    onOpenClassSelector: () -> Unit,
    modifier: Modifier = Modifier
) {
    val completedCount = homeworkList.count { it.isCompleted }
    val totalCount = homeworkList.size

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FC))
            .testTag("homework_screen")
    ) {
        // Sticky Subheader: Class & Progress Banner
        Surface(
            color = Color.White,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = TrinityNavy,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Class: $selectedClass",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinityNavy
                                )
                            )
                        }
                        Text(
                            text = "Daily Assigned Tasks & Worksheets",
                            style = MaterialTheme.typography.labelSmall.copy(color = TrinityTextSecondary)
                        )
                    }

                    // Progress badge
                    Surface(
                        color = if (completedCount == totalCount && totalCount > 0) TrinitySuccessContainer else TrinityLightBlue,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (completedCount == totalCount && totalCount > 0) Icons.Default.CheckCircle else Icons.Default.AssignmentTurnedIn,
                                contentDescription = null,
                                tint = if (completedCount == totalCount && totalCount > 0) TrinitySuccess else TrinityRoyalBlue,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "$completedCount/$totalCount Done",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (completedCount == totalCount && totalCount > 0) TrinitySuccess else TrinityNavy
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Subject Filter Chips (English, Mathematics, Science, Social Studies, Hindi, Computer)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // All Subjects chip
                    FilterChip(
                        selected = selectedSubject == SchoolSubject.ALL,
                        onClick = { onSelectSubject(SchoolSubject.ALL) },
                        label = { Text("All Subjects") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TrinityNavy,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.testTag("filter_subject_all")
                    )

                    // Core subjects
                    SchoolSubject.CORE_SUBJECTS.forEach { subject ->
                        val isSelected = selectedSubject == subject
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSelectSubject(subject) },
                            leadingIcon = {
                                Icon(
                                    imageVector = subject.icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = if (isSelected) Color.White else subject.color
                                )
                            },
                            label = { Text(subject.displayName) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = subject.color,
                                selectedLabelColor = Color.White
                            ),
                            modifier = Modifier.testTag("filter_subject_${subject.name.lowercase()}")
                        )
                    }
                }
            }
        }

        // Homework Item List
        if (homeworkList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(TrinityLightBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = TrinityRoyalBlue,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No Homework Found",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TrinityNavy
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "No pending homework tasks assigned for $selectedClass under ${selectedSubject.displayName}.",
                        style = MaterialTheme.typography.bodyMedium.copy(color = TrinityTextSecondary),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(homeworkList, key = { it.id }) { item ->
                    HomeworkCard(
                        item = item,
                        onToggle = { onToggleCompletion(item) },
                        onSubmit = { onOpenSubmission(item) },
                        onViewDocument = {
                            val content = item.attachmentContent ?: """
                                TRINITY GRAMMAR SCHOOL
                                ${item.classGrade.uppercase()} - ${item.subject.uppercase()}
                                TOPIC: ${item.title}
                                
                                INSTRUCTIONS:
                                ${item.description}
                                
                                Assigned by: ${item.teacherName}
                                Due Date: ${item.dueDate}
                            """.trimIndent()

                            onOpenDocumentViewer(
                                item.title,
                                "${item.subject} • ${item.classGrade}",
                                content,
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
fun HomeworkCard(
    item: HomeworkEntity,
    onToggle: () -> Unit,
    onSubmit: () -> Unit,
    onViewDocument: () -> Unit
) {
    val subjectEnum = SchoolSubject.fromString(item.subject)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = if (item.isCompleted) BorderStroke(1.5.dp, TrinitySuccess) else BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("homework_card_${item.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: Subject Badge, Due Date, and Checkbox
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
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
                                text = item.subject,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = subjectEnum.color
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Due Date Tag
                    Surface(
                        color = TrinityDangerContainer,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = TrinityDanger,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Due: ${item.dueDate}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinityDanger,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }

                // Completion Checkbox
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (item.isCompleted) "Done" else "Pending",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (item.isCompleted) TrinitySuccess else TrinityTextSecondary
                        )
                    )
                    Checkbox(
                        checked = item.isCompleted,
                        onCheckedChange = { onToggle() },
                        colors = CheckboxDefaults.colors(
                            checkedColor = TrinitySuccess,
                            checkmarkColor = Color.White
                        ),
                        modifier = Modifier.testTag("checkbox_homework_${item.id}")
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Homework Title
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityTextPrimary,
                    textDecoration = if (item.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Description / Instructions
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TrinityTextSecondary,
                    lineHeight = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Teacher Name & Assigned Date
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
                    text = item.teacherName,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TrinityTextSecondary,
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "• Assigned: ${item.assignedDate}",
                    style = MaterialTheme.typography.labelSmall.copy(color = TrinityTextSecondary)
                )
            }

            // Student notes / submission banner (if any)
            if (!item.studentNotes.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = TrinitySuccessContainer,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = TrinitySuccess,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Your Submission Remarks",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinitySuccess
                                )
                            )
                        }
                        Text(
                            text = item.studentNotes,
                            style = MaterialTheme.typography.bodySmall.copy(color = TrinityTextPrimary)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = Color(0xFFE2E8F0))
            Spacer(modifier = Modifier.height(10.dp))

            // Action Buttons: View Attachment / Submit Work
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // View Worksheet / PDF
                OutlinedButton(
                    onClick = onViewDocument,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TrinityNavy),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("view_pdf_button_${item.id}")
                ) {
                    Icon(
                        imageVector = Icons.Default.PictureAsPdf,
                        contentDescription = null,
                        tint = TrinityDanger,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (item.attachmentName != null) "View Worksheet" else "View Instructions",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }

                // Submit Homework
                Button(
                    onClick = onSubmit,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (item.isCompleted) TrinitySuccess else TrinityNavy
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("submit_homework_button_${item.id}")
                ) {
                    Icon(
                        imageVector = Icons.Default.UploadFile,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (item.isCompleted) "Edit Submission" else "Submit Work",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }
        }
    }
}
