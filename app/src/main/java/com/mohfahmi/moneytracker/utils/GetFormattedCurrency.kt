package com.mohfahmi.moneytracker.utils

import java.text.NumberFormat
import java.util.*

internal fun Long?.toCurrencyFormat(): String {
    return NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(this) ?: "0"
}