package com.example.signaldetector.model.api

import com.example.signaldetector.model.dto.CellInfo
import com.example.signaldetector.model.dto.CellLocation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UnwiredApiService {

    @POST("v2/process.php")
    suspend fun getLocationByCellInfo(@Body cellInfo: CellInfo): Response<CellLocation>
}