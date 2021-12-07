package com.edw88.baricharafriends.data

import com.edw88.baricharafriends.model.Sitios
import retrofit2.http.GET

interface ApiService {

    @GET("christianrruiz/pruebas/pois")
    suspend fun getPois(): Sitios

}