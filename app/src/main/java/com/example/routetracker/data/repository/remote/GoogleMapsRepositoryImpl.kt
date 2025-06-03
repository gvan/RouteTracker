package com.example.routetracker.data.repository.remote

import com.example.routetracker.data.core.local.ApiKeyHolder
import com.example.routetracker.data.datasource.remote.GoogleMapsDataSource
import com.example.routetracker.data.model.ComputeRoutesModel
import com.example.routetracker.data.model.ComputeRoutesRequestModel
import com.example.routetracker.data.model.DestinationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GoogleMapsRepositoryImpl(
    private val dataSource: GoogleMapsDataSource,
    private val apiKeyHolder: ApiKeyHolder
) :
    GoogleMapsRepository {
    override suspend fun getRoutes(
        origin: String,
        destination: String
    ): Result<ComputeRoutesModel> {
        return withContext(Dispatchers.IO) {
            runCatching {
                dataSource.getRoutes(
                    request = ComputeRoutesRequestModel(
                        origin = DestinationModel(origin),
                        destination = DestinationModel(destination),
                        travelMode = "drive"
                    ),
                    apiKey = apiKeyHolder.mapsApiKey
                )
            }
        }
    }
}