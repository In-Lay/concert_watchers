package com.inlay.map.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.inlay.map.R
import com.inlay.map.databinding.FragmentMapsBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MapsFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapsBinding
    private lateinit var mMap: GoogleMap
    private val mapsViewModel: MapsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)
        Log.d("MapsTag", "Maps Fragment Created")
        return binding.root
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        Log.d("MapsTag", "Map is ready: $p0")
        var coordinates = LatLng(0.0, 0.0)
        mapsViewModel.mapsGeo.observe(viewLifecycleOwner) {
            coordinates = LatLng(it.latitude, it.longitude)
        }
        Log.d("MapsTag", "Map is ready with coordinates: $coordinates")
        mMap.addMarker(MarkerOptions().position(coordinates).title("Your event"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val mapView = binding.vMap
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)
//        val mapFragment =
//            parentFragmentManager.findFragmentById(R.id.maps_layout) as SupportMapFragment
//
//        mapFragment.getMapAsync(this)

        super.onViewCreated(view, savedInstanceState)
    }
}