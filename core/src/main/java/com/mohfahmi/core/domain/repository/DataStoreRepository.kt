package com.mohfahmi.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveOnBoardingStateToDataStore(state: Boolean)
    suspend fun saveNameUserToDataStore(name: String)
    suspend fun saveNotificationToDataStore(state: Boolean)
    fun readOnBoardingStateFromDataStore(): Flow<Boolean>
    fun readNameUserFromDataStore(): Flow<String>
    fun readNotificationFromDataStore(): Flow<Boolean>
}