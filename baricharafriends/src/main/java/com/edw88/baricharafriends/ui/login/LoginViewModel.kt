package com.edw88.baricharafriends.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edw88.baricharafriends.data.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private var userLogin : MutableLiveData<String> = MutableLiveData()
    val onUserLoggedIn: LiveData<String> = userLogin

    private val loginRepository = LoginRepository()

    fun login(email: String, password: String) {
        GlobalScope.launch(Dispatchers.IO) {
            userLogin.postValue(loginRepository.signInUser(email,password))
        }

    }

}