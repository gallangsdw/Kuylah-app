package com.stackoverthink.kuylahapp.ui.main.itinerary.detail.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.stackoverthink.kuylahapp.databinding.FragmentDestinationBinding
import java.text.NumberFormat
import java.util.*

class DestinationFragment : Fragment() {

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
}