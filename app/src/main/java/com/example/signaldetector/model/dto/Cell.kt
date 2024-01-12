package com.example.signaldetector.model.dto

data class Cell(
    val lac: Int,
    val cid: Int,
    val psc: Int? = null
)