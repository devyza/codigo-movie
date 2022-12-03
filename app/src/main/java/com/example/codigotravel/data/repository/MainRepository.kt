package com.example.codigotravel.data.repository

import android.content.Context
import com.example.codigotravel.data.source.local.LocalDataSource
import com.example.codigotravel.data.source.remote.RemoteDataSource

class MainRepository(context: Context) {

    private val remote: RemoteDataSource = RemoteDataSource()
    private val local: LocalDataSource = LocalDataSource()

    suspend fun login(userName: String, password: String) = remote.login(userName, password)
}