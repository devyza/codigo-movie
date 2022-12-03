package com.example.codigotravel.data.source.remote.dto

data class SessionRequest(
    val username: String,
    val password: String,
    val request_token: String,
)