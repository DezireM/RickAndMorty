package com.example.aruuke_hw2_6m.ui.fragment.cartoon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aruuke_hw2_6m.data.network.model.Character
import com.example.aruuke_hw2_6m.data.repositories.repository.CartoonRepository
import com.example.aruuke_hw2_6m.utils.Resource
import kotlinx.coroutines.launch

class CartoonViewModel(
    private val repository: CartoonRepository
) : ViewModel() {

    private val _characters = MutableLiveData<Resource<List<Character>>>()
    val characters: LiveData<Resource<List<Character>>> get() = _characters

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            repository.getAllCharacters().observeForever {
                _characters.postValue(it)
            }
        }
    }
}