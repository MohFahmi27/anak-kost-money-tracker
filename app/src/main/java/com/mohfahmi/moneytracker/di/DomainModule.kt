package com.mohfahmi.moneytracker.di

import com.mohfahmi.core.data.repository.ActivityRepositoryImpl
import com.mohfahmi.core.data.repository.DataStoreRepositoryImpl
import com.mohfahmi.core.data.source.local.data_store.DataStoreSource
import com.mohfahmi.core.data.source.local.data_store.IDataStoreSource
import com.mohfahmi.core.data.source.local.database.ILocalDataSource
import com.mohfahmi.core.data.source.local.database.LocalDataSource
import com.mohfahmi.core.domain.repository.ActivityRepository
import com.mohfahmi.core.domain.repository.DataStoreRepository
import com.mohfahmi.core.domain.use_case.AddUseCase
import com.mohfahmi.core.domain.use_case.HomeUseCase
import com.mohfahmi.core.domain.use_case.OnBoardingUseCase
import com.mohfahmi.core.domain.use_case.SplashUseCase
import com.mohfahmi.core.domain.use_case.interactors.AddInteractor
import com.mohfahmi.core.domain.use_case.interactors.HomeInteractor
import com.mohfahmi.core.domain.use_case.interactors.OnBoardingInteractor
import com.mohfahmi.core.domain.use_case.interactors.SplashInteractor
import com.mohfahmi.moneytracker.AppDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<IDataStoreSource> { DataStoreSource(androidContext().AppDataStore) }
    single<ILocalDataSource> { LocalDataSource(get()) }
    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
    single<ActivityRepository> { ActivityRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory<OnBoardingUseCase> { OnBoardingInteractor(get()) }
    factory<SplashUseCase> { SplashInteractor(get()) }
    factory<HomeUseCase> { HomeInteractor(get(), get()) }
    factory<AddUseCase> { AddInteractor(get()) }
}