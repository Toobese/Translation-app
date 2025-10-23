package com.example.translationapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.translationapp.domain.model.Quiz
import com.example.translationapp.ui.common.Keyboard
import com.example.translationapp.viewmodel.QuizViewModel

@Composable
fun QuizScreen(quiz: Quiz, onBack: () -> Unit) {
    val viewModel = remember { QuizViewModel(quiz) }

    val currentWord = viewModel.currentWord.value
    val userInput = viewModel.userInput.value
    val progress = viewModel.progress

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔙 Back button
        TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
            Text("← Back", color = Color(0xFFE8D9FF))
        }

        Spacer(modifier = Modifier.height(24.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = Color(0xFFB388FF),
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )

        Spacer(modifier = Modifier.height(40.dp))

        if (currentWord != null) {
            Text(
                text = currentWord.question,
                style = MaterialTheme.typography.displaySmall,
                color = Color(0xFFB388FF)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 🌸 Make the input wider and visually centered
            OutlinedTextField(
                value = userInput,
                onValueChange = {},
                readOnly = false,
                label = { Text("Your answer") },
                textStyle = MaterialTheme.typography.bodyLarge,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.9f) // wider field (90% of screen)
                    .height(60.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFB388FF),
                    unfocusedBorderColor = Color(0xFFB0AEB6),
                    focusedLabelColor = Color(0xFFB388FF),
                    unfocusedLabelColor = Color(0xFFB0AEB6),
                    cursorColor = Color(0xFFB388FF),
                    focusedTextColor = Color(0xFFB388FF),
                    unfocusedTextColor = Color(0xFFE8D9FF)
                )
            )

        } else {
            // 🎉 Quiz finished
            Text(
                text = "Quiz Complete!",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFFFFD700)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Score: ${(viewModel.progress * 100).toInt()}%",
                color = Color(0xFFE8D9FF)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.resetQuiz() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4DD0E1))
            ) {
                Text("Restart")
            }
        }

        Keyboard()
    }
}