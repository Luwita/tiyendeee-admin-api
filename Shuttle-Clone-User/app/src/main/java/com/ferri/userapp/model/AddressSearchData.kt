package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class AddressSearchData (
    val name:String,
    val sub_name:String,
    val lat:Double,
    val lng:Double
)