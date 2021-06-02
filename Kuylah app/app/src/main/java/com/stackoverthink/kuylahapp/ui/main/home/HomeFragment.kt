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
    private lateinit var sharedViewModel: SharedViewModel

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

//    override fun onResume() {
//        super.onResume()
//        Log.d("Hello", "Im under water pillow")
//        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
//        sharedViewModel.itinerary.observe(viewLifecycleOwner, Observer {
//            Log.d("Hello", it.toString())
//            Toast.makeText(activity, "\"${it?.title}\" telah dibuat", Toast.LENGTH_SHORT).show()
//        })
//    }


//    internal var formDialogListener: FormDialogFragment.OnFormDialogListener = object : FormDialogFragment.OnFormDialogListener{
//        override fun onItinerary(itinerary: Itinerary?) {
//            val navView: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav)
//            val action = HomeFragmentDirections.actionHomeFragmentToItineraryDetailFragment22(itinerary!!)
//            binding.root.findNavController().navigate(action)
//            val item: MenuItem = navView.menu.findItem(R.id.itineraryFragment)
//            item.setChecked(true)
//            Toast.makeText(activity, "\"${itinerary?.title}\" telah dibuat", Toast.LENGTH_SHORT).show()
//        }
//    }

//    private fun showSnackbar(itinerary: Itinerary) {
//        Snackbar.make(binding.btnCreateItinerary, "Rencana perjalanan ditambahkan", Snackbar.LENGTH_LONG)
//            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
//            .setAction("Lihat"){
//
//            }
//            .show()
//    }
}