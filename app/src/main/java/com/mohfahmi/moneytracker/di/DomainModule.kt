package com.mohfahmi.moneytracker.di

import com.mohfahmi.core.data.repository.ActivityRepositoryImpl
import com.mohfahmi.core.data.repository.ArticleRepositoryImpl
import com.mohfahmi.core.data.repository.DataStoreRepositoryImpl
import com.mohfahmi.core.data.source.local.data_store.DataStoreSource
import com.mohfahmi.core.data.source.local.data_store.IDataStoreSource
import com.mohfahmi.core.data.source.local.database.ILocalDataSource
import com.mohfahmi.core.data.source.local.database.LocalDataSource
import com.mohfahmi.core.data.source.network.INetworkDataSource
import com.mohfahmi.core.data.source.network.NetworkDataSource
import com.mohfahmi.core.domain.repository.ActivityRepository
import com.mohfahmi.core.domain.repository.ArticleRepository
import com.mohfahmi.core.domain.repository.DataStoreRepository
import com.mohfahmi.core.domain.use_case.*
import com.mohfahmi.core.domain.use_case.interactors.*
import com.mohfahmi.moneytracker.AppDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<IDataStoreSource> { DataStoreSource(androidContext().AppDataStore) }
    single<ILocalDataSource> { LocalDataSource(get()) }
    single<INetworkDataSource> { NetworkDataSource(get()) }
    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
    single<ActivityRepository> { ActivityRepositoryImpl(get()) }
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory<OnBoardingUseCase> { OnBoardingInteractor(get()) }
    factory<SplashUseCase> { SplashInteractor(get()) }
    factory<HomeUseCase> { HomeInteractor(get(), get()) }
    factory<AddUseCase> { AddInteractor(get()) }
    factory<ArticleUseCase> { ArticleInteractor(get(), get()) }
    factory<EditNameUseCase> { EditNameInteractor(get()) }
    factory<ProfileUseCase> { ProfileInteractor(get()) }
}