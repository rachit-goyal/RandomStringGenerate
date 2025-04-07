package com.example.randomstringgenerate.domain.repository

import com.example.randomstringgenerate.data.model.RandomString
import kotlinx.coroutines.flow.Flow
/**
created by Rachit on 4/7/2025.
 */
interface RandomStringRepository {
    fun getAll(): Flow<List<RandomString>>
    suspend fun generateAndSave(length: Int):Boolean
    suspend fun deleteAll()
    suspend fun deleteOne(item: RandomString)
}