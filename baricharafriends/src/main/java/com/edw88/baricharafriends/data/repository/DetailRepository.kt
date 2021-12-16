package com.edw88.baricharafriends.data.repository

import com.edw88.baricharafriends.Baricharafriendsfragments
import com.edw88.baricharafriends.data.local.SitioDao
import com.edw88.baricharafriends.data.local.SitioLocal
import com.edw88.baricharafriends.model.Sitios
import com.edw88.baricharafriends.model.SitiosItem
import java.sql.Types

class DetailRepository {
    fun saveInFavorites(sitios: SitiosItem) {

        val sitioLocal = SitioLocal(
            id = Types.NULL,
            calificacion = sitios.calificacion,
            descripcion = sitios.descripcion,
            nombre = sitios.nombre,
            urlFoto = sitios.urlFoto,
            descripcionLarga = sitios.descripcionLarga,
            latitud = sitios.latitud,
            longitud = sitios.longitud
        )
        
        val sitioDao : SitioDao = Baricharafriendsfragments.database.SitioDao()
        sitioDao.createSitio(sitioLocal)

    }
}