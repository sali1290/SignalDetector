package com.example.signaldetector.model.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.CellInfo
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import java.io.IOException

fun getAllCellInfo(context: Context): List<CellInfo> {
    val telephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    val allCellInfo = telephonyManager.allCellInfo
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        throw IOException("Permission denied")
    }
    return allCellInfo
}