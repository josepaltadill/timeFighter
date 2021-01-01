package com.raywenderlich.timefighter.ui.time_fighter

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.raywenderlich.timefighter.R
import com.raywenderlich.timefighter.databinding.TimeFighterFragmentBinding

class TimeFighterFragment : Fragment() {

    private lateinit var binding: TimeFighterFragmentBinding

    companion object {
        fun newInstance() = TimeFighterFragment()
        private val TAG = TimeFighterFragment::class.java.simpleName
    }

    private lateinit var viewModel: TimeFighterViewModel

    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 20000
    internal val countDownInterval: Long = 1000
    internal var gameStarted = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = TimeFighterFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TimeFighterViewModel::class.java)
        // TODO: Use the ViewModel

        activity?.let {
            binding.tapMeButton.setOnClickListener { view ->
                val bounceAnimation = AnimationUtils.loadAnimation(it,R.anim.bounce)
                view.startAnimation(bounceAnimation)
                incrementScore()
            }
        }
        Log.d(TAG, "onCreate called. Score is: ${viewModel.score}")

        resetGame()

    }

    private fun startGame() {
        gameStarted = true
        countDownTimer.start()
    }

    private fun incrementScore() {
        if(!gameStarted) {
            startGame()
        }

        viewModel.score += 1
//        val newScore = getString(R.string.yourScore, viewModel.score)
        binding.gameScoreTextView.text = getString(R.string.yourScore, viewModel.score)
    }

    private fun endGame() {
        activity?.let {
            Toast.makeText(it, getString(R.string.gameOverMessage, viewModel.score), Toast.LENGTH_LONG).show()
            resetGame()
        }
    }

    private fun resetGame() {
        gameStarted = false
        viewModel.score = 0
        binding.gameScoreTextView.text = getString(R.string.yourScore, viewModel.score)
        binding.timeLeftTextView.text = getString(R.string.timeLeft, initialCountDown / 1000)

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                viewModel.timeLeftOnTimer = millisUntilFinished / 1000
                binding.timeLeftTextView.text = getString(R.string.timeLeft, viewModel.timeLeftOnTimer)
            }

            override fun onFinish() {
                endGame()
            }
        }
    }

}