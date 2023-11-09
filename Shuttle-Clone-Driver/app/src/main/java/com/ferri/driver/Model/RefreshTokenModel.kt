package com.ferri.driver.Model

import com.google.gson.annotations.SerializedName

data class RefreshTokenModel(

	@field:SerializedName("data")
	val data: RefreshData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class RefreshData(

	@field:SerializedName("csrfToken")
	val csrfToken: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
