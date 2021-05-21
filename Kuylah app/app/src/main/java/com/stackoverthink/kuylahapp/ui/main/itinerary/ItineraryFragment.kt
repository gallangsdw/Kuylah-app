package com.stackoverthink.kuylahapp.ui.main.itinerary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stackoverthink.kuylahapp.databinding.FragmentItineraryBinding
import com.stackoverthink.kuylahapp.models.Destination
import com.stackoverthink.kuylahapp.models.Itinerary
import java.util.*

class ItineraryFragment : Fragment() {
    private lateinit var dataTitles: Array<String>
    private lateinit var days: Array<String>
    private lateinit var dataBudgets: Array<String>
    private lateinit var categories: Array<MutableList<String>>
//    private var schedules = arrayListOf<Day>()
    private lateinit var destinationName: Array<String>
    private lateinit var destinationTime: Array<String>
    private var destinations = arrayListOf<Destination>()
    private var dataItineraries = arrayListOf<Itinerary>()

    private lateinit var binding: FragmentItineraryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItineraryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepare()
        addItem()
        showRecycleList(dataItineraries)


    }

    private fun showRecycleList(itinerary: ArrayList<Itinerary>) {
        val followAdapter = ItineraryAdapter(itinerary)
        binding.rvItineraries.layoutManager = LinearLayoutManager(activity)
        binding.rvItineraries.adapter = followAdapter
    }


    private fun prepare(){
        dataTitles = arrayOf(
            "Jalan2 ke jogja for the first time!",
            "Ke jogja lagi akhirnya",
            "Jogjaaaaa",
            "Terpaksa ke jogja",
            "Ke jogja lagiii",
            "Jogja lur"
        )

        days = arrayOf(
            "3 D",
            "2 D",
            "1 D",
            "2 D",
            "2 D",
            "2 D"
        )

        dataBudgets = arrayOf(
            "Rp500.000",
            "Rp400.000",
            "Rp800.000",
            "Rp120.000",
            "Rp50.000",
            "Rp60.000"
        )

        categories = arrayOf(
            mutableListOf("Beach", "Natural"),
            mutableListOf("Beach", "Natural", "Cultural and Historical"),
            mutableListOf("Beach", "Natural"),
            mutableListOf("Beach", "Natural"),
            mutableListOf("Beach", "Natural"),
            mutableListOf("Beach", "Natural", "Museum", "Art Gallery and Exhibition")
        )

        destinationName = arrayOf(
                "Kopi Klotok",
                "Bukit Klangon Merapi",
                "Situs Warung Boto",
        )

        destinationTime = arrayOf(
                "07:00 AM - 09:00 AM",
                "10:00 AM - 12:00 PM",
                "01:00 PM - 03:00 PM"
        )


        for (position in destinationName.indices){
            val destinationData = Destination(
                    destinationName[position],
                    destinationTime[position]
            )
            destinations.add(destinationData)
        }

//        val Day = Day(
//            1,
//            destinations
//        )
//
//        schedules.add(Day)


    }

    private fun addItem(){
//        for (position in dataTitles.indices){
//            val itinerary = Itinerary(
//                dataTitles[position],
//                days[position],
//                dataBudgets[position],
//                categories[position],
//                schedules
//            )
//            dataItineraries.add(itinerary)
//        }
    }
}