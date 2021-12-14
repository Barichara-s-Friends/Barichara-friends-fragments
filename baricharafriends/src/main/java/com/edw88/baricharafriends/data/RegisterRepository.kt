package com.edw88.baricharafriends.data

import android.util.Log
import com.edw88.baricharafriends.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RegisterRepository {

    private lateinit var auth: FirebaseAuth

    suspend fun createUser(email: String, password: String): Any {

        auth = Firebase.auth
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return  result
    }

    suspend fun createUserInDatabase(email: String, username:String): Any{

        val db = Firebase.firestore
        val idUser = db.collection("users").document()
        val user = User(id = idUser.id, email = email, username = username)

        val result = db.collection("users")
            .document(idUser.id)
            .set(user)
            .await()
        return result
    }

}