package com.example.routetracker.data.repository.location

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface LocationRepository {
    suspend fun getCurrentLocation(): Flow<LatLng>
}