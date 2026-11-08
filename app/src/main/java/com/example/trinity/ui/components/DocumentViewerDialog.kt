package com.example.trinity.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.trinity.viewmodel.DocumentViewerData
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.TrinityDanger
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark
import com.example.ui.theme.TrinityRoyalBlue
import com.example.ui.theme.TrinitySuccess
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary

@Composable
fun DocumentViewerDialog(
    data: DocumentViewerData,
    onDismiss: () -> Unit,
    onDownloadNotification: (String) -> Unit
) {
    var isDownloaded by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .testTag("document_viewer_dialog"),
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFFF8FAFC)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Top Action Bar
                Surface(
                    color = TrinityNavy,
                    contentColor = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PictureAsPdf,
                                contentDescription = null,
                                tint = TrinityDanger,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = data.title,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    maxLines = 1
                                )
                                Text(
                                    text = "${data.fileType} Document • ${data.subtitle}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color.White.copy(alpha = 0.8f)
                                    )
                                )
                            }
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier.testTag("close_document_viewer")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Document",
                                tint = Color.White
                            )
                        }
                    }
                }

                // Document Content Sheet
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Formal Academic Letterhead
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            // Crest & School Name Header
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                        .background(TrinityNavyDark)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.img_trinity_crest),
                                        contentDescription = "Trinity Crest",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "TRINITY GRAMMAR SCHOOL",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontFamily = CinzelFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            color = TrinityNavy
                                        )
                                    )
                                    Text(
                                        text = "State Syllabus School • State Board (SSC) • Academic Session 2026-27",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TrinityTextSecondary
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            HorizontalDivider(thickness = 2.dp, color = TrinityGold)
                            Spacer(modifier = Modifier.height(14.dp))

                            // Meta details
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Instructor: ${data.author}",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = TrinityRoyalBlue
                                    )
                                )
                                Text(
                                    text = "Date: ${data.date}",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = TrinityTextSecondary
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Document Body Text
                            Surface(
                                color = Color(0xFFFAFAFA),
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = data.content,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontFamily = FontFamily.Monospace,
                                            lineHeight = 22.sp,
                                            color = TrinityTextPrimary
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            HorizontalDivider(color = Color(0xFFE2E8F0))
                            Spacer(modifier = Modifier.height(12.dp))

                            // Official Stamp Signature
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = "Approved by Academic Directorate",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TrinityTextSecondary,
                                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                        )
                                    )
                                    Text(
                                        text = "TRINITY GRAMMAR SCHOOL",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = TrinityNavy
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                // Bottom Action Buttons
                Surface(
                    color = Color.White,
                    shadowElevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {
                                isDownloaded = true
                                onDownloadNotification("Downloaded ${data.title} to storage.")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isDownloaded) TrinitySuccess else TrinityNavy
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("download_pdf_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Download,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (isDownloaded) "Saved to Device" else "Download PDF")
                        }

                        OutlinedButton(
                            onClick = {
                                onDownloadNotification("Sharing ${data.title}...")
                            },
                            modifier = Modifier.testTag("share_doc_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Share")
                        }
                    }
                }
            }
        }
    }
}
