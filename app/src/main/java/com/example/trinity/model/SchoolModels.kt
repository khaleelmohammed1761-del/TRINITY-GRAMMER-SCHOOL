package com.example.trinity.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Science
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ui.theme.SubjectComputer
import com.example.ui.theme.SubjectEnglish
import com.example.ui.theme.SubjectGeneral
import com.example.ui.theme.SubjectHindi
import com.example.ui.theme.SubjectMath
import com.example.ui.theme.SubjectScience
import com.example.ui.theme.SubjectSocial

/**
 * All 13 classes supported in Trinity Grammar School
 */
enum class ClassGrade(
    val displayName: String,
    val category: String,
    val shortCode: String
) {
    NURSERY("Nursery", "Pre-Primary", "NUR"),
    LKG("LKG", "Pre-Primary", "LKG"),
    UKG("UKG", "Pre-Primary", "UKG"),
    CLASS_1("1st Class", "Primary", "1st"),
    CLASS_2("2nd Class", "Primary", "2nd"),
    CLASS_3("3rd Class", "Primary", "3rd"),
    CLASS_4("4th Class", "Primary", "4th"),
    CLASS_5("5th Class", "Primary", "5th"),
    CLASS_6("6th Class", "Secondary", "6th"),
    CLASS_7("7th Class", "Secondary", "7th"),
    CLASS_8("8th Class", "Secondary", "8th"),
    CLASS_9("9th Class", "High School", "9th"),
    CLASS_10("10th Class", "High School", "10th");

    companion object {
        val ALL_GRADES = entries.toList()

        fun fromDisplayName(name: String): ClassGrade {
            return entries.find { it.displayName.equals(name, ignoreCase = true) } ?: CLASS_10
        }
    }
}

/**
 * Subjects taught at Trinity Grammar School
 */
enum class SchoolSubject(
    val displayName: String,
    val icon: ImageVector,
    val color: Color,
    val code: String
) {
    ALL("All Subjects", Icons.Default.Book, SubjectGeneral, "ALL"),
    ENGLISH("English", Icons.Default.Language, SubjectEnglish, "ENG"),
    MATHEMATICS("Mathematics", Icons.Default.Calculate, SubjectMath, "MATH"),
    SCIENCE("Science", Icons.Default.Science, SubjectScience, "SCI"),
    SOCIAL_STUDIES("Social Studies", Icons.Default.Public, SubjectSocial, "SST"),
    HINDI("Hindi", Icons.Default.Book, SubjectHindi, "HIN"),
    COMPUTER("Computer", Icons.Default.Computer, SubjectComputer, "COMP");

    companion object {
        val CORE_SUBJECTS = listOf(
            ENGLISH,
            MATHEMATICS,
            SCIENCE,
            SOCIAL_STUDIES,
            HINDI,
            COMPUTER
        )

        fun fromString(subject: String): SchoolSubject {
            return entries.find { it.displayName.equals(subject, ignoreCase = true) } ?: ENGLISH
        }
    }
}

/**
 * App Navigation Screens
 */
enum class AppScreen(val title: String) {
    HOME("Trinity Grammar"),
    HOMEWORK("Daily Homework"),
    NOTICES("School Notices"),
    TIMETABLE("Class Timetable"),
    STUDY_MATERIALS("Study Materials"),
    ASSIGNMENTS("Assignments & Projects"),
    EXAM_SCHEDULE("Exam Schedules"),
    ABOUT_SCHOOL("About School"),
    ADMIN_PORTAL("Teacher / Admin Portal")
}
