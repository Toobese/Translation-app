package com.example.translationapp.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun Bubble(
    text: String,
    colors: BubbleColors,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    Row(
        Modifier
            .border(BorderStroke(1.dp, colors.outlineColor), CircleShape)
            .background(colors.containerColor, CircleShape)
            .then(modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text,
            color = colors.contentColor,
            maxLines = 1,
            style = textStyle,
        )
    }
}

data class BubbleColors(
    val containerColor: Color,
    val contentColor: Color,
    val outlineColor: Color,
)