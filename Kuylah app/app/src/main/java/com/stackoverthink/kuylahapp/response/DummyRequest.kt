package com.stackoverthink.kuylahapp.response

import com.google.gson.annotations.SerializedName

data class DummyRequest(

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("job")
	var job: String? = null
)
