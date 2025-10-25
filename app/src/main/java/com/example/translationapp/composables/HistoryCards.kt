package com.example.translationapp.composables

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translationapp.R
import com.example.translationapp.domain.model.Word
import com.google.gson.Gson

@Composable
fun HistoryCards(words: List<Word>, onClickInCorrect: (Word) -> Unit) {
    Column(modifier = Modifier.width(30.dp).fillMaxHeight()) {
        Box(
            modifier = Modifier.width(30.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.history),
                contentDescription = "inCorrectHistory",
                tint = Color(0xFFE53935),
                modifier = Modifier.size(30.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .width(30.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(words) { word ->
                HistoryCard(word, onClickInCorrect)
            }
        }
    }
}

@Composable
fun HistoryCard(word: Word, onClickInCorrect: (Word) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFF1E1E1E),
        border = BorderStroke(1.dp, Color(0xFFE53935))
    ) {
        Box(
            modifier = Modifier.fillMaxSize().clickable { onClickInCorrect(word) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = word.question,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE53935)
            )
        }
    }
}