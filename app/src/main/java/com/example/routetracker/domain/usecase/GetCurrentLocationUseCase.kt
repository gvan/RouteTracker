package com.example.routetracker.domain.usecase

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

fun interface GetCurrentLocationUseCase {
    suspend operator fun invoke(): Flow<LatLng>
}