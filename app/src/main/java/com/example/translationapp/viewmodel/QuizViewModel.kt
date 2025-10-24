package com.example.translationapp.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.translationapp.domain.model.Quiz
import com.example.translationapp.domain.model.Word

class QuizViewModel(quiz: Quiz) : ViewModel() {
    private val allWords = quiz.wordList + quiz.wordList // each word appears twice
    private val remainingWords = allWords.toMutableList()

    var userInput = mutableStateOf("")
    var correctCount = mutableIntStateOf(0)
    var totalCount = allWords.size
    var currentWord = mutableStateOf<Word?>(null)
    var previousWord = mutableStateOf<Word?>(null)
    var wasCorrect = mutableStateOf(false)

    val progress: Float
        get() = if (totalCount > 0)
            correctCount.intValue.toFloat() / totalCount
        else 0f

    init { pickRandomWord() }
    fun pickRandomWord() { if (remainingWords.isNotEmpty()) currentWord.value = remainingWords.random() else currentWord.value = null }
    fun onInputChange(newValue: Char) { userInput.value += newValue }
    fun onBackSpace() { userInput.value = userInput.value.dropLast(1)  }

    fun checkAnswer() {
        val word = currentWord.value ?: return
        val answer = userInput.value.trim()

        if (answer.equals(word.answer, ignoreCase = true)) {
            correctCount.intValue++
            remainingWords.remove(word)
            wasCorrect.value = true
        } else { wasCorrect.value = false }
        userInput.value = ""
        previousWord.value = currentWord.value
        if (remainingWords.isNotEmpty()) { currentWord.value = remainingWords.random() } else {
            currentWord.value = null
        }
    }

    fun autoFillAnswer() {
        val word = currentWord.value ?: return
        correctCount.intValue++
        remainingWords.remove(word)
        previousWord.value = currentWord.value
        wasCorrect.value = true
        currentWord.value = remainingWords.random()
    }

    fun resetQuiz() {
        remainingWords.clear()
        remainingWords.addAll(allWords)
        correctCount.intValue = 0
        userInput.value = ""
        previousWord.value = null
        pickRandomWord()
    }
}