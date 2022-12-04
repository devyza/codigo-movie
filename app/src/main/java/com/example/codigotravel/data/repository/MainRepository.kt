package com.example.codigotravel.data.repository

import com.example.codigotravel.data.DataResult
import com.example.codigotravel.data.source.local.LocalDataSource
import com.example.codigotravel.data.source.remote.RemoteDataSource

class MainRepository(private val pref: PreferencesRepository) {

    private val remote: RemoteDataSource = RemoteDataSource()
    private val local: LocalDataSource = LocalDataSource()

    private var token: String? = null

    suspend fun login(userName: String, password: String): DataResult<Any> {
        return when (val result = remote.login(userName, password)) {
            is DataResult.Success -> {
                updateToken(result.data.request_token, result.data.expires_at)
                result
            }
            else -> result
        }
    }
    private suspend fun updateToken(token: String, expireDate: String) {
        pref.updateToken(token, expireDate)
    }

    private suspend fun deleteToken() {
//        pref.deleteToken(null, null)
    }
}