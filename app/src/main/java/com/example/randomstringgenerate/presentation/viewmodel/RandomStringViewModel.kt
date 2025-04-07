package com.example.randomstringgenerate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomstringgenerate.data.model.RandomString
import com.example.randomstringgenerate.domain.repository.RandomStringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun generate(length: Int) = viewModelScope.launch {
        repository.generateAndSave(length)
    }

    fun deleteAll() = viewModelScope.launch { repository.deleteAll() }
    fun deleteOne(item: RandomString) = viewModelScope.launch { repository.deleteOne(item) }
}