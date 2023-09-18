package com.gema.moviewapps.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gema.moviewapps.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel(){
    fun getHome(token: String) = dataRepository.getDataMoview(token).asLiveData()
}