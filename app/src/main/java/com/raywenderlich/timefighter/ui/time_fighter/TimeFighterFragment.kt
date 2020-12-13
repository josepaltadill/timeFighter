package com.raywenderlich.timefighter.ui.time_fighter

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.timefighter.R

class TimeFighterFragment : Fragment() {

    companion object {
        fun newInstance() = TimeFighterFragment()
    }

    private lateinit var viewModel: TimeFighterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.time_fighter_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TimeFighterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}