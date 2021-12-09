package com.edw88.baricharafriends.data

class SitiosRepository {

    suspend fun getSitios() = ApiFactory.retrofit.getSitios()

}