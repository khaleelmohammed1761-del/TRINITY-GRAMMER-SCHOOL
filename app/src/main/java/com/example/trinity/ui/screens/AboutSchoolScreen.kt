package com.example.trinity.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark
import com.example.ui.theme.TrinityRoyalBlue
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary

@Composable
fun AboutSchoolScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FC))
            .testTag("about_school_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Crest & Header
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = TrinityNavy),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(TrinityNavyDark)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_trinity_crest),
                            contentDescription = "Trinity Crest",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "TRINITY GRAMMAR SCHOOL",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            letterSpacing = 1.sp
                        )
                    )

                    Text(
                        text = "Knowledge • Character • Excellence",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = TrinityGold,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "State Syllabus School • Recognized by State Board (SSC) • Est. 1988",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    )
                }
            }
        }

        // Principal's Desk
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = TrinityLightBlue,
                            shape = CircleShape,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = null,
                                    tint = TrinityRoyalBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "From the Principal's Desk",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TrinityNavy
                                )
                            )
                            Text(
                                text = "Dr. Margaret Thornton, Ph.D. (Ed.)",
                                style = MaterialTheme.typography.labelSmall.copy(color = TrinityRoyalBlue)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "\"At Trinity Grammar School, we believe every child is born with limitless potential. Our mission is to balance rigorous academic excellence with compassion, moral fortitude, and scientific inquiry. We provide a stimulating sanctuary where curiosity flourishes and young learners grow into visionary citizens.\"",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TrinityTextSecondary,
                            lineHeight = 22.sp,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                    )
                }
            }
        }

        // Campus Highlights
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "World-Class Campus Facilities",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TrinityNavy
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    FacilityItem(
                        title = "Smart Interactive Classrooms",
                        desc = "Equipped with multimedia projectors, touch panels, and acoustic sound."
                    )
                    FacilityItem(
                        title = "Advanced STEM & Robotics Labs",
                        desc = "Equipped with 3D printers, IoT kits, physics optics benches, and chemical hoods."
                    )
                    FacilityItem(
                        title = "Centennial Digital Library",
                        desc = "Over 25,000 reference volumes, peer-reviewed journals, and e-learning terminals."
                    )
                    FacilityItem(
                        title = "Olympic Grade Sports Complex",
                        desc = "Synthetic running track, swimming pool, indoor badminton, and basketball courts."
                    )
                }
            }
        }

        // Daily Bell Timings
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = TrinityNavy,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Daily School Timings",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TrinityNavy
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    TimingRow("Morning Assembly", "08:15 AM - 08:30 AM")
                    TimingRow("Periods 1 - 3", "08:30 AM - 11:00 AM")
                    TimingRow("Nutrition Recess", "11:00 AM - 11:20 AM")
                    TimingRow("Periods 4 - 5", "11:20 AM - 12:50 PM")
                    TimingRow("Lunch Break", "12:50 PM - 01:25 PM")
                    TimingRow("Periods 6 - 7 & Clubs", "01:25 PM - 02:50 PM")
                    TimingRow("Daily Dispersal", "03:00 PM")
                }
            }
        }

        // Contact Information
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Campus Contact & Administration",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TrinityNavy
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    ContactRow(
                        icon = Icons.Default.LocationOn,
                        label = "Campus Address",
                        value = "Trinity Grammar School Campus, Jubilee Hills Road No. 36, Hyderabad - 500033"
                    )

                    ContactRow(
                        icon = Icons.Default.Phone,
                        label = "Phone Enquiries",
                        value = "+91 (040) 2345-6789 / +91 98490-12345"
                    )

                    ContactRow(
                        icon = Icons.Default.Email,
                        label = "Email Address",
                        value = "admissions@trinitygrammar.edu.in / info@trinitygrammar.edu.in"
                    )
                }
            }
        }
    }
}

@Composable
private fun FacilityItem(title: String, desc: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = TrinityGold,
            modifier = Modifier
                .size(16.dp)
                .padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityTextPrimary
                )
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall.copy(color = TrinityTextSecondary)
            )
        }
    }
}

@Composable
private fun TimingRow(event: String, time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = event,
            style = MaterialTheme.typography.bodyMedium.copy(color = TrinityTextPrimary)
        )
        Text(
            text = time,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TrinityRoyalBlue
            )
        )
    }
}

@Composable
private fun ContactRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TrinityNavy,
            modifier = Modifier
                .size(18.dp)
                .padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TrinityTextSecondary
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = TrinityTextPrimary
                )
            )
        }
    }
}
