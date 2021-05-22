package com.stackoverthink.kuylahapp.ui.main.home

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.stackoverthink.kuylahapp.api.ApiConfig
import com.stackoverthink.kuylahapp.databinding.FragmentFormDialogBinding
import com.stackoverthink.kuylahapp.response.ItineraryRequest
import com.stackoverthink.kuylahapp.response.ItineraryResponse
import com.stackoverthink.kuylahapp.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FormDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentFormDialogBinding

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
        var itineraryReq = ItineraryRequest()
        itineraryReq.title = binding.etTitle.text.toString()
        itineraryReq.day = binding.etDay.text.toString()
        itineraryReq.budget = binding.etBudget.text.toString()

        val client = ApiConfig.getApiService().postItinerary(itineraryReq)
        client.enqueue(object : Callback<ItineraryResponse>{
            override fun onResponse(call: Call<ItineraryResponse>, response: Response<ItineraryResponse>) {
                val itinerary = response.body()
                Log.d("success", itinerary.toString())
            }

            override fun onFailure(call: Call<ItineraryResponse>, t: Throwable) {
                Log.e("failed", t.message.toString())
            }

        })
    }
}