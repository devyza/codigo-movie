package com.example.codigotravel.data.source.remote

import com.example.codigotravel.data.DataResult
import com.example.codigotravel.data.source.remote.dto.SessionRequest
import com.example.codigotravel.data.source.remote.dto.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RemoteDataSource(client: OkHttpClient? = null) {

    companion object {
        private const val URL = "https://api.themoviedb.org/"
        private const val API_KEY = "3a0528c730d6337131f8afecb2d2a382"
        private const val LANG = "en-US"
        private val DISPATCHER = Dispatchers.IO
    }

    private var remote: RetrofitService

    init {
        remote = getService(client)
    }

    private fun getService(client: OkHttpClient? = null): RetrofitService {
        return with(Retrofit.Builder()) {
            addConverterFactory(ScalarsConverterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(URL)
            client?.let { client(it) }
            build().create(RetrofitService::class.java)
        }
    }

    suspend fun login(userName: String, password: String): DataResult<TokenResponse> {
        return when (val result = requestToken()) {
            is DataResult.Success -> requestSession(userName, password, result.data.request_token)
            else -> result
        }
    }
    private suspend fun requestToken() = getResult(remote.requestToken(API_KEY))
    private suspend fun requestSession(userName: String, password: String, token: String) = getResult(
        remote.requestSession(API_KEY, SessionRequest(userName, password, token))
    )

    private suspend fun getRecommendations() = getResult(
        remote.getRecommendations(API_KEY, LANG, 1)
    )

    private suspend fun getUpComingMovies() = getResult(
        remote.getUpComingMovies(API_KEY, LANG, 1)
    )

    private suspend fun <T> getResult(response: Response<T>): DataResult<T> =
        withContext(DISPATCHER) {
            return@withContext try {
                when(response.code()) {
                    200 -> DataResult.Success(response.body()!!)
                    else -> DataResult.Failure("Error Code ${response.code()}")
                }
            } catch (e: Exception) {
                DataResult.Failure(e.message.toString())
            }
        }
}