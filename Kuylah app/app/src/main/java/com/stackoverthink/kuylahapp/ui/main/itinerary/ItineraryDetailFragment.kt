package com.stackoverthink.kuylahapp.ui.main.itinerary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stackoverthink.kuylahapp.R

class ItineraryDetailFragment : Fragment() {

    companion object{
        const val EXTRA_ITINERARY = "extra_itinerary"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_itinerary_detail, container, false)
    }

}