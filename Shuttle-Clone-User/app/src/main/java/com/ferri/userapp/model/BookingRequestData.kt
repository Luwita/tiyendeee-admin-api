package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class BookingRequestData(
        @field:SerializedName("passengerDetailsItem")
        val passengerDetailsItem: List<PassengerDetailsItem>?,
        @field:SerializedName("offer_code")
        val date: String?,
        @field:SerializedName("fareData")
        val fareData: FareData
)
