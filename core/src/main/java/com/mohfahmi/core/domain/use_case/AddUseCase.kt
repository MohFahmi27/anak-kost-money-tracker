package com.mohfahmi.core.domain.use_case

import com.mohfahmi.core.domain.models.ActivityDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface AddUseCase {
    fun insertData(activityDomain: ActivityDomain, coroutineScope: CoroutineScope)
    fun updateData(activityDomain: ActivityDomain, coroutineScope: CoroutineScope)
    fun deleteData(activityDomain: ActivityDomain, coroutineScope: CoroutineScope)
    fun getDetailData(id: Long): Flow<ActivityDomain>
}