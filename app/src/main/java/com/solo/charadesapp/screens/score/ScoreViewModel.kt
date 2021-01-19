package com.solo.charadesapp.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(val finalScore : Int) : ViewModel() {

    //lateinit var myScore : MutableLiveData<Int>
    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    var _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain : LiveData<Boolean>
        get() = _eventPlayAgain

    // The final score
    //var score = finalScore

    init{
        Log.i("ScoreViewModel", " ")
        _score.value = finalScore
    }

    fun onPlayAgain(){
        _eventPlayAgain.value = true
    }

    fun onPlayAgainComplete(){
        _eventPlayAgain.value = false
    }


}