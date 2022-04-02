package com.mohfahmi.core.data.source.local.database

import com.mohfahmi.core.data.source.local.database.entity.ActivityEntity
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    fun getAllActivityData(): Flow<List<ActivityEntity>>
    fun getActivityDetailData(id: Long): Flow<ActivityEntity>
    fun getSumExpensesData(): Flow<Long>
    fun getSumIncomeData(): Flow<Long>
    fun getAllSumData(): Flow<Long>
    suspend fun insertActivityData(activityEntity: ActivityEntity)
    suspend fun updateActivityData(activityEntity: ActivityEntity)
    suspend fun deleteActivityData(activityEntity: ActivityEntity)
}