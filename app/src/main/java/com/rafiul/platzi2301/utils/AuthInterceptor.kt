package com.rafiul.platzi2301.utils

import android.content.Context
import com.rafiul.platzi2301.utils.Constants
import com.rafiul.platzi2301.utils.PrefManager
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val prefManager: PrefManager) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {


        val request = chain.request().newBuilder().addHeader(
            "Authorization",
            "Bearer ${prefManager.getPreferences(Constants.KEY_ACCESS_TOKEN)}"
        )
        return chain.proceed(request.build())

        //        val request = chain.request()
//            .newBuilder()
//            .addHeader(
//                "Authorization",
//                "Bearer ${prefManager.getPreferences(Constants.KEY_ACCESS_TOKEN)}"
//            )
//            .build()
//
//        return chain.proceed(request)
    }
}