package com.example.signaldetector.model.utility

import android.telephony.CellInfo

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