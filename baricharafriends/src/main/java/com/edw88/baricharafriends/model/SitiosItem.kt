package com.edw88.baricharafriends.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SitiosItem(
    @SerializedName("calificacion")
    val calificacion: Float,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("urlFoto")
    val urlFoto: String,
    @SerializedName("descripcionLarga")
    val descripcionlarga : String,
    @SerializedName("latitud")
    val latitud : Double,
    @SerializedName("longitud")
    val longitud : Double
) : Serializable