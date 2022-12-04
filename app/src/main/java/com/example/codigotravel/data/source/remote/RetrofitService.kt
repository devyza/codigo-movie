package com.example.codigotravel.data.source.remote

import com.example.codigotravel.data.source.remote.dto.DataResponse
import com.example.codigotravel.data.source.remote.dto.MovieListResponse
import com.example.codigotravel.data.source.remote.dto.SessionRequest
import com.example.codigotravel.data.source.remote.dto.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @GET("/3/authentication/token/new")
    suspend fun requestToken(
        @Query("api_key") api_key: String,
    ): Response<TokenResponse>

    @POST("/3/authentication/token/validate_with_login")
    suspend fun requestSession(
        @Query("api_key") api_key: String,
        @Body sessionRequest: SessionRequest,
    ): Response<TokenResponse>

    @POST("/3/authentication/session")
    suspend fun deleteSession(
        @Query("api_key") api_key: String,
        @Body sessionId: String,
    ): Response<DataResponse>

    @POST("/3/movie/changes")
    suspend fun getRecommendations(
        @Query("api_key") api_key: String,
        @Query("language") language: String?,
        @Query("page") page: Int?,
    ): Response<MovieListResponse>

    @POST("/3/movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("api_key") api_key: String,
        @Query("language") language : String?,
        @Query("page") page: Int?,
    ): Response<DataResponse>
}