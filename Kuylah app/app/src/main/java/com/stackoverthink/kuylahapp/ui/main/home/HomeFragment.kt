package com.stackoverthink.kuylahapp.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.databinding.FragmentHomeBinding
import com.stackoverthink.kuylahapp.models.Itinerary

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


    internal var formDialogListener: FormDialogFragment.OnFormDialogListener = object : FormDialogFragment.OnFormDialogListener{
        override fun onItinerary() {
            binding.root.findNavController().navigate(R.id.action_homeFragment_to_itineraryFragment)
            Toast.makeText(activity, "Rencana perjalanan telah dibuat", Toast.LENGTH_SHORT).show()
        }
    }
}