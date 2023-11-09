package com.ferri.userapp.model

import com.google.gson.annotations.SerializedName

data class PaymentInitiateDataResponse(

        @field:SerializedName("data")
        val data: InitiateData? = null,

        @field:SerializedName("verify_url")
        val verifyUrl: String? = null,

        @field:SerializedName("message")
        val message: String? = null,

        @field:SerializedName("status")
        val status: Boolean? = null
)

data class PaymentSettings(

        @field:SerializedName("theme_color")
        val themeColor: String? = null,

        @field:SerializedName("contact")
        val contact: String? = null,

        @field:SerializedName("is_production")
        val isProduction: Boolean? = null,

        @field:SerializedName("text_name")
        val textName: String? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("logo")
        val logo: String? = null,

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

data class Prefill(

        @field:SerializedName("contact")
        val contact: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("email")
        val email: String? = null
)

data class InitiateData(

        @field:SerializedName("payment_settings")
        val paymentSettings: PaymentSettings? = null,

        @field:SerializedName("amount")
        val amount: Int? = null,

        @field:SerializedName("notes")
        val notes: Notes? = null,

        @field:SerializedName("orderId")
        val orderId: String? = null,

        @field:SerializedName("prefill")
        val prefill: Prefill? = null,

        @field:SerializedName("payment_mode")
        val paymentMode: String? = null,

        @field:SerializedName("name")
        val name: String? = null
)

data class Notes(

        @field:SerializedName("ferri_order_id")
        val ferriOrderId: String? = null,

        @field:SerializedName("booking_pnr_no")
        val booking_pnr_no: String? = null
)
