package com.mohfahmi.core.data.source.local.data_store

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val PREFERENCE_NAME = "app_data_preferences"

class DataStoreSource(private val dataStore: DataStore<Preferences>) : IDataStoreSource {

    private object PreferenceKeys {
        val onBoardingStateKey = booleanPreferencesKey("on_boarding_state")
        val userNameKey = stringPreferencesKey("user_name_key")
        val notificationKey = booleanPreferencesKey("notification_key")
    }

    override suspend fun saveOnBoardingState(state: Boolean) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.onBoardingStateKey] = state
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> = dataStore.data
        .catch { cause ->
            if (cause is IOException) {
                Log.d(TAG, "readOnBoardingState: ${cause.message.toString()}")
                emit(emptyPreferences())
            } else {
                throw cause
            }
        }
        .map { value ->
            value[PreferenceKeys.onBoardingStateKey] ?: false
        }

    override suspend fun saveNameUser(name: String) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.userNameKey] = name
        }
    }

    override fun readNameUser(): Flow<String> = dataStore.data
        .catch { cause ->
            if (cause is IOException) {
                Log.d(TAG, "readNameUser: ${cause.message.toString()}")
                emit(emptyPreferences())
            } else {
                throw cause
            }
        }
        .map { value ->
            value[PreferenceKeys.userNameKey] ?: ""
        }

    override suspend fun saveNotificationState(state: Boolean) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.notificationKey] = state
        }
    }

    override fun readNotificationState(): Flow<Boolean> = dataStore.data
        .catch { cause ->
            if (cause is IOException) {
                Log.d(TAG, "readNameUser: ${cause.message.toString()}")
                emit(emptyPreferences())
            } else {
                throw cause
            }
        }
        .map { value ->
            value[PreferenceKeys.notificationKey] ?: false
        }

    companion object {
        private const val TAG = "DataStoreSource"
    }
}