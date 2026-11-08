package com.example.trinity.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
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
import com.example.trinity.model.ClassGrade
import com.example.trinity.model.SchoolSubject
import com.example.ui.theme.TrinityDanger
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPortalScreen(
    onExitAdmin: () -> Unit,
    onAddHomework: (
        targetClass: String,
        subject: String,
        title: String,
        description: String,
        dueDate: String,
        teacher: String,
        attachmentName: String?,
        attachmentContent: String?
    ) -> Unit,
    onAddNotice: (
        title: String,
        content: String,
        category: String,
        targetClasses: String,
        isPinned: Boolean
    ) -> Unit,
    onAddStudyMaterial: (
        targetClass: String,
        subject: String,
        chapterTitle: String,
        chapterNumber: String,
        fileType: String,
        fileSize: String,
        content: String,
        teacher: String
    ) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(0) } // 0: Homework, 1: Notice, 2: Study Material

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FC))
            .testTag("admin_portal_screen")
    ) {
        // Staff Header
        Surface(
            color = TrinityNavy,
            contentColor = Color.White,
            shadowElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AdminPanelSettings,
                        contentDescription = null,
                        tint = TrinityGold,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Teacher Management Area",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "Staff Content Publishing & Class Admin",
                            style = MaterialTheme.typography.labelSmall.copy(color = TrinityGold)
                        )
                    }
                }

                OutlinedButton(
                    onClick = onExitAdmin,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                    modifier = Modifier.testTag("exit_admin_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Exit Staff")
                }
            }
        }

        // Action Tabs
        Surface(
            color = Color.White,
            shadowElevation = 1.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Assignment,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    label = { Text("Publish Homework") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = TrinityNavy,
                        selectedLabelColor = Color.White
                    ),
                    modifier = Modifier.testTag("admin_tab_homework")
                )

                FilterChip(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Campaign,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    label = { Text("Post Notice") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = TrinityNavy,
                        selectedLabelColor = Color.White
                    ),
                    modifier = Modifier.testTag("admin_tab_notice")
                )

                FilterChip(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    label = { Text("Upload Study Notes") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = TrinityNavy,
                        selectedLabelColor = Color.White
                    ),
                    modifier = Modifier.testTag("admin_tab_study")
                )
            }
        }

        // Form Area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            when (selectedTab) {
                0 -> PublishHomeworkForm(onPublish = onAddHomework)
                1 -> PostNoticeForm(onPublish = onAddNotice)
                2 -> UploadStudyMaterialForm(onUpload = onAddStudyMaterial)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PublishHomeworkForm(
    onPublish: (
        targetClass: String,
        subject: String,
        title: String,
        description: String,
        dueDate: String,
        teacher: String,
        attachmentName: String?,
        attachmentContent: String?
    ) -> Unit
) {
    var selectedClass by remember { mutableStateOf(ClassGrade.CLASS_10.displayName) }
    var classDropdownExpanded by remember { mutableStateOf(false) }

    var selectedSubject by remember { mutableStateOf("Mathematics") }
    var subjectDropdownExpanded by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("Tomorrow") }
    var teacherName by remember { mutableStateOf("Dr. R. K. Sharma") }
    var worksheetText by remember { mutableStateOf("") }
    var hasWorksheet by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "Add New Homework Assignment",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityNavy
                )
            )
            Text(
                text = "Instantly updates in student app for the chosen class.",
                style = MaterialTheme.typography.bodySmall.copy(color = TrinityTextSecondary)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Class Selection Dropdown
            ExposedDropdownMenuBox(
                expanded = classDropdownExpanded,
                onExpandedChange = { classDropdownExpanded = it }
            ) {
                OutlinedTextField(
                    value = selectedClass,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Target Class") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = classDropdownExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = classDropdownExpanded,
                    onDismissRequest = { classDropdownExpanded = false }
                ) {
                    ClassGrade.ALL_GRADES.forEach { grade ->
                        DropdownMenuItem(
                            text = { Text(grade.displayName) },
                            onClick = {
                                selectedClass = grade.displayName
                                classDropdownExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Subject Selection Dropdown
            ExposedDropdownMenuBox(
                expanded = subjectDropdownExpanded,
                onExpandedChange = { subjectDropdownExpanded = it }
            ) {
                OutlinedTextField(
                    value = selectedSubject,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Subject") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = subjectDropdownExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = subjectDropdownExpanded,
                    onDismissRequest = { subjectDropdownExpanded = false }
                ) {
                    SchoolSubject.CORE_SUBJECTS.forEach { sub ->
                        DropdownMenuItem(
                            text = { Text(sub.displayName) },
                            onClick = {
                                selectedSubject = sub.displayName
                                subjectDropdownExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Homework Title / Chapter") },
                placeholder = { Text("e.g. Chapter 5: Arithmetic Progressions - Ex 5.1") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("admin_hw_title_input")
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Instructions / Exercises to complete") },
                placeholder = { Text("Solve Q1 to Q10 in class register.") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                maxLines = 4
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due Date") },
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = teacherName,
                    onValueChange = { teacherName = it },
                    label = { Text("Teacher Name") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = hasWorksheet,
                    onCheckedChange = { hasWorksheet = it }
                )
                Text(
                    text = "Include Digital Printable Worksheet / PDF Handout",
                    style = MaterialTheme.typography.bodyMedium.copy(color = TrinityTextPrimary)
                )
            }

            if (hasWorksheet) {
                OutlinedTextField(
                    value = worksheetText,
                    onValueChange = { worksheetText = it },
                    label = { Text("Worksheet Text / Problems / Content") },
                    placeholder = { Text("Type practice questions or formula review here...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    maxLines = 5
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        onPublish(
                            selectedClass,
                            selectedSubject,
                            title,
                            if (description.isBlank()) "Complete assigned questions in class workbook." else description,
                            dueDate,
                            teacherName,
                            if (hasWorksheet) "${selectedSubject}_Worksheet.pdf" else null,
                            if (hasWorksheet && worksheetText.isNotBlank()) worksheetText else "Standard worksheet problems for $title."
                        )
                        title = ""
                        description = ""
                        worksheetText = ""
                    }
                },
                enabled = title.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = TrinityNavy),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("admin_publish_hw_btn")
            ) {
                Icon(
                    imageVector = Icons.Default.Upload,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Publish to $selectedClass")
            }
        }
    }
}

@Composable
private fun PostNoticeForm(
    onPublish: (title: String, content: String, category: String, targetClasses: String, isPinned: Boolean) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Academic") }
    var targetClasses by remember { mutableStateOf("All Classes") }
    var isPinned by remember { mutableStateOf(false) }

    val categories = listOf("Academic", "Sports", "Event", "Urgent", "Holiday", "General")

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "Publish School Announcement",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityNavy
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Circular Title") },
                placeholder = { Text("e.g. Science Exhibition Registration") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("admin_notice_title_input")
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Announcement Details") },
                placeholder = { Text("Enter the full announcement text...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Category",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                categories.forEach { cat ->
                    FilterChip(
                        selected = category == cat,
                        onClick = { category = cat },
                        label = { Text(cat) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TrinityNavy,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = targetClasses,
                onValueChange = { targetClasses = it },
                label = { Text("Target Audience (e.g. All Classes or 9th Class, 10th Class)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isPinned,
                    onCheckedChange = { isPinned = it }
                )
                Text(
                    text = "Pin this notice to top of student dashboard",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (title.isNotBlank() && content.isNotBlank()) {
                        onPublish(title, content, category, targetClasses, isPinned)
                        title = ""
                        content = ""
                    }
                },
                enabled = title.isNotBlank() && content.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = TrinityNavy),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("admin_publish_notice_btn")
            ) {
                Text("Publish Circular Announcement")
            }
        }
    }
}

@Composable
private fun UploadStudyMaterialForm(
    onUpload: (
        targetClass: String,
        subject: String,
        chapterTitle: String,
        chapterNumber: String,
        fileType: String,
        fileSize: String,
        content: String,
        teacher: String
    ) -> Unit
) {
    var targetClass by remember { mutableStateOf(ClassGrade.CLASS_10.displayName) }
    var subject by remember { mutableStateOf("Science") }
    var chapterNumber by remember { mutableStateOf("Chapter 06") }
    var chapterTitle by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var teacher by remember { mutableStateOf("Mr. Anirudh Verma") }

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "Upload Study Material / PDF Notes",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityNavy
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedTextField(
                value = chapterTitle,
                onValueChange = { chapterTitle = it },
                label = { Text("Chapter Title / Topic") },
                placeholder = { Text("e.g. Life Processes - Complete Concept Notes") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("admin_study_title_input")
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = chapterNumber,
                    onValueChange = { chapterNumber = it },
                    label = { Text("Chapter No.") },
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Subject") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Document Content / Summary & Formulas") },
                placeholder = { Text("Type summary, definitions, or equations here...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 6
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (chapterTitle.isNotBlank()) {
                        onUpload(
                            targetClass,
                            subject,
                            chapterTitle,
                            chapterNumber,
                            "PDF",
                            "2.5 MB",
                            if (content.isBlank()) "Study notes and practice problems for $chapterTitle." else content,
                            teacher
                        )
                        chapterTitle = ""
                        content = ""
                    }
                },
                enabled = chapterTitle.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = TrinityNavy),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("admin_upload_study_btn")
            ) {
                Text("Save & Publish Study Material")
            }
        }
    }
}
