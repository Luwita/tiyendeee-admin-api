package com.ferri.driver.RetrofitRepository

import com.ferri.driver.Model.*
import io.reactivex.Single
import retrofit2.http.*

interface ApiCalls {

    // Login driver
    @FormUrlEncoded
    @POST("drivers/login")
    fun loginDriver(@Field("phone") phone: String): Single<DriverLoginResponseModel>

    //verify OTP
    @FormUrlEncoded
    @POST("drivers/verify")
    fun verifyOtp(
        @Header("Authorization") token: String,
        @Field("device_token")deviceToken:String,
        @Field("otp") otp: Int
    ): Single<DefaultResponse>

    //Refresh token
    @FormUrlEncoded
    @POST("drivers/refresh-token")
    fun refreshToken(
        @Field("phone") phone: String?,
        @Field("csrfToken") csrfToken: String?,
        @Field("onModel") onModel: String?
    ): Single<RefreshTokenModel?>?

    @GET("drivers/getdriver")
    fun getProfileDetails(
        @Header("Authorization") token: String
    ): Single<DriverDetailsResponseModel>

    @FormUrlEncoded
    @PUT("drivers/re-send")
    fun resendOtp(
        @Header("Authorization") token: String,
        @Field("phone") phone: String
    ): Single<DefaultResponse>

    @GET("drivers/my-trips")
    fun myTripList(
        @Header("Authorization") token: String
    ): Single<TripListResponseModel>

    @FormUrlEncoded
    @POST("drivers/get-stop-details")
    fun getStopsDetails(
        @Header("Authorization") token: String,
        @Field("stopId") stopId: String,
        @Field("routeId") routeId: String,
        @Field("booking_date") bookingDate: String
    ): Single<StopsDetailResponseModel>

    @GET("drivers/notifications")
    fun getNotifications(
        @Header("Authorization") token: String
    ): Single<NotificationResponseModel>

    @POST("drivers/notifications/{id}/{readStatus}")
    fun updateNotificationStatus(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Path("readStatus") readStatus: String
    ): Single<DefaultResponse>

    @FormUrlEncoded
    @POST("drivers/update-assign-status/{id}")
    fun updateTrackingStatus(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Field("trip_status") trip_status: String,
        @Field("lat") lat: String,
        @Field("lng") lng: String,
        @Field("angle") angle: String
    ): Single<TrackingStatusResponse>

    @FormUrlEncoded
    @POST("drivers/update-booking-status")
    fun updatePassengerStatus(
        @Header("Authorization") token: String,
        @Field("pnr_no") pnr_no: String,
        @Field("travel_status") travel_status: String
    ): Single<DefaultResponse>


    @PUT("drivers/logout")
    fun logOut(
        @Header("Authorization") token: String,
        @Header("csrf-token") ctoken: String
    ): Single<DefaultResponse>

}