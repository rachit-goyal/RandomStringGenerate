package com.example.randomstringgenerate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomstringgenerate.data.model.RandomString
import com.example.randomstringgenerate.domain.repository.RandomStringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
created by Rachit on 4/7/2025.
 */
@HiltViewModel
class RandomStringViewModel @Inject constructor(
    private val repository: RandomStringRepository
) : ViewModel() {

    val allStrings: StateFlow<List<RandomString>> =
        repository.getAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun generate(length: Int) = viewModelScope.launch {
        val result = repository.generateAndSave(length)
        if (!result) {
            _errorMessage.value = "Failed to generate string. Try again."
        } else {
            _errorMessage.value = null
        }    }

    fun deleteAll() = viewModelScope.launch { repository.deleteAll() }
    fun deleteOne(item: RandomString) = viewModelScope.launch { repository.deleteOne(item) }
}