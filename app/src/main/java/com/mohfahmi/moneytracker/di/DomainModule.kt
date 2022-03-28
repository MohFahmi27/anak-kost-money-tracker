package com.mohfahmi.moneytracker.di

import com.mohfahmi.core.data.repository.DataStoreRepositoryImpl
import com.mohfahmi.core.data.source.local.data_store.DataStoreSource
import com.mohfahmi.core.data.source.local.data_store.IDataStoreSource
import com.mohfahmi.core.domain.repository.DataStoreRepository
import com.mohfahmi.core.domain.use_case.OnBoardingUseCase
import com.mohfahmi.core.domain.use_case.SplashUseCase
import com.mohfahmi.core.domain.use_case.interactors.OnBoardingInteractor
import com.mohfahmi.core.domain.use_case.interactors.SplashInteractor
import com.mohfahmi.moneytracker.AppDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<IDataStoreSource> { DataStoreSource(androidContext().AppDataStore) }
    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory<OnBoardingUseCase> { OnBoardingInteractor(get()) }
    factory<SplashUseCase> { SplashInteractor(get()) }
}