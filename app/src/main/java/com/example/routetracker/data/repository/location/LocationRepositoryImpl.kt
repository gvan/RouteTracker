package com.example.routetracker.data.repository.location

import com.example.routetracker.data.datasource.location.LocationDataSource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

class LocationRepositoryImpl(private val locationDataSource: LocationDataSource): LocationRepository {
    override suspend fun getCurrentLocation(): Flow<LatLng> {
        return locationDataSource.getCurrentLocation()
    }
}