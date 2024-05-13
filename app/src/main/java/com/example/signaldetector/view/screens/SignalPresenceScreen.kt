package com.example.signaldetector.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.signaldetector.R
import com.example.signaldetector.view.components.CustomButton
import com.example.signaldetector.view.theme.AccentColor
import com.example.signaldetector.view.theme.SecondaryTextColor
import com.example.signaldetector.view.theme.Typography
import com.example.signaldetector.view.utils.checkCellPresence
import com.example.signaldetector.view.utils.checkGpsStatus
import com.example.signaldetector.view.utils.checkMobileDataPresence
import com.example.signaldetector.view.utils.checkWifiPresence

@Composable
fun SignalPresenceScreen() {

    val signalItems = listOf(
        Signal(stringResource(R.string.wifi), R.drawable.ic_wifi),
        Signal(stringResource(R.string.cell), R.drawable.ic_cell),
        Signal(stringResource(R.string.mobile_data), R.drawable.ic_mobiledata),
        Signal(stringResource(R.string.bluetooth), R.drawable.ic_bluetooth),
        Signal(stringResource(R.string.nfc), R.drawable.ic_nfc),
        Signal(stringResource(R.string.gps), R.drawable.ic_gps),
    )

    var isWifiActive by remember { mutableStateOf(false) }
    var isCellActive by remember { mutableStateOf(false) }
    var isMobileDataActive by remember { mutableStateOf(false) }
    var isGpsActive by remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        isWifiActive = checkWifiPresence(context)
        isCellActive = checkCellPresence(context)
        isMobileDataActive = checkMobileDataPresence(context)
        isGpsActive = checkGpsStatus(context)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Your phone current signals",
            modifier = Modifier.padding(top = 10.dp),
            style = Typography.titleLarge
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            itemsIndexed(signalItems) { index, item ->
                when (index) {
                    0 -> SignalItem(signal = item, isActive = isWifiActive)
                    1 -> SignalItem(signal = item, isActive = isCellActive)
                    2 -> SignalItem(signal = item, isActive = isMobileDataActive)
                    3 -> SignalItem(signal = item, isActive = false)
                    4 -> SignalItem(signal = item, isActive = false)
                    5 -> SignalItem(signal = item, isActive = isGpsActive)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomButton(
            text = "Calculate signal presence",
            backgroundColor = AccentColor,
            onClick = {
                isWifiActive = checkWifiPresence(context)
                isCellActive = checkCellPresence(context)
                isMobileDataActive = checkMobileDataPresence(context)


                isGpsActive = checkGpsStatus(context)
            })
    }
}

@Composable
private fun SignalItem(signal: Signal, isActive: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .height(150.dp)
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = signal.icon),
                contentDescription = "Icon",
                modifier = Modifier.height(45.dp),
                tint = if (isActive) AccentColor else SecondaryTextColor
            )
            Text(
                text = signal.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Typography.bodyLarge,
                color = if (isActive) AccentColor else SecondaryTextColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignalPresenceScreenPreview() {
    SignalPresenceScreen()
}

data class Signal(
    val name: String,
    val icon: Int
)