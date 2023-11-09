package com.ferri.driver.RetrofitRepository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ferri.driver.Activity.SignInActivity
import com.ferri.driver.Activity.VerificationActivity
import com.ferri.driver.Model.*
import com.ferri.driver.Util.*
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainRepo(val application: Application) {

    val TAG = "MainRepo"

    val loginData = MutableLiveData<DriverLoginResponseModel>()
    val driverDetails = MutableLiveData<DriverDetailsResponseModel>()
    val otpVerify = MutableLiveData<DefaultResponse>()
    val reSendOtp = MutableLiveData<DefaultResponse>()
    val logout = MutableLiveData<DefaultResponse>()
    val refreshToken = MutableLiveData<RefreshTokenModel>()
    val tripList = MutableLiveData<TripListResponseModel>()
    val stopDetails = MutableLiveData<StopsDetailResponseModel>()
    val scannedTicketStatus = MutableLiveData<DefaultResponse>()
    val notificationListData = MutableLiveData<NotificationResponseModel>()
    val updateNotificationStatus = MutableLiveData<DefaultResponse>()
    val updateTrackingData = MutableLiveData<TrackingStatusResponse>()

    companion object {
        val apiClient = RetrofitClient.getClient()
    }

    fun driverLogin(phoneNo: String) {
        apiClient
            .loginDriver(phoneNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<DriverLoginResponseModel?>() {
                override fun onSuccess(response: DriverLoginResponseModel) {
                    myLog(TAG, "driverLogin->${Gson().toJson(response)}")
                    loginData.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "driverLogin->${e.localizedMessage}")
                    loginData.value = null
                }
            })
    }

    fun verifyOTP(token: String, deviceToken: String, otp: Int) {
        apiClient.verifyOtp(token,deviceToken, otp).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<DefaultResponse>() {
                override fun onSuccess(response: DefaultResponse) {
                    myLog(TAG, "verifyOTP->${Gson().toJson(response)}")
                    otpVerify.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "verifyOTP->${e.localizedMessage}")

                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) verifyOTP(
                                getPreference(application, AppConstants.TOKEN)!!, deviceToken,otp
                            )
                            else otpVerify.value = null
                        })

                }

            })
    }

    fun resendOtp(token: String, phone: String) {
        apiClient.resendOtp(token, phone).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<DefaultResponse>() {
                override fun onSuccess(response: DefaultResponse) {
                    myLog(TAG, "resendOtp->${Gson().toJson(response)}")
                    reSendOtp.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "resendOtp->${e.localizedMessage}")
                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) resendOtp(getPreference(application, AppConstants.TOKEN)!!, phone)
                            else reSendOtp.value = null
                        })

                }

            })
    }

    fun getDriverDetails(token: String) {
        apiClient.getProfileDetails(token).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<DriverDetailsResponseModel>() {
                override fun onSuccess(response: DriverDetailsResponseModel) {
                    myLog(TAG, "getDriverDetails->${Gson().toJson(response)}")
                    driverDetails.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "getDriverDetails->${e.localizedMessage}")

                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) getDriverDetails(getPreference(application, AppConstants.TOKEN)!!)
                            else driverDetails.value = null
                        })

                }

            })
    }

    fun logOut(token: String, csrftoken: String) {
        apiClient.logOut(token, csrftoken).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<DefaultResponse>() {
                override fun onSuccess(response: DefaultResponse) {
                    myLog(TAG, "logOut->${Gson().toJson(response)}")
                    logout.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "logOut->${e.localizedMessage}")
                    toast(application,"logOut->${e.localizedMessage}")

                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) logOut(getPreference(application, AppConstants.TOKEN)!!, getPreference(application, AppConstants.csrfTOKEN)!!)
                            else logout.value = null
                        })

                }

            })
    }

    fun myTrips(token: String) {
        apiClient.myTripList(token).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<TripListResponseModel>() {
                override fun onSuccess(response: TripListResponseModel) {
                    myLog(TAG, "myTrips->${Gson().toJson(response)}")
                    tripList.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "myTrips->${e.localizedMessage}")

                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) myTrips(getPreference(application, AppConstants.TOKEN)!!)
                            else tripList.value = null
                        })

                }

            })
    }

    fun stopsDetails(token: String,stopId: String,routeId: String,bookingDate: String) {
        apiClient.getStopsDetails(token,stopId, routeId, bookingDate).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<StopsDetailResponseModel>() {
                override fun onSuccess(response: StopsDetailResponseModel) {
                    myLog(TAG, "stopsDetails->${Gson().toJson(response)}")
                    stopDetails.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "stopsDetails->${e.localizedMessage}")

                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) stopsDetails(getPreference(application, AppConstants.TOKEN)!!,stopId, routeId, bookingDate)
                            else stopDetails.value = null
                        })

                }

            })
    }

    fun tokenRefresh(phone: String, csrfToken: String, onModel: String) {
        apiClient.refreshToken(phone, csrfToken, onModel)!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<RefreshTokenModel>() {
                override fun onSuccess(response: RefreshTokenModel) {
                    myLog(TAG, "tokenRefresh->${Gson().toJson(response)}")
                    refreshToken.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "tokenRefresh->${e.localizedMessage}")
                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) tokenRefresh(phone,getPreference(application, AppConstants.csrfTOKEN)!!,onModel)
                            else refreshToken.value = null
                        })


                }

            })
    }

    fun updateTicketStatus(token: String, pnrNo: String, travelStatus: String) {
        apiClient.updatePassengerStatus(token, pnrNo, travelStatus)!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<DefaultResponse>() {
                override fun onSuccess(response: DefaultResponse) {
                    myLog(TAG, "updateTicketStatus->${Gson().toJson(response)}")
                    scannedTicketStatus.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "updateTicketStatus->${e.localizedMessage}")
                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) updateTicketStatus(getPreference(application, AppConstants.TOKEN)!!,pnrNo, travelStatus)
                            else scannedTicketStatus.value = null
                        })

                }

            })
    }

    fun getNotificationData(token: String) {
        apiClient.getNotifications(token)!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<NotificationResponseModel>() {
                override fun onSuccess(response: NotificationResponseModel) {
                    myLog(TAG, "getNotificationData->${Gson().toJson(response)}")
                    notificationListData.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "getNotificationData->${e.localizedMessage}")
                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) getNotificationData(getPreference(application, AppConstants.TOKEN)!!)
                            else notificationListData.value = null
                        })

                }

            })
    }

    fun updateNotificationStatus(token: String, id: String, readStatus: String) {
        apiClient.updateNotificationStatus(token, id, readStatus)!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<DefaultResponse>() {
                override fun onSuccess(response: DefaultResponse) {
                    myLog(TAG, "updateNotificationStatus->${Gson().toJson(response)}")
                    updateNotificationStatus.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "updateNotificationStatus->${e.localizedMessage}")
                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) updateNotificationStatus(getPreference(application, AppConstants.TOKEN)!!,id,readStatus)
                            else updateNotificationStatus.value = null
                        })

                }

            })
    }

    fun updateTrackingStatus(token: String, id: String, trip_status: String, lat: String, lng: String,angle:String) {
        apiClient.updateTrackingStatus(token, id, trip_status, lat, lng,angle)!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<TrackingStatusResponse>() {
                override fun onSuccess(response: TrackingStatusResponse) {
                    myLog(TAG, "updateTrackingStatus->${Gson().toJson(response)}")
                    updateTrackingData.value = response
                }

                override fun onError(e: Throwable) {
                    myLog(TAG, "updateTrackingStatus->${e.localizedMessage}")
                    if (e.localizedMessage.equals(AppConstants.Unauthorized)||e.localizedMessage.equals(AppConstants.Forbidden))
                        checkToken(application, ApiCallBack { success ->
                            if (success) updateTrackingStatus(getPreference(application, AppConstants.TOKEN)!!,id,trip_status,lat,lng,angle)
                            else updateTrackingData.value = null
                        })

                }

            })
    }




}