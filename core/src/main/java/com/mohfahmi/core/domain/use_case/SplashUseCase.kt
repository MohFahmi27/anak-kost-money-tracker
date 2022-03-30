package com.mohfahmi.core.domain.use_case

import com.mohfahmi.core.domain.utils.UserState
import kotlinx.coroutines.flow.Flow

interface SplashUseCase {
    fun readAppStatus(): Flow<UserState>
}