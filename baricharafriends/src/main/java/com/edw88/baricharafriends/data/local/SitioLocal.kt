package com.edw88.baricharafriends.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_sitio")
data class SitioLocal (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "calificacion")val calificacion: Double = 0.0,
    @ColumnInfo(name = "descripcion")val descripcion: String = "",
    @ColumnInfo(name = "nombre")val nombre: String = "",
    @ColumnInfo(name = "urlFoto")val urlFoto: String = "",
    @ColumnInfo(name = "descripcionLarga")val descripcionLarga : String = "",
    @ColumnInfo(name = "latitud")val latitud : Double = 0.0,
    @ColumnInfo(name = "longitud")val longitud : Double = 0.0
        )