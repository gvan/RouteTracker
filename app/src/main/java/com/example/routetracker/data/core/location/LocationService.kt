package com.example.routetracker.data.core.location

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface LocationService {
    suspend fun getCurrentLocation(): Flow<LatLng>
}