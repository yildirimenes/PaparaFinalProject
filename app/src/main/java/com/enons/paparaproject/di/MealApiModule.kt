package com.enons.paparaproject.di

import com.enons.paparaproject.data.repository.MealRepository
import com.enons.paparaproject.data.repository.MealRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MealApiModule {

    @Provides
    @Singleton
    fun provideMealRepository(mealRepositoryImpl: MealRepositoryImpl): MealRepository {
        return mealRepositoryImpl
    }
}

