package com.edw88.baricharafriends.data.repository

import android.util.Log
import com.edw88.baricharafriends.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RegisterRepository {

    private lateinit var auth: FirebaseAuth

    suspend fun createUser(email: String, password: String): String {
        return  try {
            auth = Firebase.auth
            auth.createUserWithEmailAndPassword(email, password).await()
            "Usuario registrado exitosamente"
        }catch (e: FirebaseAuthException){
            return e.message.toString()
        }

    }

    suspend fun createUserInDatabase(email: String, username:String): String{

        val db = Firebase.firestore
        return  try {
            val idUser = db.collection("users").document()
            val user = User(id = idUser.id, email = email, username = username)

            db.collection("users")
                .document(idUser.id)
                .set(user)
                .await()
            "Usuario creado de forma exitosa"
        }catch (e: FirebaseFirestoreException){
            return e.message.toString()
        }
    }
}