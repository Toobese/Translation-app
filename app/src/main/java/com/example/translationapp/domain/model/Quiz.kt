package com.example.translationapp.domain.model

data class Quiz(
    val name: String,
    val wordList: List<Word>,
    val highScore: Float,
)