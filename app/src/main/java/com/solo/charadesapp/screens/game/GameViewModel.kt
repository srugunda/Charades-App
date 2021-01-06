package com.solo.charadesapp.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    var wordIndex=0
    var score=0
    val wordList = mutableListOf("guitar", "car", "tree", "comb", "phone", "ice cream", "dreaming",
        "cat", "railway", "change", "calendar", "sad")

    init {
        Log.i("GameViewModel", "GameViewModel created!")
    }

    public fun onCorrect(){
        wordIndex++
        score++
    }

    public fun onIncorrect(){
        wordIndex++
        score--
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}