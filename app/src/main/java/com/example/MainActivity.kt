package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trinity.model.AppScreen
import com.example.trinity.ui.components.AdminPinDialog
import com.example.trinity.ui.components.ClassSelectionDialog
import com.example.trinity.ui.components.DocumentViewerDialog
import com.example.trinity.ui.components.HomeworkSubmissionDialog
import com.example.trinity.ui.components.SchoolBottomNav
import com.example.trinity.ui.components.SchoolTopBar
import com.example.trinity.ui.screens.AboutSchoolScreen
import com.example.trinity.ui.screens.AdminPortalScreen
import com.example.trinity.ui.screens.AssignmentsScreen
import com.example.trinity.ui.screens.ExamScheduleScreen
import com.example.trinity.ui.screens.HomeScreen
import com.example.trinity.ui.screens.HomeworkScreen
import com.example.trinity.ui.screens.NoticesScreen
import com.example.trinity.ui.screens.StudyMaterialsScreen
import com.example.trinity.ui.screens.TimetableScreen
import com.example.trinity.viewmodel.SchoolViewModel
import com.example.ui.theme.TrinityGrammarSchoolTheme

class MainActivity : ComponentActivity() {

    private val viewModel: SchoolViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TrinityGrammarSchoolTheme {
                TrinitySchoolApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun TrinitySchoolApp(viewModel: SchoolViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val selectedClass by viewModel.selectedClass.collectAsStateWithLifecycle()
    val homeworkList by viewModel.homeworkList.collectAsStateWithLifecycle()
    val noticesList by viewModel.noticesList.collectAsStateWithLifecycle()
    val timetableList by viewModel.timetableList.collectAsStateWithLifecycle()
    val studyMaterialsList by viewModel.studyMaterialsList.collectAsStateWithLifecycle()
    val assignmentsList by viewModel.assignmentsList.collectAsStateWithLifecycle()
    val examSchedulesList by viewModel.examSchedulesList.collectAsStateWithLifecycle()

    val homeworkSubjectFilter by viewModel.homeworkSubjectFilter.collectAsStateWithLifecycle()
    val studySubjectFilter by viewModel.studySubjectFilter.collectAsStateWithLifecycle()
    val noticeCategoryFilter by viewModel.noticeCategoryFilter.collectAsStateWithLifecycle()
    val selectedTimetableDay by viewModel.selectedTimetableDay.collectAsStateWithLifecycle()

    val isClassSelectionOpen by viewModel.isClassSelectionOpen.collectAsStateWithLifecycle()
    val isAdminDialogOpen by viewModel.isAdminDialogOpen.collectAsStateWithLifecycle()
    val isAdminMode by viewModel.isAdminMode.collectAsStateWithLifecycle()
    val activeDocument by viewModel.activeDocument.collectAsStateWithLifecycle()
    val submissionHomework by viewModel.submissionHomework.collectAsStateWithLifecycle()
    val feedbackMessage by viewModel.userFeedbackMessage.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(feedbackMessage) {
        feedbackMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearFeedbackMessage()
        }
    }

    // Hardware Back Button Handler
    BackHandler(enabled = currentScreen != AppScreen.HOME) {
        if (currentScreen == AppScreen.ADMIN_PORTAL) {
            viewModel.exitAdminMode()
        } else {
            viewModel.navigateTo(AppScreen.HOME)
        }
    }

    Scaffold(
        topBar = {
            SchoolTopBar(
                currentScreen = currentScreen,
                selectedClass = selectedClass,
                isAdminMode = isAdminMode,
                onBackClick = {
                    if (currentScreen == AppScreen.ADMIN_PORTAL) {
                        viewModel.exitAdminMode()
                    } else {
                        viewModel.navigateTo(AppScreen.HOME)
                    }
                },
                onOpenClassSelector = { viewModel.openClassSelectionDialog() },
                onOpenAdminPortal = { viewModel.openAdminDialog() },
                onExitAdminMode = { viewModel.exitAdminMode() }
            )
        },
        bottomBar = {
            if (currentScreen != AppScreen.ADMIN_PORTAL) {
                SchoolBottomNav(
                    currentScreen = currentScreen,
                    onNavigate = { viewModel.navigateTo(it) }
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                AppScreen.HOME -> {
                    HomeScreen(
                        selectedClass = selectedClass,
                        homeworkList = homeworkList,
                        noticesList = noticesList,
                        onOpenClassSelector = { viewModel.openClassSelectionDialog() },
                        onNavigate = { viewModel.navigateTo(it) }
                    )
                }

                AppScreen.HOMEWORK -> {
                    HomeworkScreen(
                        selectedClass = selectedClass,
                        homeworkList = homeworkList,
                        selectedSubject = homeworkSubjectFilter,
                        onSelectSubject = { viewModel.setHomeworkSubjectFilter(it) },
                        onToggleCompletion = { viewModel.toggleHomeworkCompletion(it) },
                        onOpenSubmission = { viewModel.openHomeworkSubmission(it) },
                        onOpenDocumentViewer = { title, subtitle, content, author ->
                            viewModel.openDocumentViewer(title, subtitle, content, author)
                        },
                        onOpenClassSelector = { viewModel.openClassSelectionDialog() }
                    )
                }

                AppScreen.NOTICES -> {
                    NoticesScreen(
                        selectedClass = selectedClass,
                        noticesList = noticesList,
                        selectedCategory = noticeCategoryFilter,
                        onSelectCategory = { viewModel.setNoticeCategoryFilter(it) }
                    )
                }

                AppScreen.TIMETABLE -> {
                    TimetableScreen(
                        selectedClass = selectedClass,
                        timetableList = timetableList,
                        selectedDay = selectedTimetableDay,
                        onSelectDay = { viewModel.setTimetableDay(it) }
                    )
                }

                AppScreen.STUDY_MATERIALS -> {
                    StudyMaterialsScreen(
                        selectedClass = selectedClass,
                        materialsList = studyMaterialsList,
                        selectedSubject = studySubjectFilter,
                        onSelectSubject = { viewModel.setStudySubjectFilter(it) },
                        onOpenDocumentViewer = { title, subtitle, content, author ->
                            viewModel.openDocumentViewer(title, subtitle, content, author)
                        }
                    )
                }

                AppScreen.ASSIGNMENTS -> {
                    AssignmentsScreen(
                        selectedClass = selectedClass,
                        assignmentsList = assignmentsList,
                        onSubmitWork = { id, text -> viewModel.submitAssignmentWork(id, text) }
                    )
                }

                AppScreen.EXAM_SCHEDULE -> {
                    ExamScheduleScreen(
                        selectedClass = selectedClass,
                        examSchedulesList = examSchedulesList
                    )
                }

                AppScreen.ABOUT_SCHOOL -> {
                    AboutSchoolScreen()
                }

                AppScreen.ADMIN_PORTAL -> {
                    AdminPortalScreen(
                        onExitAdmin = { viewModel.exitAdminMode() },
                        onAddHomework = { targetClass, subject, title, desc, due, teacher, attName, attContent ->
                            viewModel.addHomework(targetClass, subject, title, desc, due, teacher, attName, attContent)
                        },
                        onAddNotice = { title, content, cat, targets, pinned ->
                            viewModel.addNotice(title, content, cat, targets, pinned)
                        },
                        onAddStudyMaterial = { targetClass, subject, chTitle, chNum, fileType, size, content, teacher ->
                            viewModel.addStudyMaterial(targetClass, subject, chTitle, chNum, fileType, size, content, teacher)
                        }
                    )
                }
            }
        }
    }

    // Modals & Dialogs
    if (isClassSelectionOpen) {
        ClassSelectionDialog(
            selectedClass = selectedClass,
            onClassSelected = { viewModel.selectClass(it) },
            onDismiss = { viewModel.closeClassSelectionDialog() }
        )
    }

    if (isAdminDialogOpen) {
        AdminPinDialog(
            onDismiss = { viewModel.closeAdminDialog() },
            onVerifyPin = { viewModel.verifyAdminPin(it) }
        )
    }

    activeDocument?.let { doc ->
        DocumentViewerDialog(
            data = doc,
            onDismiss = { viewModel.closeDocumentViewer() },
            onDownloadNotification = { message ->
                // Feedback
            }
        )
    }

    submissionHomework?.let { hw ->
        HomeworkSubmissionDialog(
            homework = hw,
            onDismiss = { viewModel.closeHomeworkSubmission() },
            onSubmit = { id, notes -> viewModel.submitStudentNotes(id, notes) }
        )
    }
}
