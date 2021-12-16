package com.edw88.baricharafriends

import android.app.Application
import androidx.room.Room
import com.edw88.baricharafriends.data.local.SitioDatabase

class Baricharafriendsfragments : Application() {

    companion object{
        lateinit var  database: SitioDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            SitioDatabase::class.java,
            "sitio.db"
        ).build()
    }
}