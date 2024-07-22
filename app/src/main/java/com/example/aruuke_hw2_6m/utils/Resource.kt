package com.example.aruuke_hw2_6m.utils

sealed class Resource<T> {

    class Loading <T> : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
    class Success<T>(val data: T) : Resource<T>()
}