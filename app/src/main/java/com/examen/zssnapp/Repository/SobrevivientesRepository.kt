package com.examen.zssnapp.Repository

import com.examen.zssnapp.Services.SobrevivienteInjector
import com.examen.zssnapp.Services.SobrevivienteServices

class SobrevivientesRepository(private val sobrevivienteService: SobrevivienteServices? = SobrevivienteInjector.injectDoggoApiService()) {
    suspend fun getSobrevientes() = sobrevivienteService?.getSobrevientes()

}