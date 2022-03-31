package com.mohfahmi.core.domain.use_case.interactors

import com.mohfahmi.core.domain.models.ActivityDomain
import com.mohfahmi.core.domain.repository.ActivityRepository
import com.mohfahmi.core.domain.use_case.AddUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AddInteractor(private val activityRepository: ActivityRepository) : AddUseCase {
    override fun insertData(activityDomain: ActivityDomain, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.IO) {
            activityRepository.insertActivityData(activityDomain)
        }
    }

    override fun updateData(activityDomain: ActivityDomain, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.IO) {
            activityRepository.updateActivityData(activityDomain)
        }
    }

    override fun deleteData(activityDomain: ActivityDomain, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.IO) {
            activityRepository.deleteActivityData(activityDomain)
        }
    }

    override fun getDetailData(id: Long): Flow<ActivityDomain> =
        activityRepository.getActivityDetailData(id)
}