package com.mohfahmi.core.domain.use_case.interactors

import com.mohfahmi.core.domain.repository.ActivityRepository
import com.mohfahmi.core.domain.use_case.ArticleUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ArticleInteractor(private val activityRepository: ActivityRepository) : ArticleUseCase {

    private val _getSumData: Flow<Float> = flow {
        activityRepository.getSumAllData().collect {
            emit(it.toFloat())
        }
    }

    override fun getPercentageIncome(): Flow<Float> = flow {
        activityRepository.getSumIncomeData().filterNotNull().collect { income ->
            val getSumData = _getSumData.first()
            emit((income.toFloat() / getSumData) * 100f)
        }
    }

    override fun getPercentageExpense(): Flow<Float> = flow {
        activityRepository.getSumExpensesData().filterNotNull().collect { expense ->
            val getSumData = _getSumData.first()
            emit((expense.toFloat() / getSumData) * 100f)
        }
    }
}