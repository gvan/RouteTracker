package com.example.routetracker.data.core.remote

import com.example.routetracker.data.model.ComputeRoutesModel
import com.example.routetracker.data.model.ComputeRoutesRequestModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface RoutesApi {

    companion object {
        val BASE_URL = "https://routes.googleapis.com/"
    }

    @Headers(
        "Content-Type: application/json",
        "X-Goog-FieldMask: routes.duration,routes.distanceMeters,routes.polyline.encodedPolyline"
    )
    @POST("/directions/v2:computeRoutes")
    suspend fun getRoutes(@Header("X-Goog-Api-Key") apiKey: String, @Body body: ComputeRoutesRequestModel): ComputeRoutesModel

}