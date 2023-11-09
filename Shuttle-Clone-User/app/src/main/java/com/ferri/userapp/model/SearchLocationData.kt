package rigo.rigotaxi.rigouserapp.ModelClass

import com.google.android.libraries.places.api.model.Place

data class SearchLocationData(
    val pickAddress: String?,
    val pickLat: Double?,
    val pickLng: Double?,
    val dropAddress: String?,
    val dropLat: Double?,
    val dropLng: Double?,
    val setAddressFor: String?,
    val zoneName: String?
)