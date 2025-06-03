package com.example.routetracker.data.repository.remote

import com.example.routetracker.data.model.ComputeRoutesModel

interface GoogleMapsRepository {
    suspend fun getRoutes(origin: String, destination: String): Result<ComputeRoutesModel>
}