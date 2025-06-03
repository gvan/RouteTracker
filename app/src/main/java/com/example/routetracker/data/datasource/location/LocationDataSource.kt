package com.example.routetracker.data.datasource.location

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface LocationDataSource {
    suspend fun getCurrentLocation(): Flow<LatLng>
}