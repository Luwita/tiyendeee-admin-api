package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class TripTrackingStatusResponse(

	@field:SerializedName("data")
	val data: TrackData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class TrackData(

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("bus_model_no")
	val busModelNo: String? = null,

	@field:SerializedName("pickup_lng")
	val pickupLng: String? = null,

	@field:SerializedName("distance")
	val distance: String? = null,

	@field:SerializedName("bus_reg_no")
	val busRegNo: String? = null,

	@field:SerializedName("bus_lat")
	val busLat: String? = null,

	@field:SerializedName("angle")
	val angle: String? = null,

	@field:SerializedName("pickup_lat")
	val pickupLat: String? = null,

	@field:SerializedName("bus_lng")
	val busLng: String? = null
)
