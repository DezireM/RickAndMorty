package com.example.aruuke_hw2_6m.data.api

import com.example.aruuke_hw2_6m.data.model.BaseResponse
import com.example.aruuke_hw2_6m.data.model.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    fun getAllCharacters(): Call<BaseResponse>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<Character>

}