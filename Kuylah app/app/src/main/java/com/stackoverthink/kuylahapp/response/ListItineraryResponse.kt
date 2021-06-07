package com.stackoverthink.kuylahapp.response

import com.google.gson.annotations.SerializedName

data class ListItineraryResponse(

	@field:SerializedName("index")
	val no: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("htm_weekday")
	val htmWeekday: Double? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("htm_weekend")
	val htmWeekend: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("vote_count")
	val voteCount: Double? = null
)
