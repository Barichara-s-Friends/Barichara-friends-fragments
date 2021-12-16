package com.edw88.baricharafriends.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SitioLocal::class], version = 1)
abstract class SitioDatabase: RoomDatabase() {

    abstract  fun SitioDao(): SitioDao
}