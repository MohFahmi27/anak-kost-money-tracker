package com.mohfahmi.moneytracker.di

import androidx.room.Room
import com.mohfahmi.core.data.source.local.database.db.ActivityDatabase
import com.mohfahmi.core.data.source.network.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<ActivityDatabase>().activityDao() }
    single {
        Room.databaseBuilder(androidContext(), ActivityDatabase::class.java, "komotrack.db")
            .fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get(), get()) }
    single { createApiService(get()) }
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://berita-indo-api.vercel.app/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
}

fun createApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}