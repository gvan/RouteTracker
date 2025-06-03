package com.example.routetracker.domain.entity

sealed interface UIResult<out T> {
    data class Success<T>(val data: T): UIResult<T>
    data class Error(val error: String): UIResult<Nothing>
}