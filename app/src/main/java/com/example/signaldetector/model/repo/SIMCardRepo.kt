package com.example.signaldetector.model.repo

import android.telephony.SubscriptionInfo
import com.example.signaldetector.model.dto.request.CellInfo
import com.example.signaldetector.model.dto.response.CellLocation

interface SIMCardRepo {

    fun getSIMCardsStrength(): List<Pair<Int, SubscriptionInfo?>>

    suspend fun getLocation(cellInfo: CellInfo): CellLocation
}