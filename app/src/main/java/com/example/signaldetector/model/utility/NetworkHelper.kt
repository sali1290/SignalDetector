package com.example.signaldetector.model.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetWorkHelper (private val context: Context) {

    fun isNetworkConnected(): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false

        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                true
            }
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                true
            }
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                true
            }
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                true
            }

            else -> {
                return false
            }
        }
    }
}