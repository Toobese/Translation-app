package com.example.translationapp

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.translationapp.composables.TopBar
import com.example.translationapp.domain.model.Mode
import com.example.translationapp.grids.QuizGrid
import com.example.translationapp.viewmodel.TranslationData

@Composable
fun QuizSelectionScreen(mode: Mode, onLoad: (Context) -> TranslationData, onBack: () -> Unit, onQuizClick: (String) -> Unit, onEditQuiz: (String) -> Unit) {
    val modes = onLoad(LocalContext.current)
    val updatedMode = modes.modes.find { it.name == mode.name } ?: mode
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(text = updatedMode.name, onBack = onBack)

        Spacer(modifier = Modifier.height(16.dp))

        QuizGrid(quizzes = updatedMode.quizzes, onQuizClick = onQuizClick, onEditQuiz = onEditQuiz)
    }
}