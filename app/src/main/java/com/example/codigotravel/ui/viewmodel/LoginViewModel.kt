package com.example.codigotravel.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.codigotravel.data.DataResult
import com.example.codigotravel.data.repository.MainRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: MainRepository): BaseViewModel() {

    private var _isLoginSuccess = MutableLiveData(false)
    private val isLoginSuccess get() = _isLoginSuccess

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            when (val result = repository.login(userName, password)) {
                is DataResult.Success -> {
                    isLoginSuccess.value = true
                    _message.value = "Success"
                }
                else -> {
                    _message.value = result.toString()
                }
            }
        }
    }
}