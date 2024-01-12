package com.example.signaldetector.model.dto

import com.example.signaldetector.BuildConfig


data class CellInfo(
    val token: String = BuildConfig.API_KEY,
    var radio: String? = null,
    var mcc: Int? = null,
    var mnc: Int? = null,
    var cells: List<Cell> = emptyList(),
    val address: Int = 1
)
