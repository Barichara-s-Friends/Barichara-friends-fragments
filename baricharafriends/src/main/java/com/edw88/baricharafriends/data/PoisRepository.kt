package com.edw88.baricharafriends.data

class PoisRepository {

    suspend fun getPois() = ApiFactory.retrofit.getPois()

}