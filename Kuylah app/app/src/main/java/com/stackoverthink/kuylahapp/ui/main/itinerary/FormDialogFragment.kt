package com.stackoverthink.kuylahapp.ui.main.itinerary

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.FragmentFormDialogBinding
import com.stackoverthink.kuylahapp.databinding.FragmentItineraryBinding


class FormDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentFormDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

}