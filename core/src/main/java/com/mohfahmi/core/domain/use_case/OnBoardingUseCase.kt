package com.mohfahmi.core.domain.use_case

import kotlinx.coroutines.CoroutineScope

interface OnBoardingUseCase {
    fun saveState(state: Boolean, coroutineScope: CoroutineScope)
}