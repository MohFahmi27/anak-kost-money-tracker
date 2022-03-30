package com.mohfahmi.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveOnBoardingStateToDataStore(state: Boolean)
    suspend fun saveNameUserToDataStore(name: String)
    fun readOnBoardingStateFromDataStore(): Flow<Boolean>
    fun readNameUserFromDataStore(): Flow<String>
}