package com.example.signaldetector.model.repoimpl

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.CellInfoCdma
import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.signaldetector.R
import com.example.signaldetector.model.repo.SIMCardRepo
import com.example.signaldetector.model.utility.LogKeys
import com.example.signaldetector.model.utility.getRegisteredCellInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException

class SIMCardRepoImpl(
    @ApplicationContext private val context: Context
) : SIMCardRepo {

    override fun getSIMCardsStrength(): List<Pair<SubscriptionInfo, Int>> {
        var strength1 = -1
        var strength2 = -1

        var sub1: SubscriptionInfo? = null
        var sub2: SubscriptionInfo? = null

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

            activeSubscriptionInfoList.forEachIndexed { _, subs ->

                if (activeSubscriptionInfoList.size >= 2) {

                    if (regCellInfo.size >= 2) {

                        if (subs.simSlotIndex == 0) {

                            if (subs.carrierName != context.getString(R.string.no_service)) {


                                strength1 = when (val info1 = regCellInfo[0]) {
                                    is CellInfoLte -> info1.cellSignalStrength.dbm
                                    is CellInfoGsm -> info1.cellSignalStrength.dbm
                                    is CellInfoCdma -> info1.cellSignalStrength.dbm
                                    is CellInfoWcdma -> info1.cellSignalStrength.dbm
                                    else -> 0
                                }

                                Log.i(LogKeys.ResultTest, "subs $subs")
                                sub1 = subs
                                Log.i(
                                    LogKeys.ResultTest,
                                    "sim1   ${subs.carrierName}  $strength1"
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
                                Log.i(LogKeys.ResultTest, "subs $subs")
                                sub2 = subs
                                Log.i(LogKeys.ResultTest, "sim2   ${subs.carrierName}  $strength2")
                            } else {

                                strength2 = -1
                            }

                        }

                    }
                } else if (activeSubscriptionInfoList.size == 1) {

                    if (regCellInfo.size >= 1 && subs.simSlotIndex == 0) {

                        if (subs.carrierName != "No service") {


                            strength1 = when (val info1 = regCellInfo[0]) {
                                is CellInfoLte -> info1.cellSignalStrength.level
                                is CellInfoGsm -> info1.cellSignalStrength.level
                                is CellInfoCdma -> info1.cellSignalStrength.level
                                is CellInfoWcdma -> info1.cellSignalStrength.level
                                else -> 0
                            }

                            Log.i(LogKeys.ResultTest, "subs $subs")

                            Log.i(
                                LogKeys.ResultTest,
                                "sim1   ${subs.carrierName}  ${subs.mnc}  $strength1"
                            )
                        } else {

                            strength1 = -1
                        }
                    }

                    strength2 = -2

                }
            }

        }

        Log.i("Result-Test", "final strength  sim1 $strength1  sim2 $strength2")

        return listOf(Pair(sub1!!, strength1), Pair(sub2!!, strength2))
    }

}