package com.example.aruuke_hw2_6m.ui.fragment.cartoon

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
class CartoonViewModel @Inject constructor(
    private val repository: CartoonRepository
) : ViewModel() {

    private val _characters = MutableLiveData<Resource<List<Character>>>()
    val characters: LiveData<Resource<List<Character>>> get() = _characters

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            val result = repository.getAllCharacters()
            _characters.postValue(result)
        }
    }
}
