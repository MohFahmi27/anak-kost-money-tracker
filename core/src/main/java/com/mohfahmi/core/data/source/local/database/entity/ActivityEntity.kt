package com.mohfahmi.core.data.source.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity")
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "amount")
    val amount: Long,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "is_expenses")
    val isExpense: Boolean
)
