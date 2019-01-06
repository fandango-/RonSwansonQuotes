package com.fandango.swanson.ron

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SwansonQuoteRepository {
    private val webservice = Webservice.create()

    fun getQuote(onSuccess: (String?) -> Unit, onFailure: () -> Unit) {
        webservice.getQuote().enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                onFailure()
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                onSuccess(response.body()?.first())
            }
        })
    }
}