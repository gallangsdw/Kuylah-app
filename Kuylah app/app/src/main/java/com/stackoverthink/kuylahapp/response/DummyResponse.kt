package com.stackoverthink.kuylahapp.response

import com.google.gson.annotations.SerializedName

data class DummyResponse(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("job")
	val job: String? = null
)
