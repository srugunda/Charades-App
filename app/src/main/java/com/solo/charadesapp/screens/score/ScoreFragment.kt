package com.solo.charadesapp.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.solo.charadesapp.R
import com.solo.charadesapp.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    lateinit var binding : FragmentScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )

        val args = ScoreFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(context, "Score is ${args.theScore} , ${args.one} , ${args.two}, ${args.three}," +
                " ${args.four} , ${args.five}" , Toast.LENGTH_SHORT).show()

        return binding.root
    }

}