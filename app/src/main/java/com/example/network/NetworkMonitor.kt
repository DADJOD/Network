package com.example.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService

class NetworkMonitor : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()

            when (networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> {
                    Log.d("happySDK", "TYPE_WIFI")
                    Toast.makeText(context, "Connected with WIFI", Toast.LENGTH_SHORT).show()
                }

                ConnectivityManager.TYPE_MOBILE -> {
                    Log.d("happySDK", "TYPE_MOBILE")
                    Toast.makeText(context, "Connected with MOBILE ETHERNET", Toast.LENGTH_SHORT)
                        .show()
                }

                else ->
                    Log.d("happySDK", "Never mind")
            }
        } else {
            Toast.makeText(context, "Not connected", Toast.LENGTH_SHORT).show()
        }
    }
}