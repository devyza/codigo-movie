package com.example.codigotravel.data.repository

import com.example.codigotravel.data.source.local.LocalDataSource
import com.example.codigotravel.data.source.remote.RemoteDataSource

class MainRepository {

    private val remote: RemoteDataSource = RemoteDataSource()
    private val local: LocalDataSource = LocalDataSource()

    suspend fun login(userName: String, password: String) = remote.login(userName, password)
}