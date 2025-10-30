package com.example.translationapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.translationapp.composables.TopBar
import com.example.translationapp.domain.model.Mode
import com.example.translationapp.grids.QuizGrid

@Composable
fun QuizSelectionScreen(mode: Mode, onBack: () -> Unit, onQuizClick: (String) -> Unit, onEditQuiz: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(text = mode.name, onBack = onBack)

        Spacer(modifier = Modifier.height(16.dp))

        QuizGrid(quizzes = mode.quizzes, onQuizClick = onQuizClick, onEditQuiz = onEditQuiz)
    }
}