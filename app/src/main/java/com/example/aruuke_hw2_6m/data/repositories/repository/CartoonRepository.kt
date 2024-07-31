package com.example.aruuke_hw2_6m.data.repositories.repository

import androidx.lifecycle.LiveData
import com.example.aruuke_hw2_6m.data.network.api.CartoonApiService
import com.example.aruuke_hw2_6m.data.network.model.Character
import com.example.aruuke_hw2_6m.data.base.repository.BaseRepository
import com.example.aruuke_hw2_6m.utils.Resource

class CartoonRepository(
    private val apiService: CartoonApiService
) : BaseRepository() {

    fun getAllCharacters(): LiveData<Resource<List<Character>>> = doRequest {
        apiService.getAllCharacters().characters
    }

    fun getCharactersById(id: Int): LiveData<Resource<Character>> = doRequest {
        apiService.getCharacterById(id)
    }
}