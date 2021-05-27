package com.stackoverthink.kuylahapp.response

import com.google.gson.annotations.SerializedName

data class ItineraryResponse(

	@field:SerializedName("htm_total")
	val htmTotal: Double? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("day")
	val day: String? = null,

	@field:SerializedName("budget")
	val budget: String? = null,

	@field:SerializedName("destination")
	val destination: List<ListItineraryResponse>
)
