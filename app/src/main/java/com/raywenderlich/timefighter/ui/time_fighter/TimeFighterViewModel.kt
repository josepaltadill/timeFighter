package com.raywenderlich.timefighter.ui.time_fighter

import androidx.lifecycle.ViewModel

class TimeFighterViewModel : ViewModel() {
    var score:Int = 0
    var timeLeftOnTimer:Long = 20000
}