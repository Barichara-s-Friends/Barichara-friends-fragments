package com.edw88.baricharafriends.data.local

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface SitioDao {

    @Insert
    fun createSitio(sitioLocal: SitioLocal)
}