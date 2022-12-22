package com.example.primerproyecto.utils

sealed class AsyncResult<T>(val data:T?=null, val message:String?=null) {
    class Loading<T>(data: T? = null) : AsyncResult<T>(data)
    class Success<T>(data: T) : AsyncResult<T>(data)
    class Error<T>(message: String, data: T?= null) : AsyncResult<T>(data, message)
}