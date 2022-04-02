package com.mohfahmi.core.domain.use_case.interactors

import com.mohfahmi.core.domain.repository.DataStoreRepository
import com.mohfahmi.core.domain.use_case.EditNameUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditNameInteractor(private val dataStoreRepository: DataStoreRepository) : EditNameUseCase {
    override fun editUsername(name: String, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveNameUserToDataStore(name)
        }
    }
}