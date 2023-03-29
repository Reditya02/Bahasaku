package com.example.bahasaku.di

import com.example.bahasaku.data.Repository
import com.example.bahasaku.data.room.BDao
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
    fun provideRepository(dao: BDao) = Repository(dao)
}