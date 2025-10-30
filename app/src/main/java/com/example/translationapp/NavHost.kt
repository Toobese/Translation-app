package com.example.translationapp

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.translationapp.domain.model.Mode
import com.example.translationapp.domain.model.Quiz
import com.example.translationapp.viewmodel.QuizSelectionViewModel
import com.google.gson.Gson

@Composable
fun AppNavHost(
    viewModel: QuizSelectionViewModel,
    modifier: Modifier = Modifier,
    darkBackground: Color = Color(0xFF121212)
) {
    val navController = rememberNavController()

    val onBack: () -> Unit = {
        if (navController.previousBackStackEntry != null) {
            navController.popBackStack()
        }
    }

    Surface(
        modifier = modifier,
        color = darkBackground
    ) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                ModeSelectionContent(
                    viewModel = viewModel,
                    onModeClick = { modeJson ->
                        navController.navigate("quizSelection/$modeJson")
                    }
                )
            }

            composable(
                route = "quizSelection/{modeJson}",
                arguments = listOf(navArgument("modeJson") { type = NavType.StringType })
            ) { backStackEntry ->
                val modeJson = backStackEntry.arguments?.getString("modeJson")
                val mode = Gson().fromJson(modeJson, Mode::class.java)
                QuizSelectionScreen(
                    mode = mode,
                    onBack = onBack,
                    onEditQuiz = { quizJson ->
                        navController.navigate("quizOverViewScreen/$quizJson")
                    },
                    onQuizClick = { quizJson ->
                        navController.navigate("quizScreen/$quizJson")
                    }
                )
            }

            composable(
                route = "quizOverViewScreen/{quizJson}",
                arguments = listOf(navArgument("quizJson") { type = NavType.StringType })
            ) { backStackEntry ->
                val quizJson = backStackEntry.arguments?.getString("quizJson")
                val quiz = Gson().fromJson(quizJson, Quiz::class.java)
                QuizOverViewScreen(
                    quiz = quiz,
                    onBack = onBack,
                )
            }

            composable(
                route = "quizScreen/{quizJson}",
                arguments = listOf(navArgument("quizJson") { type = NavType.StringType })
                ) { backStackEntry ->
                val quizJson = backStackEntry.arguments?.getString("quizJson")
                val quiz = Gson().fromJson(quizJson, Quiz::class.java)
                QuizScreen(quiz = quiz, onBack = onBack)
            }
        }
    }
}