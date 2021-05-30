package com.stackoverthink.kuylahapp.response

import com.google.gson.annotations.SerializedName

data class ListItineraryResponse(

	@field:SerializedName("index")
	var no: Int? = null,

	@field:SerializedName("nama")
	var nama: String? = null,

	@field:SerializedName("vote_average")
	var voteAverage: Double? = null,

	@field:SerializedName("htm_weekday")
	var htmWeekday: Double? = null,

	@field:SerializedName("description")
	var description: String? = null,

	@field:SerializedName("htm_weekend")
	var htmWeekend: String? = null,

	@field:SerializedName("location")
	var location: String? = null,

	@field:SerializedName("type")
	var type: String? = null,

	@field:SerializedName("vote_count")
	var voteCount: Double? = null
)
