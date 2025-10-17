package com.example.translationapp.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translationapp.domain.model.Mode
import com.example.translationapp.ui.common.Bubble
import com.example.translationapp.ui.common.BubbleColors

@Composable
fun GroupGrip(
    mode: Mode,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(mode.groups) { group ->
            Bubble(
                group.name,
                colors =
                    BubbleColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        outlineColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
            )
        }
    }
}