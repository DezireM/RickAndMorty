package com.example.aruuke_hw2_6m.mvvm

import com.example.aruuke_hw2_6m.data.api.CartoonApiService
import javax.inject.Inject

class CartoonRepository @Inject constructor(
    private val apiService: CartoonApiService) {

    suspend fun getAllCharacters() = apiService.getAllCharacters()
}