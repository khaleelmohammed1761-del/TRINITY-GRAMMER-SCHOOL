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
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Warning
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
import com.example.trinity.data.local.NoticeEntity
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityGoldLight
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark
import com.example.ui.theme.TrinityRoyalBlue
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary
import com.example.ui.theme.TrinityWarning

@Composable
fun NoticesScreen(
    selectedClass: String,
    noticesList: List<NoticeEntity>,
    selectedCategory: String,
    onSelectCategory: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = listOf("All", "Academic", "Sports", "Event", "Holiday", "General")
    val filteredList = if (selectedCategory == "All") {
        noticesList
    } else {
        noticesList.filter { it.category.equals(selectedCategory, ignoreCase = true) }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FC))
            .testTag("notices_screen")
    ) {
        // Top Filter Bar
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
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(TrinityLightBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Campaign,
                            contentDescription = null,
                            tint = TrinityNavy,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Official Circulars & Notices",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TrinityNavy
                            )
                        )
                        Text(
                            text = "Trinity Grammar School Academic Administration",
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
                    categories.forEach { cat ->
                        val isSelected = selectedCategory == cat
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSelectCategory(cat) },
                            label = { Text(cat) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = TrinityNavy,
                                selectedLabelColor = Color.White
                            ),
                            modifier = Modifier.testTag("filter_notice_${cat.lowercase()}")
                        )
                    }
                }
            }
        }

        // Notices List
        if (filteredList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = TrinityRoyalBlue,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No Circulars in $selectedCategory",
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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredList, key = { it.id }) { notice ->
                    NoticeCard(notice = notice)
                }
            }
        }
    }
}

@Composable
fun NoticeCard(notice: NoticeEntity) {
    val (badgeBg, badgeFg) = when (notice.category.lowercase()) {
        "urgent" -> Color(0xFFFEE2E2) to Color(0xFFDC2626)
        "sports" -> Color(0xFFDCFCE7) to Color(0xFF16A34A)
        "holiday" -> Color(0xFFFEF3C7) to Color(0xFFD97706)
        "academic" -> TrinityLightBlue to TrinityRoyalBlue
        else -> Color(0xFFF1F5F9) to TrinityNavy
    }

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = if (notice.isPinned) BorderStroke(1.5.dp, TrinityGold) else BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("notice_card_${notice.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = badgeBg,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = notice.category.uppercase(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = badgeFg,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = notice.date,
                        style = MaterialTheme.typography.labelSmall.copy(color = TrinityTextSecondary)
                    )
                }

                if (notice.isPinned) {
                    Surface(
                        color = TrinityGoldLight,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.PushPin,
                                contentDescription = "Pinned",
                                tint = TrinityGold,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "PINNED",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinityGold,
                                    fontSize = 10.sp
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = notice.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityTextPrimary
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = notice.content,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TrinityTextSecondary,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = null,
                    tint = TrinityNavy,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Applies to: ${notice.targetClasses}",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TrinityRoyalBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}
