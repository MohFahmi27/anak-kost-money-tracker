package com.mohfahmi.core.data.source.local.database.db

import androidx.room.*
import com.mohfahmi.core.data.source.local.database.entity.ActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activity ORDER BY id DESC")
    fun getAllActivity(): Flow<List<ActivityEntity>>

    @Query("SELECT * FROM activity WHERE id=:id")
    fun getActivityDetail(id: Long): Flow<ActivityEntity>

    @Query("SELECT SUM(amount) FROM activity WHERE is_expenses=1")
    fun getSumExpenses(): Flow<Long>

    @Query("SELECT SUM(amount) FROM activity WHERE is_expenses=0")
    fun getSumIncome(): Flow<Long>

    @Query("SELECT SUM(amount) FROM activity")
    fun getSumAll(): Flow<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activityEntity: ActivityEntity)

    @Update
    suspend fun updateActivity(activityEntity: ActivityEntity)

    @Delete
    suspend fun deleteActivity(activityEntity: ActivityEntity)
}