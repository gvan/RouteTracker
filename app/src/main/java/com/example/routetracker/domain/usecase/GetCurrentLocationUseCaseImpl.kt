package com.example.routetracker.domain.usecase

import com.example.routetracker.data.repository.location.LocationRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

class GetCurrentLocationUseCaseImpl(private val locationRepository: LocationRepository) :
    GetCurrentLocationUseCase {
    override suspend fun invoke(): Flow<LatLng> {
        return locationRepository.getCurrentLocation()
    }
}