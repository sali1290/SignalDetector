package com.example.signaldetector.model.utility

enum class SignalStatus {
    Perfect, Good, OK, Bad;

    fun measureSignal(signalStrength: Int): SignalStatus {
        return Good
    }
}