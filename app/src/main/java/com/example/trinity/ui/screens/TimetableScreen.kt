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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trinity.data.local.TimetableEntity
import com.example.trinity.model.SchoolSubject
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityRoyalBlue
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary

@Composable
fun TimetableScreen(
    selectedClass: String,
    timetableList: List<TimetableEntity>,
    selectedDay: String,
    onSelectDay: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FC))
            .testTag("timetable_screen")
    ) {
        // Sticky Header with Day Selector
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
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                                tint = TrinityNavy,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "$selectedClass Timetable",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinityNavy
                                )
                            )
                        }
                        Text(
                            text = "Daily Academic Routine • 7 Periods",
                            style = MaterialTheme.typography.labelSmall.copy(color = TrinityTextSecondary)
                        )
                    }

                    Surface(
                        color = TrinityLightBlue,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = selectedDay,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TrinityNavy
                            ),
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Day Tabs
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    days.forEach { day ->
                        val isSelected = selectedDay.equals(day, ignoreCase = true)
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSelectDay(day) },
                            label = { Text(day) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = TrinityNavy,
                                selectedLabelColor = Color.White
                            ),
                            modifier = Modifier.testTag("day_chip_${day.lowercase()}")
                        )
                    }
                }
            }
        }

        // Timetable Periods List
        if (timetableList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No Classes Scheduled for $selectedDay",
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
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(timetableList, key = { it.id }) { period ->
                    PeriodCard(period = period)
                }
            }
        }
    }
}

@Composable
fun PeriodCard(period: TimetableEntity) {
    val subjectEnum = SchoolSubject.fromString(period.subject)

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("period_card_${period.periodNumber}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Period Number Badge
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(54.dp)
            ) {
                Surface(
                    color = TrinityLightBlue,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "P${period.periodNumber}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TrinityNavy
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Details
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = subjectEnum.icon,
                            contentDescription = null,
                            tint = subjectEnum.color,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = period.subject,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TrinityTextPrimary
                            )
                        )
                    }

                    // Room Tag
                    Surface(
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.MeetingRoom,
                                contentDescription = null,
                                tint = TrinityNavy,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = period.roomNumber,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinityNavy,
                                    fontSize = 10.sp
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Time slot
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = TrinityTextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = period.timeSlot,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TrinityTextSecondary,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Teacher
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = TrinityRoyalBlue,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = period.teacherName,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = TrinityRoyalBlue,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}
