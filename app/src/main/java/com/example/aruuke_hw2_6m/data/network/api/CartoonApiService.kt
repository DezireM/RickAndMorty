package com.example.aruuke_hw2_6m.data.network.api

import com.example.aruuke_hw2_6m.data.network.model.BaseResponse
import com.example.aruuke_hw2_6m.data.network.model.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    suspend fun getAllCharacters(): BaseResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character

}