package com.example.signaldetector.view.screens

sealed class Screen(val route: String) {

    data object MainScreen : Screen(route = "MainScreen")

    data object SIMInfoScreen : Screen(route = "SIMInfoScreen")

    data object UserLocationScreen : Screen(route = "UserLocationScreen")

    data object SignalPresenceScreen : Screen(route = "SignalPresenceScreen")
}