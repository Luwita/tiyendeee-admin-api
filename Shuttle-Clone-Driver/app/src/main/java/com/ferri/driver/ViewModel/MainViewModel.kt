package com.ferri.driver.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ferri.driver.Activity.VerificationActivity
import com.ferri.driver.Model.*
import com.ferri.driver.RetrofitRepository.MainRepo

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repo = MainRepo(application)

    fun driverLogin(phoneNo: String): MutableLiveData<DriverLoginResponseModel> {
        repo.driverLogin(phoneNo)
        return repo.loginData
    }

    fun verifyOTP(token: String,deviceToken:String, otp: Int): MutableLiveData<DefaultResponse> {
        repo.verifyOTP(token,deviceToken,otp)
        return repo.otpVerify
    }

    fun resendOtp(token: String, phoneNo: String): MutableLiveData<DefaultResponse> {
        repo.resendOtp(token,phoneNo)
        return repo.reSendOtp
    }

    fun myTrips(token: String): MutableLiveData<TripListResponseModel> {
        repo.myTrips(token)
        return repo.tripList
    }

    fun stopsDetails(token: String, stopId: String, routeId: String, bookingDate: String): MutableLiveData<StopsDetailResponseModel> {
        repo.stopsDetails(token,stopId, routeId, bookingDate)
        return repo.stopDetails
    }

    fun getDriverDetails(token: String): MutableLiveData<DriverDetailsResponseModel> {
        repo.getDriverDetails(token)
        return repo.driverDetails
    }

    fun tokenRefresh(phone: String, csrfToken: String, onModel: String): MutableLiveData<RefreshTokenModel> {
        repo.tokenRefresh(phone, csrfToken, onModel)
        return repo.refreshToken
    }

    fun updateTicketStatus(token: String, pnrno: String,travelStatus:String): MutableLiveData<DefaultResponse> {
        repo.updateTicketStatus(token,pnrno,travelStatus)
        return repo.scannedTicketStatus
    }

    fun getNotificationData(token: String): MutableLiveData<NotificationResponseModel> {
        repo.getNotificationData(token)
        return repo.notificationListData
    }

    fun updateNotificationStatus(token: String, id: String,readStatus:String): MutableLiveData<DefaultResponse> {
        repo.updateNotificationStatus(token,id,readStatus)
        return repo.updateNotificationStatus
    }

    fun updateTrackingStatus(token: String, id: String,trip_status:String, lat: String,lng:String,angle:String): MutableLiveData<TrackingStatusResponse> {
        repo.updateTrackingStatus(token,id,trip_status,lat,lng,angle)
        return repo.updateTrackingData
    }


    fun logOut(token: String, csrfToken: String): MutableLiveData<DefaultResponse> {
        repo.logOut(token, csrfToken)
        return repo.logout
    }

}