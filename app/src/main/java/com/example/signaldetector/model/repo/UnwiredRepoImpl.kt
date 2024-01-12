package com.example.signaldetector.model.repo

import android.util.Log
import com.example.signaldetector.model.api.UnwiredApiService
import com.example.signaldetector.model.dto.CellInfo
import com.example.signaldetector.model.dto.CellLocation
import com.example.signaldetector.model.utility.NetWorkHelper
import java.io.IOException

class UnwiredRepoImpl(
    private val unwiredApiService: UnwiredApiService,
    private val netWorkHelper: NetWorkHelper
) : UnwiredRepo {
    override suspend fun getLocationByCellInfo(cellInfo: CellInfo): CellLocation {
        if (netWorkHelper.isNetworkConnected()) {
            val response = unwiredApiService.getLocationByCellInfo(cellInfo)
            if (response.isSuccessful) {
                Log.i("API-Test", response.body().toString())
                return response.body()!!
            } else {
                throw IOException(response.message())
            }
        } else {
            throw IOException("مشکل در اتصال به اینترنت")
        }
    }


}