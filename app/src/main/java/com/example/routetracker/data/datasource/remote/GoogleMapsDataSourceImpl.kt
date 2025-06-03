package com.example.routetracker.data.datasource.remote

import com.example.routetracker.data.core.remote.RoutesApi
import com.example.routetracker.data.model.ComputeRoutesModel
import com.example.routetracker.data.model.ComputeRoutesRequestModel

class GoogleMapsDataSourceImpl(private val routesApi: RoutesApi) : GoogleMapsDataSource {
    override suspend fun getRoutes(request: ComputeRoutesRequestModel, apiKey: String): ComputeRoutesModel {
        return routesApi.getRoutes(body = request, apiKey = apiKey)
    }
}