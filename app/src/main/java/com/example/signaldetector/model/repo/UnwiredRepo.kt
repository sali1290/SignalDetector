package com.example.signaldetector.model.repo

import com.example.signaldetector.model.dto.CellInfo
import com.example.signaldetector.model.dto.CellLocation

interface UnwiredRepo {

    suspend fun getLocationByCellInfo(cellInfo: CellInfo): CellLocation

}