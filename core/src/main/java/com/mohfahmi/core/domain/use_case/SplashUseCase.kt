package com.mohfahmi.core.domain.use_case

import kotlinx.coroutines.flow.Flow

interface SplashUseCase {
    fun readOnBoardingState(): Flow<Boolean>
}