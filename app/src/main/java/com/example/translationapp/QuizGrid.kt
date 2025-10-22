package com.example.translationapp

import com.example.translationapp.domain.model.Quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translationapp.ui.components.ScoreDisplay

@Composable
fun QuizGrid(
    quizzes: List<Quiz>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(quizzes) { quiz ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
//                    .clickable { onModeClick(mode.name) },
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF1E1E1E),
                border = BorderStroke(1.dp, Color(0xFF2C2C2C))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = quiz.name.uppercase(),
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEDE7F6)
                        )

                        Text(
                            text = "${quiz.wordList.size} words",
                            fontSize = 16.sp,
                            color = Color(0xFFB0AEB6)
                        )
                    }
                    ScoreDisplay(quiz.highScore)
                }
            }
        }
    }
}