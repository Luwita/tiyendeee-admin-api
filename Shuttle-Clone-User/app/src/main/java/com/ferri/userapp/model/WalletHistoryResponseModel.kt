package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class WalletHistoryResponseModel(

	@field:SerializedName("data")
	val data: ArrayList<WalletData>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class WalletData(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("payment_status")
	val paymentStatus: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: Int? = null,

	@field:SerializedName("payment_created")
	val paymentCreated: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
