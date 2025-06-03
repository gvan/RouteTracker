package com.example.routetracker.presentation.feature.tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routetracker.domain.entity.RouteEntity
import com.example.routetracker.domain.entity.UIResult
import com.example.routetracker.domain.usecase.FindRouteUseCase
import com.example.routetracker.domain.usecase.GetCurrentLocationUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TrackerViewModel(
    private val findRouteUseCase: FindRouteUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
) : ViewModel() {

    private val _route = MutableStateFlow<List<LatLng>>(emptyList())
    private val _showError = MutableSharedFlow<String>()
    private val _userTrack = MutableStateFlow<List<LatLng>>(emptyList())

    val route: StateFlow<List<LatLng>> = _route.asStateFlow()
    val showError: SharedFlow<String> = _showError.asSharedFlow()
    val userTrack: StateFlow<List<LatLng>> = _userTrack.asStateFlow()

    fun findRoute(origin: String, destination: String) {
        viewModelScope.launch {
            when (val result = findRouteUseCase(origin, destination)) {
                is UIResult.Success<RouteEntity> -> _route.value = result.data.route
                is UIResult.Error -> _showError.emit(result.error)
            }
            startTracking()
        }
    }

    private suspend fun startTracking() {
        getCurrentLocationUseCase.invoke().collectLatest { location ->
            val currentTrack = _userTrack.value.toMutableList()
            currentTrack.add(location)
            _userTrack.value = currentTrack
        }
    }

}