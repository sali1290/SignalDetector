package com.example.signaldetector.view.screens

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.signaldetector.model.utils.LogKeys
import com.example.signaldetector.view.theme.LightPrimaryColor

@Composable
fun WifiListScreen() {
    val context = LocalContext.current
    val wifiManager =
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    var wifiList by remember { mutableStateOf<List<ScanResult>>(emptyList()) }

    DisposableEffect(Unit) {
        val wifiReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (ActivityCompat.checkSelfPermission(
                        context!!,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    Log.d(LogKeys.REQUEST, "Permission denied")
                }
                wifiList = wifiManager.scanResults
            }
        }

        context.registerReceiver(
            wifiReceiver,
            IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        )
        wifiManager.startScan()

        onDispose {
            context.unregisterReceiver(wifiReceiver)
        }
    }

    WifiList(wifiList)
}

@Composable
fun WifiList(wifiList: List<ScanResult>) {
    LazyColumn {
        itemsIndexed(wifiList) { _, wifi ->
            WifiItem(wifi = wifi)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun WifiItem(wifi: ScanResult) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(width = 1.dp, color = LightPrimaryColor, shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = wifi.SSID, modifier = Modifier.weight(0.5f))
            Text(
                text = wifi.BSSID,
                modifier = Modifier
                    .weight(0.5f)
                    .horizontalScroll(rememberScrollState()),
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Strength: ${wifi.level}", modifier = Modifier
                    .weight(0.5f)
                    .horizontalScroll(rememberScrollState())
            )
            Text(
                text = estimateDistance(wifi.level), modifier = Modifier
                    .weight(0.5f)
                    .horizontalScroll(rememberScrollState())
            )
        }
    }
}

fun estimateDistance(rssi: Int): String {
    return when (rssi) {
        in -50 downTo -70 -> "0-10 meters (estimated)"
        in -70 downTo -90 -> "10-20 meters (estimated)"
        else -> "20+ meters (estimated)"
    }
}