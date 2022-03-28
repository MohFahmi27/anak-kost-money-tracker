package com.mohfahmi.moneytracker.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohfahmi.core.domain.use_case.OnBoardingUseCase

class OnBoardingViewModel(private val onBoardingUseCase: OnBoardingUseCase): ViewModel() {
    fun saveState(state: Boolean) = onBoardingUseCase.saveState(state, viewModelScope)
}