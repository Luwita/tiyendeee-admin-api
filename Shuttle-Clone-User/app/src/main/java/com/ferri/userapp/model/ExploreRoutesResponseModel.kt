package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class ExploreRoutesResponseModel(

	@field:SerializedName("data")
	val data: List<RoutesDataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class RoutesDataItem(

	@field:SerializedName("routeId")
	val routeId: String? = null,

	@field:SerializedName("route_title")
	val routeTitle: String? = null,

	@field:SerializedName("stops")
	val stops: List<StopsList>? = null
)

data class StopsList(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)
