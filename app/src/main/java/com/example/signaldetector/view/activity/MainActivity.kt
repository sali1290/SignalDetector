package com.example.signaldetector.view.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.CellInfo
import android.telephony.CellInfoCdma
import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.signaldetector.view.theme.SignalDetectorTheme
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignalDetectorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var loc by remember { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        try {
            getNetworkStrength(context)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    Text(text = loc)

}

fun getRegisteredCellInfo(cellInfoList: List<CellInfo>): ArrayList<CellInfo> {
    val registeredCellInfo = ArrayList<CellInfo>()
    if (cellInfoList.isNotEmpty()) {
        for (i in cellInfoList.indices) {
            if (cellInfoList[i].isRegistered) {
                registeredCellInfo.add(cellInfoList[i])
            }
        }
    }
    return registeredCellInfo
}

fun getNetworkStrength(context: Context): Pair<Int, Int> {

    var strength1 = -1
    var strength2 = -1


    val manager =
        context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

    val telephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager


    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        throw IOException("Permission denied")
    }
    if (telephonyManager.allCellInfo != null) {


        val allCellinfo = telephonyManager.allCellInfo
        val activeSubscriptionInfoList = manager.activeSubscriptionInfoList

        val regCellInfo = getRegisteredCellInfo(allCellinfo)

        activeSubscriptionInfoList.forEachIndexed { Subindex, subs ->

            if (activeSubscriptionInfoList.size >= 2) {

                if (regCellInfo.size >= 2) {

                    if (subs.simSlotIndex == 0) {

                        if (subs.carrierName != "No service") {


                            strength1 = when (val info1 = regCellInfo[0]) {
                                is CellInfoLte -> info1.cellSignalStrength.dbm
                                is CellInfoGsm -> info1.cellSignalStrength.dbm
                                is CellInfoCdma -> info1.cellSignalStrength.dbm
                                is CellInfoWcdma -> info1.cellSignalStrength.dbm
                                else -> 0
                            }

                            Log.i("Result-Test", "subs $subs")

                            Log.i(
                                "Result-Test",
                                "sim1   ${subs.carrierName}  ${subs.mnc}  $strength1"
                            )
                        } else {

                            strength1 = -1
                        }

                    } else if (subs.simSlotIndex == 1) {

                        if (subs.carrierName != "No service") {

                            strength2 = when (val info2 = regCellInfo[1]) {
                                is CellInfoLte -> info2.cellSignalStrength.dbm
                                is CellInfoGsm -> info2.cellSignalStrength.dbm
                                is CellInfoCdma -> info2.cellSignalStrength.dbm
                                is CellInfoWcdma -> info2.cellSignalStrength.dbm
                                else -> 0
                            }

                            Log.i("Result-Test", "sim2   ${subs.carrierName}  $strength2")
                        } else {

                            strength2 = -1
                        }

                    }

                }
            } else if (activeSubscriptionInfoList.size == 1) {

                if (regCellInfo.size >= 1) {

                    if (subs.simSlotIndex == 0) {

                        if (subs.carrierName != "No service") {


                            strength1 = when (val info1 = regCellInfo[0]) {
                                is CellInfoLte -> info1.cellSignalStrength.level
                                is CellInfoGsm -> info1.cellSignalStrength.level
                                is CellInfoCdma -> info1.cellSignalStrength.level
                                is CellInfoWcdma -> info1.cellSignalStrength.level
                                else -> 0
                            }

                            Log.i("Result-Test", "subs $subs")

                            Log.i(
                                "Result-Test",
                                "sim1   ${subs.carrierName}  ${subs.mnc}  $strength1"
                            )
                        } else {

                            strength1 = -1
                        }

                    }
                }

                strength2 = -2

            }
        }

    }

    Log.i("Result-Test", "final strenght   sim1 $strength1  sim2 $strength2")

    return Pair(strength1, strength2)
}
