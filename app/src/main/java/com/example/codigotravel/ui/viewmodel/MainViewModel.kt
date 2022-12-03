package com.example.codigotravel.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.codigotravel.data.repository.MainRepository
import com.example.codigotravel.data.repository.PreferencesRepository
import com.example.codigotravel.data.repository.dataStore
import kotlinx.coroutines.launch

class MainViewModel(
    repository: MainRepository,
    prefRepository: PreferencesRepository
): ViewModel() {

    private var _token = MutableLiveData<String>()
    val token get() = _token

    init {
        viewModelScope.launch {
            prefRepository.getToken().collect{
                _token.postValue(it)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel(
                    MainRepository(),
                    PreferencesRepository(this[APPLICATION_KEY]!!.dataStore),
                )
            }
        }
    }
}