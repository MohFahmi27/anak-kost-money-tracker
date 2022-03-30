package com.mohfahmi.core.data.repository

import com.mohfahmi.core.data.source.local.data_store.IDataStoreSource
import com.mohfahmi.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreRepositoryImpl(private val dataStoreSource: IDataStoreSource) : DataStoreRepository {
    override suspend fun saveOnBoardingStateToDataStore(state: Boolean) =
        dataStoreSource.saveOnBoardingState(state)

    override fun readOnBoardingStateFromDataStore(): Flow<Boolean> =
        dataStoreSource.readOnBoardingState()

    override suspend fun saveNameUserToDataStore(name: String) {
        dataStoreSource.saveNameUser(name)
    }

    override fun readNameUserFromDataStore(): Flow<String> = dataStoreSource.readNameUser()
}