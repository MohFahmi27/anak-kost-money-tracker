package com.mohfahmi.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveOnBoardingStateToDataStore(state: Boolean)
    fun readOnBoardingStateFromDataStore(): Flow<Boolean>
}