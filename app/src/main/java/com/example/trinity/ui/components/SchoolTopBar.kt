package com.example.trinity.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.trinity.model.AppScreen
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark

@Composable
fun SchoolTopBar(
    currentScreen: AppScreen,
    selectedClass: String,
    isAdminMode: Boolean,
    onBackClick: () -> Unit,
    onOpenClassSelector: () -> Unit,
    onOpenAdminPortal: () -> Unit,
    onExitAdminMode: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = TrinityNavy,
        contentColor = Color.White,
        shadowElevation = 4.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left: Back button (if on subscreen) or Crest Logo
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    if (currentScreen != AppScreen.HOME) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier
                                .size(40.dp)
                                .testTag("topbar_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back to Home",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                    } else {
                        // School Crest
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(TrinityNavyDark)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_trinity_crest),
                                contentDescription = "Trinity School Crest",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(42.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                    Column {
                        Text(
                            text = if (currentScreen == AppScreen.HOME) "TRINITY GRAMMAR" else currentScreen.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                letterSpacing = 0.5.sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = if (currentScreen == AppScreen.HOME) "Excellence in Education • Est. 1988" else "Trinity Grammar School",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TrinityGold,
                                fontWeight = FontWeight.Medium
                            ),
                            maxLines = 1
                        )
                    }
                }

                // Right: Class Pill & Admin Toggle
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Class Selector Pill
                    Surface(
                        color = Color.White.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable(onClick = onOpenClassSelector)
                            .testTag("class_selector_pill")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = TrinityGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = selectedClass,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Change Class",
                                tint = Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    // Admin / Teacher Portal Button
                    IconButton(
                        onClick = {
                            if (isAdminMode) {
                                onExitAdminMode()
                            } else {
                                onOpenAdminPortal()
                            }
                        },
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(if (isAdminMode) TrinityGold else Color.White.copy(alpha = 0.12f))
                            .testTag("admin_portal_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AdminPanelSettings,
                            contentDescription = if (isAdminMode) "Exit Admin Mode" else "Teacher Admin Portal",
                            tint = if (isAdminMode) TrinityNavyDark else Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
