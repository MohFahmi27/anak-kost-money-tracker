package com.mohfahmi.core.data.source.local.data_store

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val PREFERENCE_NAME = "app_data_preferences"
class DataStoreSource(private val dataStore: DataStore<Preferences>) : IDataStoreSource {

    private object PreferenceKeys {
        val onBoardingStateKey = booleanPreferencesKey("on_boarding_state")
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

    companion object {
        private const val TAG = "DataStoreSource"
    }
}