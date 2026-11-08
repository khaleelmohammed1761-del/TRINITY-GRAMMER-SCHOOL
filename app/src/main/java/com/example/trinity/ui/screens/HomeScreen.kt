package com.example.trinity.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.trinity.data.local.HomeworkEntity
import com.example.trinity.data.local.NoticeEntity
import com.example.trinity.model.AppScreen
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityGoldLight
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark
import com.example.ui.theme.TrinityRoyalBlue
import com.example.ui.theme.TrinitySuccess
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary
import com.example.ui.theme.TrinityWarning

@Composable
fun HomeScreen(
    selectedClass: String,
    homeworkList: List<HomeworkEntity>,
    noticesList: List<NoticeEntity>,
    onOpenClassSelector: () -> Unit,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val pendingHomeworkCount = homeworkList.count { !it.isCompleted }
    val pinnedNotice = noticesList.firstOrNull { it.isPinned } ?: noticesList.firstOrNull()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FC))
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Academic Welcome Banner (No foreign photo, clear State Syllabus School branding)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .testTag("home_welcome_banner"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = TrinityNavy),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    TrinityNavyDark,
                                    TrinityNavy,
                                    TrinityRoyalBlue
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Official School Crest Logo
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.img_trinity_crest),
                                    contentDescription = "Trinity School Crest",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(60.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Surface(
                                    color = TrinityGold,
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.padding(bottom = 6.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Verified,
                                            contentDescription = null,
                                            tint = TrinityNavyDark,
                                            modifier = Modifier.size(13.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "STATE SYLLABUS SCHOOL",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = TrinityNavyDark,
                                                fontSize = 11.sp,
                                                letterSpacing = 0.8.sp
                                            )
                                        )
                                    }
                                }

                                Text(
                                    text = "State Board of Secondary Education (SSC)",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color.White.copy(alpha = 0.85f),
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Welcome to Trinity Grammar School",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 21.sp,
                                lineHeight = 28.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Knowledge • Character • Excellence • Est. 1988",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TrinityGoldLight,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }
        }

        // Active Class Selector Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .testTag("active_class_card"),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(TrinityLightBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = TrinityNavy,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Selected Class",
                                style = MaterialTheme.typography.labelSmall.copy(color = TrinityTextSecondary)
                            )
                            Text(
                                text = selectedClass,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinityNavy
                                )
                            )
                        }
                    }

                    Button(
                        onClick = onOpenClassSelector,
                        colors = ButtonDefaults.buttonColors(containerColor = TrinityNavy),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("change_class_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Change",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }
                }
            }
        }

        // Urgent Notice Ticker / Banner
        if (pinnedNotice != null) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .clickable { onNavigate(AppScreen.NOTICES) }
                        .testTag("home_pinned_notice_card"),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBEB)),
                    border = BorderStroke(1.dp, TrinityGold.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(TrinityGoldLight),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Campaign,
                                contentDescription = null,
                                tint = TrinityGold,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    color = TrinityWarning,
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = pinnedNotice.category.uppercase(),
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 9.sp
                                        ),
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = pinnedNotice.date,
                                    style = MaterialTheme.typography.labelSmall.copy(color = TrinityTextSecondary)
                                )
                            }
                            Text(
                                text = pinnedNotice.title,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinityTextPrimary
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "Read Notice",
                            tint = TrinityGold
                        )
                    }
                }
            }
        }

        // Section Title: Student Dashboard
        item {
            Padding(horizontal = 16.dp, top = 16.dp, bottom = 8.dp) {
                Text(
                    text = "STUDENT ACADEMIC DASHBOARD",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TrinityRoyalBlue,
                        letterSpacing = 1.sp
                    )
                )
            }
        }

        // 6 Core Dashboard Grid Cards
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Row 1: Daily Homework & School Notices
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    DashboardTile(
                        title = "Daily Homework",
                        subtitle = if (pendingHomeworkCount > 0) "$pendingHomeworkCount Pending" else "All Done!",
                        badge = if (pendingHomeworkCount > 0) "$pendingHomeworkCount" else "✓",
                        badgeColor = if (pendingHomeworkCount > 0) TrinityWarning else TrinitySuccess,
                        icon = Icons.Default.Assignment,
                        iconTint = TrinityNavy,
                        onClick = { onNavigate(AppScreen.HOMEWORK) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("dashboard_homework_tile")
                    )

                    DashboardTile(
                        title = "School Notices",
                        subtitle = "${noticesList.size} Circulars",
                        badge = "New",
                        badgeColor = TrinityRoyalBlue,
                        icon = Icons.Default.Campaign,
                        iconTint = TrinityGold,
                        onClick = { onNavigate(AppScreen.NOTICES) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("dashboard_notices_tile")
                    )
                }

                // Row 2: Class Timetable & Study Materials
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    DashboardTile(
                        title = "Class Timetable",
                        subtitle = "Mon - Sat Schedule",
                        badge = "7 Periods",
                        badgeColor = TrinityNavy,
                        icon = Icons.Default.CalendarMonth,
                        iconTint = TrinityNavy,
                        onClick = { onNavigate(AppScreen.TIMETABLE) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("dashboard_timetable_tile")
                    )

                    DashboardTile(
                        title = "Study Materials",
                        subtitle = "Notes & PDF Docs",
                        badge = "PDFs",
                        badgeColor = TrinityRoyalBlue,
                        icon = Icons.Default.MenuBook,
                        iconTint = TrinityRoyalBlue,
                        onClick = { onNavigate(AppScreen.STUDY_MATERIALS) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("dashboard_study_tile")
                    )
                }

                // Row 3: Assignments & Exam Schedule
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    DashboardTile(
                        title = "Assignments",
                        subtitle = "Project Deadlines",
                        badge = "Due Soon",
                        badgeColor = TrinityWarning,
                        icon = Icons.Default.EventNote,
                        iconTint = TrinityWarning,
                        onClick = { onNavigate(AppScreen.ASSIGNMENTS) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("dashboard_assignments_tile")
                    )

                    DashboardTile(
                        title = "Exam Schedule",
                        subtitle = "Pre-Board & Tests",
                        badge = "Datesheet",
                        badgeColor = TrinityNavyDark,
                        icon = Icons.Default.CheckCircle,
                        iconTint = TrinitySuccess,
                        onClick = { onNavigate(AppScreen.EXAM_SCHEDULE) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("dashboard_exams_tile")
                    )
                }
            }
        }

        // About School & Contact Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp)
                    .clickable { onNavigate(AppScreen.ABOUT_SCHOOL) }
                    .testTag("home_about_school_card"),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = TrinityNavyDark),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = TrinityGold,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "About Trinity Grammar School",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "Campus Tour, Bell Timings, Facilities & Principal's Desk",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Read About School",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun DashboardTile(
    title: String,
    subtitle: String,
    badge: String,
    badgeColor: Color,
    icon: ImageVector,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(TrinityLightBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = iconTint,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Surface(
                    color = badgeColor.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = badge,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = badgeColor,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityTextPrimary
                )
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = TrinityTextSecondary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun Padding(
    horizontal: androidx.compose.ui.unit.Dp = 0.dp,
    vertical: androidx.compose.ui.unit.Dp = 0.dp,
    top: androidx.compose.ui.unit.Dp = vertical,
    bottom: androidx.compose.ui.unit.Dp = vertical,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.padding(
            start = horizontal,
            end = horizontal,
            top = top,
            bottom = bottom
        )
    ) {
        content()
    }
}
