package com.example.signaldetector.view.screens

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat

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
                    return
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
            Text(text = wifi.SSID + "   " + wifi.capabilities + "    " + wifi.frequency + "    " + wifi.channelWidth)
        }
    }
}