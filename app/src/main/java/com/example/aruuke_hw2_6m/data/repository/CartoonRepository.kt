package com.example.aruuke_hw2_6m.data.repository

import com.example.aruuke_hw2_6m.data.api.CartoonApiService
import com.example.aruuke_hw2_6m.data.model.Character
import com.example.aruuke_hw2_6m.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class CartoonRepository(
    private val apiService: CartoonApiService
) {

    suspend fun getAllCharacters(): Resource<List<Character>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getAllCharacters()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!.characters)
            } else {
                Resource.Error("Server Error")
            }
        } catch (e: Exception) {
            Resource.Error(handleException(e))
        }
    }

    suspend fun getCharactersById(id: Int): Resource<Character> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getCharacterById(id)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Server Error")
            }
        } catch (e: Exception) {
            Resource.Error(handleException(e))
        }
    }

    private fun handleException(e: Exception): String {
        return when (e) {
            is IOException -> e.localizedMessage ?: "Network Error"
            is HttpException -> e.localizedMessage ?: "Server Error"
            else -> e.localizedMessage ?: "Unknown message"
        }
    }
}