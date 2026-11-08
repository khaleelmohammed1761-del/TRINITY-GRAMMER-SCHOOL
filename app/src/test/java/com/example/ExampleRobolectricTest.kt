package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.trinity.data.local.SeedData
import com.example.trinity.model.ClassGrade
import com.example.trinity.model.SchoolSubject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read app title from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Trinity Grammar School", appName)
    }

    @Test
    fun `school affiliation string specifies State Syllabus`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val affiliation = context.getString(R.string.school_established)
        assertTrue(affiliation.contains("State Syllabus"))
    }

    @Test
    fun `all 13 classes are configured in ClassGrade`() {
        val classes = ClassGrade.ALL_GRADES
        assertEquals(13, classes.size)

        val expectedNames = listOf(
            "Nursery", "LKG", "UKG",
            "1st Class", "2nd Class", "3rd Class", "4th Class", "5th Class",
            "6th Class", "7th Class", "8th Class", "9th Class", "10th Class"
        )
        expectedNames.forEach { name ->
            val grade = ClassGrade.fromDisplayName(name)
            assertEquals(name, grade.displayName)
        }
    }

    @Test
    fun `core subjects are configured`() {
        val coreSubjects = SchoolSubject.CORE_SUBJECTS.map { it.displayName }
        assertTrue(coreSubjects.contains("English"))
        assertTrue(coreSubjects.contains("Mathematics"))
        assertTrue(coreSubjects.contains("Science"))
        assertTrue(coreSubjects.contains("Social Studies"))
        assertTrue(coreSubjects.contains("Hindi"))
        assertTrue(coreSubjects.contains("Computer"))
    }

    @Test
    fun `initial homework seed data includes assignments with worksheets`() {
        val homeworkList = SeedData.generateInitialHomework()
        assertTrue(homeworkList.isNotEmpty())

        val math10 = homeworkList.find { it.classGrade == "10th Class" && it.subject == "Mathematics" }
        assertNotNull(math10)
        assertTrue(math10!!.title.contains("Quadratic Equations"))
        assertNotNull(math10.attachmentContent)

        val english10 = homeworkList.find { it.classGrade == "10th Class" && it.subject == "English" }
        assertNotNull(english10)
        assertTrue(english10!!.title.contains("Madam Rides the Bus"))
    }

    @Test
    fun `initial notices contain pinned announcements`() {
        val notices = SeedData.generateInitialNotices()
        val pinned = notices.filter { it.isPinned }
        assertTrue(pinned.isNotEmpty())
    }

    @Test
    fun `timetable covers full Monday to Saturday weekly periods`() {
        val timetable = SeedData.generateInitialTimetable()
        val class10Timetable = timetable.filter { it.classGrade == "10th Class" }
        val days = class10Timetable.map { it.dayOfWeek }.distinct()
        assertEquals(6, days.size) // Mon to Sat
    }
}
