package com.edw88.baricharafriends.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edw88.baricharafriends.data.SitiosRepository
import com.edw88.baricharafriends.model.Sitios
import com.edw88.baricharafriends.model.SitiosItem
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream

class ListViewModel : ViewModel() {

    private var listpoiLoad : MutableLiveData<ArrayList<SitiosItem>> = MutableLiveData()
    val onListpoiLoaded : LiveData<ArrayList<SitiosItem>> = listpoiLoad

    private val repository = SitiosRepository()

    fun getSitioFromServer(){
        GlobalScope.launch(Dispatchers.IO) {
            listpoiLoad.postValue(repository.getSitio())
        }
    }

    fun loadMockJson(poiString: InputStream?) {
        val poiString = poiString?.bufferedReader().use { it!!.readText() }
        val gson = Gson()
        listpoiLoad.value = gson.fromJson(poiString, Sitios::class.java)

    }
}