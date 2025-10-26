package com.example.translationapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScoreDisplay(
    score: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = getScoreColor(score),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${(score * 100).toInt()}%",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

fun getScoreColor(score: Float): Color {
    return when {
        score >= 1.0f -> Color(0xFFB388FF)
        score >= 0.8f -> Color(0xFF4CAF50)
        score >= 0.5f -> Color(0xFFFFA726)
        score == 0.0f -> Color(0xFF3A3A3A)
        else -> Color(0xFFE53935)
    }
}