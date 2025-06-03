package com.example.routetracker.data.datasource.remote

import com.example.routetracker.data.model.ComputeRoutesModel
import com.example.routetracker.data.model.ComputeRoutesRequestModel

interface GoogleMapsDataSource {
    suspend fun getRoutes(request: ComputeRoutesRequestModel, apiKey: String): ComputeRoutesModel
}