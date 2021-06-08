package com.stackoverthink.kuylahapp.ui.main.itinerary.detail.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.FragmentDestinationBinding
import java.text.NumberFormat
import java.util.*

class DestinationFragment : Fragment(), OnMapReadyCallback{

    private lateinit var binding: FragmentDestinationBinding
    private lateinit var mMap: GoogleMap


    private val args by navArgs<DestinationFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDestinationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val destination = args.destination

        val _weekday = destination.htmWeekday
        val weekday = NumberFormat.getNumberInstance(Locale.US).format(_weekday)

        val _weekend = destination.htmWeekend?.toDouble()
        val weekend = NumberFormat.getNumberInstance(Locale.US).format(_weekend)

        binding.tvDestinationTitle.text = destination.nama
        binding.tvDestinationDescription.text = destination.description
        binding.tvDestinationHtmWeekday2.text = "Rp. $weekday"
        binding.tvDestinationHtmWeekend2.text = "Rp. $weekend"
        binding.tvDestinationType2.text = destination.type

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.fr_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val example = LatLng(-8.003873302, 110.27037560000001)
        mMap = googleMap
        mMap.setMinZoomPreference(1f)
        mMap.setMaxZoomPreference(30f)
        updateMap(example)
    }

    private fun updateMap(location: LatLng) {
        mMap.addMarker(
            MarkerOptions()
                .position(location)
                .title("Rumah Siape ni?")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }

}