package com.solo.charadesapp.screens.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
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

        //Observe
        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        viewModel.wordIndex.observe(viewLifecycleOwner, Observer { newWordIndex ->
            //binding.scoreText.text = newScore.toString()
            binding.theWordText.text = viewModel.wordList[newWordIndex]
        })

        viewModel.gameFinishedEvent.observe(viewLifecycleOwner, Observer<Boolean> { hasFinished ->
            if (hasFinished)
                gameIsFinished()
        })

        //click listeners
        binding.gotItButton.setOnClickListener {
            viewModel.onCorrect()
            //updateWordText()
            //updateScoreText()
        }

        binding.skipText.setOnClickListener{
            viewModel.onIncorrect()
            //updateWordText()
            //updateScoreText()
        }

       binding.endGameText.setOnClickListener{view : View ->
            view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(
                viewModel.score.value!!,1,2,3,4,5))  //assert non null otherwise throw exception
        }

        return binding.root
    }

    private fun gameIsFinished(){
        Toast.makeText(context, "Words are done", Toast.LENGTH_SHORT).show()
        viewModel.onGameFinishedComplete()
        findNavController(this).navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(
            viewModel.score.value!!,1,2,3,4,5))

    }

    private fun updateWordText(){
        binding.theWordText.text = viewModel.wordList[viewModel.wordIndex.value?:0]  //elvis operator
    }

    private fun updateScoreText(){
        binding.scoreText.text = viewModel.score.toString()
    }

}