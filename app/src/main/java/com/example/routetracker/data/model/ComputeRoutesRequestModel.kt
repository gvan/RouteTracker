package com.example.routetracker.data.model

data class ComputeRoutesRequestModel(val origin: DestinationModel, val destination: DestinationModel, val travelMode: String)

data class DestinationModel(val address: String)