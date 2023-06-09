package com.example.bahasaku.di

import android.content.Context
import com.example.bahasaku.model.room.BDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryProvider {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context, coroutineScope: CoroutineScope) =
        BDatabase.getDatabase(context, coroutineScope)

    @Provides
    @Singleton
    fun provideCoroutineScopes() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideRoomDao(bDatabase: BDatabase) = bDatabase.dao()
}