package com.example.translationapp.domain.model

data class Quiz(
    val name: String,
    val questionList: Map<String, String>
)