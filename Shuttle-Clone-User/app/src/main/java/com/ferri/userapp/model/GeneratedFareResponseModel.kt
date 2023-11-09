package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GeneratedFareResponseModel(

	@field:SerializedName("data")
	val data: FareData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
): Serializable

data class FareData(

	@field:SerializedName("route_id")
	val routeId: String? = null,

	@field:SerializedName("distance")
	val distance: String? = null,

	@field:SerializedName("no_of_seats")
	val noOfSeats: String? = null,

	@field:SerializedName("pickup_name")
	val pickupName: String? = null,

	@field:SerializedName("fee")
	val fee: String? = null,

	@field:SerializedName("seat_no")
	val seatNo: String? = null,

	@field:SerializedName("tax")
	val tax: String? = null,

	@field:SerializedName("tax_amount")
	val tax_amount : String? = null,

	@field:SerializedName("drop_stop_id")
	val dropStopId: String? = null,

	@field:SerializedName("bus_id")
	val bus_id: String? = null,

	@field:SerializedName("pnr_no")
	val pnrNo: String? = null,

	@field:SerializedName("sub_total")
	val subTotal: String? = null,

	@field:SerializedName("drop_name")
	val dropName: String? = null,

	@field:SerializedName("drop_time")
	val dropTime: String? = null,

	@field:SerializedName("pickup_stop_id")
	val pickupStopId: String? = null,

	@field:SerializedName("created_date")
	val createdDate: String? = null,

	@field:SerializedName("pickup_time")
	val pickupTime: String? = null,

	@field:SerializedName("has_return")
	val hasReturn: String? = null,

	@field:SerializedName("final_total_fare")
	val finalTotalFare: String? = null
) : Serializable
