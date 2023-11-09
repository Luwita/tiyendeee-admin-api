package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BusSeatsResponseModel(

    @field:SerializedName("data")
    val data: BusData? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
) : Serializable

data class BusSeatsItem(

    @field:SerializedName("bus")
    val bus: Int? = null,

    @field:SerializedName("seat_no")
    val seatNo: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("is_ladies")
    val isladies: Boolean? = false,

    @field:SerializedName("seat_status")
    val seatStatus: String? = null
) : Serializable

data class BusData(

    @field:SerializedName("bus_model_no")
    val busModelNo: String? = null,

    @field:SerializedName("bus_type")
    val busType: String? = null,

    @field:SerializedName("bus_amenities")
    val busAmenities: List<String>? = null,

    @field:SerializedName("buslayoutId")
    val buslayout: BusLayout? = null,

    @field:SerializedName("bus_brand")
    val busBrand: String? = null,

    @field:SerializedName("bus_reg_no")
    val busRegNo: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("final_total_fare")
    val final_total_fare: String? = null,

    @field:SerializedName("tax")
    val tax: String? = null,

    @field:SerializedName("final_pass_fare")
    val passesResponseModel: List<PassesList>? = null,

    @field:SerializedName("bus_name")
    val busName: String? = null,

    @field:SerializedName("pickup_name")
    val pickupName: String? = null,

    @field:SerializedName("pickup_time")
    val pickupTime: String? = null,

    @field:SerializedName("drop_name")
    val dropName: String? = null,

    @field:SerializedName("drop_time")
    val dropTime: String? = null,

    @field:SerializedName("seat_no")
    val seatNo: String? = null,

    @field:SerializedName("created_date")
    val createdDate: String? = null,

    @field:SerializedName("user_total_wallet_amount")
    val userWalletAmount: String? = null


) : Serializable

data class BusLayout(

    @field:SerializedName("layout")
    val layout: String? = null,

    @field:SerializedName("seat_numbers")
    val seatNumbers: String? = null,

    @field:SerializedName("max_seats")
    val maxSeats: String? = null,

    @field:SerializedName("combine_seats")
    val combineSeats: List<List<BusSeatsItem>>? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("last_seat")
    val lastSeat: String? = null
) : Serializable


data class PassesList(

    @field:SerializedName("pass_no_of_rides")
    val passNoOfRides: Int? = null,

    @field:SerializedName("totalFare")
    val totalFare: Int? = null,

    @field:SerializedName("pass_no_of_valid_days")
    val passNoOfValidDays: Int? = null,

    @field:SerializedName("totalsingleFare")
    val totalsingleFare: Int? = null,

    @field:SerializedName("passId")
    val passId: String? = null,

    @field:SerializedName("pass_terms")
    val passTerms: String? = null,

    @field:SerializedName("pass_description")
    val passDescription: String? = null
) : Serializable
