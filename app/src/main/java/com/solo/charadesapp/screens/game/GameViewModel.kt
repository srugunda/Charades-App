package com.solo.charadesapp.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private val _wordIndex = MutableLiveData<Int>()
    val wordIndex : LiveData<Int>
        get() = _wordIndex

    private var _gameFinishedEvent = MutableLiveData<Boolean>()
    val gameFinishedEvent : LiveData<Boolean>
        get() = _gameFinishedEvent


    val wordList = mutableListOf("guitar", "car", "tree", "comb", "phone", "ice cream", "dreaming",
        "cat", "railway", "change", "calendar", "sad")

    init {
        Log.i("GameViewModel", "GameViewModel created!")
        _score.value=0
        _wordIndex.value=0
    }

    public fun onCorrect(){
        _score.value = (score.value)?.plus(1)

        if(_wordIndex.value!! < wordList.size-1) {
            _wordIndex.value = (wordIndex.value)?.plus(1)
        } else{
            _gameFinishedEvent.value = true
        }
    }

    public fun onIncorrect(){
        _score.value = (score.value)?.minus(1)
        if(_wordIndex.value!! < wordList.size-1) {
            _wordIndex.value = (wordIndex.value)?.plus(1)
        }else{
            _gameFinishedEvent.value = true
        }
    }

    fun onGameFinishedComplete(){
        _gameFinishedEvent.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}