package com.example.translationapp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.translationapp.viewmodel.QuizSelectionViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: QuizSelectionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val modes = viewModel.loadModes(this)
//        val example = quizzes.Kanji["N5"]?.get("group1")?.get("水")

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        TopBar("Modes")

                        Spacer(modifier = Modifier.height(16.dp))

                        ModeGrid(modes)
                    }
                }
            }
        }
    }
}