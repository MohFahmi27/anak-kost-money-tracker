package com.mohfahmi.core.domain.models

data class ActivityDomain(
    val id: Long = 0,
    val name: String,
    val amount: Long,
    val date: String,
    val isExpense: Boolean
)
