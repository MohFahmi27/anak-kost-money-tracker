package com.mohfahmi.moneytracker.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mohfahmi.core.domain.use_case.ArticleUseCase

class ArticleViewModel(articleUseCase: ArticleUseCase) : ViewModel() {
    val getPercentageIncome: LiveData<Float> = articleUseCase.getPercentageIncome().asLiveData()
    val getPercentageExpense: LiveData<Float> = articleUseCase.getPercentageExpense().asLiveData()
}