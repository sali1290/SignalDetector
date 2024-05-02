package com.example.signaldetector.view.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.signaldetector.model.utility.LogKeys
import com.example.signaldetector.view.components.SimSlotItem
import com.example.signaldetector.viewmodel.SIMCardViewModel

@Composable
fun SIMInfoScreen() {
    val simCardViewModel: SIMCardViewModel = hiltViewModel()
    val context = LocalContext.current

    val simInfo by simCardViewModel.simCardsInfo.collectAsState()
    simCardViewModel.apply {
        LaunchedEffect(key1 = Unit) {
            getSIMCardsInformation()
        }
        LaunchedEffect(key1 = simInfo) {
            Log.d(
                LogKeys.ResultTest,
                simInfo.result.toString()
            )
            if (!simInfo.error.isNullOrEmpty())
                Toast.makeText(context, simInfo.error, Toast.LENGTH_LONG).show()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        itemsIndexed(items = simInfo.result) { _, item ->
            SimSlotItem(
                slot = item.second?.simSlotIndex ?: 0,
                providerName = item.second?.displayName.toString(),
                power = item.first,
                iconColor = item.second?.iconTint ?: 0,
                country = item.second?.countryIso.toString()
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SIMInfoScreenPreview() {
    SIMInfoScreen()
}