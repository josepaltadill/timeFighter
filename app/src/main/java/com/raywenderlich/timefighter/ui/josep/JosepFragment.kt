package com.raywenderlich.timefighter.ui.josep

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.timefighter.R

class JosepFragment : Fragment() {

    companion object {
        fun newInstance() = JosepFragment()
    }

    private lateinit var viewModel: JosepViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.josep_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JosepViewModel::class.java)
        // TODO: Use the ViewModel
    }

}