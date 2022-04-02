package com.mohfahmi.moneytracker.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mohfahmi.core.domain.models.ActivityDomain
import com.mohfahmi.core.domain.use_case.HomeUseCase
import com.mohfahmi.moneytracker.utils.toCurrencyFormat

class HomeViewModel(homeUseCase: HomeUseCase) : ViewModel() {
    val getUserName: LiveData<String> = homeUseCase.readUserName().asLiveData()
    val getAllActivityData: LiveData<List<ActivityDomain>> =
        homeUseCase.getAllActivityData().asLiveData()

    private val _getIncome: LiveData<Long> = homeUseCase.readSumIncome().asLiveData()
    val getIncome: LiveData<String> = Transformations.map(_getIncome) {
        it.toCurrencyFormat()
    }
    private val _getExpenses: LiveData<Long> = homeUseCase.readSumExpenses().asLiveData()
    val getExpenses: LiveData<String> = Transformations.map(_getExpenses) {
        it.toCurrencyFormat()
    }
}