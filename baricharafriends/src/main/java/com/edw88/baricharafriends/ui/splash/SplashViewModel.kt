package com.edw88.baricharafriends.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashViewModel : ViewModel() {

    private  lateinit var auth: FirebaseAuth

    private var userLogin: MutableLiveData<Boolean> = MutableLiveData()
    val onUserLoggedInt: LiveData<Boolean> = userLogin

    fun checkUserConnected(){
        auth = Firebase.auth

        val currentUser = auth.currentUser
        if(currentUser !=null){
            userLogin.value = true
        }else
            userLogin.value = false
    }
}