package com.raywenderlich.timefighter.ui.data_binding

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.timefighter.R

class DataBindingFragment : Fragment() {

    companion object {
        fun newInstance() = DataBindingFragment()
    }

    private lateinit var viewModel: DataBindingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_binding_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DataBindingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}