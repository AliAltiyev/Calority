package com.altyyev.calority.di

import com.altyyev.calority.data.WeightRepository
import com.altyyev.calority.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideWeightRepository(database: AppDatabase) = WeightRepository(database.weightDao())

}