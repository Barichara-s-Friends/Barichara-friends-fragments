package com.edw88.baricharafriends.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edw88.baricharafriends.data.RegisterRepository
import com.edw88.baricharafriends.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {



    private var userCreate : MutableLiveData<Boolean> = MutableLiveData()
    val onUserCreated: LiveData<Boolean> = userCreate

    private val registerRepository = RegisterRepository()

    fun register(email: String, password: String) {
       GlobalScope.launch(Dispatchers.IO){
           val result = registerRepository.createUser(email,password)
       }
    }

    fun createUserAccount(email: String, username: String) {
        GlobalScope.launch(Dispatchers.IO){
            val result = registerRepository.createUserInDatabase(email, username)
        }



    }

}