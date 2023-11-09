package com.ferri.userapp.ui.fragment

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Animatable
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Log.i
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.FacebookSdk
import com.ferri.userapp.Adapters.ApiLocationSearchResultAdapter
import com.ferri.userapp.R
import com.ferri.userapp.ViewModel.HomeFragmentViewModel
import com.ferri.userapp.model.GApiSearchResultModel
import com.ferri.userapp.model.SaveLocationRequestModel
import com.ferri.userapp.model.SavedItems
import com.ferri.userapp.model.SearchedDataItem
import com.ferri.userapp.ui.events.SetLocationsEvent
import com.ferri.userapp.ui.events.SuggestRouteLocationEvent
import com.ferri.userapp.ui.events.UpdateLocationEvent
import com.ferri.userapp.utils.*
import com.ferri.userapp.utils.Constants.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.greenrobot.eventbus.EventBus
import java.util.*

class SearchLocationDialogFragment(context: Context, val searchFor: String) : DialogFragment(),
    View.OnClickListener {
    private val TAG = javaClass.name
    private lateinit var my_location_edt: EditText
    private lateinit var destination_edt: EditText
    private lateinit var imgProgressLoading: ImageView
    private lateinit var imgClearPick: ImageView
    private lateinit var imgClearDrop: ImageView
    var imgLoadingAnimation: Animatable? = null
    private var homeFragmentViewModel: HomeFragmentViewModel? = null
    private var apiLocationSearchResultAdapter: ApiLocationSearchResultAdapter? = null
    private lateinit var myApi_search_result_recyclerview: RecyclerView
    private lateinit var search_result_recyclerview: RecyclerView
    private var SET_RESULT_TO = ""
    private val SET_ADDRESS_FOR = ""
    private var SEARCHING_FOR = ""
    private val pickUpAddress: String? = null
    private val dropAddress: String? = null
    private var isSearching = true
    private var isFirstTime = false
    private var pickUpData: SearchedDataItem? = null
    private var dropData: SearchedDataItem? = null
    private var googlePlacesData: SearchedDataItem? = null
    private lateinit var no_search_result_layout: LinearLayout
    private var bottomSheetDialog: Dialog? = null
    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    private var root: View? = null
    private var mContext: Context? = null
    private lateinit var btnConfirmLocation: Button
    private val pickUpLat = 0.0
    private val pickUpLng = 0.0
    private val dropLat = 0.0
    private val dropLng = 0.0
    private var placesClient: PlacesClient? = null
    private var token: AutocompleteSessionToken? = null
    private var searchedDataItemsList: MutableList<SearchedDataItem> = ArrayList()
    private var gApiSearchResultModelList: MutableList<GApiSearchResultModel> = ArrayList()
    private var gApiSaveSearchResultModelList: MutableList<SavedItems> = ArrayList()
    var eventBus = EventBus.getDefault()

    //    private var searchResultModelList: MutableList<SearchedDataItem>? = null
    private val placeFields: List<Place.Field> = Arrays.asList(Place.Field.LAT_LNG)
    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        root = LayoutInflater.from(mContext).inflate(R.layout.fragment_search_location_dialog, null)
        bottomSheetDialog = super.onCreateDialog(savedInstanceState)
        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)



        init(root!!)
        bottomSheetDialog!!.setContentView(root!!)
        bottomSheetDialog!!.setCancelable(false)
        bottomSheetDialog!!.setCanceledOnTouchOutside(false)
        bottomSheetDialog!!.show()
        return bottomSheetDialog!!
    }


    private fun init(view: View) {
        view.findViewById<View>(R.id.imgBack).setOnClickListener {
            mContext!!.hideKeyboard(view)
            dismiss()
        }
        my_location_edt = view.findViewById(R.id.my_location_edt)
        destination_edt = view.findViewById(R.id.destination_edt)
        myApi_search_result_recyclerview = view.findViewById(R.id.myApi_search_result_recyclerview)
        search_result_recyclerview = view.findViewById(R.id.search_result_recyclerview)
        no_search_result_layout = view.findViewById(R.id.no_search_result_layout)
        btnConfirmLocation = view.findViewById(R.id.btnConfirmLocation)
        imgProgressLoading = view.findViewById(R.id.imgLoadingProgress)
        imgClearPick = view.findViewById(R.id.imgClearPick)
        imgClearDrop = view.findViewById(R.id.imgClearDrop)
        imgClearPick.setOnClickListener(this)
        imgClearDrop.setOnClickListener(this)
        btnConfirmLocation.setOnClickListener(this)
        my_location_edt.setOnClickListener(this)
        destination_edt.setOnClickListener(this)
        no_search_result_layout!!.setVisibility(View.VISIBLE)

        if (searchFor == SEARCH_FOR_ROUTS) {
            view.findViewById<LinearLayout>(R.id.tvDropLocationSearch).setVisibility(View.VISIBLE)
            destination_edt!!.requestFocus()
        } else view.findViewById<LinearLayout>(R.id.tvDropLocationSearch).setVisibility(View.GONE)


        apiLocationSearchResultAdapter =
            ApiLocationSearchResultAdapter(mContext!!, this@SearchLocationDialogFragment)
        myApi_search_result_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = apiLocationSearchResultAdapter
        }
        RunLayoutAnimation(mContext!!, myApi_search_result_recyclerview)
        //Initilize Places api
        initPlacesApi()

        //get Current Location
        if (ActivityCompat.checkSelfPermission(
                mContext!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                mContext!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            LocationServices.getFusedLocationProviderClient(mContext!!).lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val address =
                            getLocationName(location!!.latitude, location.longitude, mContext!!)
                        pickUpData = SearchedDataItem(
                            address,
                            address,
                            location!!.latitude,
                            location.longitude,
                            "",
                            getPreference(mContext, Constants.CURRENT_STATE),
                            getPreference(mContext, Constants.CURRENT_CITY),
                            "google"
                        )
                        isFirstTime = true
                        isSearching = false
                        my_location_edt.setText(address)
                        isSearching = true
                    }
                }

        }


        my_location_edt!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (my_location_edt.getText().toString().length >= 1) {

                    /*if (isFirstTime)isSearching=false
                    else {isSearching=true}*/

                    isFirstTime = false

                    SET_RESULT_TO = Constants.PICKUP
                    searchedDataItemsList.clear()
                    if (isSearching) {
                        startImageLoading()
                        SEARCHING_FOR = my_location_edt.getText().toString()
                        homeFragmentViewModel!!.searchLocation(
                            mContext,
                            getPreference(mContext, Constants.TOKEN),
                            SEARCHING_FOR,
                            "5"
                        )
//                        searchApi(my_location_edt.getText().toString())
                    }
                } else {
                    searchedDataItemsList.clear()
                    apiLocationSearchResultAdapter!!.setData(
                        searchedDataItemsList,
                        SET_RESULT_TO,
                        SET_ADDRESS_FOR
                    )
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        destination_edt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (destination_edt.getText().toString().length >= 1) {
                    SET_RESULT_TO = Constants.DROP
                    searchedDataItemsList.clear()

                    /*if (isFirstTime)isSearching=false
                    else isSearching=true*/

                    isFirstTime = false

                    if (isSearching) {
                        startImageLoading()
                        SEARCHING_FOR = destination_edt.getText().toString()

                        homeFragmentViewModel!!.searchLocation(
                            mContext,
                            getPreference(mContext, Constants.TOKEN),
                            SEARCHING_FOR,
                            "5"
                        )

//                        searchApi(destination_edt.getText().toString())
                    }
                } else {
                    searchedDataItemsList.clear()
                    apiLocationSearchResultAdapter!!.setData(
                        searchedDataItemsList,
                        SET_RESULT_TO,
                        SET_ADDRESS_FOR
                    )
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        homeFragmentViewModel!!.searchLocationList.observe(this, Observer { searchedDataItems ->
            try {

                searchedDataItemsList.clear()
                if (searchedDataItems != null) {
                    searchedDataItemsList = searchedDataItems
                    homeFragmentViewModel!!.getSavedSearchLocation(
                        mContext,
                        getPreference(mContext, Constants.TOKEN),
                        SEARCHING_FOR,
                        "5"
                    )

                } else {
                    homeFragmentViewModel!!.getSavedSearchLocation(
                        mContext,
                        getPreference(mContext, Constants.TOKEN),
                        SEARCHING_FOR,
                        "5"
                    )
                }
            } catch (e: Exception) {
                toast(activity, getString(R.string.txt_Something_wrong))
            }
        })

        homeFragmentViewModel!!.savedSearchLocationList.observe(
            this,
            Observer { searchedDataItems ->

                try {
                    if (searchedDataItems != null) {
                        no_search_result_layout.setVisibility(View.GONE)
                        myApi_search_result_recyclerview.setVisibility(View.VISIBLE)

                        searchedDataItemsList.addAll(searchedDataItems)
                        /*for (data in searchedDataItems) {
                            searchedDataItemsList.add(data)
                        }*/

                        Handler().postDelayed({
                            apiLocationSearchResultAdapter!!.setData(
                                searchedDataItemsList,
                                SET_RESULT_TO,
                                SET_ADDRESS_FOR
                            )
                            stopImageLoading()
                        }, 500)

                    } else {
                        searchApi(SEARCHING_FOR)
                    }
                } catch (e: Exception) {
                    toast(activity, getString(R.string.txt_Something_wrong))
                }
            })

        homeFragmentViewModel!!.isDataSaved.observe(this, Observer {
            try {
                if (it) gApiSaveSearchResultModelList.clear()
            } catch (e: Exception) {
                i(TAG, "init: error=${e.localizedMessage}")
            }


        })

    }

    private fun searchApi(searchRequest: String) {
        try {
            val request =
                FindAutocompletePredictionsRequest.builder() // Call either setLocationBias() OR setLocationRestriction().
                    //                .setLocationBias(bounds)
                    //                .setLocationRestriction(bounds)
                    .setCountry("in") //India
                    //                .setTypeFilter(TypeFilter.ADDRESS)
                    .setSessionToken(token)
                    .setQuery(searchRequest)
                    .build()

            placesClient!!.findAutocompletePredictions(request)
                .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
                    try {

                        if (response.autocompletePredictions.size != 0) {
                            for (prediction in response.autocompletePredictions) {

                                i(
                                    TAG,
                                    "searchApi: SEARCH RESULT=" + prediction.getPrimaryText(null)
                                        .toString()
                                )

                                val request1: FetchPlaceRequest =
                                    FetchPlaceRequest.newInstance(prediction.placeId, placeFields)

                                placesClient!!.fetchPlace(request1).addOnSuccessListener {
                                    val placeLatLon = it.place
                                    if (placeLatLon != null) {

                                        i(
                                            TAG,
                                            "searchResult: RESULTTT=${placeLatLon?.latLng?.latitude.toString()}"
                                        )

                                        i(
                                            TAG,
                                            "searchResult: RESULTTT=${
                                                prediction.getPrimaryText(null)
                                                    .toString() + " -- " + prediction.getSecondaryText(
                                                    null
                                                ).toString()
                                            }"
                                        )


                                        val savedDataList = SavedItems(
                                            prediction.placeId,
                                            prediction.getPrimaryText(null).toString(),
                                            prediction.getSecondaryText(null).toString(),
                                            getPreference(mContext, Constants.CURRENT_CITY),
                                            getPreference(mContext, Constants.CURRENT_STATE),
                                            placeLatLon?.latLng?.latitude.toString(),
                                            placeLatLon?.latLng?.longitude.toString()
                                        )

                                        googlePlacesData = SearchedDataItem(
                                            prediction.getPrimaryText(null).toString(),
                                            prediction.getSecondaryText(null).toString(),
                                            placeLatLon?.latLng!!.latitude,
                                            placeLatLon?.latLng!!.longitude,
                                            "",
                                            getPreference(mContext, Constants.CURRENT_STATE),
                                            getPreference(mContext, Constants.CURRENT_CITY),
                                            "google"
                                        )

                                        if (!searchedDataItemsList!!.contains(googlePlacesData!!))
                                            searchedDataItemsList?.add(googlePlacesData!!)

                                        if (!gApiSaveSearchResultModelList!!.contains(savedDataList!!))
                                            gApiSaveSearchResultModelList?.add(savedDataList!!)

                                    }

                                }.addOnFailureListener {
                                    i(TAG, "searchResult: Errorrrr=${it.localizedMessage}")
                                }

                            }
                            no_search_result_layout!!.visibility = View.GONE
                            myApi_search_result_recyclerview!!.visibility = View.VISIBLE
                            i(
                                TAG,
                                "searchResult: searchedDataItemsList=${searchedDataItemsList.size}"
                            )
                            Handler().postDelayed({
                                apiLocationSearchResultAdapter!!.setData(
                                    searchedDataItemsList!!,
                                    SET_RESULT_TO,
                                    SET_ADDRESS_FOR
                                )
                                stopImageLoading()
                            }, 500)

                        } else {
                            no_search_result_layout!!.visibility = View.VISIBLE
                            myApi_search_result_recyclerview!!.visibility = View.GONE
                        }
                    } catch (e: Exception) {
                        i(TAG, "searchApi: Error=${e.localizedMessage}")
                    }
                }.addOnFailureListener { exception: Exception? ->
                    if (exception is ApiException) {
                        no_search_result_layout!!.visibility = View.VISIBLE
                        myApi_search_result_recyclerview!!.visibility = View.GONE
                    }
                }

        } catch (e: Exception) {
            Log.i(TAG, "searchApi: Error=" + e.localizedMessage)
        }
    }


    private fun initPlacesApi() {
        try {
            val apiKey = getString(R.string.google_maps_key)
            if (!Places.isInitialized()) {
                Places.initialize(FacebookSdk.getApplicationContext(), apiKey)
            }
            // Create a new Places client instance.
            placesClient = Places.createClient(mContext!!)
            token = AutocompleteSessionToken.newInstance()
        } catch (e: Exception) {
            Log.i(TAG, "initPlacesApi: Error=" + e.localizedMessage)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.my_location_edt -> apiLocationSearchResultAdapter!!.setResultTO(Constants.PICKUP)

            R.id.destination_edt -> apiLocationSearchResultAdapter!!.setResultTO(Constants.DROP)
            R.id.btnConfirmLocation -> {

                when (searchFor) {
                    SEARCH_FOR_ROUTS -> {
                        if (pickUpData != null && dropData != null) {
                            eventBus.post(SetLocationsEvent(pickUpData, dropData))
                            homeFragmentViewModel!!.saveSearchLocation(
                                mContext,
                                getPreference(mContext, Constants.TOKEN),
                                SaveLocationRequestModel(gApiSaveSearchResultModelList)
                            )
                            dismiss()
                        }
                    }
                    else -> {
                        setSearchPoints()
                    }
                }

            }
            R.id.imgClearPick -> {
                my_location_edt.setText("")
                searchedDataItemsList.clear()
                no_search_result_layout.setVisibility(View.VISIBLE)
                myApi_search_result_recyclerview.setVisibility(View.GONE)
                pickUpData = null

            }
            R.id.imgClearDrop -> {
                destination_edt.setText("")
                searchedDataItemsList.clear()
                no_search_result_layout.setVisibility(View.VISIBLE)
                myApi_search_result_recyclerview.setVisibility(View.GONE)
                dropData = null

            }
        }
    }


    private fun startImageLoading() {
        try {
            imgProgressLoading.visibility = View.VISIBLE
            imgLoadingAnimation = imgProgressLoading!!.drawable as Animatable
            imgLoadingAnimation!!.start()
        } catch (e: Exception) {
            i(TAG, "startImageLoading: ${e.localizedMessage}")
        }
    }

    private fun stopImageLoading() {
        try {
            imgProgressLoading.visibility = View.GONE
//            imgLoadingAnimation!!.stop()
        } catch (e: Exception) {
            i(TAG, "stopImageLoading: ${e.localizedMessage}")
            imgProgressLoading.visibility = View.VISIBLE
        }
    }

    fun setSearchResult(
        searchedDataItem: SearchedDataItem,
        SET_RESULT_TO: String,
        SET_ADDRESS_FOR: String?
    ) {
        isSearching = false
        if (SET_RESULT_TO === PICKUP) {
            no_search_result_layout!!.visibility = View.VISIBLE
            myApi_search_result_recyclerview!!.visibility = View.GONE
            my_location_edt!!.setText(searchedDataItem.title)
            pickUpData = searchedDataItem
            destination_edt!!.requestFocus()
            searchedDataItemsList.clear()
            apiLocationSearchResultAdapter!!.notifyDataSetChanged()
            apiLocationSearchResultAdapter!!.setResultTO(Constants.DROP)
            isSearching = true

            setSearchPoints()

        } else {
            destination_edt!!.setText(searchedDataItem.title)
            dropData = searchedDataItem
            searchedDataItemsList.clear()
            apiLocationSearchResultAdapter!!.notifyDataSetChanged()
            isSearching = true
        }
    }

    private fun setSearchPoints() {
        when (searchFor) {
            UPDATE_HOME, UPDATE_OFFICE -> {
                if (pickUpData != null) {
                    eventBus.post(UpdateLocationEvent(pickUpData, searchFor))
                    homeFragmentViewModel!!.saveSearchLocation(
                        mContext,
                        getPreference(mContext, Constants.TOKEN),
                        SaveLocationRequestModel(gApiSaveSearchResultModelList)
                    )
                    dismiss()
                }
            }
            SUGGEST_PICK, SUGGEST_DROP -> {
                if (pickUpData != null) {
                    eventBus.post(SuggestRouteLocationEvent(pickUpData, searchFor))
                    homeFragmentViewModel!!.saveSearchLocation(
                        mContext,
                        getPreference(mContext, Constants.TOKEN),
                        SaveLocationRequestModel(gApiSaveSearchResultModelList)
                    )
                    dismiss()
                }
            }
        }


    }

}