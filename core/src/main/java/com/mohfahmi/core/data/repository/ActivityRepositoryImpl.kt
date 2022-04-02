package com.mohfahmi.core.data.repository

import com.mohfahmi.core.data.source.local.database.ILocalDataSource
import com.mohfahmi.core.domain.models.ActivityDomain
import com.mohfahmi.core.domain.repository.ActivityRepository
import com.mohfahmi.core.domain.utils.mapToDomain
import com.mohfahmi.core.domain.utils.mapToEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow

class ActivityRepositoryImpl(private val localDataSource: ILocalDataSource) : ActivityRepository {
    override fun getAllActivityData(): Flow<List<ActivityDomain>> = flow {
        localDataSource.getAllActivityData().collect{ activityEntities ->
            emit(activityEntities.map { it.mapToDomain() })
        }
    }

    override fun getActivityDetailData(id: Long): Flow<ActivityDomain> = flow {
        localDataSource.getActivityDetailData(id).filterNotNull().collect {
            emit(it.mapToDomain())
        }
    }

    override fun getSumExpensesData(): Flow<Long> = localDataSource.getSumExpensesData()

    override fun getSumIncomeData(): Flow<Long> = localDataSource.getSumIncomeData()

    override fun getSumAllData(): Flow<Long> = localDataSource.getAllSumData()

    override suspend fun insertActivityData(activityDomain: ActivityDomain) {
        localDataSource.insertActivityData(activityDomain.mapToEntity())
    }

    override suspend fun updateActivityData(activityDomain: ActivityDomain) {
        localDataSource.updateActivityData(activityDomain.mapToEntity())
    }

    override suspend fun deleteActivityData(activityDomain: ActivityDomain) {
        localDataSource.deleteActivityData(activityDomain.mapToEntity())
    }
}