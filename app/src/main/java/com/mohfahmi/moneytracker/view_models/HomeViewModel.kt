package com.mohfahmi.moneytracker.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mohfahmi.core.domain.models.ActivityDomain
import com.mohfahmi.core.domain.use_case.HomeUseCase

class HomeViewModel(homeUseCase: HomeUseCase) : ViewModel() {
    val getUserName: LiveData<String> = homeUseCase.readUserName().asLiveData()
    val getAllActivityData: LiveData<List<ActivityDomain>> =
        homeUseCase.getAllActivityData().asLiveData()
    val getIncome: LiveData<Long> = homeUseCase.readSumIncome().asLiveData()
    val getExpenses: LiveData<Long> = homeUseCase.readSumExpenses().asLiveData()
}