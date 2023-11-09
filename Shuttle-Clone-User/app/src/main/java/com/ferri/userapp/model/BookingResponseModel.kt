package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BookingResponseModel(

	@field:SerializedName("data")
	val data: BookingData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
):Serializable

data class GetbookingData(

	@field:SerializedName("busId")
	val busId: String? = null,

	@field:SerializedName("passengers")
	val passengers: String? = null,

	@field:SerializedName("drop_date")
	val dropDate: String? = null,

	@field:SerializedName("dropoffId")
	val dropoffId: String? = null,

	@field:SerializedName("route_name")
	val routeName: String? = null,

	@field:SerializedName("pickup_name")
	val pickupName: String? = null,

	@field:SerializedName("fee")
	val fee: String? = null,

	@field:SerializedName("discount")
	val discount: String? = null,

	@field:SerializedName("seat_nos")
	val seatNos: List<String?>? = null,

	@field:SerializedName("tax")
	val tax: String? = null,

	@field:SerializedName("tax_amount")
	val tax_amount : String? = null,

	@field:SerializedName("pickupId")
	val pickupId: String? = null,

	@field:SerializedName("pnr_no")
	val pnrNo: String? = null,

	@field:SerializedName("travel_status")
	val travelStatus: String? = null,

	@field:SerializedName("start_time")
	val startTime: String? = null,

	@field:SerializedName("bus_model_no")
	val busModelNo: String? = null,

	@field:SerializedName("booking_assign")
	val driverInfo: DriverInfo? = null,

	@field:SerializedName("routeId")
	val routeId: String? = null,

	@field:SerializedName("sub_total")
	val subTotal: String? = null,

	@field:SerializedName("drop_time")
	val dropTime: String? = null,

	@field:SerializedName("drop_name")
	val dropName: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("has_return")
	val hasReturn: String? = null,

	@field:SerializedName("bus_name")
	val busName: String? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null,

	@field:SerializedName("png_string")
	val png_string: String? = null,

	@field:SerializedName("final_total_fare")
	val finalTotalFare: String? = null
):Serializable

data class PersistedPassengerItem(

	@field:SerializedName("busId")
	val busId: String? = null,

	@field:SerializedName("seat")
	val seat: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("bookingId")
	val bookingId: String? = null,

	@field:SerializedName("age")
	val age: String? = null
):Serializable

data class BookingData(

	@field:SerializedName("walletBalance")
	val walletBalance: Int? = null,

	@field:SerializedName("getbookingData")
	val getbookingData: GetbookingData? = null,

	@field:SerializedName("persistedPassenger")
	val persistedPassenger: List<PersistedPassengerItem?>? = null
):Serializable

data class DriverInfo(
	@field:SerializedName("driver_fullname")
	val driverName: String? = null,

	@field:SerializedName("driver_phone")
	val driverNo: String? = null
):Serializable
