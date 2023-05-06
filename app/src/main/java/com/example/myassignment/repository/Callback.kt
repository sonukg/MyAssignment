package com.example.myassignment.repository

interface Callback<T> {
    fun onSuccess(response: T)
    fun onError(errorCode: String?, errorMessage: String?)
}
