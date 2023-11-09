package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class WalletBalanceResponseModel(

	@field:SerializedName("data")
	val data: WData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class WData(

	@field:SerializedName("amount")
	val amount: Int? = null
)
