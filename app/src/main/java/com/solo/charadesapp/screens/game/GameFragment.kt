package com.solo.charadesapp.screens.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.solo.charadesapp.R
import com.solo.charadesapp.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    lateinit var binding : FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

//        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
//            R.layout.fragment_game,container,false)

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game, container, false)

        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        //binding.theWordText.text = wordList.get(index)  //show first word
        updateWordText() //at index 0
        //binding.scoreText.text = score.toString()
        updateScoreText() //starting with 0

        binding.gotItButton.setOnClickListener {
            viewModel.onCorrect()
            updateWordText()
            updateScoreText()
        }

        binding.skipText.setOnClickListener{
            viewModel.onIncorrect()
            updateWordText()
            updateScoreText()
        }

        return binding.root
    }

    private fun updateWordText(){
        binding.theWordText.text = viewModel.wordList[viewModel.wordIndex]
    }

    private fun updateScoreText(){
        binding.scoreText.text = viewModel.score.toString()
    }



}