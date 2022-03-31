package com.mohfahmi.core.domain.repository

import com.mohfahmi.core.domain.models.ActivityDomain
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    fun getAllActivityData(): Flow<List<ActivityDomain>>
    fun getActivityDetailData(id: Long): Flow<ActivityDomain>
    fun getSumExpensesData(): Flow<Long>
    fun getSumIncomeData(): Flow<Long>
    suspend fun insertActivityData(activityDomain: ActivityDomain)
    suspend fun updateActivityData(activityDomain: ActivityDomain)
    suspend fun deleteActivityData(activityDomain: ActivityDomain)
}