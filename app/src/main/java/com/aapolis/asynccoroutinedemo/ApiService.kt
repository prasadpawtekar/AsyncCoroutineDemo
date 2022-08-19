package com.aapolis.asynccoroutinedemo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("axis_balance.php")
    suspend fun axisBalance(@Query("acno") acno: String): Response<BankBalance>

    @GET("hdfc_balance.php")
    suspend fun hdfcBalance(@Query("acno") acno: String): Response<BankBalance>

    companion object {
        fun getInstance() = ApiClient.retrofit.create(ApiService::class.java)
    }
}