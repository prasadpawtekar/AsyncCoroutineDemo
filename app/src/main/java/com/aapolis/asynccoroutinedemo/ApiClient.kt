package com.aapolis.asynccoroutinedemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit: Retrofit by lazy {
        val mRetrofit = Retrofit.Builder()
            .baseUrl("https://psmobitech.com/bankapi/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mRetrofit
    }
}
