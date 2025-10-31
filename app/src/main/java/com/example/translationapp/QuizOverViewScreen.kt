package com.example.translationapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.translationapp.composables.TopBar
import com.example.translationapp.domain.model.Quiz
import com.example.translationapp.grids.AnswerQuestionGrid
import com.example.translationapp.viewmodel.QuizOverViewViewModel

@Composable
fun QuizOverViewScreen(quiz: Quiz, onBack: () -> Unit) {
    val viewModel = remember { QuizOverViewViewModel(quiz) }
//    viewModel.reloadQuiz(LocalContext.current)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(text = quiz.name, onBack = onBack)

        Spacer(modifier = Modifier.height(16.dp))

        AnswerQuestionGrid(
            quiz = viewModel.quiz,
            currentEditingWord = viewModel.currentEditingWord,
            onSave = viewModel::onSave,
            onWordClick = viewModel::onWordClick,
            onUpdateCurrentEditingWord = viewModel::onUpdateCurrentEditingWord,
            onCreateWord = viewModel::onCreateWord
        )
    }
}