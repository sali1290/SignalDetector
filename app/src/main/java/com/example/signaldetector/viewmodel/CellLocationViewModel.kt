package com.example.signaldetector.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signaldetector.model.dto.request.CellInfo
import com.example.signaldetector.model.dto.response.CellLocation
import com.example.signaldetector.model.repo.SIMCardRepo
import com.example.signaldetector.viewmodel.utils.ResultState
import com.example.signaldetector.viewmodel.utils.updateInBackground
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CellLocationViewModel @Inject constructor(private val simCardRepo: SIMCardRepo) :
    ViewModel() {

    private val _cellLocation = MutableStateFlow(ResultState<CellLocation?>(result = null))
    val cellLocation = _cellLocation.asStateFlow()
    fun getCellLocation(cellInfo: CellInfo) =
        updateInBackground(flow = _cellLocation, scope = viewModelScope) {
            simCardRepo.getLocation(cellInfo)
        }

}