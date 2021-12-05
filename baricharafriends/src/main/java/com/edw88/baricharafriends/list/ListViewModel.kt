package com.edw88.baricharafriends.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edw88.baricharafriends.model.Sitios
import com.edw88.baricharafriends.model.SitiosItem
import com.google.gson.Gson
import java.io.InputStream

class ListViewModel : ViewModel() {

    private var listpoiLoad : MutableLiveData<ArrayList<SitiosItem>> = MutableLiveData()
    val onListpoiLoaded : LiveData<ArrayList<SitiosItem>> = listpoiLoad

    fun loadMockJson(poiString: InputStream?) {
        val poiString = poiString?.bufferedReader().use { it!!.readText() }
        val gson = Gson()
        listpoiLoad.value = gson.fromJson(poiString, Sitios::class.java)

    }
}