package com.example.signaldetector.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signaldetector.model.dto.CellInfo
import com.example.signaldetector.model.dto.CellLocation
import com.example.signaldetector.model.repo.UnwiredRepo
import com.example.signaldetector.view.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.IOException

class LocationByCellInfoViewModel(private val unwiredRepo: UnwiredRepo) : ViewModel() {

    private val _location = MutableSharedFlow<Resource<CellLocation>>()
    val location = _location.asSharedFlow()
    fun getLocationByCellInfo(cellInfo: CellInfo) = viewModelScope.launch(Dispatchers.IO) {
        _location.emit(Resource.Loading())
        try {
            val result = unwiredRepo.getLocationByCellInfo(cellInfo)
            _location.emit(Resource.Success(result))
        } catch (exception: IOException) {
            val message = exception.message!!
            Log.d("ViewModel-Test", message)
            _location.emit(Resource.Error(message))
        }
    }


}