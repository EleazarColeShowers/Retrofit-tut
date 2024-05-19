package com.example.retrofittut

import com.example.retrofittut.data.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Initialize retrofit
object RetrofitInstance {
    //step 1: create an HTTP Login interceptor to log what we're doing
    private val interceptor: HttpLoggingInterceptor= HttpLoggingInterceptor().apply {
        level= HttpLoggingInterceptor.Level.BODY
    }

    //step 2: create an okhttp client

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    //step 3: create API instance
    val api: Api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())//converts our json body to our data class
        .baseUrl(Api.BASE_URL)//created in our api interface
        .client(client)
        .build()
        .create(Api::class.java)
}