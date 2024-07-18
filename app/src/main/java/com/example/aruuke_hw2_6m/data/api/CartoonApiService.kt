package com.example.aruuke_hw2_6m.data.api

import com.example.aruuke_hw2_6m.data.model.BaseResponse
import retrofit2.http.GET

interface CartoonApiService {
    @GET("character")
    suspend fun getAllCharacters(): BaseResponse
}