package com.example.signaldetector.model.repo

import com.example.signaldetector.model.api.UnwiredApiService
import com.example.signaldetector.model.dto.CellInfo
import com.example.signaldetector.model.dto.CellLocation

class UnwiredRepoImpl(private val unwiredApiService: UnwiredApiService) : UnwiredRepo {
    override suspend fun getLocationByCellInfo(cellInfo: CellInfo): CellLocation {
        TODO("Not yet implemented")
    }
}