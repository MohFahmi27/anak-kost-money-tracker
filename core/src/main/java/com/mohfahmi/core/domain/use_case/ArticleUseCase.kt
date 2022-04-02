package com.mohfahmi.core.domain.use_case

import kotlinx.coroutines.flow.Flow

interface ArticleUseCase {
    fun getPercentageIncome(): Flow<Float>
    fun getPercentageExpense(): Flow<Float>
}