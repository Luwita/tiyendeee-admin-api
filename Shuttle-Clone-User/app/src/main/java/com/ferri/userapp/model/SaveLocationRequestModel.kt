package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class SaveLocationRequestModel(

        @field:SerializedName("search_name")
        val searchName: List<SavedItems>? = null
)

data class SavedItems(

        @field:SerializedName("placeId")
        val placeId: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("sub_name")
        val subName: String? = null,


        @field:SerializedName("city")
        val city: String? = null,


        @field:SerializedName("state")
        val state: String? = null,

        @field:SerializedName("lat")
        val lat: String? = null,

        @field:SerializedName("lng")
        val lng: String? = null

)
