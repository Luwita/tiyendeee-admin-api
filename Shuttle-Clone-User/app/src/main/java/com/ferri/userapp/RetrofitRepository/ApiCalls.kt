package com.ferri.userapp.RetrofitRepository

import com.ferri.userapp.model.*
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiCalls {
    // Register new user
    @FormUrlEncoded
    @POST("users/register")
    fun registerUser(@Field("phone") phone: String?, @Field("device_id")device_id: String): Single<UserRegisterResponseModel>

    // verify new  user
    @FormUrlEncoded
    @POST("users/verify")
    fun verifyUser(
            @Header("Authorization") token: String?,
            @Field("device_token")device_token: String,
            @Field("otp") otp: Int): Single<DefaultResponse>

    @FormUrlEncoded
    @POST("users/re-send")
    fun resendOtp(
            @Header("Authorization") token: String?,
            @Field("phone") otp: String?): Single<DefaultResponse>

    @FormUrlEncoded
    @POST("users/refresh-token")
    fun refreshToken(
            @Field("phone") phone: String?,
            @Field("csrfToken") csrfToken: String?,
            @Field("onModel") onModel: String?): Single<RefreshTokenModel>

    @FormUrlEncoded
    @POST("users/help")
    fun getHelp(
            @Header("Authorization") token: String?,
            @Field("contact") contact: String?,
            @Field("helpemail") helpemail: String?,
            @Field("description") description: String?): Single<DefaultResponse>

    @FormUrlEncoded
    @POST("users/payment/verify")
    fun verifyWalletAddPayment(
            @Header("Authorization") token: String?,
            @Field("paymentId") paymentId: String?,
            @Field("orderId") orderId: String?,
            @Field("signature") signature: String?,
            @Field("status") status: Boolean?): Single<DefaultResponse>

    @FormUrlEncoded
    @POST("booking/pass-payment-verify")
    fun verifyPassPayment(
            @Header("Authorization") token: String?,
            @Field("paymentId") paymentId: String?,
            @Field("orderId") orderId: String?,
            @Field("signature") signature: String?,
            @Field("status") status: Boolean?): Single<DefaultResponse>

    @FormUrlEncoded
    @POST("booking/payment-verify")
    fun verifyBookingPayment(
            @Header("Authorization") token: String?,
            @Field("paymentId") paymentId: String?,
            @Field("orderId") orderId: String?,
            @Field("signature") signature: String?,
            @Field("status") status: Boolean?): Single<DefaultResponse>

    @FormUrlEncoded
    @POST("users/addmoney")
    fun addMoney(
            @Header("Authorization") token: String?,
            @Field("amount") amount: String?): Single<PaymentInitiateDataResponse>

    @GET("users/walletcheck")
    fun checkWalletBalance(
            @Header("Authorization") token: String?): Single<WalletBalanceResponseModel>

    @FormUrlEncoded
    @POST("users/trip-track")
    fun tripTracking(
        @Header("Authorization") token: String?,
        @Field("pnr_no") pnr_no: String?,
    ): Single<TripTrackingStatusResponse>

    @FormUrlEncoded
    @POST("users/searchlocation")
    fun searchLocation(
            @Header("Authorization") token: String?,
            @Field("address") address: String?,
            @Field("limit") limit: String?): Single<SearchLocationResponseModel>

    @FormUrlEncoded
    @POST("searches/google")
    fun getSearchLocation(
        @Header("Authorization") token: String?,
        @Field("address") address: String?,
        @Field("limit") limit: String?): Single<SearchLocationResponseModel>

    @POST("searches/savelocation")
    fun saveSearchLocationData(
        @Header("Authorization") token: String?,
        @Body data: SaveLocationRequestModel?): Single<DefaultResponse>


    @GET("routes/explore")
    fun exploreRoutes(
            @Header("Authorization") token: String?): Single<ExploreRoutesResponseModel>

    @FormUrlEncoded
    @POST("routes/{id}")
    fun routeStops(
            @Header("Authorization") token: String?,
            @Path("id") address: String?,
            @Field("pickup_stop_id") pickup_stop_id: String?,
            @Field("drop_stop_id") drop_stop_id: String?): Single<RouteStopsResponseModel>

    @FormUrlEncoded
    @POST("buses/{id}")
    fun busSeatsLayout(
            @Header("Authorization") token: String?,
            @Path("id") address: String?,
            @Field("route_id") route_id: String?,
            @Field("pickup_stop_id") pickup_stop_id: String?,
            @Field("drop_stop_id") drop_stop_id: String?,
            @Field("type") type: String?,
            @Field("has_return") has_return: String?,
            @Field("current_date") currentDate: String?,
            @Field("end_date") endDate: String?
    ): Single<BusSeatsResponseModel>

    @FormUrlEncoded
    @POST("routes/route-search")
    fun searchRoutes(
            @Header("Authorization") token: String?,
            @Field("pickup_lat") pickup_lat: String?,
            @Field("pickup_long") pickup_long: String?,
            @Field("drop_lat") drop_lat: String?,
            @Field("drop_long") drop_long: String?,
            @Field("current_date") current_date: String?,
            @Field("end_date") end_date: String?,
            @Field("type") type: String?,
            @Field("has_return") has_return: String?
    ): Single<SearchRoutesResponseModel>

    @FormUrlEncoded
    @POST("users/add-update-office-and-home")
    fun addOfficeRideAddress(
            @Header("Authorization") token: String?,
            @Field("home_lat") home_lat: String?,
            @Field("home_lng") home_lng: String?,
            @Field("home_address") home_address: String?,
            @Field("home_timing") home_timing: String?,
            @Field("office_lat") office_lat: String?,
            @Field("office_lng") office_lng: String?,
            @Field("office_address") office_address: String?,
            @Field("office_timing") office_timing: String?): Single<UserProfileUpdateResponse>

    @FormUrlEncoded
    @POST("booking/payment-pass")
    fun payForPass(
            @Header("Authorization") token: String?,
            @Field("bus_id") bus_id: String?,
            @Field("route_id") route_id: String?,
            @Field("pickup_stop_id") pickup_stop_id: String?,
            @Field("drop_stop_id") drop_stop_id: String?,
            @Field("pass_id") pass_id: String?,
            @Field("pass_no_of_rides") pass_no_of_rides: String?,
            @Field("pass_amount") pass_amount: String?,
            @Field("seat_no") seat_no: String?,
            @Field("has_return") has_return: String?,
            @Field("payment_mode") payment_mode: String?,
            @Field("date") date: String?
    ): Single<PaymentInitiateDataResponse>



    @FormUrlEncoded
    @POST("fare/generate-seat-fare")
    fun getRouteFare(
            @Header("Authorization") token: String?,
            @Field("bus_id") bus_id: String?,
            @Field("route_id") route_id: String?,
            @Field("pickup_stop_id") pickup_stop_id: String?,
            @Field("drop_stop_id") drop_stop_id: String?,
            @Field("seat_no") seat_no: String,
            @Field("start_date") start_date: String,
            @Field("has_return") has_return: String?): Single<GeneratedFareResponseModel>


    @POST("booking/create")
    fun createBooking(
            @Header("Authorization") token: String?,
            @Body data: BookingRequestData?): Single<BookingResponseModel>

    @FormUrlEncoded
    @POST("users/my-trips")
    fun getBookingList(
            @Header("Authorization") token: String?,
            @Field("offset") offset: Int?,
            @Field("limit") limit: Int?,
            @Field("travel_status") travelStatus: String?
    ): Single<BookingListResponseModel>

    @FormUrlEncoded
    @POST("booking/payment")
    fun payRouteFee(
            @Header("Authorization") token: String?,
            @Field("pnr_no") pnr_no: String?,
            @Field("amount") amount: String?,
            @Field("payment_mode") payment_mode: String?,
            @Field("date") date: String?
    ): Single<PaymentInitiateDataResponse>

    @FormUrlEncoded
    @POST("booking/setreminder")
    fun setReminder(
            @Header("Authorization") token: String?,
            @Field("pnr_no") pnr_no: String?,
            @Field("every") every: ArrayList<String>,
            @Field("time") time: String?): Single<DefaultResponse>


    @FormUrlEncoded
    @POST("suggest/create")
    fun suggestRoute(
            @Header("Authorization") token: String?,
            @Field("pickup_address") pickup_address: String?,
            @Field("pickup_lat") pickup_lat: String?,
            @Field("pickup_lng") pickup_lng: String?,
            @Field("drop_address") drop_address: String?,
            @Field("drop_lat") drop_lat: String?,
            @Field("drop_lng") drop_lng: String?,
            @Field("pickup_city") pickup_city: String?,
            @Field("pickup_state") pickup_state: String?,
            @Field("drop_city") drop_city: String?,
            @Field("drop_state") drop_state: String?
    ): Single<DefaultResponse>

    @FormUrlEncoded
    @POST("booking/cancel")
    fun cancelBooking(
            @Header("Authorization") token: String?,
            @Field("pnr_no") pnr_no: String?,
            @Field("current_date") current_date: String?
    ): Single<DefaultResponse>

    @GET("offers")
    fun getOffers(
        @Header("Authorization") token: String?): Single<OffersResponseModel>

    @GET("users/wallet-transactions")
    fun getWalletHistory(
            @Header("Authorization") token: String?): Single<WalletHistoryResponseModel>

    @GET("users/booking-transactions")
    fun getBookingHistory(
            @Header("Authorization") token: String?,
            @Query("offset") offset: Int?,
            @Query("limit") limit: Int?,
    ): Single<BookingTransactionHistoryResponse>

    @PUT("users/logout")
    fun logOutUser(
            @Header("Authorization") token: String?, @Header("csrf-token") ctoken: String?): Single<DefaultResponse>

    @GET("users/me")
    fun getProfileDetails(
            @Header("Authorization") token: String?): Single<UserProfileUpdateResponse>

    @GET("users/refercode")
    fun getReferDetails(
            @Header("Authorization") token: String?): Single<ReferCodeModel>

    @GET("settings?type=payments")
    fun paymentSettings(
            @Header("Authorization") token: String?): Single<RzPayDataResponseModel>

    // Update  user
    @Multipart
    @POST("users/updateuser")
    fun updateUser(
            @Header("Authorization") token: String?,
            @Part file: MultipartBody.Part?,
            @Part("firstname") firstname: RequestBody?,
            @Part("lastname") lastname: RequestBody?,
            @Part("gender") gender: RequestBody?,
            @Part("email") email: RequestBody?,
            @Part("referedby") referedby: RequestBody?,
            @Part("social_id") socialid: RequestBody?,
            @Part("mode") mode: RequestBody?,
            @Part("device_token") device_token: RequestBody?,
            @Part("home_address") home_address: RequestBody?,
            @Part("home_lat") home_lat: RequestBody?,
            @Part("home_lng") home_lng: RequestBody?,
            @Part("office_address") office_address: RequestBody?,
            @Part("office_lat") office_lat: RequestBody?,
            @Part("office_lng") office_lng: RequestBody?,
            @Part("home_timing") home_timing: RequestBody?,
            @Part("office_timing") office_timing: RequestBody?
    ): Single<UserProfileUpdateResponse>
}