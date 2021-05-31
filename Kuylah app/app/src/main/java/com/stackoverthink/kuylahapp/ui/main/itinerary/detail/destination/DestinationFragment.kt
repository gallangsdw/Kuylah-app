package com.stackoverthink.kuylahapp.ui.main.itinerary.detail.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.FragmentDestinationBinding

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

        binding.tvDestinationTitle.text = destination.nama
        binding.tvDestinationDescription.text = destination.description
        binding.tvDestinationHtmWeekday2.text = destination.htmWeekday.toString()
        binding.tvDestinationHtmWeekend2.text = destination.htmWeekend
        binding.tvDestinationType2.text = destination.type
    }
}