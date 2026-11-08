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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.example.trinity.data.local.ExamScheduleEntity
import com.example.trinity.model.SchoolSubject
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityGoldLight
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark
import com.example.ui.theme.TrinityRoyalBlue
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary

@Composable
fun ExamScheduleScreen(
    selectedClass: String,
    examSchedulesList: List<ExamScheduleEntity>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FC))
            .testTag("exam_schedule_screen")
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
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = TrinityNavy,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "$selectedClass Examination Datesheet",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TrinityNavy
                        )
                    )
                    Text(
                        text = "Official Schedule & Syllabus Guidelines",
                        style = MaterialTheme.typography.labelSmall.copy(color = TrinityTextSecondary)
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Exam Guidelines Notice Card
            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = TrinityGoldLight.copy(alpha = 0.5f)),
                    border = BorderStroke(1.dp, TrinityGold.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Rule,
                                contentDescription = null,
                                tint = TrinityGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "EXAMINATION HALL PROTOCOL",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinityGold
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "• Be seated 15 minutes before reading time (08:45 AM).\n• Bring Trinity School ID card and standard geometry kit.\n• Electronic devices and smartwatches are strictly prohibited.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TrinityNavyDark,
                                lineHeight = 18.sp
                            )
                        )
                    }
                }
            }

            if (examSchedulesList.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No Scheduled Exams for $selectedClass at this time.",
                            style = MaterialTheme.typography.bodyMedium.copy(color = TrinityTextSecondary)
                        )
                    }
                }
            } else {
                items(examSchedulesList, key = { it.id }) { exam ->
                    ExamCard(exam = exam)
                }
            }
        }
    }
}

@Composable
fun ExamCard(exam: ExamScheduleEntity) {
    val subjectEnum = SchoolSubject.fromString(exam.subject)

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("exam_card_${exam.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    color = TrinityLightBlue,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = TrinityNavy,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = exam.examDate,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TrinityNavy
                            )
                        )
                    }
                }

                Surface(
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "Max: ${exam.maxMarks} Marks",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = TrinityRoyalBlue
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = exam.subject,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityTextPrimary
                )
            )

            Text(
                text = exam.examName,
                style = MaterialTheme.typography.labelSmall.copy(color = TrinityRoyalBlue)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = TrinityTextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = exam.timing,
                        style = MaterialTheme.typography.bodySmall.copy(color = TrinityTextSecondary)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.MeetingRoom,
                        contentDescription = null,
                        tint = TrinityTextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = exam.roomNumber,
                        style = MaterialTheme.typography.bodySmall.copy(color = TrinityTextSecondary)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = Color(0xFFE2E8F0))
            Spacer(modifier = Modifier.height(8.dp))

            // Syllabus
            Text(
                text = "Syllabus:",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityNavy
                )
            )
            Text(
                text = exam.syllabus,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TrinityTextSecondary,
                    lineHeight = 18.sp
                )
            )
        }
    }
}
