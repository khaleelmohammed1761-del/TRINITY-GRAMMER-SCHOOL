package com.example.trinity.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.TrinityGold
import com.example.ui.theme.TrinityLightBlue
import com.example.ui.theme.TrinityNavy
import com.example.ui.theme.TrinityNavyDark
import com.example.ui.theme.TrinityTextPrimary
import com.example.ui.theme.TrinityTextSecondary

@Composable
fun AdminPinDialog(
    onDismiss: () -> Unit,
    onVerifyPin: (String) -> Boolean
) {
    var pin by remember { mutableStateOf("") }
    var hasError by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("admin_pin_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Gray
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(TrinityLightBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = TrinityNavy,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Teacher & Admin Portal",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TrinityNavy
                    )
                )

                Text(
                    text = "Enter staff security PIN to upload homework, notices, and class materials",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TrinityTextSecondary
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = pin,
                    onValueChange = {
                        if (it.length <= 6) {
                            pin = it
                            hasError = false
                        }
                    },
                    label = { Text("Security PIN") },
                    placeholder = { Text("1234") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    singleLine = true,
                    isError = hasError,
                    supportingText = {
                        if (hasError) {
                            Text("Incorrect PIN. Please try again.")
                        } else {
                            Text("Default Staff PIN: 1234")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("admin_pin_input")
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val success = onVerifyPin(pin)
                        if (!success) {
                            hasError = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = TrinityNavy),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("verify_pin_button")
                ) {
                    Text("Unlock Staff Area")
                }
            }
        }
    }
}
