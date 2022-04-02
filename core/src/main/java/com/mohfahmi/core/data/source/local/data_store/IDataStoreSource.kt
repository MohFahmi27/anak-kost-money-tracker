package com.mohfahmi.core.data.source.local.data_store

import kotlinx.coroutines.flow.Flow

interface IDataStoreSource {
    suspend fun saveOnBoardingState(state: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveNameUser(name: String)
    fun readNameUser(): Flow<String>
    suspend fun saveNotificationState(state: Boolean)
    fun readNotificationState(): Flow<Boolean>
}