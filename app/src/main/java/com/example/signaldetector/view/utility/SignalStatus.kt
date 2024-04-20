package com.example.signaldetector.view.utility

enum class SignalStatus {
    Perfect, Good, OK, Bad;


    fun measureSignal(signalStrength: Int): SignalStatus {
        return Good
    }
}