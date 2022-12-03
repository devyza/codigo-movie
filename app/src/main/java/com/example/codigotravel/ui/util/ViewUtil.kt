package com.example.codigotravel.ui.util

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient

class ViewUtil {

    companion object {
        fun getNetworkInterceptor(context: Context): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(
                with(ChuckerInterceptor.Builder(context)) {
                    collector(ChuckerCollector((context)))
                    maxContentLength(250000L)
                    redactHeaders(emptySet())
                    alwaysReadResponseBody(false)
                    build()
                }
            ).build()
        }
    }
}