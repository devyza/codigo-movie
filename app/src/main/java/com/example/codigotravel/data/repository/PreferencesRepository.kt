package com.example.codigotravel.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class PreferencesRepository(private val dataStore: DataStore<Preferences>) {

    suspend fun updateToken(token: String, tokenExpire: String) {
        dataStore.edit { preferences ->
            preferences[PrefKeys.TOKEN] = token
            preferences[PrefKeys.TOKEN_EXPIRE] = tokenExpire
        }
    }

    suspend fun getToken() = dataStore.data.map { it[PrefKeys.TOKEN] }

    private object PrefKeys {
        val TOKEN = stringPreferencesKey("token")
        val TOKEN_EXPIRE = stringPreferencesKey("expire")
    }
}