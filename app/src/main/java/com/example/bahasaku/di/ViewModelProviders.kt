package com.example.bahasaku.di

import com.example.bahasaku.model.repository.FirestoreRepository
import com.example.bahasaku.model.repository.RoomRepository
import com.example.bahasaku.model.room.BDao
import com.example.bahasaku.model.repository.AuthRepository
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
    fun provideRoomRepository(dao: BDao) = RoomRepository(dao)

    @Provides
    @Singleton
    fun provideAuthRepository() = AuthRepository()

    @Provides
    @Singleton
    fun provideFirestoreRepository(auth: AuthRepository) = FirestoreRepository(auth)
}