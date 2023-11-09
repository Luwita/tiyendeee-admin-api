package com.ferri.userapp.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferri.userapp.R
import com.ferri.userapp.RetrofitRepository.ApiCallBack
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import com.ferri.userapp.model.*
import com.ferri.userapp.utils.*
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeFragmentViewModel : ViewModel() {
    private val TAG = "HomeFragmentViewModel"

    val searchLocationList: MutableLiveData<MutableList<SearchedDataItem>?>
    val savedSearchLocationList: MutableLiveData<MutableList<SearchedDataItem>?>
    val searchedRoutes: MutableLiveData<SearchRoutesResponseModel>
    val routeStops: MutableLiveData<RouteStopsResponseModel>
    val trackRoutes: MutableLiveData<TripTrackingStatusResponse>
    val busSeatsResponse: MutableLiveData<BusSeatsResponseModel>
    val exploreRoutes: MutableLiveData<ExploreRoutesResponseModel>
    val getRouteFare: MutableLiveData<GeneratedFareResponseModel>
    val bookingResponse: MutableLiveData<BookingResponseModel>
    val paymentResponse: MutableLiveData<PaymentInitiateDataResponse>
    val bookingList: MutableLiveData<BookingListResponseModel>
    val addOfficeRideAddress: MutableLiveData<UserProfileUpdateResponse>
    val setReminder: MutableLiveData<DefaultResponse>
    val payForPass: MutableLiveData<PaymentInitiateDataResponse>
    val cancelBooking: MutableLiveData<DefaultResponse>
    val isDataSaved: MutableLiveData<Boolean>
    val offers: MutableLiveData<OffersResponseModel>

    init {
        searchLocationList = MutableLiveData()
        savedSearchLocationList = MutableLiveData()
        searchedRoutes = MutableLiveData()
        isDataSaved = MutableLiveData()
        exploreRoutes = MutableLiveData()
        getRouteFare = MutableLiveData()
        bookingResponse = MutableLiveData()
        paymentResponse = MutableLiveData()
        busSeatsResponse = MutableLiveData()
        bookingList = MutableLiveData()
        addOfficeRideAddress = MutableLiveData()
        setReminder = MutableLiveData()
        routeStops = MutableLiveData()
        payForPass = MutableLiveData()
        offers = MutableLiveData()
        cancelBooking = MutableLiveData()
        trackRoutes = MutableLiveData()
    }

    fun searchLocation(mContext: Context?, token: String?, location: String?, limit: String?) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().searchLocation(token, location, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchLocationResponseModel?>() {
                    override fun onSuccess(response: SearchLocationResponseModel) {
                        try {
                            Log.i(TAG, "onResponse: 1" + Gson().toJson(response))
                            if (response.isStatus) searchLocationList.postValue(response.data) else searchLocationList.postValue(
                                null
                            )
                        } catch (e: Exception) {
                            Log.i(TAG, "onResponse:searchLocation Error=" + e.localizedMessage)
                            searchLocationList.postValue(null)
                        }
                    }

                    override fun onError(e: Throwable) {
                        searchLocationList.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) searchLocation(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        location,
                                        limit
                                    )
                                    else sessionExpireDialog(mContext)
                                })

                    }

                })

        } else toast(mContext, mContext.getString(R.string.txt_Network))
    }

    fun getSavedSearchLocation(
        mContext: Context?,
        token: String?,
        location: String?,
        limit: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().getSearchLocation(token, location, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchLocationResponseModel?>() {
                    override fun onSuccess(response: SearchLocationResponseModel) {
                        try {
                            Log.i(TAG, "onResponse: 2" + Gson().toJson(response))
                            if (response.isStatus) savedSearchLocationList.postValue(response.data) else savedSearchLocationList.postValue(
                                null
                            )
                        } catch (e: Exception) {
                            Log.i(
                                TAG,
                                "onResponse:getSavedSearchLocation Error=" + e.localizedMessage
                            )
                            savedSearchLocationList.postValue(null)
                        }
                    }

                    override fun onError(e: Throwable) {
                        savedSearchLocationList.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getSavedSearchLocation(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        location,
                                        limit
                                    )
                                    else sessionExpireDialog(mContext)
                                })

                    }

                })

        } else toast(mContext, mContext.getString(R.string.txt_Network))
    }

    fun saveSearchLocation(
        mContext: Context?,
        token: String?,
        locationList: SaveLocationRequestModel?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().saveSearchLocationData(token, locationList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        isDataSaved.postValue(
                            try {
                                Log.i(TAG, "onResponse: " + Gson().toJson(response))
                                if (response.isStatus) true else false
                            } catch (e: Exception) {
                                false
                            }
                        )
                    }

                    override fun onError(e: Throwable) {
                        isDataSaved.postValue(false)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) saveSearchLocation(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        locationList
                                    )
                                    else sessionExpireDialog(mContext)
                                })

                    }

                })

        } else toast(mContext, mContext.getString(R.string.txt_Network))
    }

    fun getOffers(
        mContext: Context?,
        token: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().getOffers(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<OffersResponseModel?>() {
                    override fun onSuccess(response: OffersResponseModel) {
                        mylog(TAG, "offersResponse: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            offers.postValue(response)
                        } else {
                            offers.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: offersResponse=" + e.localizedMessage)
                        offers.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getOffers(mContext, getPreference(mContext, Constants.TOKEN))
                                    else sessionExpireDialog(mContext)
                                })
                    }

                })

        } else toast(mContext, mContext.getString(R.string.txt_Network))
    }

    fun searchRoutes(
        mContext: Context?,
        token: String?,
        pickUpLat: String,
        pickUpLng: String,
        dropLat: String,
        dropLng: String,
        currentDate: String,
        endDate: String,
        type: String,
        has_return: String
    ) {
        if (isNetworkAvailable(mContext!!)) {

            RetrofitClient.getClient()
                .searchRoutes(token, pickUpLat, pickUpLng, dropLat, dropLng, currentDate,
                        endDate, type, has_return)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchRoutesResponseModel?>() {
                    override fun onSuccess(response: SearchRoutesResponseModel) {
                        mylog(TAG, "searchRoutes: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            searchedRoutes.postValue(response)
                        } else {
                            searchedRoutes.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: searchRoutes=" + e.localizedMessage)
                        searchedRoutes.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) searchRoutes(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        pickUpLat,
                                        pickUpLng,
                                        dropLat,
                                        dropLng,
                                        currentDate,
                                        endDate,
                                        type,
                                        has_return
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun addOfficeRideAddress(
        mContext: Context?,
        token: String?,
        home_lat: String,
        home_lng: String,
        home_address: String,
        home_timing: String,
        office_lat: String,
        office_lng: String,
        office_address: String,
        office_timing: String
    ) {
        if (isNetworkAvailable(mContext!!)) {

            RetrofitClient.getClient().addOfficeRideAddress(
                token,
                home_lat,
                home_lng,
                home_address,
                home_timing,
                office_lat,
                office_lng,
                office_address,
                office_timing
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserProfileUpdateResponse?>() {
                    override fun onSuccess(response: UserProfileUpdateResponse) {
                        mylog(TAG, "addOfficeRideAddress: Response=${Gson().toJson(response)}")
                        if (response.isStatus) {
                            addOfficeRideAddress.postValue(response)
                        } else {
                            addOfficeRideAddress.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: addOfficeRideAddress=" + e.localizedMessage)
                        addOfficeRideAddress.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) addOfficeRideAddress(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        home_lat,
                                        home_lng,
                                        home_address,
                                        home_timing,
                                        office_lat,
                                        office_lng,
                                        office_address,
                                        office_timing
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun payForPass(
        mContext: Context?,
        token: String?,
        bus_id: String,
        route_id: String,
        pickup_stop_id: String,
        drop_stop_id: String,
        pass_id: String,
        pass_no_of_rides: String,
        pass_amount: String,
        seat_no: String,
        has_return: String,
        payment_mode: String,
        bookingDate: String
    ) {
        if (isNetworkAvailable(mContext!!)) {

            RetrofitClient.getClient().payForPass(
                token,
                bus_id,
                route_id,
                pickup_stop_id,
                drop_stop_id,
                pass_id,
                pass_no_of_rides,
                pass_amount,
                seat_no,
                has_return,
                payment_mode,
                bookingDate
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PaymentInitiateDataResponse?>() {
                    override fun onSuccess(response: PaymentInitiateDataResponse) {
                        mylog(TAG, "payForPass: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            payForPass.postValue(response)
                        } else {
                            payForPass.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: payForPass=" + e.localizedMessage)
                        payForPass.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) payForPass(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        bus_id,
                                        route_id,
                                        pickup_stop_id,
                                        drop_stop_id,
                                        pass_id,
                                        pass_no_of_rides,
                                        pass_amount,
                                        seat_no,
                                        has_return,
                                        payment_mode,
                                        bookingDate
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun routeStops(
        mContext: Context?,
        token: String?,
        id: String,
        pickup_stop_id: String,
        drop_stop_id: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().routeStops(token, id, pickup_stop_id, drop_stop_id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RouteStopsResponseModel?>() {
                    override fun onSuccess(response: RouteStopsResponseModel) {
                        mylog(TAG, "routeStops: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            routeStops.postValue(response)
                        } else {
                            routeStops.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: routeStops=" + e.localizedMessage)
                        routeStops.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) routeStops(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        id,
                                        pickup_stop_id,
                                        drop_stop_id
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun trackRoutes(
        mContext: Context?,
        token: String?,
        pnr_no: String?
    ) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().tripTracking(token, pnr_no)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TripTrackingStatusResponse?>() {
                    override fun onSuccess(response: TripTrackingStatusResponse) {
                        mylog(TAG, "trackRoutes: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            trackRoutes.postValue(response)
                        } else {
                            trackRoutes.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: trackRoutes=" + e.localizedMessage)
                        trackRoutes.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) trackRoutes(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        pnr_no
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }


    fun busSeats(
        mContext: Context?,
        token: String?,
        bus_id: String,
        route_id: String,
        pickup_stop_id: String,
        drop_stop_id: String,
        type: String,
        has_return: String,
        currentDate: String,
        endDate: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().busSeatsLayout(
                token,
                bus_id,
                route_id,
                pickup_stop_id,
                drop_stop_id,
                type,
                has_return,
                currentDate,
                endDate
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BusSeatsResponseModel?>() {
                    override fun onSuccess(response: BusSeatsResponseModel) {
                        mylog(TAG, "busSeats: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            busSeatsResponse.postValue(response)
                        } else {
                            busSeatsResponse.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: busSeats=" + e.localizedMessage)
                        busSeatsResponse.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) busSeats(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        bus_id,
                                        route_id,
                                        pickup_stop_id,
                                        drop_stop_id,
                                        type,
                                        has_return,
                                        currentDate,
                                        endDate
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun getRouteFare(
        mContext: Context?,
        token: String?,
        bus_id: String,
        route_id: String,
        pickup_stop_id: String,
        drop_stop_id: String,
        seat_no: String,
        start_date: String,
        has_return: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            mylog(TAG, "getRouteFare: seat_no=${seat_no}")
            RetrofitClient.getClient().getRouteFare(
                token,
                bus_id,
                route_id,
                pickup_stop_id,
                drop_stop_id,
                seat_no,
                start_date,
                has_return
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<GeneratedFareResponseModel?>() {
                    override fun onSuccess(response: GeneratedFareResponseModel) {
                        mylog(TAG, "getRouteFare: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            getRouteFare.postValue(response)
                        } else {
                            getRouteFare.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: getRouteFare=" + e.localizedMessage)
                        getRouteFare.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getRouteFare(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        bus_id,
                                        route_id,
                                        pickup_stop_id,
                                        drop_stop_id,
                                        seat_no,
                                        start_date,
                                        has_return
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun createBooking(mContext: Context?, token: String?, bookingData: BookingRequestData?) {
        if (isNetworkAvailable(mContext!!)) {
            mylog(TAG, "createBooking: Request=${Gson().toJson(bookingData)}")
            RetrofitClient.getClient().createBooking(token, bookingData)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BookingResponseModel?>() {
                    override fun onSuccess(response: BookingResponseModel) {
                        mylog(TAG, "createBooking: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            bookingResponse.postValue(response)
                        } else {
                            bookingResponse.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: createBooking=" + e.localizedMessage)
                        bookingResponse.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) createBooking(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        bookingData
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun payRouteFee(
        mContext: Context?,
        token: String?,
        pnr_no: String,
        amount: String,
        payment_mode: String,
        bookingDate: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().payRouteFee(token, pnr_no, amount, payment_mode,bookingDate)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PaymentInitiateDataResponse?>() {
                    override fun onSuccess(response: PaymentInitiateDataResponse) {
                        mylog(TAG, "payRouteFee: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            paymentResponse.postValue(response)
                        } else {
                            paymentResponse.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: payRouteFee=" + e.localizedMessage)
                        paymentResponse.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) payRouteFee(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        pnr_no,
                                        amount,
                                        payment_mode,
                                        bookingDate
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun setReminder(
        mContext: Context?,
        token: String?,
        pnr_no: String,
        every: ArrayList<String>,
        time: String
    ) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().setReminder(token, pnr_no, every, time)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        mylog(TAG, "setReminder: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            setReminder.postValue(response)
                        } else {
                            setReminder.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: setReminder=" + e.localizedMessage)
                        setReminder.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) setReminder(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),
                                        pnr_no,
                                        every,
                                        time
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun exploreRoutes(mContext: Context?, token: String?) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().exploreRoutes(token)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ExploreRoutesResponseModel?>() {
                    override fun onSuccess(response: ExploreRoutesResponseModel) {
                        mylog(TAG, "exploreRoutes: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            exploreRoutes.postValue(response)
                        } else {
                            exploreRoutes.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: exploreRoutes=" + e.localizedMessage)
                        exploreRoutes.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) exploreRoutes(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN)
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun getBookings(mContext: Context?, token: String?, offset: Int?, limit: Int?, travelStatus: String?) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().getBookingList(token,offset,limit,travelStatus)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BookingListResponseModel?>() {
                    override fun onSuccess(response: BookingListResponseModel) {
                        mylog(TAG, "getBookings: Response=${Gson().toJson(response)}")
                        if (response.status!!) {
                            bookingList.postValue(response)
                        } else {
                            bookingList.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: getBookings=" + e.localizedMessage)
                        bookingList.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) getBookings(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),offset,limit,travelStatus
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    fun cancelBookings(mContext: Context?, token: String?, pnr_no: String, currentDate: String?) {
        if (isNetworkAvailable(mContext!!)) {
            RetrofitClient.getClient().cancelBooking(token,pnr_no,currentDate)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                    override fun onSuccess(response: DefaultResponse) {
                        mylog(TAG, "cancelBookings: Response=${Gson().toJson(response)}")
                        if (response.isStatus!!) {
                            cancelBooking.postValue(response)
                        } else {
                            cancelBooking.postValue(response)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mylog(TAG, "onError: cancelBookings=" + e.localizedMessage)
                        bookingList.postValue(null)
                        if (e.localizedMessage.equals(Constants.Unauthorized)||e.localizedMessage.equals(Constants.Forbidden))
                            checkToken(
                                mContext,
                                ApiCallBack { success ->
                                    if (success) cancelBookings(
                                        mContext,
                                        getPreference(mContext, Constants.TOKEN),pnr_no,currentDate
                                    )
                                    else sessionExpireDialog(mContext)
                                })
                    }
                })
        } else toast(mContext, mContext.getString(R.string.txt_Network))

    }

    override fun onCleared() {
        super.onCleared()
    }


}