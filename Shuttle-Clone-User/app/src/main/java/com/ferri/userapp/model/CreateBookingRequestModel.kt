package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class CreateBookingRequestModel(

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

	@field:SerializedName("drop_stop_id")
	val dropStopId: String? = null,

	@field:SerializedName("pnr_no")
	val pnrNo: String? = null,

	@field:SerializedName("offer_id")
	val offerId: String? = null,

	@field:SerializedName("passenger_details")
	val passengerDetails: List<PassengerDetailsItem?>? = null,

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

	@field:SerializedName("bus_id")
	val busId: String? = null,

	@field:SerializedName("pickup_time")
	val pickupTime: String? = null,

	@field:SerializedName("has_return")
	val hasReturn: String? = null,

	@field:SerializedName("final_total_fare")
	val finalTotalFare: String? = null
)

data class PassengerDetailsItem(

	@field:SerializedName("seat")
	val seat: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("age")
	val age: String? = null
)
