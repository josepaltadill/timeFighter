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
        Log.d(TAG, "onCreate called. Score is: ${viewModel.score}, temps es: ${viewModel.timeLeftOnTimer} i partida començada: ${viewModel.gameStarted}")
//        restoreGame()

        if (viewModel.gameStarted) {
            Log.d(TAG, "onCreate called true. Score is: ${viewModel.score}, temps es: ${viewModel.timeLeftOnTimer} i partida començada: ${viewModel.gameStarted}")
            restoreGame()
        } else {
            Log.d(TAG, "onCreate called false. Score is: ${viewModel.score}, temps es: ${viewModel.timeLeftOnTimer} i partida començada: ${viewModel.gameStarted}")
            resetGame()
        }

    }

    private fun startGame() {
        viewModel.gameStarted = true
        countDownTimer.start()
    }

    private fun incrementScore() {
        if(!viewModel.gameStarted) {
            startGame()
        }

        viewModel.score += 1
        binding.gameScoreTextView.text = getString(R.string.yourScore, viewModel.score)
    }

    private fun endGame() {
        activity?.let {
            Toast.makeText(it, getString(R.string.gameOverMessage, viewModel.score), Toast.LENGTH_LONG).show()
            resetGame()
        }
    }

    private fun resetGame() {
        viewModel.score = 0
        binding.gameScoreTextView.text = getString(R.string.yourScore, viewModel.score)

        val initialTimeLeft = initialCountDown / 1000
        binding.timeLeftTextView.text = getString(R.string.timeLeft, initialTimeLeft)
        Log.d(TAG, "Estic al reset. Score is: ${viewModel.score} i temps es: ${viewModel.timeLeftOnTimer}")

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                activity?.let {
                    viewModel.timeLeftOnTimer = millisUntilFinished
                    val timeLeft = millisUntilFinished / 1000
                    binding.timeLeftTextView.text = getString(R.string.timeLeft, timeLeft)
                    Log.d(TAG, "Estic al contador del resetgame. Temps es: ${viewModel.timeLeftOnTimer}")
                }
            }

            override fun onFinish() {
                endGame()
            }
        }
        viewModel.gameStarted = false
    }

    private fun restoreGame() {
        binding.gameScoreTextView.text = getString(R.string.yourScore, viewModel.score)

        val restoredTime = viewModel.timeLeftOnTimer / 1000
        binding.timeLeftTextView.text = getString(R.string.timeLeft, restoredTime)

        Log.d(TAG, "Estic al restore. Score is: ${viewModel.score} i temps es: ${viewModel.timeLeftOnTimer}")

        countDownTimer = object : CountDownTimer(viewModel.timeLeftOnTimer, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                activity?.let {
                    viewModel.timeLeftOnTimer = millisUntilFinished
                    val timeLeft = millisUntilFinished / 1000
                    binding.timeLeftTextView.text = getString(R.string.timeLeft, timeLeft)
                    Log.d(TAG, "Estic al contador del restoregame. Temps es: ${timeLeft}")
                }
            }

            override fun onFinish() {
                endGame()
            }
        }

        startGame()
    }

}