package com.example.signaldetector.view.screens

sealed class Screen(val route: String) {

    data object MainScreen : Screen(route = "MainScreen")

    data object SIMInfoScreen : Screen(route = "SIMInfoScreen")

    data object IPInfoScreen : Screen(route = "IPInfoScreen")

}