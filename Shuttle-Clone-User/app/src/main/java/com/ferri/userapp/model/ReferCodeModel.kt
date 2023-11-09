package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class ReferCodeModel(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class Data(

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("referral_policy")
	val referralPolicy: String? = null,

	@field:SerializedName("refercode")
	val refercode: String? = null
)
