package com.mohfahmi.core.domain.use_case.interactors

import com.mohfahmi.core.domain.repository.DataStoreRepository
import com.mohfahmi.core.domain.use_case.OnBoardingUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnBoardingInteractor(private val dataStoreRepository: DataStoreRepository) :
    OnBoardingUseCase {
    override fun saveState(state: Boolean, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveOnBoardingStateToDataStore(state)
        }
    }

    override fun saveUserName(name: String, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveNameUserToDataStore(name)
        }
    }
}