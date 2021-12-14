package com.edw88.baricharafriends.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edw88.baricharafriends.data.repository.ListRepository
import com.edw88.baricharafriends.model.Sitios
import com.edw88.baricharafriends.model.SitiosItem
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream

class ListViewModel : ViewModel() {

    private val listRepository = ListRepository()

    private var listpoiLoad : MutableLiveData<ArrayList<SitiosItem>> = MutableLiveData()
    val onListpoiLoaded : LiveData<ArrayList<SitiosItem>> = listpoiLoad

    private var userLogin : MutableLiveData<Boolean> = MutableLiveData()
    val onUserLoggedIn: LiveData<Boolean> = userLogin

    fun checkUserConnected(){
        userLogin.value = listRepository.checkUserConnected()
    }

    fun getSitioFromServer(){
        GlobalScope.launch(Dispatchers.IO) {
            listpoiLoad.postValue(listRepository.getSitio())
        }
    }

    fun loadMockFromJson(poiString: InputStream?) {
        val poiString = poiString?.bufferedReader().use { it!!.readText() }
        val gson = Gson()
        listpoiLoad.value = gson.fromJson(poiString, Sitios::class.java)

    }

    fun getSitioFromFirebase() {
        GlobalScope.launch(Dispatchers.IO){
            listpoiLoad.postValue(listRepository.getSitioFromFirebase())
        }
    }
}