package com.example.translationapp.domain.model

data class Quiz(
    val name: String,
    var wordList: List<Word>,
    val highScore: Float,
)