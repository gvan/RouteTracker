package com.example.routetracker.domain.usecase

import com.example.routetracker.data.repository.GoogleMapsRepository
import com.example.routetracker.domain.entity.RouteEntity
import com.example.routetracker.domain.entity.UIResult
import com.example.routetracker.domain.entity.toEntity

class FindRouteUseCaseImpl(private val googleMapsRepository: GoogleMapsRepository) :
    FindRouteUseCase {
    override suspend fun invoke(origin: String, destination: String): UIResult<RouteEntity> {
        val result = googleMapsRepository.getRoutes(origin, destination)
        return result.fold(
            onSuccess = {
                UIResult.Success(it.toEntity())
            },
            onFailure = {
                UIResult.Error(it.message ?: "")
            }
        )
    }
}