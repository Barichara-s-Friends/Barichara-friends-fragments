package com.edw88.baricharafriends.data.repository


import com.edw88.baricharafriends.data.ApiFactory
import com.edw88.baricharafriends.model.SitiosItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ListRepository {

    suspend fun getSitio() = ApiFactory.retrofit.getSitio()

    suspend fun getSitioFromFirebase(): ArrayList<SitiosItem> {
        val db = Firebase.firestore

        val result = db.collection("sitiosturisticos").get().await()

        val listSitios: ArrayList<SitiosItem> = arrayListOf()
        for (sitio in result)
            listSitios.add(sitio.toObject<SitiosItem>())

        return listSitios
    }

    fun checkUserConnected(): Boolean{
        val auth: FirebaseAuth = Firebase.auth
        val currentUser = auth.currentUser
        return  currentUser != null
    }
}