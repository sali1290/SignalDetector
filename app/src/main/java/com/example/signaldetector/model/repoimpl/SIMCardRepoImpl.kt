package com.example.signaldetector.model.repoimpl

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.CellInfo
import android.telephony.CellInfoCdma
import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.signaldetector.R
import com.example.signaldetector.model.dto.response.CellLocation
import com.example.signaldetector.model.repo.SIMCardRepo
import com.example.signaldetector.model.services.UnwiredLabsRemoteApi
import com.example.signaldetector.model.utils.LogKeys
import com.example.signaldetector.model.utils.NetWorkHelper
import com.example.signaldetector.model.utils.getAllCellInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class SIMCardRepoImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val unwiredLabsRemoteApi: UnwiredLabsRemoteApi,
    private val netWorkHelper: NetWorkHelper
) : SIMCardRepo {

    override fun getSIMCardsStrength(): List<Pair<Int, SubscriptionInfo?>> {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            throw IOException("Permission denied")
        }

        var strength1 = -1
        var strength2 = -1

        var sub1: SubscriptionInfo? = null
        var sub2: SubscriptionInfo? = null

        val manager =
            context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

        val allCellInfo = getAllCellInfo(context)
        val activeSubscriptionInfoList = manager.activeSubscriptionInfoList
        val regCellInfo = getRegisteredCellInfo(allCellInfo)

        activeSubscriptionInfoList.forEachIndexed { _, subs ->

            if (activeSubscriptionInfoList.size == 1) {
                if (regCellInfo.size >= 1 && subs.simSlotIndex == 0) {
                    strength1 = if (subs.carrierName != context.getString(R.string.no_service)) {
                        when (val info1 = regCellInfo[0]) {
                            is CellInfoLte -> info1.cellSignalStrength.level
                            is CellInfoGsm -> info1.cellSignalStrength.level
                            is CellInfoCdma -> info1.cellSignalStrength.level
                            is CellInfoWcdma -> info1.cellSignalStrength.level
                            else -> 0
                        }
                    } else {
                        -1
                    }
                }
                strength2 = -2
            }

            if (activeSubscriptionInfoList.size == 2 && regCellInfo.size >= 2) {
                if (subs.simSlotIndex == 0) {
                    if (subs.carrierName != context.getString(R.string.no_service)) {
                        strength1 = when (val info1 = regCellInfo[0]) {
                            is CellInfoLte -> info1.cellSignalStrength.dbm
                            is CellInfoGsm -> info1.cellSignalStrength.dbm
                            is CellInfoCdma -> info1.cellSignalStrength.dbm
                            is CellInfoWcdma -> info1.cellSignalStrength.dbm
                            else -> 0
                        }
                        sub1 = subs
                    } else {
                        strength1 = -1
                    }
                }
                if (subs.simSlotIndex == 1) {
                    if (subs.carrierName != context.getString(R.string.no_service)) {
                        strength2 = when (val info2 = regCellInfo[1]) {
                            is CellInfoLte -> info2.cellSignalStrength.dbm
                            is CellInfoGsm -> info2.cellSignalStrength.dbm
                            is CellInfoCdma -> info2.cellSignalStrength.dbm
                            is CellInfoWcdma -> info2.cellSignalStrength.dbm
                            else -> 0
                        }
                        sub2 = subs
                    } else {
                        strength2 = -1
                    }
                }
            }
        }
        Log.i(LogKeys.REQUEST, "final strength  sim1 $strength1  sim2 $strength2")
        return listOf(Pair(strength1, sub1), Pair(strength2, sub2))
    }

    override suspend fun getLocation(
        cellInfo: com.example.signaldetector.model.dto.request.CellInfo
    ): CellLocation {
        if (netWorkHelper.isNetworkConnected()) {
            val response = unwiredLabsRemoteApi.getLocationByCellInfo(cellInfo)
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                throw IOException(response.message())
            }
        } else {
            throw IOException(context.getString(R.string.internet_is_not_connected))
        }
    }


    private fun getRegisteredCellInfo(cellInfoList: List<CellInfo>): ArrayList<CellInfo> {
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
}