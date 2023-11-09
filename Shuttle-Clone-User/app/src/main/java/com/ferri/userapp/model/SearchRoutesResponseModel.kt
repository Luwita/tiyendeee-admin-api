package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchRoutesResponseModel(

	@field:SerializedName("data")
	val data: RoutesData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
):Serializable

data class RoutesItem(

	@field:SerializedName("drop_stop_name")
	val dropStopName: String? = null,

	@field:SerializedName("pickup_stop_lng")
	val pickupStopLng: Double? = null,

	@field:SerializedName("pickup_distance")
	val pickupDistance: String? = null,

	@field:SerializedName("drop_stop_lat")
	val dropStopLat: Double? = null,

	@field:SerializedName("route_busId")
	val routeBusId: String? = null,

	@field:SerializedName("route_bus_timetable")
	val routeBusTimetable: List<String>? = null,

	@field:SerializedName("total_of_stops")
	val total_of_stops: String? = null,

	@field:SerializedName("drop_stop_id")
	val dropStopId: String? = null,

	@field:SerializedName("pickup_stop_lat")
	val pickupStopLat: Double? = null,

	@field:SerializedName("drop_stop_lng")
	val dropStopLng: Double? = null,

	@field:SerializedName("drop_distance")
	val dropDistance: String? = null,

	@field:SerializedName("pickup_stop_departure_time")
	val pickupStopDepartureTime: String? = null,

	@field:SerializedName("routeId")
	val routeId: String? = null,

	@field:SerializedName("pickup_stop_minimum_fare_drop")
	val pickupStopMinimumFareDrop: String? = null,

	@field:SerializedName("drop_stop_minimum_fare_drop")
	val dropStopMinimumFareDrop: String? = null,

	@field:SerializedName("drop_stop_price_per_km_drop")
	val dropStopPricePerKmDrop: String? = null,

	@field:SerializedName("drop_stop_arrival_time")
	val dropStopArrivalTime: String? = null,

	@field:SerializedName("pickup_stop_id")
	val pickupStopId: String? = null,

	@field:SerializedName("pickup_stop_minimum_fare_pickup")
	val pickupStopMinimumFarePickup: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("pickup_stop_name")
	val pickupStopName: String? = null
):Serializable

data class RoutesData(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("routes")
	val routes: List<RoutesItem?>? = null
):Serializable
