package com.solo.charadesapp.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.solo.charadesapp.R
import com.solo.charadesapp.databinding.FragmentScoreBinding
import com.solo.charadesapp.screens.game.GameViewModel


class ScoreFragment : Fragment() {

    lateinit var binding : FragmentScoreBinding
    lateinit var viewModel : ScoreViewModel
    lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )


        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).theScore)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

       // binding.scoreText.text = viewModel.score.toString()


        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer{ playAgain ->
            if(playAgain){
                viewModel.onPlayAgainComplete()
                findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
            }
        })

        binding.playAgainButton.setOnClickListener { view : View ->

            viewModel.onPlayAgain()

            //Go to game screen
            //ScoreFragmentDirections.actionScoreFragmentToGameFragment()
            //view.findNavController().navigate(ScoreFragmentDirections.ScoreFragmentToGameFragment())
            //Words and score should start from zero
        }


//        val args = ScoreFragmentArgs.fromBundle(requireArguments())
//        viewModelFactory = ScoreViewModelFactory(args.theScore)

//        val args = ScoreFragmentArgs.fromBundle(requireArguments())
//        Toast.makeText(context, "Score is ${args.theScore} , ${args.one} , ${args.two}, ${args.three}," +
//                " ${args.four} , ${args.five}" , Toast.LENGTH_SHORT).show()

        return binding.root
    }

}