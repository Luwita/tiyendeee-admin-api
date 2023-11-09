package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class RouteStopsResponseModel(

	@field:SerializedName("data")
	val data: List<StopsItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class StopsItem(

	@field:SerializedName("drop")
	val drop: Boolean? = null,

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("pickup")
	val pickup: Boolean? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)
