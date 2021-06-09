package com.stackoverthink.kuylahapp.ui.main.itinerary.detail.destination

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.FragmentDestinationBinding
import java.text.NumberFormat
import java.util.*


class DestinationFragment : Fragment(), OnMapReadyCallback{

    private lateinit var binding: FragmentDestinationBinding
    private lateinit var mMap: GoogleMap
    private lateinit var location: LatLng



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
//
//        val supportLayout: View = view.findViewById(R.id.include)
//        supportLayout.visibility = View.GONE

        BottomSheetBehavior.from(binding.sheet).apply {
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val destination = args.destination

        location = LatLng(destination.latitude!!, destination.longitude!!)

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
        updateMap(location)
    }

    private fun updateMap(location: LatLng) {
        val markTitle = "Buka di Google Maps"
        mMap.addMarker(
            MarkerOptions()
                .position(location)
                .title(markTitle)
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))

        mMap.setOnInfoWindowClickListener {
            val strUri =
                "http://maps.google.com/maps?q=loc:" + location.latitude.toString() + "," + location.longitude.toString() + " (" + markTitle + ")"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))

            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )

            startActivity(intent)
            }
    }
}
