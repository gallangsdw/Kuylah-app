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
    private lateinit var location: LatLng
    private lateinit var markTitle: String



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

        location = LatLng(destination.latitude!!, destination.longitude!!)
        markTitle = destination.nama!!

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
        mMap = googleMap
        mMap.setMinZoomPreference(1f)
        mMap.setMaxZoomPreference(30f)
        updateMap(location, markTitle)
    }

    private fun updateMap(location: LatLng, markTitle: String) {
        mMap.addMarker(
            MarkerOptions()
                .position(location)
                .title(markTitle)
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }

}