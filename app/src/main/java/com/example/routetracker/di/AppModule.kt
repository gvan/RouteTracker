package com.example.routetracker.di

import com.example.routetracker.data.core.local.ApiKeyHolder
import com.example.routetracker.data.core.location.LocationService
import com.example.routetracker.data.core.location.LocationServiceImpl
import com.example.routetracker.data.core.remote.RoutesApi
import com.example.routetracker.data.datasource.location.LocationDataSource
import com.example.routetracker.data.datasource.location.LocationDataSourceImpl
import com.example.routetracker.data.datasource.remote.GoogleMapsDataSource
import com.example.routetracker.data.datasource.remote.GoogleMapsDataSourceImpl
import com.example.routetracker.data.repository.location.LocationRepository
import com.example.routetracker.data.repository.location.LocationRepositoryImpl
import com.example.routetracker.data.repository.remote.GoogleMapsRepository
import com.example.routetracker.data.repository.remote.GoogleMapsRepositoryImpl
import com.example.routetracker.domain.usecase.FindRouteUseCase
import com.example.routetracker.domain.usecase.FindRouteUseCaseImpl
import com.example.routetracker.domain.usecase.GetCurrentLocationUseCase
import com.example.routetracker.domain.usecase.GetCurrentLocationUseCaseImpl
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
    singleOf(::LocationServiceImpl) bind LocationService::class
    single { provideOkHttpClient() }
    single { provideConverterFactory() }
    single { provideRetrofit(get(), get()) }
    single { provideRoutesApi(get()) }
    singleOf(::GoogleMapsDataSourceImpl) bind GoogleMapsDataSource::class
    singleOf(::LocationDataSourceImpl) bind LocationDataSource::class
    singleOf(::GoogleMapsRepositoryImpl) bind GoogleMapsRepository::class
    singleOf(::LocationRepositoryImpl) bind LocationRepository::class

    // Domain layer
    singleOf(::FindRouteUseCaseImpl) bind FindRouteUseCase::class
    singleOf(::GetCurrentLocationUseCaseImpl) bind GetCurrentLocationUseCase::class

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

