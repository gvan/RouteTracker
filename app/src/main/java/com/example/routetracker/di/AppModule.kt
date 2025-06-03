package com.example.routetracker.di

import com.example.routetracker.data.core.local.ApiKeyHolder
import com.example.routetracker.data.core.remote.RoutesApi
import com.example.routetracker.data.datasource.remote.GoogleMapsDataSource
import com.example.routetracker.data.datasource.remote.GoogleMapsDataSourceImpl
import com.example.routetracker.data.repository.GoogleMapsRepository
import com.example.routetracker.data.repository.GoogleMapsRepositoryImpl
import com.example.routetracker.domain.usecase.FindRouteUseCase
import com.example.routetracker.domain.usecase.FindRouteUseCaseImpl
import com.example.routetracker.presentation.feature.tracker.TrackerViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    // Data layer
    singleOf(::ApiKeyHolder)
    single { provideOkHttpClient() }
    single { provideConverterFactory() }
    single { provideRetrofit(get(), get()) }
    single { provideRoutesApi(get()) }
    singleOf(::GoogleMapsDataSourceImpl) bind GoogleMapsDataSource::class
    singleOf(::GoogleMapsRepositoryImpl) bind GoogleMapsRepository::class

    // Domain layer
    singleOf(::FindRouteUseCaseImpl) bind FindRouteUseCase::class

    // Presentation layer
    viewModelOf(::TrackerViewModel)
}

fun provideOkHttpClient(): OkHttpClient = OkHttpClient
    .Builder()
    .build()

fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit = Retrofit.Builder()
    .baseUrl(RoutesApi.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(gsonConverterFactory)
    .build()

fun provideRoutesApi(retrofit: Retrofit): RoutesApi = retrofit.create(RoutesApi::class.java)

