package com.example.routetracker.data.core.local

import android.content.Context
import com.example.routetracker.R

class ApiKeyHolder(private val context: Context) {

    val mapsApiKey: String
        get() = context.getString(R.string.maps_api_key)

}