package com.example.medimateadmin.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api = Retrofit.Builder().baseUrl(API_Builder.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(API_Builder::class.java)
}