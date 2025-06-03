package com.example.routetracker.data.core.location

import android.Manifest
import android.content.Context
import android.icu.text.DecimalFormat
import android.os.Looper
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.math.RoundingMode
import java.util.Locale

class LocationServiceImpl(private val context: Context) : LocationService {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override suspend fun getCurrentLocation(): Flow<LatLng> = callbackFlow {
        val locationRequest = LocationRequest
            .Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val location = locationResult.lastLocation
                if (location != null) {
                    val latitude = String.format(Locale.ENGLISH, "%.5f", location.latitude).toDouble()
                    val longitude = String.format(Locale.ENGLISH, "%.5f", location.longitude).toDouble()
                    val coordinates = LatLng(latitude, longitude)
                    trySend(coordinates)
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose { fusedLocationClient.removeLocationUpdates(locationCallback) }
    }
}