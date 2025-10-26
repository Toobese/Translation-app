package com.example.translationapp.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun EditBox(userInput: String, onValueChange: (String) -> Unit, fontSize: Int, color: Color, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    BasicTextField(
        value = userInput,
        onValueChange = { onValueChange(it) },
        interactionSource = interactionSource,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            textAlign = TextAlign.Center,
            fontSize = fontSize.sp,
            color = if (isFocused) Color(0xFFB388FF) else color
        ),
        cursorBrush = SolidColor(Color(0xFFB388FF)),
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
    )
}