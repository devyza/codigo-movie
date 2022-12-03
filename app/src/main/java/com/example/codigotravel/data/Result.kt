package com.example.codigotravel.data

sealed class DataResult<out R> {
    data class Success<out T>(val data: T): DataResult<T>()
    data class Error(val message: String?): DataResult<Nothing>() {
        override fun toString(): String {  return message?: "" }
    }
    data class Failure(val message: String?): DataResult<Nothing>() {
        override fun toString(): String {  return message?: "" }
    }
    object Unauthorized: DataResult<Nothing>()
}