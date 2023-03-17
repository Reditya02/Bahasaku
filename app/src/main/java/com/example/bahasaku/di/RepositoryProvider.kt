package com.example.bahasaku.di

import android.content.Context
import com.example.bahasaku.data.room.BDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryProvider {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context) = BDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideRoomDao(bDatabase: BDatabase) = bDatabase.dao()
}