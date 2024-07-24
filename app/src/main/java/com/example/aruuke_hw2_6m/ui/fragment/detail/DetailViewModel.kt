package com.example.aruuke_hw2_6m.ui.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aruuke_hw2_6m.data.model.Character
import com.example.aruuke_hw2_6m.data.repository.CartoonRepository
import com.example.aruuke_hw2_6m.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CartoonRepository
) : ViewModel() {

    private val _characterDetails = MutableLiveData<Character>()
    val characterDetails: LiveData<Character> get() = _characterDetails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var characterId: Int? = null

    fun setCharacterId(id: Int) {
        characterId = id
        fetchCharacterDetails()
    }

    private fun fetchCharacterDetails() {
        characterId?.let { id ->
            viewModelScope.launch {
                when (val resource = repository.getCharactersById(id)) {
                    is Resource.Success -> _characterDetails.postValue(resource.data)
                    is Resource.Error -> _error.postValue(resource.message)
                    is Resource.Loading -> {}
                }
            }
        }
    }
}