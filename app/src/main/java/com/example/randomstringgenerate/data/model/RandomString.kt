package com.example.randomstringgenerate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
created by Rachit on 4/7/2025.
 */
@Entity(tableName = "random_strings")
data class RandomString(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val value: String,
    val length: Int,
    val created: String
)