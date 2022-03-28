package com.mohfahmi.moneytracker.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mohfahmi.core.domain.use_case.SplashUseCase

class SplashViewModel(private val splashUseCase: SplashUseCase): ViewModel() {
    fun readOnBoardingState(): LiveData<Boolean> = splashUseCase.readOnBoardingState().asLiveData()
}