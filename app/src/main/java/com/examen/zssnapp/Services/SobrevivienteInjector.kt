package com.examen.zssnapp.Services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SobrevivienteInjector {
    private const val BASE_URL = "http://192.168.100.37:8080/"
    private var retrofit: Retrofit? = null

    fun getIntance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }


    fun injectDoggoApiService(retrofit: Retrofit = getIntance()): SobrevivienteServices {
        return retrofit.create(SobrevivienteServices::class.java)
    }
}