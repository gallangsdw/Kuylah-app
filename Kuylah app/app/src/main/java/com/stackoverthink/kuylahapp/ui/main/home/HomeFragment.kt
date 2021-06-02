package com.stackoverthink.kuylahapp.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
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
//            val itinerary = Itinerary()
//            val action = HomeFragmentDirections.actionHomeFragmentToItineraryDetailFragment22(itinerary)
//            it.findNavController().navigate(action)
            val mFormDialogFragment = FormDialogFragment()
            val mFragmentManager = childFragmentManager
            mFormDialogFragment.show(mFragmentManager, FormDialogFragment::class.java.simpleName)
        }
    }

    internal var formDialogListener: FormDialogFragment.OnFormDialogListener = object : FormDialogFragment.OnFormDialogListener{
        override fun onItinerary(itinerary: Itinerary?) {
            //Toast.makeText(activity, "\"${itinerary?.title}\" telah dibuat", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSnackbar(itinerary: Itinerary) {
        Snackbar.make(binding.btnCreateItinerary, "Rencana perjalanan ditambahkan", Snackbar.LENGTH_LONG)
            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
            .setAction("Lihat"){

            }
            .show()
    }
}