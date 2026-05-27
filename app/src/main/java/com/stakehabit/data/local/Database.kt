package com.stakehabit.data.local

import androidx.room.*
import com.stakehabit.domain.model.Habit
import com.stakehabit.domain.model.HabitCheckIn
import com.stakehabit.domain.model.Stake

@Database(
    entities = [Habit::class, HabitCheckIn::class, Stake::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class StakeHabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun checkInDao(): CheckInDao
    abstract fun stakeDao(): StakeDao()
}
