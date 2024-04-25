package com.example.signaldetector.model.repo

import android.telephony.SubscriptionInfo

interface SIMCardRepo {

    fun getSIMCardsStrength(): List<Pair<Int, SubscriptionInfo?>>

}