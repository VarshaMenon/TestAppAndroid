package com.example.androidtestapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class CommonMethods {

    fun formatDate(date: String): String {
        val fmt = SimpleDateFormat(Constants.DATE_FORMAT_FROM_API, Locale.US)
        val inputDate: Date = fmt.parse(date)!!

        val fmtOut = SimpleDateFormat(Constants.DATE_FORMAT_APP, Locale.US)
        return fmtOut.format(inputDate)
    }

    fun isInternetConnected(context: Context):Boolean{
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectionManager.activeNetwork ?: return false
            val activeNetwork = connectionManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                else -> false
            }
        }
        else {
            return connectionManager.activeNetworkInfo != null &&
                    connectionManager.activeNetworkInfo?.isConnectedOrConnecting!!
        }

    }
}