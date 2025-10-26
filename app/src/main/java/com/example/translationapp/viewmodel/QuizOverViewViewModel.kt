package com.example.translationapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.translationapp.domain.model.Quiz
import com.example.translationapp.domain.model.Word
import com.google.gson.Gson
import java.io.File
import java.io.InputStreamReader

class QuizOverViewViewModel(quiz: Quiz) : ViewModel() {
    private val name = quiz.name
    private val gson = Gson()

    var quiz = quiz

    fun onSave(updatedWords: List<Word>, context: Context) {
        val file = File(context.filesDir, "kanji.json")

        val reader = InputStreamReader(file.inputStream())
        val appData = gson.fromJson(reader, TranslationData::class.java)
        reader.close()

        val mode = appData.modes.find { mode ->
            mode.quizzes.any { it.name == name }
        } ?: return

        val quizToUpdate = mode.quizzes.find { it.name == name } ?: return
        quizToUpdate.wordList = updatedWords.toMutableList()
        file.writeText(gson.toJson(appData))
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
}