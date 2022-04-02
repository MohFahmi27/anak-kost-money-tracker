package com.mohfahmi.core.domain.use_case.interactors

import com.mohfahmi.core.domain.repository.DataStoreRepository
import com.mohfahmi.core.domain.use_case.ProfileUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProfileInteractor(private val dataStoreRepository: DataStoreRepository) : ProfileUseCase {
    override fun saveNotification(state: Boolean, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveNotificationToDataStore(state)
        }
    }

    override fun readNotificationState(): Flow<Boolean> = dataStoreRepository.readNotificationFromDataStore()

    override fun readNameUser(): Flow<String> = dataStoreRepository.readNameUserFromDataStore()
}