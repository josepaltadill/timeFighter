package com.raywenderlich.timefighter.ui.data_binding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindingViewModel : ViewModel() {
    var counter:Int = 0
//    private val _counter = MutableLiveData<Int>().apply {
//        value = 0
//    }
//
//    val counter : MutableLiveData<Int> = _counter

//    val counter : MutableLiveData<Int> by lazy {
//        MutableLiveData<Int>(0)
//    }
}