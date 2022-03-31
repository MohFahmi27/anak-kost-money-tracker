package com.mohfahmi.core.domain.use_case

import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    fun readUserName(): Flow<String>
}