package com.example.translationapp.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translationapp.domain.model.Word

@Composable
fun AnswerCard(previousWord: Word?, correct: Boolean) {
    val mainColor = when {
        previousWord == null -> Color(0xFF2C2C2C)
        correct -> Color(0xFFB388FF)
        else -> Color(0xFFE53935)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp)
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFF1E1E1E),
        border = BorderStroke(1.dp, mainColor)
    ) {
        previousWord?.let { word ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = word.question,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = mainColor
                )

                Text(
                    text = word.answer,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = word.mnemonic,
                        color = Color(0xFFB0AEB6),
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (word.radicals.isNotBlank()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = word.radicals,
                            color = Color(0xFFB0AEB6),
                            fontSize = 13.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }
}