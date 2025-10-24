package com.example.translationapp.domain.model

data class Word(
    val question: String,
    val answer: String,
    val mnemonic: String?,
    val radicals: String?,
)