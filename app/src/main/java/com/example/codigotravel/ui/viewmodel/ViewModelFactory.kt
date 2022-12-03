package com.example.codigotravel.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codigotravel.data.repository.MainRepository


class ViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MainViewModel:: class.java -> MainViewModel(repository)
            LoginViewModel:: class.java -> LoginViewModel(repository)
            else -> throw IllegalArgumentException("Unknown Class Name")
        } as T
    }
}