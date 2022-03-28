package com.mohfahmi.core.domain.use_case.interactors

import com.mohfahmi.core.domain.repository.DataStoreRepository
import com.mohfahmi.core.domain.use_case.SplashUseCase
import kotlinx.coroutines.flow.Flow

class SplashInteractor(private val dataStoreRepository: DataStoreRepository) : SplashUseCase {
    override fun readOnBoardingState(): Flow<Boolean> = dataStoreRepository.readOnBoardingStateFromDataStore()
}