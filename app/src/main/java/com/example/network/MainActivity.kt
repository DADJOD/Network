package com.example.network

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val monitor = NetworkMonitor()

    companion object {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(monitor, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(monitor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun checkNetwork(view: View) {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()

            when (networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> {
                    Log.d("happySDK", "TYPE_WIFI")
                    Toast.makeText(this, "Connected with WIFI", Toast.LENGTH_SHORT).show()
                }

                ConnectivityManager.TYPE_MOBILE -> {
                    Log.d("happySDK", "TYPE_MOBILE")
                    Toast.makeText(this, "Connected with MOBILE ETHERNET", Toast.LENGTH_SHORT)
                        .show()
                }

                else ->
                    Log.d("happySDK", "Never mind")
            }
        } else {
            Toast.makeText(this, "Not connected", Toast.LENGTH_SHORT).show()
        }
    }
}