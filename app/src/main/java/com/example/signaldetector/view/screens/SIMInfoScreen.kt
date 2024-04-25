package com.example.signaldetector.view.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.signaldetector.model.utility.LogKeys
import com.example.signaldetector.viewmodel.SIMCardViewModel

@Composable
fun SIMInfoScreen() {
    val simCardViewModel: SIMCardViewModel = hiltViewModel()
    simCardViewModel.apply {
        LaunchedEffect(key1 = Unit) {
            getSIMCardsInformation()
        }
        simCardsInfo.collectAsState()
        LaunchedEffect(key1 = simCardsInfo.value) {
            Log.d(LogKeys.ResultTest, simCardsInfo.value.result.toString())
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SIMInfoScreenPreview() {
    SIMInfoScreen()
}