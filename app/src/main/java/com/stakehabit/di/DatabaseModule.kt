package com.stakehabit.di

import android.content.Context
import androidx.room.Room
import com.stakehabit.data.local.CheckInDao
import com.stakehabit.data.local.HabitDao
import com.stakehabit.data.local.StakeDao
import com.stakehabit.data.local.StakeHabitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StakeHabitDatabase = Room.databaseBuilder(
        context,
        StakeHabitDatabase::class.java,
        "stakehabit.db"
    ).build()

    @Provides
    fun provideHabitDao(db: StakeHabitDatabase): HabitDao = db.habitDao()

    @Provides
    fun provideCheckInDao(db: StakeHabitDatabase): CheckInDao = db.checkInDao()

    @Provides
    fun provideStakeDao(db: StakeHabitDatabase): StakeDao = db.stakeDao()
}
