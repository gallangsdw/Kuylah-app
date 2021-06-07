package com.stackoverthink.kuylahapp.ui.main.itinerary.detail.destination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.stackoverthink.kuylahapp.R

class DestinatinActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinatin)

        val fragment = MapFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameMaps, fragment)
            .commit()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap?.apply {
            val sydney = LatLng(-33.852, 151.211)
            addMarker(
                MarkerOptions()
                    .position(sydney)
                    .title("Marker in Sydney")
            )
        }
    }
}