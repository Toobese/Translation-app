package com.example.translationapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translationapp.R
import com.example.translationapp.ui.components.ScoreDisplay
import com.example.translationapp.ui.components.getScoreColor

@Composable
fun TopBar(text: String, onBack: () -> Unit, modifier: Modifier = Modifier, score: Float? = null) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "Back",
                tint = Color(0xFFE8D9FF),
                modifier = Modifier.size(50.dp)
            )
        }

        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.displayMedium,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE8D9FF)
        )

        score?.let { score ->
            Text(
                text = "${(score * 100).toInt()}%",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = getScoreColor(score),
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}