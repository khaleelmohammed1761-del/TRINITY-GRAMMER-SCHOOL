package com.example.trinity.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TrinityDao {

    // --- HOMEWORK ---
    @Query("SELECT * FROM homework WHERE classGrade = :classGrade ORDER BY id DESC")
    fun getHomeworkForClass(classGrade: String): Flow<List<HomeworkEntity>>

    @Query("SELECT * FROM homework WHERE classGrade = :classGrade AND subject = :subject ORDER BY id DESC")
    fun getHomeworkBySubject(classGrade: String, subject: String): Flow<List<HomeworkEntity>>

    @Query("SELECT COUNT(*) FROM homework")
    suspend fun getHomeworkCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomework(homework: HomeworkEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomeworkList(list: List<HomeworkEntity>)

    @Update
    suspend fun updateHomework(homework: HomeworkEntity)

    @Query("UPDATE homework SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun updateHomeworkCompletion(id: Long, isCompleted: Boolean)

    @Query("UPDATE homework SET studentNotes = :notes WHERE id = :id")
    suspend fun updateHomeworkStudentNotes(id: Long, notes: String)

    @Delete
    suspend fun deleteHomework(homework: HomeworkEntity)


    // --- NOTICES ---
    @Query("SELECT * FROM notices ORDER BY isPinned DESC, id DESC")
    fun getAllNotices(): Flow<List<NoticeEntity>>

    @Query("SELECT * FROM notices WHERE targetClasses = 'All Classes' OR targetClasses LIKE '%' || :classGrade || '%' ORDER BY isPinned DESC, id DESC")
    fun getNoticesForClass(classGrade: String): Flow<List<NoticeEntity>>

    @Query("SELECT COUNT(*) FROM notices")
    suspend fun getNoticesCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotice(notice: NoticeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoticeList(list: List<NoticeEntity>)

    @Delete
    suspend fun deleteNotice(notice: NoticeEntity)


    // --- TIMETABLE ---
    @Query("SELECT * FROM timetable WHERE classGrade = :classGrade AND dayOfWeek = :dayOfWeek ORDER BY periodNumber ASC")
    fun getTimetableForDay(classGrade: String, dayOfWeek: String): Flow<List<TimetableEntity>>

    @Query("SELECT * FROM timetable WHERE classGrade = :classGrade ORDER BY periodNumber ASC")
    fun getAllTimetableForClass(classGrade: String): Flow<List<TimetableEntity>>

    @Query("SELECT COUNT(*) FROM timetable")
    suspend fun getTimetableCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimetableList(list: List<TimetableEntity>)


    // --- STUDY MATERIALS ---
    @Query("SELECT * FROM study_materials WHERE classGrade = :classGrade ORDER BY id DESC")
    fun getStudyMaterialsForClass(classGrade: String): Flow<List<StudyMaterialEntity>>

    @Query("SELECT * FROM study_materials WHERE classGrade = :classGrade AND subject = :subject ORDER BY id DESC")
    fun getStudyMaterialsBySubject(classGrade: String, subject: String): Flow<List<StudyMaterialEntity>>

    @Query("SELECT COUNT(*) FROM study_materials")
    suspend fun getStudyMaterialsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudyMaterial(material: StudyMaterialEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudyMaterialList(list: List<StudyMaterialEntity>)

    @Delete
    suspend fun deleteStudyMaterial(material: StudyMaterialEntity)


    // --- ASSIGNMENTS ---
    @Query("SELECT * FROM assignments WHERE classGrade = :classGrade ORDER BY dueDate ASC")
    fun getAssignmentsForClass(classGrade: String): Flow<List<AssignmentEntity>>

    @Query("SELECT COUNT(*) FROM assignments")
    suspend fun getAssignmentsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: AssignmentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignmentList(list: List<AssignmentEntity>)

    @Update
    suspend fun updateAssignment(assignment: AssignmentEntity)

    @Query("UPDATE assignments SET isSubmitted = :isSubmitted, submissionDate = :date, studentWork = :work WHERE id = :id")
    suspend fun submitAssignment(id: Long, isSubmitted: Boolean, date: String?, work: String?)


    // --- EXAM SCHEDULES ---
    @Query("SELECT * FROM exam_schedules WHERE classGrade = :classGrade ORDER BY examDate ASC")
    fun getExamSchedulesForClass(classGrade: String): Flow<List<ExamScheduleEntity>>

    @Query("SELECT COUNT(*) FROM exam_schedules")
    suspend fun getExamSchedulesCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExamSchedule(exam: ExamScheduleEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExamScheduleList(list: List<ExamScheduleEntity>)


    // --- USER PREFERENCES ---
    @Query("SELECT value FROM user_preferences WHERE key = :key LIMIT 1")
    fun getPreferenceFlow(key: String): Flow<String?>

    @Query("SELECT value FROM user_preferences WHERE key = :key LIMIT 1")
    suspend fun getPreference(key: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePreference(pref: UserPreferenceEntity)
}
