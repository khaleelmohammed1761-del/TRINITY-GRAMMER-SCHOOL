package com.example.trinity.data.local

import com.example.trinity.model.ClassGrade

object SeedData {

    fun generateInitialHomework(): List<HomeworkEntity> {
        val list = mutableListOf<HomeworkEntity>()

        // 10th Class Homework (Board Exam Class)
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "Mathematics",
                title = "Quadratic Equations - Exercise 4.3 & 4.4",
                description = "Complete questions 1 to 8 on nature of roots and discriminant method. Show all steps in your Classwork register. Memorize the quadratic formula.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Dr. R. K. Sharma (Head of Maths)",
                attachmentName = "Maths_Ch4_Problem_Set.pdf",
                attachmentType = "PDF",
                attachmentContent = """
                    TRINITY GRAMMAR SCHOOL - DEPARTMENT OF MATHEMATICS
                    CLASS: 10TH GRADE | CHAPTER: QUADRATIC EQUATIONS
                    
                    WORKSHEET 4.3: NATURE OF ROOTS
                    Formula Reference:
                    Discriminant D = b² - 4ac
                    • If D > 0: Two distinct real roots
                    • If D = 0: Two equal real roots (-b / 2a)
                    • If D < 0: No real roots
                    
                    PRACTICE PROBLEMS:
                    1. Find discriminant of 2x² - 4x + 3 = 0 and determine nature of roots.
                    2. For what value of k does kx(x - 2) + 6 = 0 have equal roots?
                    3. Solve using quadratic formula: 3x² - 5x + 2 = 0
                    4. A train travels 360 km at a uniform speed. If speed had been 5 km/h more, it would have taken 1 hour less. Find initial speed.
                    
                    SUBMISSION: Submit completed register by 8:30 AM tomorrow.
                """.trimIndent()
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "English",
                title = "First Flight - Madam Rides the Bus: Character Analysis",
                description = "Write a 150-word analytical paragraph highlighting Valli's sense of curiosity and meticulous planning. Review questions on page 127.",
                assignedDate = "07 Nov 2026",
                dueDate = "09 Nov 2026",
                teacherName = "Mrs. Sarah Henderson",
                attachmentName = "Valli_Character_Sketch_Guide.pdf",
                attachmentType = "PDF",
                attachmentContent = """
                    TRINITY GRAMMAR SCHOOL - ENGLISH LITERATURE
                    CLASS 10 | 'MADAM RIDES THE BUS' (VALLIKKANNAN)
                    
                    KEY THEMES & DISCUSSION POINTS:
                    • Valliammai (Valli): An eight-year-old girl with an insatiable longing to experience the world beyond her village.
                    • Courage & Independence: Resisting temptations at the village fair to save money for the bus fare (thirty paise each way).
                    • Confrontation with Mortality: The dead cow on the roadside marks her transition from childhood innocence to sobering realization.
                    
                    HOMEWORK TASK:
                    Write a formal character sketch highlighting:
                    1. Her resourcefulness in saving 60 paise.
                    2. Her dignified interaction with the bus conductor.
                    3. Her mature reaction upon seeing the accident.
                """.trimIndent()
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "Science",
                title = "Light: Reflection & Refraction - Ray Diagrams",
                description = "Draw all 6 concave mirror ray diagrams in lab journal with proper focal point and center of curvature labels. Solve numericals 1-4 on page 186.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mr. Anirudh Verma (Physics)",
                attachmentName = "Ray_Diagram_Rules_Sheet.pdf",
                attachmentType = "PDF",
                attachmentContent = """
                    TRINITY GRAMMAR SCHOOL - PHYSICS LABORATORY
                    CLASS 10 | RAY DIAGRAM ESSENTIAL RULES
                    
                    RULES FOR LIGHT RAYS:
                    1. A ray parallel to principal axis passes through principal focus (F) after reflection.
                    2. A ray passing through principal focus emerges parallel to principal axis.
                    3. A ray passing through center of curvature (C) is reflected back along the same path.
                    
                    LAB MANDATE:
                    • Use a sharp pencil and clean straight ruler.
                    • Draw principal axis, pole (P), focus (F = 3 cm), center of curvature (C = 6 cm).
                    • Position object at: Infinity, Beyond C, At C, Between C and F, At F, Between P and F.
                """.trimIndent()
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "Social Studies",
                title = "Nationalism in India - Map Pointing & Chronology",
                description = "Mark Champaran, Kheda, Ahmedabad, and Dandi on an outline map of India. Summarize the Rowlatt Act and Jallianwala Bagh incident in 100 words.",
                assignedDate = "06 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Ms. Kavita Menon",
                attachmentName = "India_Map_Satyagraha_Centres.pdf",
                attachmentType = "PDF",
                attachmentContent = """
                    TRINITY GRAMMAR SCHOOL - SOCIAL SCIENCES (HISTORY)
                    IMPORTANT MAP ITEMS FOR STATE BOARD (SSC):
                    
                    1. Champaran (Bihar) - Movement of Indigo Peasants (1917)
                    2. Kheda (Gujarat) - Peasant Satyagraha (1918)
                    3. Ahmedabad (Gujarat) - Cotton Mill Workers Satyagraha (1918)
                    4. Amritsar (Punjab) - Jallianwala Bagh Incident (13 April 1919)
                    5. Chauri Chaura (U.P.) - Calling off the Non-Cooperation Movement (1922)
                    6. Dandi (Gujarat) - Civil Disobedience Movement (1930)
                """.trimIndent()
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "Hindi",
                title = "स्पर्श भाग 2 - साखी (कबीरदास) पद व्याख्या",
                description = "कबीरदास की प्रथम तीन साखियों का भावार्थ अपनी अभ्यास पुस्तिका में लिखिए तथा कठिन शब्दों के अर्थ याद कीजिए।",
                assignedDate = "07 Nov 2026",
                dueDate = "09 Nov 2026",
                teacherName = "आचार्य सुरेश शास्त्री",
                attachmentName = "Kabir_Sakhi_Vyakhya.pdf",
                attachmentType = "PDF",
                attachmentContent = """
                    ट्रिनिटी ग्रामर स्कूल - हिंदी विभाग (कक्षा 10)
                    पाठ: साखी - संत कबीर
                    
                    साखी 1:
                    'ऐसी बानी बोलिए, मन का आपा खोइ।
                    औरन को सीतल करै, आपहु सीतल होइ।।'
                    
                    भावार्थ: कबीरदास जी कहते हैं कि मनुष्य को अपने अहंकार का त्याग करके ऐसी मधुर वाणी बोलनी चाहिए जिससे सुनने वालों को सुख और शांति मिले तथा स्वयं का मन भी प्रसन्न रहे।
                """.trimIndent()
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "Computer",
                title = "Python Programming - Functions & Recursion Assignment",
                description = "Write Python scripts to calculate factorial of a number using recursion and check for palindrome string. Save as .py and write logic in journal.",
                assignedDate = "07 Nov 2026",
                dueDate = "10 Nov 2026",
                teacherName = "Mr. David Chen (IT Dept)",
                attachmentName = "Python_Lab_Worksheet_05.pdf",
                attachmentType = "PDF",
                attachmentContent = """
                    TRINITY GRAMMAR SCHOOL - COMPUTER SCIENCE LAB
                    CODE SPECIFICATION - RECURSION LAB
                    
                    Task 1: Factorial Function
                    def factorial(n):
                        if n <= 1:
                            return 1
                        return n * factorial(n - 1)
                        
                    Task 2: Palindrome Checker
                    def is_palindrome(text):
                        clean = text.lower().replace(" ", "")
                        return clean == clean[::-1]
                """.trimIndent()
            )
        )

        // 9th Class Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_9.displayName,
                subject = "Science",
                title = "Structure of Atom - Bohr-Bury Scheme",
                description = "Draw electronic configuration schematics for the first 18 elements of the periodic table in your workbook.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Dr. Malini Rao",
                attachmentName = "Bohr_Model_Handout.pdf",
                attachmentType = "PDF",
                attachmentContent = "Rules: Max electrons in shell = 2n². Valence shell cannot exceed 8 electrons."
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_9.displayName,
                subject = "Mathematics",
                title = "Linear Equations in Two Variables - Graph Plotting",
                description = "Plot graphs for 2x + 3y = 12 and x - y = 1 on graph paper. Find the point of intersection.",
                assignedDate = "07 Nov 2026",
                dueDate = "09 Nov 2026",
                teacherName = "Mr. S. K. Gupta",
                attachmentName = "Linear_Graph_Exercises.pdf",
                attachmentType = "PDF",
                attachmentContent = "Complete questions from NCERT Exemplar Exercise 4.2."
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_9.displayName,
                subject = "English",
                title = "Beehive - The Road Not Taken: Poetic Devices",
                description = "Identify metaphor, alliteration, and symbolism in Robert Frost's poem. Write answer to Question 3 in notebook.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mrs. Sarah Henderson"
            )
        )

        // 8th Class Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_8.displayName,
                subject = "Mathematics",
                title = "Rational Numbers - Properties & Number Line",
                description = "Represent -2/5, 3/7 and -5/8 on separate number lines. Solve Exercise 1.2 Q1 to Q5.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mr. T. Srinivas",
                attachmentName = "Rational_Numbers_WS.pdf",
                attachmentType = "PDF",
                attachmentContent = "Practice finding 5 rational numbers between 2/3 and 4/5."
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_8.displayName,
                subject = "Science",
                title = "Microorganisms: Friend and Foe - Chart Work",
                description = "Create a table listing 4 beneficial bacteria, fungi, and protozoa with their commercial and medicinal uses.",
                assignedDate = "07 Nov 2026",
                dueDate = "09 Nov 2026",
                teacherName = "Mrs. Priya Nambiar"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_8.displayName,
                subject = "Computer",
                title = "HTML5 & CSS Basics - Web Page Project",
                description = "Design a simple HTML webpage about 'My Favorite Scientist' using heading tags, unordered list, and an image tag.",
                assignedDate = "07 Nov 2026",
                dueDate = "10 Nov 2026",
                teacherName = "Mr. David Chen"
            )
        )

        // 7th Class Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_7.displayName,
                subject = "Science",
                title = "Nutrition in Plants - Photosynthesis Experiment",
                description = "Draw and label the cross-section of a leaf showing stomata and guard cells. Explain chlorophyll function.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mrs. Neha Bajaj"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_7.displayName,
                subject = "Mathematics",
                title = "Fractions and Decimals - Division of Fractions",
                description = "Solve Exercise 2.4 Questions 1 to 4. Practice reciprocal concept.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mr. T. Srinivas"
            )
        )

        // 6th Class Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_6.displayName,
                subject = "English",
                title = "A Tale of Two Birds - Moral of the Story",
                description = "Read Chapter 1 from Supplementary Reader. Write 5 sentences explaining how company affects our nature.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Ms. Sunita Roy"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_6.displayName,
                subject = "Social Studies",
                title = "The Earth in the Solar System - Planet Orbit Chart",
                description = "Draw the solar system showing all 8 planets in order from the Sun. Highlight inner and outer planets.",
                assignedDate = "07 Nov 2026",
                dueDate = "09 Nov 2026",
                teacherName = "Mr. Amit Patel"
            )
        )

        // 5th Class Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_5.displayName,
                subject = "Mathematics",
                title = "The Fish Tale - Speed, Distance and Time",
                description = "Solve word problems 1 to 5 on page 14 of Math-Magic. Remember: Distance = Speed × Time.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mrs. Leela Nair"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_5.displayName,
                subject = "English",
                title = "Ice-Cream Man Poem - Rhyming Words",
                description = "Write 10 rhyming words from the poem. Write a short paragraph describing your favorite ice cream flavor.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Ms. Grace Mary"
            )
        )

        // 4th Class Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_4.displayName,
                subject = "Science",
                title = "Plant Life - Types of Roots",
                description = "Collect two fallen leaves showing tap root and fibrous root venation. Paste neatly in EVS scrapbook.",
                assignedDate = "07 Nov 2026",
                dueDate = "09 Nov 2026",
                teacherName = "Mrs. Sunita Deshmukh"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_4.displayName,
                subject = "Mathematics",
                title = "Building with Bricks - Perimeter & Area basics",
                description = "Measure length and width of your notebook using a 15 cm scale. Calculate perimeter.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mrs. Leela Nair"
            )
        )

        // 3rd Class Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_3.displayName,
                subject = "English",
                title = "Good Morning Poem - Reading & Handwriting",
                description = "Recite poem with expression. Copy first two stanzas in cursive four-line notebook with neat handwriting.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Ms. Grace Mary"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_3.displayName,
                subject = "Mathematics",
                title = "Where to Look From - Top, Front and Side Views",
                description = "Draw top view and side view of: 1. A table, 2. A car, 3. A cup.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mrs. Meena Joshi"
            )
        )

        // 2nd Class Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_2.displayName,
                subject = "English",
                title = "First Day at School - Sight Words & Sentences",
                description = "Read page 2. Make short sentences with words: 'Puppy', 'Teacher', 'Grandmother', 'Stare'.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mrs. Anjali Kulkarni"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_2.displayName,
                subject = "Mathematics",
                title = "What is Long, What is Round? - Sorting Activity",
                description = "Find 5 round things and 5 long objects in your home. Write their names in your math notebook.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mrs. Anjali Kulkarni"
            )
        )

        // 1st Class Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_1.displayName,
                subject = "English",
                title = "A Happy Child - Rhyme Recitation & Color House",
                description = "Recite the poem 'My house is red - a little house'. Color the house picture on page 3.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Ms. Pooja Reddy"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.CLASS_1.displayName,
                subject = "Mathematics",
                title = "Shapes and Space - Inside / Outside & Bigger / Smaller",
                description = "Circle the bigger animal and cross the smaller object on worksheet page 5.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Ms. Pooja Reddy"
            )
        )

        // UKG Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.UKG.displayName,
                subject = "English",
                title = "Three Letter 'a' Family Words (cat, bat, mat)",
                description = "Read and write words: cat, bat, mat, rat, fan, pan 3 times each in four-line book.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mrs. Jennifer D'Souza"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.UKG.displayName,
                subject = "Mathematics",
                title = "Number Writing 1 to 50 & Count the Apples",
                description = "Write missing numbers from 1 to 50 in square notebook. Count and color the stars.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Mrs. Jennifer D'Souza"
            )
        )

        // LKG Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.LKG.displayName,
                subject = "English",
                title = "Alphabet Tracing: Letters G, H, I",
                description = "Trace letters G, H, I on worksheet page 12 with red and blue crayons.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Ms. Fatima Khan"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.LKG.displayName,
                subject = "Mathematics",
                title = "Numbers 1 to 10 - Count & Match",
                description = "Count the balloons and match with correct number. Color the balloons brightly.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Ms. Fatima Khan"
            )
        )

        // Nursery Homework
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.NURSERY.displayName,
                subject = "English",
                title = "Standing Lines & Sleeping Lines Tracing",
                description = "Hold wax crayon properly. Trace standing lines (|) and sleeping lines (—) on practice sheet.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Ms. Shalini Iyer"
            )
        )
        list.add(
            HomeworkEntity(
                classGrade = ClassGrade.NURSERY.displayName,
                subject = "Mathematics",
                title = "Color Recognition: Yellow Sun & Red Apple",
                description = "Identify yellow and red colors. Color the big apple with red wax crayon.",
                assignedDate = "07 Nov 2026",
                dueDate = "08 Nov 2026",
                teacherName = "Ms. Shalini Iyer"
            )
        )

        return list
    }

    fun generateInitialNotices(): List<NoticeEntity> {
        return listOf(
            NoticeEntity(
                title = "State Board (SSC) Pre-Board Examination Schedule 2026-27",
                content = "Pre-Board I examinations for 10th Class will commence from December 1st, 2026. Hall tickets and comprehensive revision schedules have been distributed to respective class teachers. Ensure 100% attendance.",
                category = "Academic",
                date = "06 Nov 2026",
                targetClasses = "9th Class, 10th Class",
                isPinned = true
            ),
            NoticeEntity(
                title = "37th Annual Inter-School Sports Meet & Athletic Carnival",
                content = "Trinity Grammar School is hosting the Hyderabad District Inter-School Athletic Carnival on November 21st & 22nd. Student registration for Track & Field events (100m, 400m, relay, long jump) closes this Friday with Physical Education teachers.",
                category = "Sports",
                date = "05 Nov 2026",
                targetClasses = "All Classes",
                isPinned = true
            ),
            NoticeEntity(
                title = "National Science Day Exhibition & Robotics Expo",
                content = "Students from Classes 4th to 10th are invited to submit working models and innovative science project abstracts for the Annual Trinity Tech & Innovation Fair. Category prizes include gold medals and STEM vouchers.",
                category = "Event",
                date = "04 Nov 2026",
                targetClasses = "All Classes",
                isPinned = false
            ),
            NoticeEntity(
                title = "Winter School Uniform Advisory Effective November 15th",
                content = "All students are requested to switch to the official navy blue Trinity blazer/sweater and tie from Monday, 15th November. Uniform counters at school reception are open from 9:00 AM to 3:30 PM.",
                category = "General",
                date = "03 Nov 2026",
                targetClasses = "All Classes",
                isPinned = false
            ),
            NoticeEntity(
                title = "Parent-Teacher Meeting (PTM) for Term 1 Assessment",
                content = "Term 1 progress report discussion will be held on Saturday, 14th November from 8:30 AM to 1:00 PM. Parents are requested to adhere to the roll-number time slots sent via student diary.",
                category = "Academic",
                date = "02 Nov 2026",
                targetClasses = "All Classes",
                isPinned = false
            ),
            NoticeEntity(
                title = "Holiday Declaration: Guru Nanak Jayanti",
                content = "The school will remain closed on Friday, 15th November 2026 on the auspicious occasion of Guru Nanak Jayanti. Normal academic classes will resume on Monday.",
                category = "Holiday",
                date = "01 Nov 2026",
                targetClasses = "All Classes",
                isPinned = false
            )
        )
    }

    fun generateInitialTimetable(): List<TimetableEntity> {
        val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        val result = mutableListOf<TimetableEntity>()

        // Generate full weekly schedules for all classes
        ClassGrade.ALL_GRADES.forEach { grade ->
            days.forEach { day ->
                val subjectsForDay = when (day) {
                    "Monday" -> listOf("Mathematics", "English", "Science", "Social Studies", "Hindi", "Computer", "Sports")
                    "Tuesday" -> listOf("English", "Science", "Mathematics", "Social Studies", "Computer", "Hindi", "Library")
                    "Wednesday" -> listOf("Science", "Mathematics", "English", "Social Studies", "Hindi", "Art & Craft", "Computer")
                    "Thursday" -> listOf("Mathematics", "English", "Science", "Hindi", "Computer", "Social Studies", "Music")
                    "Friday" -> listOf("Science", "Social Studies", "Mathematics", "English", "Computer", "Hindi", "Club Activity")
                    else -> listOf("English", "Mathematics", "Science", "Quiz / Debate", "Physical Training", "Assembly")
                }

                val timeSlots = listOf(
                    "08:30 AM - 09:15 AM",
                    "09:15 AM - 10:00 AM",
                    "10:15 AM - 11:00 AM",
                    "11:00 AM - 11:45 AM",
                    "12:25 PM - 01:10 PM",
                    "01:10 PM - 01:55 PM",
                    "01:55 PM - 02:40 PM"
                )

                subjectsForDay.forEachIndexed { index, sub ->
                    if (index < timeSlots.size) {
                        val teacher = when (sub) {
                            "Mathematics" -> "Dr. R. K. Sharma"
                            "English" -> "Mrs. Sarah Henderson"
                            "Science" -> "Mr. Anirudh Verma"
                            "Social Studies" -> "Ms. Kavita Menon"
                            "Hindi" -> "Acharya Suresh"
                            "Computer" -> "Mr. David Chen"
                            else -> "Coach Vikram / Staff"
                        }
                        result.add(
                            TimetableEntity(
                                classGrade = grade.displayName,
                                dayOfWeek = day,
                                periodNumber = index + 1,
                                timeSlot = timeSlots[index],
                                subject = sub,
                                teacherName = teacher,
                                roomNumber = "Room ${grade.shortCode}-${101 + index % 4}"
                            )
                        )
                    }
                }
            }
        }
        return result
    }

    fun generateInitialStudyMaterials(): List<StudyMaterialEntity> {
        return listOf(
            StudyMaterialEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "Mathematics",
                chapterTitle = "Quadratic Equations - Complete Formula & Concept Summary",
                chapterNumber = "Chapter 04",
                fileType = "PDF",
                fileSize = "2.8 MB",
                uploadDate = "05 Nov 2026",
                teacherName = "Dr. R. K. Sharma",
                documentContent = """
                    TRINITY GRAMMAR SCHOOL
                    SENIOR SECONDARY ACADEMIC NOTES
                    MATHEMATICS: CHAPTER 4 - QUADRATIC EQUATIONS
                    
                    1. STANDARD FORM
                    ax² + bx + c = 0 (where a ≠ 0, a, b, c are real numbers)
                    
                    2. METHODS OF SOLVING:
                    A. Factorization Method: Splitting the middle term.
                    B. Quadratic Formula (Sridharacharya Formula):
                       x = (-b ± √(b² - 4ac)) / (2a)
                       
                    3. NATURE OF ROOTS:
                    Discriminant D = b² - 4ac
                    • Case I: D > 0 -> Two distinct real roots.
                    • Case II: D = 0 -> Two equal and real roots (x = -b / 2a).
                    • Case III: D < 0 -> No real roots (imaginary roots).
                    
                    4. KEY WORD PROBLEM TYPES:
                    • Age Problems
                    • Speed, Distance & Time (Upstream/Downstream)
                    • Work and Time relations
                    • Geometry (Right triangle hypotenuse relationships)
                """.trimIndent()
            ),
            StudyMaterialEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "Science",
                chapterTitle = "Chemical Reactions & Equations - Revision Handout",
                chapterNumber = "Chapter 01",
                fileType = "PDF",
                fileSize = "3.2 MB",
                uploadDate = "04 Nov 2026",
                teacherName = "Dr. Malini Rao",
                documentContent = """
                    TRINITY GRAMMAR SCHOOL - CHEMISTRY LAB
                    CHAPTER 1: CHEMICAL REACTIONS & EQUATIONS
                    
                    1. TYPES OF CHEMICAL REACTIONS:
                    • Combination Reaction: Quick lime (CaO) + Water (H₂O) -> Slaked Lime [Ca(OH)₂] + Heat.
                    • Decomposition Reaction:
                      Thermal: 2FeSO₄(s) --Δ--> Fe₂O₃(s) + SO₂(g) + SO₃(g)
                      Photolytic: 2AgCl(s) --Sunlight--> 2Ag(s) + Cl₂(g)
                    • Displacement Reaction: Fe(s) + CuSO₄(aq) -> FeSO₄(aq) + Cu(s)
                    • Double Displacement: Na₂SO₄(aq) + BaCl₂(aq) -> BaSO₄(s) [White ppt] + 2NaCl(aq)
                    • Oxidation & Reduction (Redox): CuO + H₂ -> Cu + H₂O
                    
                    2. PREVENTING CORROSION & RANCIDITY:
                    • Galvanization, painting, greasing.
                    • Flushing potato chip packets with nitrogen gas.
                """.trimIndent()
            ),
            StudyMaterialEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "Computer",
                chapterTitle = "Python Data Structures: Lists, Tuples & Dictionaries",
                chapterNumber = "Chapter 05",
                fileType = "DOC",
                fileSize = "1.5 MB",
                uploadDate = "02 Nov 2026",
                teacherName = "Mr. David Chen",
                documentContent = """
                    TRINITY GRAMMAR SCHOOL - IT LAB
                    PYTHON DATA STRUCTURES CHEAT SHEET
                    
                    1. LISTS (Mutable, Ordered):
                    my_list = [10, 20, 30]
                    my_list.append(40)
                    my_list.sort()
                    
                    2. TUPLES (Immutable, Ordered):
                    my_tuple = (10, 20, 30)
                    
                    3. DICTIONARIES (Key-Value pairs):
                    student = {"name": "Arjun", "grade": 10, "house": "Blue Diamond"}
                    print(student.get("name"))
                """.trimIndent()
            ),
            StudyMaterialEntity(
                classGrade = ClassGrade.CLASS_9.displayName,
                subject = "Science",
                chapterTitle = "Matter in Our Surroundings - State Changes & Evaporation",
                chapterNumber = "Chapter 01",
                fileType = "PDF",
                fileSize = "2.1 MB",
                uploadDate = "03 Nov 2026",
                teacherName = "Dr. Malini Rao",
                documentContent = "Solid -> Liquid -> Gas. Factors affecting evaporation: Surface area, Temperature, Humidity, Wind speed."
            ),
            StudyMaterialEntity(
                classGrade = ClassGrade.CLASS_8.displayName,
                subject = "Mathematics",
                chapterTitle = "Linear Equations in One Variable - Algebraic Practice",
                chapterNumber = "Chapter 02",
                fileType = "PDF",
                fileSize = "1.9 MB",
                uploadDate = "01 Nov 2026",
                teacherName = "Mr. T. Srinivas",
                documentContent = "Solving equations having linear expressions on one side and numbers on the other."
            )
        )
    }

    fun generateInitialAssignments(): List<AssignmentEntity> {
        return listOf(
            AssignmentEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "Science",
                title = "Working Model Report: Clean Energy & Solar Powered Irrigation",
                description = "Prepare a 4-page handwritten or typed investigative project report covering circuit diagram, efficiency analysis, and societal benefits.",
                assignedDate = "01 Nov 2026",
                dueDate = "12 Nov 2026",
                maxMarks = 25,
                isSubmitted = false
            ),
            AssignmentEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "Social Studies",
                title = "Disaster Management & Sustainable Development Portfolio",
                description = "Case study on rainwater harvesting in urban communities and flood prevention infrastructure in coastal cities.",
                assignedDate = "03 Nov 2026",
                dueDate = "15 Nov 2026",
                maxMarks = 20,
                isSubmitted = false
            ),
            AssignmentEntity(
                classGrade = ClassGrade.CLASS_10.displayName,
                subject = "English",
                title = "Formal Letter Writing - Editorial on Traffic Safety",
                description = "Write a formal letter to the Editor of The National Chronicle about reckless driving near school zones. Follow prescribed State Board letter layout.",
                assignedDate = "04 Nov 2026",
                dueDate = "09 Nov 2026",
                maxMarks = 10,
                isSubmitted = true,
                submissionDate = "06 Nov 2026",
                studentWork = "Submitted draft via English portal. Letter addressed to Chief Editor."
            ),
            AssignmentEntity(
                classGrade = ClassGrade.CLASS_9.displayName,
                subject = "Mathematics",
                title = "Heron's Formula Application in Real Estate Surveying",
                description = "Calculate area of irregular quadrilateral fields by splitting into two triangles.",
                assignedDate = "02 Nov 2026",
                dueDate = "11 Nov 2026",
                maxMarks = 20,
                isSubmitted = false
            )
        )
    }

    fun generateInitialExamSchedules(): List<ExamScheduleEntity> {
        val class10 = ClassGrade.CLASS_10.displayName
        return listOf(
            ExamScheduleEntity(
                classGrade = class10,
                examName = "Term 1 Pre-Board Examination 2026",
                subject = "English Language & Literature",
                examDate = "01 Dec 2026",
                timing = "09:00 AM - 12:00 PM",
                syllabus = "Reading Comprehension, Formal Letters, Analytical Paragraph, First Flight Ch 1-8, Footprints Without Feet Ch 1-6",
                maxMarks = 80,
                roomNumber = "Auditorium Hall B"
            ),
            ExamScheduleEntity(
                classGrade = class10,
                examName = "Term 1 Pre-Board Examination 2026",
                subject = "Mathematics (Standard / Basic)",
                examDate = "03 Dec 2026",
                timing = "09:00 AM - 12:00 PM",
                syllabus = "Real Numbers, Polynomials, Linear Equations, Quadratic Equations, Arithmetic Progressions, Triangles, Coordinate Geometry",
                maxMarks = 80,
                roomNumber = "Auditorium Hall B"
            ),
            ExamScheduleEntity(
                classGrade = class10,
                examName = "Term 1 Pre-Board Examination 2026",
                subject = "Science (Physics, Chemistry, Biology)",
                examDate = "05 Dec 2026",
                timing = "09:00 AM - 12:00 PM",
                syllabus = "Chemical Reactions, Acids Bases & Salts, Metals & Non-metals, Life Processes, Control & Coordination, Light, Human Eye",
                maxMarks = 80,
                roomNumber = "Auditorium Hall B"
            ),
            ExamScheduleEntity(
                classGrade = class10,
                examName = "Term 1 Pre-Board Examination 2026",
                subject = "Social Science",
                examDate = "08 Dec 2026",
                timing = "09:00 AM - 12:00 PM",
                syllabus = "History Ch 1-3, Geography Ch 1-4, Political Science Ch 1-3, Economics Ch 1-3 + Complete Map Work",
                maxMarks = 80,
                roomNumber = "Auditorium Hall B"
            ),
            ExamScheduleEntity(
                classGrade = class10,
                examName = "Term 1 Pre-Board Examination 2026",
                subject = "Second Language (Hindi Course B)",
                examDate = "10 Dec 2026",
                timing = "09:00 AM - 12:00 PM",
                syllabus = "स्पर्श गद्य खंड एवं पद्य खंड (पाठ 1-5), संचयन भाग 2 (पाठ 1-2), व्यावहारिक व्याकरण एवं रचनात्मक लेखन",
                maxMarks = 80,
                roomNumber = "Auditorium Hall B"
            ),
            ExamScheduleEntity(
                classGrade = class10,
                examName = "Term 1 Pre-Board Examination 2026",
                subject = "Information Technology / Computer",
                examDate = "12 Dec 2026",
                timing = "09:00 AM - 11:00 AM",
                syllabus = "Employability Skills (Unit 1-4), Subject Specific Skills: Digital Documentation, Electronic Spreadsheet, Database Management",
                maxMarks = 50,
                roomNumber = "IT Block Lab 1 & 2"
            )
        )
    }
}
