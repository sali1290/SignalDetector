package com.example.signaldetector.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blongho.country_data.World
import com.example.signaldetector.view.components.PermissionDialog
import com.example.signaldetector.view.screens.MainScreen
import com.example.signaldetector.view.screens.SIMInfoScreen
import com.example.signaldetector.view.screens.Screen
import com.example.signaldetector.view.screens.SignalPresenceScreen
import com.example.signaldetector.view.screens.UserLocationScreen
import com.example.signaldetector.view.screens.WifiListScreen
import com.example.signaldetector.view.theme.SignalDetectorTheme
import com.example.signaldetector.view.utils.checkPermissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        World.init(applicationContext)
        setContent {
            SignalDetectorTheme {
                DetermineFirstScreen()
            }
        }
    }
}

@Composable
fun DetermineFirstScreen() {
    var permissionsAreGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        permissionsAreGranted = checkPermissions(context)
    }

    if (permissionsAreGranted)
        SignalDetectorNavHost()
    else
        PermissionDialog(onDismissRequest = { (context as Activity).finish() }) {
            permissionsAreGranted = true
        }
}

@Composable
fun SignalDetectorNavHost() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
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
                composable(route = Screen.WifiListScreen.route) {
                    WifiListScreen()
                }
            }
        }
    }
}


