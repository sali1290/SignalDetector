package com.example.signaldetector.view.utils

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.nfc.NfcManager
import android.telephony.SubscriptionManager
import android.widget.Toast
import androidx.core.app.ActivityCompat


fun checkWifiPresence(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val activeNetwork =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

    return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
}

fun checkMobileDataPresence(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val activeNetwork =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

    return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
}

fun checkCellPresence(context: Context): Boolean {
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_PHONE_STATE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
    }
    val sManager =
        context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager
    val infoSim1 = sManager.getActiveSubscriptionInfoForSimSlotIndex(0)
    val infoSim2 = sManager.getActiveSubscriptionInfoForSimSlotIndex(1)
    return infoSim1 != null || infoSim2 != null
}

fun checkGpsSignalPresence(context: Context): Boolean {
    val locationManager =
        context.getSystemService(LOCATION_SERVICE) as LocationManager? ?: return false
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

fun checkNfcSignalPresence(context: Context): Boolean {
    val manager = context.getSystemService(Context.NFC_SERVICE) as NfcManager
    val adapter = manager.defaultAdapter
    return adapter != null && adapter.isEnabled
}

fun checkBluetoothSignalPresence(context: Context): Boolean {
    val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val adapter = bluetoothManager.adapter
    return adapter != null && adapter.isEnabled
}