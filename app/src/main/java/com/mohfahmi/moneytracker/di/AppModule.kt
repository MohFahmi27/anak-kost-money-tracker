package com.mohfahmi.moneytracker.di

import com.mohfahmi.moneytracker.view_models.AddDataViewModel
import com.mohfahmi.moneytracker.view_models.HomeViewModel
import com.mohfahmi.moneytracker.view_models.OnBoardingViewModel
import com.mohfahmi.moneytracker.view_models.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OnBoardingViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { AddDataViewModel(get()) }
}