package com.example.routetracker.data.repository

import com.example.routetracker.data.model.ComputeRoutesModel

interface GoogleMapsRepository {
    suspend fun getRoutes(origin: String, destination: String): Result<ComputeRoutesModel>
}