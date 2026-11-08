package com.example.trinity.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "homework")
data class HomeworkEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classGrade: String,
    val subject: String,
    val title: String,
    val description: String,
    val assignedDate: String,
    val dueDate: String,
    val teacherName: String,
    val isCompleted: Boolean = false,
    val attachmentName: String? = null,
    val attachmentType: String? = null, // "PDF", "IMAGE", "DOC"
    val attachmentContent: String? = null, // Rich text worksheet/content for built-in reader
    val studentNotes: String? = null
)

@Entity(tableName = "notices")
data class NoticeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String,
    val category: String, // "Urgent", "Academic", "Holiday", "Sports", "Event"
    val date: String,
    val targetClasses: String = "All Classes",
    val isPinned: Boolean = false
)

@Entity(tableName = "timetable")
data class TimetableEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classGrade: String,
    val dayOfWeek: String, // "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    val periodNumber: Int,
    val timeSlot: String,
    val subject: String,
    val teacherName: String,
    val roomNumber: String
)

@Entity(tableName = "study_materials")
data class StudyMaterialEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classGrade: String,
    val subject: String,
    val chapterTitle: String,
    val chapterNumber: String,
    val fileType: String = "PDF",
    val fileSize: String = "2.4 MB",
    val uploadDate: String,
    val teacherName: String,
    val documentContent: String // Rich formatted educational notes/summary
)

@Entity(tableName = "assignments")
data class AssignmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classGrade: String,
    val subject: String,
    val title: String,
    val description: String,
    val assignedDate: String,
    val dueDate: String,
    val maxMarks: Int = 20,
    val isSubmitted: Boolean = false,
    val submissionDate: String? = null,
    val studentWork: String? = null
)

@Entity(tableName = "exam_schedules")
data class ExamScheduleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classGrade: String,
    val examName: String,
    val subject: String,
    val examDate: String,
    val timing: String,
    val syllabus: String,
    val maxMarks: Int = 100,
    val roomNumber: String
)

@Entity(tableName = "user_preferences")
data class UserPreferenceEntity(
    @PrimaryKey val key: String,
    val value: String
)
