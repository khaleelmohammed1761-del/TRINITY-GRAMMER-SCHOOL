package com.example.trinity.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.trinity.model.AppScreen
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark

@Composable
fun SchoolBottomNav(
    currentScreen: AppScreen,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .testTag("school_bottom_nav")
    ) {
        val items = listOf(
            Triple(AppScreen.HOME, Icons.Filled.Home to Icons.Outlined.Home, "Home"),
            Triple(AppScreen.HOMEWORK, Icons.Filled.Assignment to Icons.Outlined.Assignment, "Homework"),
            Triple(AppScreen.NOTICES, Icons.Filled.Campaign to Icons.Outlined.Campaign, "Notices"),
            Triple(AppScreen.TIMETABLE, Icons.Filled.CalendarMonth to Icons.Outlined.CalendarMonth, "Timetable"),
            Triple(AppScreen.STUDY_MATERIALS, Icons.Filled.MenuBook to Icons.Outlined.MenuBook, "Study")
        )

        items.forEach { (screen, icons, label) ->
            val isSelected = currentScreen == screen
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(screen) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) icons.first else icons.second,
                        contentDescription = label
                    )
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TrinityNavy,
                    selectedTextColor = TrinityNavy,
                    indicatorColor = TrinityLightBlue,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                ),
                modifier = Modifier.testTag("nav_item_${screen.name.lowercase()}")
            )
        }
    }
}
