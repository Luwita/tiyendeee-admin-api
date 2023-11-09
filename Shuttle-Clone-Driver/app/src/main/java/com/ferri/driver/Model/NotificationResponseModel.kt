package com.ferri.driver.Model

import com.google.gson.annotations.SerializedName

data class NotificationResponseModel(

	@field:SerializedName("data")
	val data: List<NotificationDataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class NotificationDataItem(

	@field:SerializedName("read")
	val read: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)
