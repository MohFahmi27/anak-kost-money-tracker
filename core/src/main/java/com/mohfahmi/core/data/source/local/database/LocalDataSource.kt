package com.mohfahmi.core.data.source.local.database

import com.mohfahmi.core.data.source.local.database.db.ActivityDao
import com.mohfahmi.core.data.source.local.database.entity.ActivityEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val activityDao: ActivityDao) : ILocalDataSource {
    override fun getAllActivityData(): Flow<List<ActivityEntity>> = activityDao.getAllActivity()

    override fun getActivityDetailData(id: Long): Flow<ActivityEntity> =
        activityDao.getActivityDetail(id)

    override fun getSumExpensesData(): Flow<Long> = activityDao.getSumExpenses()

    override fun getSumIncomeData(): Flow<Long> = activityDao.getSumIncome()

    override suspend fun insertActivityData(activityEntity: ActivityEntity) {
        activityDao.insertActivity(activityEntity)
    }

    override suspend fun updateActivityData(activityEntity: ActivityEntity) {
        activityDao.updateActivity(activityEntity)
    }

    override suspend fun deleteActivityData(activityEntity: ActivityEntity) {
        activityDao.deleteActivity(activityEntity)
    }
}