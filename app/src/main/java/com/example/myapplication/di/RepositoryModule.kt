package com.example.myapplication.di

import com.example.myapplication.provider.NewsProvider
import com.example.myapplication.repository.NewsRepository
import com.example.myapplication.repository.NewsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providerNewsRepository(provider: NewsProvider): NewsRepository =
        NewsRepositoryImp(provider)
    }