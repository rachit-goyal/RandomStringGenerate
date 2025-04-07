
package com.example.randomstringgenerate.data.remote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomstringgenerate.data.model.RandomString
import kotlinx.coroutines.flow.Flow
/**
created by Rachit on 4/7/2025.
 */
@Dao
interface RandomStringDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(randomString: RandomString)

    @Query("SELECT * FROM random_strings ORDER BY id DESC")
    fun getAll(): Flow<List<RandomString>>

    @Query("DELETE FROM random_strings")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteOne(item: RandomString)
}