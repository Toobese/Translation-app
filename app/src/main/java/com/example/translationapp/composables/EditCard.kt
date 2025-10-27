package com.example.translationapp.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.translationapp.domain.model.Word

@Composable
fun EditCard(word: Word, onWordChange: (Word) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp)
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFF1E1E1E),
        border = BorderStroke(1.dp, Color(0xFF2C2C2C))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            EditBox(
                userInput = word.question,
                onValueChange = { onWordChange(word.copy(question = it)) },
                fontSize = 38,
                color = Color(0xFFE0E0E0),
                modifier = Modifier.height(60.dp),
            )

            EditBox(
                userInput = word.answer,
                onValueChange = { onWordChange(word.copy(answer = it)) },
                fontSize = 20,
                color = Color(0xFFE0E0E0),
                modifier = Modifier.height(30.dp),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EditBox(
                    userInput = word.mnemonic ?: "",
                    onValueChange = { onWordChange(word.copy(mnemonic = it)) },
                    fontSize = 14,
                    color = Color(0xFF888888),
                    modifier = Modifier.height(20.dp),
                )

                EditBox(
                    userInput = word.radicals ?: "",
                    onValueChange = { onWordChange(word.copy(radicals = it)) },
                    fontSize = 13,
                    color = Color(0xFF888888),
                    modifier = Modifier.height(20.dp),
                )
            }
        }
    }
}