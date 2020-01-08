package com.ahrybuk.swivltest.api.configuration

import com.ahrybuk.swivltest.api.ApiConsts
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    val retrofit = buildRetrofit()

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConsts.ENDPOINT)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}