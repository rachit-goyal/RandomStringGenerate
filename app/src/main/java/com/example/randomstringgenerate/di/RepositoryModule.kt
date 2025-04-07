package com.example.randomstringgenerate.di

import com.example.randomstringgenerate.domain.repository.RandomStringRepository
import com.example.randomstringgenerate.data.repository.RandomStringRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
created by Rachit on 4/7/2025.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        impl: RandomStringRepositoryImpl
    ): RandomStringRepository
}