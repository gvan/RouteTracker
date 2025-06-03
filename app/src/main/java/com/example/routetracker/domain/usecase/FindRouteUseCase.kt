package com.example.routetracker.domain.usecase

import com.example.routetracker.domain.entity.RouteEntity
import com.example.routetracker.domain.entity.UIResult

fun interface FindRouteUseCase {
    suspend operator fun invoke(origin: String, destination: String): UIResult<RouteEntity>
}