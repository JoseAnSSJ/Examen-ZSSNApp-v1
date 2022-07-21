package com.examen.zssnapp.Services

import com.examen.zssnapp.Model.Response.SobrevivientesResponse
import retrofit2.http.GET

interface SobrevivienteServices{
    @GET("sobrevivientes/getSobrevivientes")
    suspend fun getSobrevientes(): List<SobrevivientesResponse>
}