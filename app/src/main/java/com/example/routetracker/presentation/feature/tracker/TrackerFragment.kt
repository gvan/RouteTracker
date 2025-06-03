package com.example.routetracker.presentation.feature.tracker

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.routetracker.R
import com.example.routetracker.databinding.FragmentRoutesBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackerFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentRoutesBinding
    private var map: GoogleMap? = null
    private val polylines = mutableListOf<Polyline?>()
    private val viewModel: TrackerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        collectData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_routes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearchRoute.setOnClickListener {
            val origin = binding.etOrigin.text.toString()
            val destination = binding.etDestination.text.toString()
            viewModel.findRoute(origin, destination)
        }

        binding.fMap.getFragment<SupportMapFragment>().getMapAsync(this)
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.route.flowWithLifecycle(lifecycle).collectLatest { route ->
                for (polyline in polylines) {
                    polyline?.remove()
                }
                polylines.clear()

                val polylineOptions = PolylineOptions().apply {
                    addAll(route)
                    color(Color.BLUE)
                    width(16f)
                }
                val polyline = map?.addPolyline(polylineOptions)
                polylines.add(polyline)
            }
        }
        lifecycleScope.launch {
            viewModel.showError.flowWithLifecycle(lifecycle).collectLatest { error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        with(map.uiSettings) {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = true
        }
    }

}