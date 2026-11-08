package com.example.trinity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.trinity.data.local.AssignmentEntity
import com.example.trinity.data.local.ExamScheduleEntity
import com.example.trinity.data.local.HomeworkEntity
import com.example.trinity.data.local.NoticeEntity
import com.example.trinity.data.local.StudyMaterialEntity
import com.example.trinity.data.local.TimetableEntity
import com.example.trinity.data.local.TrinityDatabase
import com.example.trinity.data.repository.SchoolRepository
import com.example.trinity.model.AppScreen
import com.example.trinity.model.ClassGrade
import com.example.trinity.model.SchoolSubject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class DocumentViewerData(
    val title: String,
    val subtitle: String,
    val content: String,
    val author: String,
    val fileType: String = "PDF",
    val date: String
)

class SchoolViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SchoolRepository
    init {
        val database = TrinityDatabase.getDatabase(application)
        repository = SchoolRepository(database.trinityDao())
    }

    // Navigation and Modals
    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _isClassSelectionOpen = MutableStateFlow(false)
    val isClassSelectionOpen: StateFlow<Boolean> = _isClassSelectionOpen.asStateFlow()

    private val _isAdminDialogOpen = MutableStateFlow(false)
    val isAdminDialogOpen: StateFlow<Boolean> = _isAdminDialogOpen.asStateFlow()

    private val _isAdminMode = MutableStateFlow(false)
    val isAdminMode: StateFlow<Boolean> = _isAdminMode.asStateFlow()

    private val _activeDocument = MutableStateFlow<DocumentViewerData?>(null)
    val activeDocument: StateFlow<DocumentViewerData?> = _activeDocument.asStateFlow()

    private val _submissionHomework = MutableStateFlow<HomeworkEntity?>(null)
    val submissionHomework: StateFlow<HomeworkEntity?> = _submissionHomework.asStateFlow()

    private val _userFeedbackMessage = MutableStateFlow<String?>(null)
    val userFeedbackMessage: StateFlow<String?> = _userFeedbackMessage.asStateFlow()

    // Filters
    private val _selectedClass = MutableStateFlow(ClassGrade.CLASS_10.displayName)
    val selectedClass: StateFlow<String> = _selectedClass.asStateFlow()

    private val _homeworkSubjectFilter = MutableStateFlow(SchoolSubject.ALL)
    val homeworkSubjectFilter: StateFlow<SchoolSubject> = _homeworkSubjectFilter.asStateFlow()

    private val _studySubjectFilter = MutableStateFlow(SchoolSubject.ALL)
    val studySubjectFilter: StateFlow<SchoolSubject> = _studySubjectFilter.asStateFlow()

    private val _noticeCategoryFilter = MutableStateFlow("All")
    val noticeCategoryFilter: StateFlow<String> = _noticeCategoryFilter.asStateFlow()

    private val _selectedTimetableDay = MutableStateFlow(getTodayDayOfWeek())
    val selectedTimetableDay: StateFlow<String> = _selectedTimetableDay.asStateFlow()

    init {
        // Observe saved class preference
        viewModelScope.launch {
            repository.selectedClassFlow.collect { savedClass ->
                if (!savedClass.isNullOrBlank()) {
                    _selectedClass.value = savedClass
                }
            }
        }
    }

    // Reactive Data Streams
    @OptIn(ExperimentalCoroutinesApi::class)
    val homeworkList: StateFlow<List<HomeworkEntity>> = combine(
        _selectedClass,
        _homeworkSubjectFilter
    ) { grade, subject ->
        Pair(grade, subject)
    }.flatMapLatest { (grade, subject) ->
        if (subject == SchoolSubject.ALL) {
            repository.getHomeworkForClass(grade)
        } else {
            repository.getHomeworkBySubject(grade, subject.displayName)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val noticesList: StateFlow<List<NoticeEntity>> = combine(
        _selectedClass,
        _noticeCategoryFilter
    ) { grade, category ->
        Pair(grade, category)
    }.flatMapLatest { (grade, category) ->
        repository.getNoticesForClass(grade)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val timetableList: StateFlow<List<TimetableEntity>> = combine(
        _selectedClass,
        _selectedTimetableDay
    ) { grade, day ->
        Pair(grade, day)
    }.flatMapLatest { (grade, day) ->
        repository.getTimetableForDay(grade, day)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val studyMaterialsList: StateFlow<List<StudyMaterialEntity>> = combine(
        _selectedClass,
        _studySubjectFilter
    ) { grade, subject ->
        Pair(grade, subject)
    }.flatMapLatest { (grade, subject) ->
        if (subject == SchoolSubject.ALL) {
            repository.getStudyMaterials(grade)
        } else {
            repository.getStudyMaterialsBySubject(grade, subject.displayName)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val assignmentsList: StateFlow<List<AssignmentEntity>> = _selectedClass
        .flatMapLatest { grade -> repository.getAssignments(grade) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val examSchedulesList: StateFlow<List<ExamScheduleEntity>> = _selectedClass
        .flatMapLatest { grade -> repository.getExamSchedules(grade) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- ACTIONS ---

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    fun selectClass(gradeName: String) {
        _selectedClass.value = gradeName
        viewModelScope.launch {
            repository.setSelectedClass(gradeName)
            _userFeedbackMessage.value = "Switched to $gradeName"
        }
        _isClassSelectionOpen.value = false
    }

    fun openClassSelectionDialog() {
        _isClassSelectionOpen.value = true
    }

    fun closeClassSelectionDialog() {
        _isClassSelectionOpen.value = false
    }

    fun setHomeworkSubjectFilter(subject: SchoolSubject) {
        _homeworkSubjectFilter.value = subject
    }

    fun setStudySubjectFilter(subject: SchoolSubject) {
        _studySubjectFilter.value = subject
    }

    fun setNoticeCategoryFilter(category: String) {
        _noticeCategoryFilter.value = category
    }

    fun setTimetableDay(day: String) {
        _selectedTimetableDay.value = day
    }

    fun toggleHomeworkCompletion(homework: HomeworkEntity) {
        viewModelScope.launch {
            val newState = !homework.isCompleted
            repository.toggleHomeworkComplete(homework.id, newState)
            _userFeedbackMessage.value = if (newState) "Homework marked as Done!" else "Homework marked as Pending"
        }
    }

    fun openHomeworkSubmission(homework: HomeworkEntity) {
        _submissionHomework.value = homework
    }

    fun closeHomeworkSubmission() {
        _submissionHomework.value = null
    }

    fun submitStudentNotes(homeworkId: Long, notes: String) {
        viewModelScope.launch {
            repository.saveStudentNotes(homeworkId, notes)
            repository.toggleHomeworkComplete(homeworkId, true)
            _submissionHomework.value = null
            _userFeedbackMessage.value = "Homework notes submitted successfully!"
        }
    }

    fun openDocumentViewer(
        title: String,
        subtitle: String,
        content: String,
        author: String,
        fileType: String = "PDF",
        date: String = "Today"
    ) {
        _activeDocument.value = DocumentViewerData(
            title = title,
            subtitle = subtitle,
            content = content,
            author = author,
            fileType = fileType,
            date = date
        )
    }

    fun closeDocumentViewer() {
        _activeDocument.value = null
    }

    fun submitAssignmentWork(assignmentId: Long, text: String) {
        viewModelScope.launch {
            repository.submitAssignment(assignmentId, text)
            _userFeedbackMessage.value = "Assignment submitted successfully!"
        }
    }

    // --- ADMIN / TEACHER PORTAL ---

    fun openAdminDialog() {
        _isAdminDialogOpen.value = true
    }

    fun closeAdminDialog() {
        _isAdminDialogOpen.value = false
    }

    fun verifyAdminPin(pin: String): Boolean {
        if (pin == "1234" || pin == "0000") {
            _isAdminMode.value = true
            _isAdminDialogOpen.value = false
            _currentScreen.value = AppScreen.ADMIN_PORTAL
            _userFeedbackMessage.value = "Teacher Portal unlocked"
            return true
        }
        return false
    }

    fun exitAdminMode() {
        _isAdminMode.value = false
        _currentScreen.value = AppScreen.HOME
        _userFeedbackMessage.value = "Logged out of Teacher Portal"
    }

    fun addHomework(
        targetClass: String,
        subject: String,
        title: String,
        description: String,
        dueDate: String,
        teacher: String,
        attachmentName: String?,
        attachmentContent: String?
    ) {
        viewModelScope.launch {
            val entity = HomeworkEntity(
                classGrade = targetClass,
                subject = subject,
                title = title,
                description = description,
                assignedDate = getFormattedToday(),
                dueDate = dueDate,
                teacherName = teacher,
                attachmentName = attachmentName,
                attachmentType = if (attachmentName != null) "PDF" else null,
                attachmentContent = attachmentContent
            )
            repository.addHomework(entity)
            _userFeedbackMessage.value = "New homework posted for $targetClass!"
        }
    }

    fun addNotice(
        title: String,
        content: String,
        category: String,
        targetClasses: String,
        isPinned: Boolean
    ) {
        viewModelScope.launch {
            val entity = NoticeEntity(
                title = title,
                content = content,
                category = category,
                date = getFormattedToday(),
                targetClasses = targetClasses,
                isPinned = isPinned
            )
            repository.addNotice(entity)
            _userFeedbackMessage.value = "Announcement published!"
        }
    }

    fun addStudyMaterial(
        targetClass: String,
        subject: String,
        chapterTitle: String,
        chapterNumber: String,
        fileType: String,
        fileSize: String,
        content: String,
        teacher: String
    ) {
        viewModelScope.launch {
            val entity = StudyMaterialEntity(
                classGrade = targetClass,
                subject = subject,
                chapterTitle = chapterTitle,
                chapterNumber = chapterNumber,
                fileType = fileType,
                fileSize = fileSize,
                uploadDate = getFormattedToday(),
                teacherName = teacher,
                documentContent = content
            )
            repository.addStudyMaterial(entity)
            _userFeedbackMessage.value = "Study material uploaded for $targetClass!"
        }
    }

    fun clearFeedbackMessage() {
        _userFeedbackMessage.value = null
    }

    private fun getTodayDayOfWeek(): String {
        val calendar = Calendar.getInstance()
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            else -> "Monday" // Default to Monday if Sunday
        }
    }

    private fun getFormattedToday(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Calendar.getInstance().time)
    }
}
