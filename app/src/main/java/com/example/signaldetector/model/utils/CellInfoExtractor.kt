package com.example.signaldetector.model.utils

import android.content.Context
import android.os.Build
import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import com.example.signaldetector.model.dto.request.Cell
import com.example.signaldetector.model.dto.request.CellInfo
import com.example.signaldetector.model.dto.request.RadioType

fun getCurrentCellInfo(context: Context): List<CellInfo> {
    val allCellInfo = getAllCellInfo(context)
    return allCellInfo.mapNotNull {
        when (it) {
            is CellInfoLte -> getCellInfo(it)
            is CellInfoGsm -> getCellInfo(it)
            is CellInfoWcdma -> getCellInfo(it)
            else -> null
        }
    }
}

@Suppress("DEPRECATION")
private fun getCellInfo(info: CellInfoGsm): CellInfo {
    val cellInfo = CellInfo()
    cellInfo.radio = RadioType.GSM

    info.cellIdentity.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            cellInfo.mcc = it.mccString?.toInt() ?: 0
            cellInfo.mnc = it.mncString?.toInt() ?: 0
        } else {
            cellInfo.mcc = it.mcc
            cellInfo.mnc = it.mnc
        }
        cellInfo.cells = listOf(Cell(it.lac, it.cid, it.psc))
    }
    return cellInfo
}

@Suppress("DEPRECATION")
private fun getCellInfo(info: CellInfoWcdma): CellInfo {
    val cellInfo = CellInfo()

    cellInfo.radio = RadioType.CDMA

    info.cellIdentity.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            cellInfo.mcc = it.mccString?.toInt() ?: 0
            cellInfo.mnc = it.mncString?.toInt() ?: 0
        } else {
            cellInfo.mcc = it.mcc
            cellInfo.mnc = it.mnc
        }
        cellInfo.cells = listOf(Cell(it.lac, it.cid, it.psc))
    }
    return cellInfo
}

@Suppress("DEPRECATION")
private fun getCellInfo(info: CellInfoLte): CellInfo {
    val cellInfo = CellInfo()

    cellInfo.radio = RadioType.LTE
    info.cellIdentity.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            cellInfo.mcc = it.mccString?.toInt() ?: 0
            cellInfo.mnc = it.mncString?.toInt() ?: 0
        } else {
            cellInfo.mcc = it.mcc
            cellInfo.mnc = it.mnc
        }
        cellInfo.cells = listOf(Cell(it.tac, it.ci, it.pci))
    }
    return cellInfo
}