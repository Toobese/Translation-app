package com.example.translationapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.translationapp.grids.ModeGrid
import com.example.translationapp.viewmodel.QuizSelectionViewModel

@Composable
fun ModeSelectionContent(
    viewModel: QuizSelectionViewModel,
    onModeClick: (String) -> Unit
) {
    viewModel.ensureJsonCopied(LocalContext.current)
    val modes = viewModel.loadModes(LocalContext.current)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Modes",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE8D9FF)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ModeGrid(
            modes = modes,
            onModeClick = onModeClick
        )
    }
}