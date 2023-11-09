package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class RzPayDataResponseModel(

	@field:SerializedName("data")
	val data: RzPayData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class RzPayData(

	@field:SerializedName("theme_color")
	val themeColor: String? = null,

	@field:SerializedName("contact")
	val contact: String? = null,

	@field:SerializedName("is_production")
	val isProduction: Boolean? = null,

	@field:SerializedName("text_name")
	val textName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: Any? = null,

	@field:SerializedName("payment_capture")
	val paymentCapture: Boolean? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("secret")
	val secret: String? = null,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
