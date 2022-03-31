package com.mohfahmi.core.domain.use_case.interactors

import com.mohfahmi.core.domain.repository.DataStoreRepository
import com.mohfahmi.core.domain.use_case.HomeUseCase
import kotlinx.coroutines.flow.Flow

class HomeInteractor(private val dataStoreRepository: DataStoreRepository): HomeUseCase {
    override fun readUserName(): Flow<String> = dataStoreRepository.readNameUserFromDataStore()
}