package com.example.translationapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translationapp.composables.AnswerBox
import com.example.translationapp.composables.AnswerCard
import com.example.translationapp.composables.HistoryCards
import com.example.translationapp.composables.ProgressBar
import com.example.translationapp.composables.TopBar
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
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        TopBar(text = quiz.name, onBack = onBack, score = viewModel.score)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProgressBar(progress)
            Spacer(modifier = Modifier.height(40.dp))

            if (currentWord != null) {
                Text(
                    text = currentWord.question,
                    fontSize = 50.sp,
                    style = MaterialTheme.typography.displaySmall,
                    color = Color(0xFFB388FF)
                )
                Spacer(modifier = Modifier.height(40.dp))
                AnswerBox(userInput)
                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.height(150.dp)
                ) {
                    if(viewModel.inCorrectHistory.value.isNotEmpty()) HistoryCards(viewModel.inCorrectHistory.value, viewModel::showInCorrectAnswer)
                    AnswerCard(viewModel.previousWord.value, viewModel.wasCorrect.value)
                }
            } else {
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
        }
        if (currentWord != null) Keyboard(onKeyPress = viewModel::onInputChange, onBackSpace = viewModel::onBackSpace, onSubmit = viewModel::checkAnswer)
    }
}