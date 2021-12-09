package com.edw88.baricharafriends.data

import com.edw88.baricharafriends.model.Sitios
import retrofit2.http.GET

interface ApiService {

    @GET("Barichara-s-Friends/Barichara-friends-fragments/sitios")
    suspend fun getSitios(): Sitios
}