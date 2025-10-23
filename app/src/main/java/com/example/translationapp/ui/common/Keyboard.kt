package com.example.translationapp.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun Keyboard(
    onKeyPress: (Char) -> Unit = {},
    onBackspace: () -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    val letterRows = listOf(
        listOf('Q','W','E','R','T','Y','U','I','O','P'),
        listOf('A','S','D','F','G','H','J','K','L'),
        listOf('Z','X','C','V','B','N','M',','),
        listOf('←',' ', '✓')
    )

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val keyWidth = screenWidth / 12.5f
    val keyHeight = keyWidth * 1.2f
    val horizontalSpacing = 5.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .padding(bottom = 55.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        letterRows.forEachIndexed { index, row ->
            // Subtle stagger offsets (like real QWERTY)
            val rowOffset = when (index) {
                1 -> keyWidth / 2.2f      // second row (A) shifts right
                2 -> keyWidth * 1.1f      // third row (Z) shifts more
                3 -> 0.dp                 // bottom stays centered
                else -> 0.dp
            }

            Row(
                modifier = Modifier
                    .padding(start = rowOffset)
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { key ->
                    val width = when (key) {
                        ' ' -> keyWidth * 6.5f
                        '←' -> keyWidth * 1.8f
                        '✓' -> keyWidth * 1.8f
                        else -> keyWidth
                    }

                    KeyButton(
                        key = key,
                        keyWidth = width,
                        keyHeight = keyHeight,
                        onClick = when (key) {
                            '←' -> onBackspace
                            '✓' -> onSubmit
                            else -> { { onKeyPress(key) } }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun KeyButton(
    key: Char,
    keyWidth: Dp,
    keyHeight: Dp,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(keyWidth)
            .height(keyHeight)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFF2C2C2C),
        border = BorderStroke(1.dp, Color(0xFF3C3C3C))
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = when (key) {
                    ' ' -> ""
                    else -> key.toString()
                },
                fontSize = if (key == ' ') 14.sp else 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFE8D9FF)
            )
        }
    }
}