package com.example.translationapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.translationapp.domain.model.Mode
import com.google.gson.Gson
import java.io.InputStreamReader

class QuizSelectionViewModel : ViewModel() {
    fun loadModes(context: Context): TranslationData {
        val input = context.assets.open("kanji.json")
        val reader = InputStreamReader(input)
        val data = Gson().fromJson(reader, TranslationData::class.java)
        reader.close()
        return data
    }
}


data class TranslationData(
    val modes: List<Mode>
)