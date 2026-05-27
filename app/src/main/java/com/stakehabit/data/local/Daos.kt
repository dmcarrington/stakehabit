package com.stakehabit.data.local

import androidx.room.*
import com.stakehabit.domain.model.Habit
import com.stakehabit.domain.model.HabitCheckIn
import com.stakehabit.domain.model.Stake
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits WHERE isActive = 1 ORDER BY createdAt DESC")
    fun getAllActive(): Flow<List<Habit>>

    @Query("SELECT * FROM habits WHERE id = :id")
    suspend fun getById(id: Long): Habit?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(habit: Habit): Long

    @Query("UPDATE habits SET isActive = 0 WHERE id = :id")
    suspend fun deactivate(id: Long)
}

@Dao
interface CheckInDao {
    @Query("SELECT * FROM habit_checkins WHERE habitId = :habitId ORDER BY date DESC")
    fun getForHabit(habitId: Long): Flow<List<HabitCheckIn>>

    @Query("SELECT * FROM habit_checkins WHERE habitId = :habitId AND date = :date LIMIT 1")
    suspend fun getForDate(habitId: Long, date: String): HabitCheckIn?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(checkIn: HabitCheckIn): Long

    @Query("SELECT COUNT(*) FROM habit_checkins WHERE habitId = :habitId AND date >= :sinceDate")
    suspend fun streakCount(habitId: Long, sinceDate: String): Int
}

@Dao
interface StakeDao {
    @Query("SELECT * FROM stakes WHERE isActive = 1 ORDER BY createdAt DESC")
    fun getAllActive(): Flow<List<Stake>>

    @Query("SELECT * FROM stakes WHERE habitId = :habitId AND isActive = 1")
    fun getForHabit(habitId: Long): Flow<List<Stake>>

    @Query("SELECT SUM(amountSats) FROM stakes WHERE isActive = 1")
    fun totalStaked(): Flow<Long?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(stake: Stake): Long

    @Query("UPDATE stakes SET isActive = 0 WHERE id = :id")
    suspend fun deactivate(id: Long)
}
