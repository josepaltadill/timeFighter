package com.raywenderlich.timefighter.ui.data_binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.raywenderlich.timefighter.databinding.DataBindingFragmentBinding

class DataBindingFragment : Fragment() {

    private lateinit var binding : DataBindingFragmentBinding

    companion object {
        fun newInstance() = DataBindingFragment()
    }

    private lateinit var viewModel: DataBindingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DataBindingViewModel::class.java)
        // TODO: Use the ViewModel
        binding.counter.setText(viewModel.counter.toString())

        binding.buttonPlus.setOnClickListener { _ ->
            viewModel.counter++
            binding.counter.setText(viewModel.counter.toString())
        }

        binding.buttonMinus.setOnClickListener { _ ->
            viewModel.counter--
            binding.counter.setText(viewModel.counter.toString())
        }
    }

}