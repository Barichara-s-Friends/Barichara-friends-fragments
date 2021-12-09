package com.edw88.baricharafriends.data

class SitiosRepository {

    suspend fun getSitio() = ApiFactory.retrofit.getSitio()
}