package com.enons.paparaproject.di

import android.content.Context
import androidx.room.Room
import com.enons.paparaproject.data.local.db.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideMealDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MealDatabase::class.java,
        "recipe_box_database"
    )
        .fallbackToDestructiveMigration()
        .build()
}
