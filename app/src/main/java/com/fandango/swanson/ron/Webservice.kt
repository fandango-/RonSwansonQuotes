package com.fandango.swanson.ron

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Webservice {
    @GET("v2/quotes")
    fun getQuote(): Call<List<String>>

    companion object {
        private const val BASE_URL = "https://ron-swanson-quotes.herokuapp.com/"

        fun create(): Webservice = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Webservice::class.java)
    }
}