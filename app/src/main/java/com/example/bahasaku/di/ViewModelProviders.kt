package com.example.bahasaku.di

import com.example.bahasaku.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ViewModelProviders {

    @Provides
    @Singleton
    fun provireRepository() = Repository()
}