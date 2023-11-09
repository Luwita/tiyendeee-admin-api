package com.ferri.driver.Model

import com.google.gson.annotations.SerializedName

data class ScanTicketResponseModel(

	@field:SerializedName("bus_model_no")
	val busModelNo: String? = null,

	@field:SerializedName("passengers")
	val passengers: String? = null,

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("seat_nos")
	val seatNos: List<String?>? = null,

	@field:SerializedName("has_return")
	val hasReturn: String? = null,

	@field:SerializedName("bus_name")
	val busName: String? = null,

	@field:SerializedName("pnr_no")
	val pnrNo: String? = null,

	@field:SerializedName("final_total_fare")
	val finalTotalFare: String? = null,

	@field:SerializedName("travel_status")
	val travelStatus: String? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null
)
