package com.stackoverthink.kuylahapp.ui.main.home

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stackoverthink.kuylahapp.api.ApiConfig
import com.stackoverthink.kuylahapp.databinding.FragmentFormDialogBinding
import com.stackoverthink.kuylahapp.response.ItineraryRequest
import com.stackoverthink.kuylahapp.response.ItineraryResponse
import com.stackoverthink.kuylahapp.response.ListItineraryResponse
import com.stackoverthink.kuylahapp.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FormDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentFormDialogBinding
    private var dataItinerary = MutableLiveData<ItineraryResponse>()
    private var dataSchedule = MutableLiveData<ListItineraryResponse>()
    private var dataSchedules = ArrayList<MutableLiveData<ListItineraryResponse>>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFormDialogBinding.inflate(layoutInflater)
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAction()
    }

    @Suppress("DEPRECATION")
    fun initAction() {
        requireActivity().run {
            binding.btnGenerateItinerary.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
                postItinerary()
                finish()
            }
        }
    }

    fun postItinerary() {
        val itineraryReq = ItineraryRequest()
        itineraryReq.title = binding.etTitle.text.toString()
        itineraryReq.day = binding.etDay.text.toString()
        itineraryReq.budget = binding.etBudget.text.toString()
        itineraryReq.category = binding.etCategory.text.toString()

        Log.d("Cek DOoang", "cek title ${itineraryReq.title}")

        val client = ApiConfig.getApiService().postItinerary(itineraryReq)
        client.enqueue(object : Callback<ItineraryResponse>{
            override fun onResponse(call: Call<ItineraryResponse>, response: Response<ItineraryResponse>) {
                val itinerary = response.body()
                Log.d("SUKSES COKK", itinerary.toString())
                if (itinerary != null) {
                    addItineraryToDatabase(itinerary)
                }

            }

            override fun onFailure(call: Call<ItineraryResponse>, t: Throwable) {
                Log.e("Kenapa", t.message.toString())
            }
        })
    }

    private fun addItineraryToDatabase(itinerary: ItineraryResponse){
        val db = Firebase.firestore
        dataItinerary.value = ItineraryResponse(
            htmTotal = itinerary.htmTotal,
            title = itinerary.title,
            day = itinerary.day,
            budget = itinerary.budget
        )
//        db.document("users/${FirebaseAuth.getInstance().uid}/itineraries/${itinerary.title.toString()}")
//            .set(dataItinerary)
          db.collection("users/${FirebaseAuth.getInstance().uid}/itineraries").document(itinerary.title.toString())
              .set(dataItinerary.value!!)

        //Adding the details
        val schedules = itinerary.destination
        Log.d("schedules ni", schedules.toString())
        for (i in 1..itinerary.day!!.toInt()){
            val destination = schedules!![i-1].schedule

            Log.d("destination ni", destination.toString())
            for (j in 1..destination.size){
                dataSchedule.value = ListItineraryResponse(
                no = destination[j-1].no,
                score = destination[j-1].score,
                nama = destination[j-1].nama,
                voteAverage = destination[j-1].voteAverage,
                htmWeekday = destination[j-1].htmWeekday,
                description = destination[j-1].description,
                htmWeekend = destination[j-1].htmWeekend,
                location = destination[j-1].location,
                type = destination[j-1].type,
                voteCount = destination[j-1].voteCount
            )

                db.document("users/${FirebaseAuth.getInstance().uid}/itineraries/${itinerary.title.toString()}/schedule/$i/destination/${dataSchedule.value!!.nama}")
                    .set(dataSchedule.value!!)
                    .addOnSuccessListener {

                    }

                val p = destination[j-1]
                Log.d("each destination ni", p.toString())
                Log.d("each destination name", p.nama.toString())
            }
        }
    }
}