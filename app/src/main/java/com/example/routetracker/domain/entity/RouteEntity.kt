package com.example.routetracker.domain.entity

import com.example.routetracker.data.model.ComputeRoutesModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil

data class RouteEntity(val route: List<LatLng>)

fun ComputeRoutesModel.toEntity(): RouteEntity {
    if(!routes.isNullOrEmpty()) {
        val encodedLine = routes[0].polyline.encodedPolyline
        val decodedLine = PolyUtil.decode(encodedLine)
        return RouteEntity(decodedLine)
    }
    return RouteEntity(emptyList())
}
