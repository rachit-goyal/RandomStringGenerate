package com.example.randomstringgenerate.data.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomstringgenerate.data.model.RandomString

/**
created by Rachit on 4/7/2025.
 */
@Database(entities = [RandomString::class], version = 1)
abstract class RandomStringDatabase : RoomDatabase() {
    abstract fun dao(): RandomStringDao
}
