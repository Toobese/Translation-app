package com.example.translationapp.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnswerBox(userInput: String) {
    OutlinedTextField(
        value = userInput,
        onValueChange = {},
        readOnly = true,
        label = { Text("Your answer") },
        textStyle = MaterialTheme.typography.bodyLarge,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFB388FF),
            unfocusedBorderColor = Color(0xFFB388FF),
            focusedLabelColor = Color(0xFFB388FF),
            unfocusedLabelColor = Color(0xFFB388FF),
            cursorColor = Color(0xFFB388FF),
            focusedTextColor = Color(0xFFB388FF),
            unfocusedTextColor = Color(0xFFB388FF)
        )
    )
}