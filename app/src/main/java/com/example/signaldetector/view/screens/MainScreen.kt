package com.example.signaldetector.view.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.signaldetector.view.utility.SignalPowerDetector

@Composable
fun MainScreen() {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        try {
            SignalPowerDetector.getNetworkStrength(context)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}