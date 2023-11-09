package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BookingTransactionHistoryResponse(

	@field:SerializedName("data")
	val data: List<BookingDataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
): Serializable

data class BookingDetailsItem(

	@field:SerializedName("start_time")
	val startTime: String? = null,

	@field:SerializedName("drop_date")
	val dropDate: String? = null,

	@field:SerializedName("route_name")
	val routeName: String? = null,

	@field:SerializedName("bus_detail")
	val busDetail: BusDetail? = null,

	@field:SerializedName("booking_date")
	val bookingDate: String? = null,

	@field:SerializedName("pickup_name")
	val pickupName: String? = null,

	@field:SerializedName("drop_time")
	val dropTime: String? = null,

	@field:SerializedName("seat_nos")
	val seatNos: String? = null,

	@field:SerializedName("dropoff_title")
	val dropoffTitle: String? = null,

	@field:SerializedName("pnr_no")
	val pnrNo: String? = null,

	@field:SerializedName("travel_status")
	val travelStatus: String? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null
): Serializable

data class BusDetail(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("reg_no")
	val regNo: String? = null,

	@field:SerializedName("chassis_no")
	val chassisNo: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("brand")
	val brand: String? = null,

	@field:SerializedName("model_no")
	val modelNo: String? = null
): Serializable

data class BookingDataItem(

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("payment_status")
	val paymentStatus: String? = null,

	@field:SerializedName("booking_details")
	val bookingDetails: List<BookingDetailsItem>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: Int? = null,

	@field:SerializedName("no_of_pasess")
	val noOfPasses: Int? = null,

	@field:SerializedName("is_pass")
	val isPass: Boolean? = null,

	@field:SerializedName("payment_created")
	val paymentCreated: String? = null,

	@field:SerializedName("status")
	val status: String? = null
): Serializable
