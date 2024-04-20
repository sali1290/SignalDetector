package com.example.signaldetector.view.screens

import android.telephony.SubscriptionInfo
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signaldetector.R
import com.example.signaldetector.view.components.SimSlotItem
import com.example.signaldetector.view.theme.Purple40
import com.example.signaldetector.view.theme.PurpleGrey40
import com.example.signaldetector.view.utility.SignalPowerDetector
import kotlinx.coroutines.delay

@Composable
fun MainScreen() {
//    val context = LocalContext.current
//
//    var simStrength1 by remember { mutableIntStateOf(0) }
//    var simStrength2 by remember { mutableIntStateOf(0) }
//
//    var simSubscription1 by remember { mutableStateOf<SubscriptionInfo?>(null) }
//    var simSubscription2 by remember { mutableStateOf<SubscriptionInfo?>(null) }
//
//    LaunchedEffect(key1 = Unit) {
//        try {
//            val simData = SignalPowerDetector.getNetworkStrength(context)
//            simData.forEachIndexed { index, item ->
//                if (index == 0) {
//                    simSubscription1 = item.first
//                    simStrength1 = item.second
//                }
//                if (index == 1) {
//                    simSubscription2 = item.first
//                    simStrength2 = item.second
//                }
//            }
//        } catch (e: Exception) {
//            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
//        }
//    }
    var titleVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        LaunchedEffect(key1 = Unit) {
            delay(1500)
            titleVisibility = true
        }
        AnimatedVisibility(visible = titleVisibility) {
            Text(
                text = "Signal Detector",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = PurpleGrey40,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        SimSlotItem(iconId = R.drawable.ic_mtn_irancell, )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}