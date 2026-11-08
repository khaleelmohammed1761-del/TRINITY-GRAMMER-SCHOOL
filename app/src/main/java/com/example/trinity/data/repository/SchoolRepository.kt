package com.example.trinity.data.repository

import com.example.trinity.data.local.AssignmentEntity
import com.example.trinity.data.local.ExamScheduleEntity
import com.example.trinity.data.local.HomeworkEntity
import com.example.trinity.data.local.NoticeEntity
import com.example.trinity.data.local.SeedData
import com.example.trinity.data.local.StudyMaterialEntity
import com.example.trinity.data.local.TimetableEntity
import com.example.trinity.data.local.TrinityDao
import com.example.trinity.data.local.UserPreferenceEntity
import com.example.trinity.model.ClassGrade
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SchoolRepository(
    private val dao: TrinityDao,
    private val externalScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) {

    init {
        // Pre-populate seed data asynchronously if database is freshly created
        externalScope.launch {
            if (dao.getHomeworkCount() == 0) {
                dao.insertHomeworkList(SeedData.generateInitialHomework())
            }
            if (dao.getNoticesCount() == 0) {
                dao.insertNoticeList(SeedData.generateInitialNotices())
            }
            if (dao.getTimetableCount() == 0) {
                dao.insertTimetableList(SeedData.generateInitialTimetable())
            }
            if (dao.getStudyMaterialsCount() == 0) {
                dao.insertStudyMaterialList(SeedData.generateInitialStudyMaterials())
            }
            if (dao.getAssignmentsCount() == 0) {
                dao.insertAssignmentList(SeedData.generateInitialAssignments())
            }
            if (dao.getExamSchedulesCount() == 0) {
                dao.insertExamScheduleList(SeedData.generateInitialExamSchedules())
            }
            if (dao.getPreference("selected_class") == null) {
                // Default to 10th Class so the dashboard starts with rich secondary content
                dao.savePreference(UserPreferenceEntity("selected_class", ClassGrade.CLASS_10.displayName))
            }
        }
    }

    // --- CLASS PREFERENCE ---
    val selectedClassFlow: Flow<String?> = dao.getPreferenceFlow("selected_class")

    suspend fun setSelectedClass(className: String) {
        dao.savePreference(UserPreferenceEntity("selected_class", className))
    }

    // --- HOMEWORK ---
    fun getHomeworkForClass(classGrade: String): Flow<List<HomeworkEntity>> =
        dao.getHomeworkForClass(classGrade)

    fun getHomeworkBySubject(classGrade: String, subject: String): Flow<List<HomeworkEntity>> =
        dao.getHomeworkBySubject(classGrade, subject)

    suspend fun toggleHomeworkComplete(id: Long, isCompleted: Boolean) {
        dao.updateHomeworkCompletion(id, isCompleted)
    }

    suspend fun saveStudentNotes(id: Long, notes: String) {
        dao.updateHomeworkStudentNotes(id, notes)
    }

    suspend fun addHomework(homework: HomeworkEntity): Long =
        dao.insertHomework(homework)

    suspend fun deleteHomework(homework: HomeworkEntity) =
        dao.deleteHomework(homework)

    // --- NOTICES ---
    fun getNoticesForClass(classGrade: String): Flow<List<NoticeEntity>> =
        dao.getNoticesForClass(classGrade)

    val allNotices: Flow<List<NoticeEntity>> = dao.getAllNotices()

    suspend fun addNotice(notice: NoticeEntity): Long =
        dao.insertNotice(notice)

    suspend fun deleteNotice(notice: NoticeEntity) =
        dao.deleteNotice(notice)

    // --- TIMETABLE ---
    fun getTimetableForDay(classGrade: String, dayOfWeek: String): Flow<List<TimetableEntity>> =
        dao.getTimetableForDay(classGrade, dayOfWeek)

    fun getAllTimetable(classGrade: String): Flow<List<TimetableEntity>> =
        dao.getAllTimetableForClass(classGrade)

    // --- STUDY MATERIALS ---
    fun getStudyMaterials(classGrade: String): Flow<List<StudyMaterialEntity>> =
        dao.getStudyMaterialsForClass(classGrade)

    fun getStudyMaterialsBySubject(classGrade: String, subject: String): Flow<List<StudyMaterialEntity>> =
        dao.getStudyMaterialsBySubject(classGrade, subject)

    suspend fun addStudyMaterial(material: StudyMaterialEntity): Long =
        dao.insertStudyMaterial(material)

    suspend fun deleteStudyMaterial(material: StudyMaterialEntity) =
        dao.deleteStudyMaterial(material)

    // --- ASSIGNMENTS ---
    fun getAssignments(classGrade: String): Flow<List<AssignmentEntity>> =
        dao.getAssignmentsForClass(classGrade)

    suspend fun submitAssignment(id: Long, submissionText: String) {
        dao.submitAssignment(
            id = id,
            isSubmitted = true,
            date = "Today",
            work = submissionText
        )
    }

    suspend fun addAssignment(assignment: AssignmentEntity): Long =
        dao.insertAssignment(assignment)

    // --- EXAM SCHEDULES ---
    fun getExamSchedules(classGrade: String): Flow<List<ExamScheduleEntity>> =
        dao.getExamSchedulesForClass(classGrade)

    suspend fun addExamSchedule(exam: ExamScheduleEntity): Long =
        dao.insertExamSchedule(exam)
}
