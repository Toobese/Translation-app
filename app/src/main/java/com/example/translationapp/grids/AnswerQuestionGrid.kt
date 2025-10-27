package com.example.translationapp.grids

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translationapp.composables.EditCard
import com.example.translationapp.domain.model.Quiz
import com.example.translationapp.domain.model.Word

@Composable
fun AnswerQuestionGrid(
    quiz: Quiz,
    onSave: (List<Word>, Context) -> Unit
) {
    val context = LocalContext.current
    var words by remember { mutableStateOf(quiz.wordList + Word("", "", "", "")) }
//    words += Word("", "", "", "")

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(50.dp),
        color = Color(0xFFB388FF),
        onClick = { onSave(words, context) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Save Quiz",
                fontSize = 20.sp,
                color = Color(0xFFE0E0E0)
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
    EditCard(
        word = Word("", "", "", ""),
        onWordChange = {
                updatedWord ->
            words = words.toMutableList().also { it[words.size - 1] = updatedWord }
        }
    )

    TinyWordGrid(words)
}