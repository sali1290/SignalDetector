package com.example.signaldetector.model.services

import com.example.signaldetector.model.dto.request.CellInfo
import com.example.signaldetector.model.dto.response.CellLocation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

fun interface UnwiredLabsRemoteApi {

    @POST("process")
    suspend fun getLocationByCellInfo(@Body cellInfo: CellInfo): Response<CellLocation>

    companion object {
        const val BASE_URL = "https://eu1.unwiredlabs.com/v2/"
    }
}