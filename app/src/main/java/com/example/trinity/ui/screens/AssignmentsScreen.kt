package com.example.trinity.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.window.Dialog
import com.example.trinity.data.local.AssignmentEntity
import com.example.trinity.model.SchoolSubject
import com.example.ui.theme.TrinityDanger
import com.example.ui.theme.TrinityDangerContainer
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinitySuccess
import com.example.ui.theme.TrinitySuccessContainer
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary

@Composable
fun AssignmentsScreen(
    selectedClass: String,
    assignmentsList: List<AssignmentEntity>,
    onSubmitWork: (Long, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var activeSubmissionAssignment by remember { mutableStateOf<AssignmentEntity?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FC))
            .testTag("assignments_screen")
    ) {
        // Header
        Surface(
            color = Color.White,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.EventNote,
                    contentDescription = null,
                    tint = TrinityNavy,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "$selectedClass Assignments & Projects",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TrinityNavy
                        )
                    )
                    Text(
                        text = "Practical assessments, portfolios and term projects",
                        style = MaterialTheme.typography.labelSmall.copy(color = TrinityTextSecondary)
                    )
                }
            }
        }

        if (assignmentsList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No Assignments for $selectedClass",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TrinityNavy
                        )
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(assignmentsList, key = { it.id }) { item ->
                    AssignmentCard(
                        assignment = item,
                        onSubmitClick = { activeSubmissionAssignment = item }
                    )
                }
            }
        }
    }

    // Submission Dialog
    activeSubmissionAssignment?.let { assignment ->
        var workText by remember { mutableStateOf("") }
        Dialog(onDismissRequest = { activeSubmissionAssignment = null }) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .testTag("assignment_submit_modal")
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Submit Project Work",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TrinityNavy
                        )
                    )
                    Text(
                        text = assignment.title,
                        style = MaterialTheme.typography.bodySmall.copy(color = TrinityTextSecondary)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = workText,
                        onValueChange = { workText = it },
                        label = { Text("Project Summary / Submission Link") },
                        placeholder = { Text("e.g. Completed solar irrigation report draft, attached 4 pages.") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        maxLines = 5
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            onSubmitWork(assignment.id, workText)
                            activeSubmissionAssignment = null
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = TrinityNavy),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("confirm_submit_assignment_btn")
                    ) {
                        Text("Confirm Submission")
                    }
                }
            }
        }
    }
}

@Composable
fun AssignmentCard(
    assignment: AssignmentEntity,
    onSubmitClick: () -> Unit
) {
    val subjectEnum = SchoolSubject.fromString(assignment.subject)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = if (assignment.isSubmitted) BorderStroke(1.5.dp, TrinitySuccess) else BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("assignment_card_${assignment.id}")
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
                    Text(
                        text = "${assignment.subject} • Max ${assignment.maxMarks} Marks",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = subjectEnum.color
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    color = if (assignment.isSubmitted) TrinitySuccessContainer else TrinityDangerContainer,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (assignment.isSubmitted) Icons.Default.CheckCircle else Icons.Default.Schedule,
                            contentDescription = null,
                            tint = if (assignment.isSubmitted) TrinitySuccess else TrinityDanger,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (assignment.isSubmitted) "SUBMITTED" else "DUE: ${assignment.dueDate}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (assignment.isSubmitted) TrinitySuccess else TrinityDanger,
                                fontSize = 10.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = assignment.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityTextPrimary
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = assignment.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TrinityTextSecondary,
                    lineHeight = 20.sp
                )
            )

            if (!assignment.studentWork.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = TrinitySuccessContainer,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Submitted Work: ${assignment.studentWork}",
                            style = MaterialTheme.typography.bodySmall.copy(color = TrinityTextPrimary)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onSubmitClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (assignment.isSubmitted) TrinitySuccess else TrinityNavy
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("assignment_submit_btn_${assignment.id}")
            ) {
                Icon(
                    imageVector = if (assignment.isSubmitted) Icons.Default.Check else Icons.Default.Upload,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(if (assignment.isSubmitted) "Update Submission" else "Submit Assignment Work")
            }
        }
    }
}
