package com.ferri.driver.Model

import com.google.gson.annotations.SerializedName
class DefaultResponse {
    @SerializedName("message")
    val message: String? = null

    @SerializedName("otp")
    val otp: Int? = null

    @SerializedName("status")
    val isStatus = false

}