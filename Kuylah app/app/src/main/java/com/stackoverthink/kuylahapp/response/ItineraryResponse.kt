package com.stackoverthink.kuylahapp.response

import com.google.gson.annotations.SerializedName

data class ItineraryResponse(

	@field:SerializedName("no")
	val no: Int,

	@field:SerializedName("score")
	val score: Double,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("htm_weekday")
	val htmWeekday: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("htm_weekend")
	val htmWeekend: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("vote_count")
	val voteCount: Double
)
