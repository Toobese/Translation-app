package com.example.translationapp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import android.os.Bundle
import androidx.compose.material3.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.example.translationapp.viewmodel.QuizSelectionViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class ModeSelectionScreen : ComponentActivity() {

    private val viewModel: QuizSelectionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
            val darkBackground = Color(0xFF121212)

            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = darkBackground,
                    darkIcons = false
                )
                systemUiController.setNavigationBarColor(
                    color = darkBackground,
                    darkIcons = false
                )
            }
            MaterialTheme {
                AppNavHost(viewModel = viewModel, darkBackground = darkBackground)
            }
        }
    }
}