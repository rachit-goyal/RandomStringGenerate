package com.example.randomstringgenerate.data.repository

import android.content.Context
import com.example.randomstringgenerate.domain.repository.RandomStringRepository
import com.example.randomstringgenerate.component.util.ContentProviderHelper
import com.example.randomstringgenerate.data.model.RandomString
import com.example.randomstringgenerate.data.remote.RandomStringDao
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
/**
created by Rachit on 4/7/2025.
 */
class RandomStringRepositoryImpl @Inject constructor(
    private val dao: RandomStringDao,
    @ApplicationContext private val context: Context,
) : RandomStringRepository {

    override fun getAll(): Flow<List<RandomString>> = dao.getAll()

    override suspend fun generateAndSave(length: Int):Boolean {
        val result = ContentProviderHelper.fetchRandomString(context, length)
        return if (result != null) {
            dao.insert(result)
            false
        } else {
            false
        }
    }

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun deleteOne(item: RandomString) = dao.deleteOne(item)
}