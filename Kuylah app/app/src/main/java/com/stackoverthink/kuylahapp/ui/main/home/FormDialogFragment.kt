package com.stackoverthink.kuylahapp.ui.main.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.api.ApiConfig
import com.stackoverthink.kuylahapp.databinding.FragmentFormDialogBinding
import com.stackoverthink.kuylahapp.databinding.FragmentItineraryBinding
import com.stackoverthink.kuylahapp.databinding.ItemItineraryBinding
import com.stackoverthink.kuylahapp.response.DummyRequest
import com.stackoverthink.kuylahapp.response.DummyResponse
import com.stackoverthink.kuylahapp.response.ItineraryRequest
import com.stackoverthink.kuylahapp.response.ItineraryResponse
import com.stackoverthink.kuylahapp.ui.auth.AuthenticationActivity
import com.stackoverthink.kuylahapp.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FormDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentFormDialogBinding
    private lateinit var itineraryBinding: FragmentItineraryBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormDialogBinding.inflate(layoutInflater)
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE);
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
            }
        }
    }

    fun postItinerary() {
        var itineraryReq = DummyRequest()
        itineraryReq.name = binding.etTitle.text.toString()
        itineraryReq.job = binding.etBudget.text.toString()

        val client = ApiConfig.getApiService().postItinerary(itineraryReq)
        client.enqueue(object : Callback<DummyResponse>{
            override fun onResponse(call: Call<DummyResponse>, response: Response<DummyResponse>) {
                val itinerary = response.body()
                Log.d("success", itinerary.toString())
            }

            override fun onFailure(call: Call<DummyResponse>, t: Throwable) {
                Log.e("failed", t.message.toString())
            }

        })
    }
}