package com.mohfahmi.moneytracker.di

import androidx.room.Room
import com.mohfahmi.core.data.source.local.database.db.ActivityDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<ActivityDatabase>().activityDao() }
    single {
        Room.databaseBuilder(androidContext(), ActivityDatabase::class.java, "komotrack.db")
            .fallbackToDestructiveMigration().build()
    }
}