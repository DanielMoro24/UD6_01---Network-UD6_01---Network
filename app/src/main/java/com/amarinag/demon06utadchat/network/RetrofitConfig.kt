package com.amarinag.demon06utadchat.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    private val logging = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    //logging.setLevel(Level.BASIC);

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.1.200.111:3000/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val SERVICE: UserService = retrofit.create(UserService::class.java)
}