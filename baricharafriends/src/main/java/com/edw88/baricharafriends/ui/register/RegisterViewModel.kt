package com.edw88.baricharafriends.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edw88.baricharafriends.data.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private var userRegister: MutableLiveData<String> = MutableLiveData()
    val onUserRegistered: LiveData<String> = userRegister

    private var userCreate : MutableLiveData<String> = MutableLiveData()
    val onUserCreated: LiveData<String> = userCreate

    private val registerRepository = RegisterRepository()

    fun register(email: String, password: String) {
       GlobalScope.launch(Dispatchers.IO){
           userRegister.postValue(registerRepository.createUser(email, password))
           }
    }

    fun createUserAccount(email: String, username: String) {
        GlobalScope.launch(Dispatchers.IO){
            userCreate.postValue(registerRepository.createUserInDatabase(email, username))
        }
    }
}