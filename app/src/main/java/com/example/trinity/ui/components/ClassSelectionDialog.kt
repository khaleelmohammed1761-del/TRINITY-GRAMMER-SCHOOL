package com.example.trinity.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.trinity.model.ClassGrade
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark
import com.example.ui.theme.TrinityRoyalBlue
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ClassSelectionDialog(
    selectedClass: String,
    onClassSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .testTag("class_selection_dialog")
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(TrinityLightBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = TrinityNavy,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Select Your Class",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinityTextPrimary
                                )
                            )
                            Text(
                                text = "Direct access • No login required",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = TrinityTextSecondary
                                )
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(32.dp)
                            .testTag("close_class_selection")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = DividerDefaults.color.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(14.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Pre-Primary
                    ClassCategorySection(
                        title = "PRE-PRIMARY",
                        grades = listOf(ClassGrade.NURSERY, ClassGrade.LKG, ClassGrade.UKG),
                        selectedClass = selectedClass,
                        onClassSelected = onClassSelected
                    )

                    // Primary (1st to 5th)
                    ClassCategorySection(
                        title = "PRIMARY SCHOOL (CLASS 1 - 5)",
                        grades = listOf(
                            ClassGrade.CLASS_1,
                            ClassGrade.CLASS_2,
                            ClassGrade.CLASS_3,
                            ClassGrade.CLASS_4,
                            ClassGrade.CLASS_5
                        ),
                        selectedClass = selectedClass,
                        onClassSelected = onClassSelected
                    )

                    // Secondary & High School (6th to 10th)
                    ClassCategorySection(
                        title = "SECONDARY & HIGH SCHOOL (CLASS 6 - 10)",
                        grades = listOf(
                            ClassGrade.CLASS_6,
                            ClassGrade.CLASS_7,
                            ClassGrade.CLASS_8,
                            ClassGrade.CLASS_9,
                            ClassGrade.CLASS_10
                        ),
                        selectedClass = selectedClass,
                        onClassSelected = onClassSelected
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    color = TrinityLightBlue,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "💡 Your class choice is remembered permanently on this phone.",
                            style = MaterialTheme.typography.bodySmall.copy(color = TrinityNavyDark)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ClassCategorySection(
    title: String,
    grades: List<ClassGrade>,
    selectedClass: String,
    onClassSelected: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TrinityRoyalBlue,
                letterSpacing = 1.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            grades.forEach { grade ->
                val isSelected = selectedClass.equals(grade.displayName, ignoreCase = true)
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) TrinityNavy else Color(0xFFF1F5F9),
                    contentColor = if (isSelected) Color.White else TrinityTextPrimary,
                    border = if (isSelected) BorderStroke(1.5.dp, TrinityGold) else BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onClassSelected(grade.displayName) }
                        .testTag("class_button_${grade.shortCode.lowercase()}")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                    ) {
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = TrinityGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                        Text(
                            text = grade.displayName,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
        }
    }
}
