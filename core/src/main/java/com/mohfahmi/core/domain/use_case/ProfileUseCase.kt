package com.mohfahmi.core.domain.use_case

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {
    fun saveNotification(state: Boolean, coroutineScope: CoroutineScope)
    fun readNotificationState(): Flow<Boolean>
    fun readNameUser(): Flow<String>
}