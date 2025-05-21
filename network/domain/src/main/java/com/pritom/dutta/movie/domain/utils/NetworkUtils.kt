package com.pritom.dutta.movie.domain.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected == true
    }
}