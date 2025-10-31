package com.example.translationapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.translationapp.domain.model.Quiz
import com.example.translationapp.domain.model.Word
import com.google.gson.Gson
import java.io.File
import java.io.InputStreamReader

class QuizOverViewViewModel(initialQuiz: Quiz) : ViewModel() {

    private val gson = Gson()

    var quiz by mutableStateOf(initialQuiz)
    var currentEditingWord by mutableStateOf(quiz.wordList.first())

    fun onSave(context: Context) {
        val file = File(context.filesDir, "kanji.json")

        val reader = InputStreamReader(file.inputStream())
        val appData = gson.fromJson(reader, TranslationData::class.java)
        reader.close()

        val mode = appData.modes.find { mode ->
            mode.quizzes.any { it.name == quiz.name }
        } ?: return

        val quizToUpdate = mode.quizzes.find { it.name == quiz.name } ?: return
        quizToUpdate.wordList = quiz.wordList.toMutableList()
        file.writeText(gson.toJson(appData))
        reloadQuiz(context)
    }

    fun reloadQuiz(context: Context) {
        val file = File(context.filesDir, "kanji.json")
        val reader: InputStreamReader = if (file.exists()) {
            InputStreamReader(file.inputStream())
        } else {
            InputStreamReader(context.assets.open("kanji.json"))
        }

        val data = Gson().fromJson(reader, TranslationData::class.java)
        val newQuiz = data.modes
            .flatMap { it.quizzes }
            .find { it.name == quiz.name } ?: return
        quiz = newQuiz
    }

    fun onCreateWord() { quiz = quiz.copy(wordList = quiz.wordList + Word("", "", "", "")) }
    fun onWordClick(word: Word) { currentEditingWord = word }
    fun onUpdateCurrentEditingWord(word: Word) { currentEditingWord = word }
    fun onUpdateQuizWordList(wordList: List<Word>) { quiz = quiz.copy(wordList = wordList) }
}