package com.example.translationapp

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.translationapp.viewmodel.QuizSelectionViewModel

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
                    onModeClick = { modeName ->
                        navController.navigate("modeSelectionContent/$modeName")
                    }
                )
            }

            composable(
                route = "modeSelectionContent/{modeName}",
                arguments = listOf(navArgument("modeName") { type = NavType.StringType })
            ) { backStackEntry ->
                val modeName = backStackEntry.arguments?.getString("modeName") ?: ""
                GroupSelectionScreen(modeName = modeName, onBack = onBack)
            }
        }
    }
}