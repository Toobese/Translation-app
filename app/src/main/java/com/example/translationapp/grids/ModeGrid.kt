package com.example.translationapp.grids

import TinyQuizGrid
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.translationapp.viewmodel.TranslationData
import com.google.gson.Gson

@Composable
fun ModeGrid(
    modes: TranslationData,
    onModeClick: (String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(modes.modes) { mode ->
            val (accentColor, iconRes) = when (mode.name.lowercase()) {
                "kanji" -> Color(0xFF9CCC65) to R.drawable.kanji
                "conjunctions" -> Color(0xFFB388FF) to R.drawable.conjunction
                "news segments" -> Color(0xFFFFCA28) to R.drawable.news
                "names" -> Color(0xFF4DD0E1) to R.drawable.person
                else -> Color(0xFFEDE7F6) to R.drawable.ic_launcher_background
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clickable {
                        val modeJson = Uri.encode(Gson().toJson(mode))
                        onModeClick(modeJson) },
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF1E1E1E),
                border = BorderStroke(1.dp, Color(0xFF2C2C2C))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = mode.name,
                        tint = accentColor,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(end = 16.dp)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = mode.name.uppercase(),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEDE7F6)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        TinyQuizGrid(mode)

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "${mode.quizzes.size} quizzes",
                            fontSize = 13.sp,
                            color = Color(0xFFB0AEB6)
                        )
                    }
                }
            }
        }
    }
}