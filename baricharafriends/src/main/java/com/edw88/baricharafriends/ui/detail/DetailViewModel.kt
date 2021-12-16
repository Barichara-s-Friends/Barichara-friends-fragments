package com.edw88.baricharafriends.ui.detail

import androidx.lifecycle.ViewModel
import com.edw88.baricharafriends.data.repository.DetailRepository
import com.edw88.baricharafriends.model.SitiosItem

class DetailViewModel : ViewModel() {

    val detailRepository = DetailRepository()

    fun saveInfavorites(sitios: SitiosItem) {

        detailRepository.saveInFavorites(sitios)
    }
}