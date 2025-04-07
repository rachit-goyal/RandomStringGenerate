
package com.example.randomstringgenerate.di

import android.content.Context
import androidx.room.Room
import com.example.randomstringgenerate.data.remote.RandomStringDao
import com.example.randomstringgenerate.data.remote.RandomStringDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
created by Rachit on 4/7/2025.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RandomStringDatabase =
        Room.databaseBuilder(
            context,
            RandomStringDatabase::class.java,
            "random_string_db"
        ).build()

    @Provides
    fun provideDao(db: RandomStringDatabase): RandomStringDao = db.dao()

}
