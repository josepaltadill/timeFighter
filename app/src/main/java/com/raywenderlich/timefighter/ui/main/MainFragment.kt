package com.raywenderlich.timefighter.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.raywenderlich.timefighter.R
import com.raywenderlich.timefighter.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater)

        binding.timeFighterButton.setOnClickListener { view ->
            findNavController().navigate(R.id.action_mainFragment_to_timeFighterFragment)
        }

        binding.dataBindingButton.setOnClickListener { view ->
            findNavController().navigate(R.id.action_mainFragment_to_dataBindingFragment)
        }

        binding.testButton.setOnClickListener { view ->
            findNavController().navigate(R.id.action_mainFragment_to_josepFragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}