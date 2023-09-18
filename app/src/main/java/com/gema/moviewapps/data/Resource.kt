package com.gema.moviewapps.data

sealed class Resouce<T> {
    class Loading<T> : Resouce<T>()
    data class Success<T>(val data: T) : Resouce<T>()
    data class Error<T>(val error: String) : Resouce<T>()
}