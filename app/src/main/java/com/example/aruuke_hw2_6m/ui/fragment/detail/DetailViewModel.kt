package com.example.aruuke_hw2_6m.ui.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aruuke_hw2_6m.data.network.model.Character
import com.example.aruuke_hw2_6m.data.repositories.repository.CartoonRepository
import com.example.aruuke_hw2_6m.utils.Resource
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: CartoonRepository
) : ViewModel() {

    private val _characterDetails = MutableLiveData<Resource<Character>>()
    val characterDetails: LiveData<Resource<Character>> get() = _characterDetails

    fun fetchCharacterDetails(characterId: Int) {
        viewModelScope.launch {
            repository.getCharactersById(characterId).observeForever {
                _characterDetails.postValue(it)
            }
        }
    }
}