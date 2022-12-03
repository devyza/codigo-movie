package com.example.codigotravel.data.source.remote.dto

data class TokenResponse(
    val success: Boolean,
    val expires_at: String,
    val request_token: String,
)