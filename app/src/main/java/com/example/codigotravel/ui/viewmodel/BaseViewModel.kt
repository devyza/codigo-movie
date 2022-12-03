package com.example.codigotravel.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    internal var _message = MutableLiveData<String?>(null)
    val message get() = _message

    private var _isLoading = MutableLiveData(false)
    private val isLoading get() = _isLoading

    fun resetMessage() {
        _message.value = null
    }

    fun toggleLoading() {
        _isLoading.value?.let {
            _isLoading.value = !it
        }
    }

}