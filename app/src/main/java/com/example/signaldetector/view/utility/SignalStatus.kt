package com.example.signaldetector.view.utility

import androidx.compose.ui.graphics.Color

enum class SignalStatus(val value: String) {
    Perfect("Perfect"), Good("Good"), OK("Average"), Bad("Poor"), VeryPoor("Very poor");
}

fun measureSignalPower(signalStrength: Int): Pair<String, Color> {
    return when {

        signalStrength >= -79 -> Pair(SignalStatus.Perfect.value, Color(0xFF89FF00))

        signalStrength >= -89 -> Pair(SignalStatus.Good.value, Color(0xFFA7CF79))

        signalStrength >= -99 -> Pair(SignalStatus.OK.value, Color(0xFF57614C))

        signalStrength >= -109 -> Pair(SignalStatus.Bad.value, Color(0xFFFF7272))

        signalStrength >= -120 -> Pair(SignalStatus.VeryPoor.value, Color(0xFFFF0000))

        else -> Pair(SignalStatus.VeryPoor.value, Color(0xFFFF0000))
    }
}