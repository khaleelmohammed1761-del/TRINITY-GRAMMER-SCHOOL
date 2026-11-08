package com.example.trinity.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.window.Dialog
import com.example.trinity.data.local.HomeworkEntity
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityRoyalBlue
import com.example.ui.theme.TrinitySuccess
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary

@Composable
fun HomeworkSubmissionDialog(
    homework: HomeworkEntity,
    onDismiss: () -> Unit,
    onSubmit: (Long, String) -> Unit
) {
    var notesText by remember { mutableStateOf(homework.studentNotes ?: "") }
    var attachedFile by remember { mutableStateOf<String?>(null) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("homework_submission_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Submit Homework",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TrinityNavy
                            )
                        )
                        Text(
                            text = "${homework.subject} • ${homework.classGrade}",
                            style = MaterialTheme.typography.labelSmall.copy(color = TrinityRoyalBlue)
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("close_submission_dialog")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Title snippet
                Text(
                    text = homework.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TrinityTextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Solution/Notes Input
                OutlinedTextField(
                    value = notesText,
                    onValueChange = { notesText = it },
                    label = { Text("Your Notes / Answer / Work Remarks") },
                    placeholder = { Text("e.g. Completed questions 1 to 8 in register. Solved quadratic roots...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .testTag("submission_notes_field"),
                    maxLines = 6
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Attachment Simulator
                if (attachedFile == null) {
                    OutlinedButton(
                        onClick = {
                            attachedFile = "Homework_Photo_${System.currentTimeMillis() % 1000}.jpg"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("attach_homework_photo_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddPhotoAlternate,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Attach Homework Photo / PDF")
                    }
                } else {
                    Surface(
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, TrinitySuccess),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = TrinitySuccess,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = attachedFile ?: "",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                                )
                            }
                            IconButton(
                                onClick = { attachedFile = null },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Remove file",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Submit button
                Button(
                    onClick = {
                        val fullNotes = if (attachedFile != null) {
                            "$notesText\n[Attachment: $attachedFile]"
                        } else {
                            notesText
                        }
                        onSubmit(homework.id, fullNotes)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = TrinityNavy),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("confirm_submit_homework_button")
                ) {
                    Text("Confirm & Mark Done")
                }
            }
        }
    }
}
