package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OffersResponseModel(

	@field:SerializedName("data")
	val data: ArrayList<OffersData>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
):Serializable

data class OffersData(

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("routeId")
	val routeId: Any? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("terms")
	val terms: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("ids")
	val ids: String? = null,

	@field:SerializedName("discount")
	val discount: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null,

	@field:SerializedName("picture")
	val picture: String? = null,

	@field:SerializedName("status")
	val status: String? = null
):Serializable
