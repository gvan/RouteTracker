package com.example.routetracker.data.datasource.location

import com.example.routetracker.data.core.location.LocationService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

class LocationDataSourceImpl(private val locationService: LocationService) : LocationDataSource {
    override suspend fun getCurrentLocation(): Flow<LatLng> {
        return locationService.getCurrentLocation()
    }
}