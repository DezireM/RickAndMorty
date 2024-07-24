package com.example.aruuke_hw2_6m.data.api

import com.example.aruuke_hw2_6m.data.model.BaseResponse
import com.example.aruuke_hw2_6m.data.model.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    suspend fun getAllCharacters(): Response<BaseResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Character>

}