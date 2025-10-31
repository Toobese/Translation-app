package com.example.translationapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.translationapp.domain.model.Mode
import com.google.gson.Gson
import java.io.File
import java.io.InputStreamReader

class QuizSelectionViewModel : ViewModel() {
    fun loadModes(context: Context): TranslationData {
        val file = File(context.filesDir, "kanji.json")
        val reader: InputStreamReader = if (file.exists()) {
            InputStreamReader(file.inputStream())
        } else {
            InputStreamReader(context.assets.open("kanji.json"))
        }

        val data = Gson().fromJson(reader, TranslationData::class.java)
        reader.close()
        return data
    }


    fun ensureJsonCopied(context: Context) {
        val file = File(context.filesDir, "kanji.json")
        if (!file.exists()) {
            context.assets.open("kanji.json").use { input ->
                file.outputStream().use { output -> input.copyTo(output) }
            }
            println("📂 Copied kanji.json from assets to internal storage.")
        } else {
            println("✅ kanji.json already exists in internal storage.")
        }
    }
}


data class TranslationData(
    val modes: List<Mode>
)