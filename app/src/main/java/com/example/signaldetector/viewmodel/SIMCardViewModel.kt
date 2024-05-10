package com.example.signaldetector.viewmodel

import android.telephony.SubscriptionInfo
import androidx.lifecycle.ViewModel
import com.example.signaldetector.model.repo.SIMCardRepo
import com.example.signaldetector.viewmodel.utils.ResultState
import com.example.signaldetector.viewmodel.utils.updateInBackground
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SIMCardViewModel @Inject constructor(
    private val simCardRepo: SIMCardRepo
) : ViewModel() {

    private val _simCardsInfo =
        MutableStateFlow(ResultState<List<Pair<Int, SubscriptionInfo?>>>(result = emptyList()))
    val simCardsInfo = _simCardsInfo.asStateFlow()
    fun getSIMCardsInformation() = updateInBackground(flow = _simCardsInfo) {
        simCardRepo.getSIMCardsStrength()
    }


}