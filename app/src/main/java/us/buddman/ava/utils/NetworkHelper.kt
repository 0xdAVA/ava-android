package us.buddman.ava.utils


import android.content.Context
import android.net.ConnectivityManager

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Junseok on 2017-09-02.
 */

object NetworkHelper {
    private val url = "http://220.95.213.60/"
    lateinit private var retrofit: Retrofit
    val instance: NetworkAPI
        get() {
            retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(NetworkAPI::class.java)
        }

    fun returnNetworkState(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}
