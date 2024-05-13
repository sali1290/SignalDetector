package com.example.signaldetector.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blongho.country_data.World
import com.example.signaldetector.view.screens.MainScreen
import com.example.signaldetector.view.screens.SIMInfoScreen
import com.example.signaldetector.view.screens.Screen
import com.example.signaldetector.view.screens.SignalPresenceScreen
import com.example.signaldetector.view.screens.UserLocationScreen
import com.example.signaldetector.view.theme.SignalDetectorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        World.init(applicationContext)
        setContent {
            SignalDetectorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(route = Screen.MainScreen.route) {
                            MainScreen(navController)
                        }
                        composable(route = Screen.SIMInfoScreen.route) {
                            SIMInfoScreen()
                        }
                        composable(route = Screen.UserLocationScreen.route) {
                            UserLocationScreen()
                        }
                        composable(route = Screen.SignalPresenceScreen.route) {
                            SignalPresenceScreen()
                        }
                    }
                }
            }
        }
    }
}


