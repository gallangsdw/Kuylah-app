package com.stackoverthink.kuylahapp.ui.main.itinerary.detail.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.stackoverthink.kuylahapp.databinding.FragmentDestinationBinding
import java.text.NumberFormat
import java.util.*

class DestinationFragment : Fragment(), OnMapReadyCallback{

    private lateinit var binding: FragmentDestinationBinding

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
    }

    override fun onMapReady(p0: GoogleMap) {
        p0.apply {
            val sydney = LatLng(-33.852, 151.211)
            addMarker(
                MarkerOptions()
                    .position(sydney)
                    .title("Marker in Sydney")
            )
            moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }
}