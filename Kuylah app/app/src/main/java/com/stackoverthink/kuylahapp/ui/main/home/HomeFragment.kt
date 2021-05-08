package com.stackoverthink.kuylahapp.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.FragmentHomeBinding
import com.stackoverthink.kuylahapp.databinding.FragmentItineraryBinding
import com.stackoverthink.kuylahapp.ui.main.itinerary.FormDialogFragment

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreateItinerary.setOnClickListener {
            val mFormDialogFragment = FormDialogFragment()
            val mFragmentManager = childFragmentManager
            mFormDialogFragment.show(mFragmentManager, FormDialogFragment::class.java.simpleName)
        }
    }
}