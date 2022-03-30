package com.mohfahmi.core.domain.utils

sealed class UserState {
    object OnBoarding: UserState()
    object Welcome: UserState()
    object Menu: UserState()
}