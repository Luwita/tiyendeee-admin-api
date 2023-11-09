package com.ferri.userapp.ui.fragment

import android.Manifest
import android.annotation.TargetApi
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.Db.RecentSearchData
import com.ferri.userapp.Db.RecentSearchViewModel
import com.ferri.userapp.R
import com.ferri.userapp.RetrofitRepository.ApiCallBack
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import com.ferri.userapp.ViewModel.HomeFragmentViewModel
import com.ferri.userapp.model.*
import com.ferri.userapp.ui.activity.BusListActivity
import com.ferri.userapp.ui.activity.OffersActivity
import com.ferri.userapp.ui.adapter.NewOfferAdapter
import com.ferri.userapp.ui.adapter.RecentSearchAdapter
import com.ferri.userapp.ui.events.SetLocationsEvent
import com.ferri.userapp.utils.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import kotlin.collections.ArrayList

class HomeFragmentNewest : Fragment(), View.OnClickListener, RecentSearchAdapter.onClickListener,
    LocationListener {
    /*variable declaration*/
    private val TAG = "HomeFragmentNewest"
    private var mEdFromCity: TextView? = null
    private var mEdToCity: TextView? = null
    private var mValue = 0
    private var isSwap = false
    private var mView: View? = null
    private var pickUpData: SearchedDataItem? = null
    private var dropData: SearchedDataItem? = null
    private var temp: SearchedDataItem? = null
    private var mRadioGroupBookingType: RadioGroup? = null
    private var mNewOfferList: ArrayList<OffersData>? = null
    private var mRecentSearchList: List<RecentSearchData>? = null
    private var officeRecord: UpdatedProfileData? = null
    private var mRvNewOffer: RecyclerView? = null
    private var mRvRecentSearch: RecyclerView? = null
    private var mLayRecentSearch: RelativeLayout? = null
    private var mTvClearSearchData: TextView? = null
    private var mIvSwap: ImageView? = null
    private var mIvDescrease: ImageView? = null
    private var mIvIncrease: ImageView? = null
    private var mSearch: ImageView? = null
    private var mTvAC: TextView? = null
    private var mTvNonAc: TextView? = null
    private var mTvSleeper: TextView? = null
    private var mTvSeater: TextView? = null
    private var mTvCount: TextView? = null
    private var mPbLoadData: ProgressBar? = null

    private var tvHomeAddress: TextView? = null
    private var tvOfficeAddress: TextView? = null
    private var tvLeavingHomeAt: TextView? = null
    private var tvLeavingOfficeAt: TextView? = null
    private var checkboxBookReturn: CheckBox? = null
    private var btnfindRoutes: Button? = null
    private var fabSwipeAddress: FloatingActionButton? = null

    private var mCvlayOfficeRide: CardView? = null
    private var mCvlayOneWayRide: CardView? = null
    private var mTvViewNewOffers: TextView? = null
    private var btnFindInstantRoute: Button? = null
    private var mDepartDateCalendar: Calendar? = null
    private var mRecentSearchAdapter: RecentSearchAdapter? = null
    private var mEdDepartDate: TextView? = null
    private var homeFragmentViewModel: HomeFragmentViewModel? = null
    private var recentSearchViewModel: RecentSearchViewModel? = null
    private var searchFragment: SearchLocationDialogFragment? = null


    private var officeAddress = ""
    private var officeLat = ""
    private var officeLng = ""
    private var homeAddress = ""
    private var homeLat = ""
    private var homeLng = ""
    private var homeLeaveTime = ""
    private var officeLeaveTime = ""

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2


    private var currentTime = ""
    private var endDate = ""
    private val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        mDepartDateCalendar!![Calendar.YEAR] = year
        mDepartDateCalendar!![Calendar.MONTH] = monthOfYear
        mDepartDateCalendar!![Calendar.DAY_OF_MONTH] = dayOfMonth
        updateLabel()
    }
    private var mIsAcSelected = false
    private var mIsNonAcSelected = false
    private var mIsSeaterSelected = false
    private var mIsSleeperSelected = false
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var busIntent: Intent
    var eventBus = EventBus.getDefault()

    /* create view */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, null)

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
            eventBus = EventBus.getDefault()
        }

        initView(view)
        setListener()
        handelViewModel()

        if (mDepartDateCalendar == null) {
            mDepartDateCalendar = Calendar.getInstance()
        }
        updateLabel()
//        getLocation()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
//            getLocation()
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    try {
                        if (location != null) {
                            mylog(TAG, location.toString())
                            mEdFromCity!!.text =
                                getLocationName(
                                    location!!.latitude,
                                    location.longitude,
                                    requireActivity()
                                )
                        }
                    } catch (e: Exception) {
                        mylog(TAG, "onCreateView: Error=${e.localizedMessage}")
                    }
                }
        }

        return view
    }

    private fun getLocation() {
        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        }

    }

    override fun onLocationChanged(location: Location) {
        if (location != null) {
            mylog(TAG, location.toString())
            mEdFromCity!!.text =
                getLocationName(location!!.latitude, location.longitude, requireActivity())
        }
    }

    private fun handelViewModel() {
        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        recentSearchViewModel = ViewModelProviders.of(this).get(RecentSearchViewModel::class.java)

        homeFragmentViewModel!!.searchedRoutes.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                LoadingDialog.cancelLoading()
                try {

                    if (it != null && it.status!! && it.data != null) {

                            it.data.routes.let {routes->
                                if (routes!!.size!=0){
                                val bundle = Bundle()
                                bundle.putSerializable("routes",it.data)
                                busIntent.putExtras(bundle)
                                startActivity(busIntent)
                                }
                                else toast(activity, "Route Not Available")
                            }?:toast(activity, "Route Not Available")

                    } else toast(activity, it.message)
                } catch (e: Exception) {
                    toast(activity, getString(R.string.txt_Something_wrong))
                }

            })

        homeFragmentViewModel!!.getOffers(
            requireActivity(),
            getPreference(activity, Constants.TOKEN)
        )

        homeFragmentViewModel!!.offers.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            LoadingDialog.cancelLoading()
            if (it != null && it.status!!) {
                mNewOfferList = it.data
                setOfferAdapter()
            } else if (it != null) toast(activity, it.message)
        })

        recentSearchViewModel!!.getAllRecentSearchData()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                mRecentSearchList = it
                setRecentSearchRecord(mRecentSearchList!!)
            })
    }

    private fun setRecentSearchRecord(recentSearchRecord: List<RecentSearchData>) {
        try {
            mylog(TAG, "setRecentSearchRecord: Called")
            mylog(TAG, "setRecentSearchRecord: DATA=${Gson().toJson(recentSearchRecord)}")
            if (recentSearchRecord.size != 0) {
                mLayRecentSearch!!.visibility = View.VISIBLE
                mRecentSearchAdapter = RecentSearchAdapter(requireActivity(), recentSearchRecord)
                mRvRecentSearch!!.layoutManager =
                    LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                mRvRecentSearch!!.adapter = mRecentSearchAdapter
                mRecentSearchAdapter!!.setOnClickListener(this)
            } else {
                mLayRecentSearch!!.visibility = View.GONE
            }

        } catch (e: Exception) {
            mylog(TAG, "setRecentSearchRecord: Error=${e.localizedMessage}")
        }

    }


    /* update label */
    private fun updateLabel() {
        mEdDepartDate!!.text =
            Constants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar!!.time)
    }

    /* set adapter */
    private fun setOfferAdapter() {
        mRvNewOffer!!.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        mRvNewOffer!!.adapter = NewOfferAdapter(requireActivity(), mNewOfferList!!)
    }

    /* set listener */
    private fun setListener() {
        mSearch!!.setOnClickListener(this)
        mTvViewNewOffers!!.setOnClickListener(this)
        btnFindInstantRoute!!.setOnClickListener(this)
        mTvAC!!.setOnClickListener(this)
        mTvNonAc!!.setOnClickListener(this)
        mTvSleeper!!.setOnClickListener(this)
        mTvSeater!!.setOnClickListener(this)
        mIvSwap!!.setOnClickListener(this)
        mIvDescrease!!.setOnClickListener(this)
        mIvIncrease!!.setOnClickListener(this)
        mEdDepartDate!!.setOnClickListener(this)
        mEdFromCity!!.setOnClickListener(this)
        mEdToCity!!.setOnClickListener(this)
        btnfindRoutes!!.setOnClickListener(this)
        mTvClearSearchData!!.setOnClickListener(this)
        mEdFromCity!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (mEdFromCity!!.length() > 0) {
                    mView!!.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                    mView!!.alpha = 0.2f
                    // homeFragmentViewModel.searchLocation(UtilMethods.getPreference(getContext(),Constants.TOKEN),mEdFromCity.getText().toString(),"10");
                } else {
                    mView!!.setBackgroundColor(resources.getColor(R.color.view_color))
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
        mEdToCity!!.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //  if (validate()) {
                mFrom = mEdFromCity!!.text.toString()
                mTo = mEdToCity!!.text.toString()
                val intent = Intent(activity, BusListActivity::class.java)
                intent.putExtra(
                    Constants.intentdata.TRIP_KEY,
                    mEdFromCity!!.text.toString() + " To " + mEdToCity!!.text.toString()
                )
                startActivity(intent)
                //  }
                return@OnEditorActionListener true
            }
            false
        })

        mRadioGroupBookingType!!.setOnCheckedChangeListener { radioGroup, isCheckedID ->
            when (isCheckedID) {
                R.id.radioBtnOneWay -> {
                    savePreference(context, Constants.IsCheckedOffice, false)
                    mCvlayOfficeRide!!.visibility = View.GONE
                    mCvlayOneWayRide!!.visibility = View.VISIBLE
                    bookingType = "oneway"
                    has_return = "1"
                    isSwap = false
                }
                R.id.radioBtnOfficeRide -> {
                    savePreference(context, Constants.IsCheckedOffice, true)
                    mCvlayOfficeRide!!.visibility = View.VISIBLE
                    mCvlayOneWayRide!!.visibility = View.GONE
                    bookingType = "office"
                    has_return = "1"
                    checkboxBookReturn!!.isChecked = false
                    isSwap = false
                    getProfileDetails()
                }
            }
        }

        checkboxBookReturn!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) has_return = "2"
            else has_return = "1"
        }
    }

    /* init view */
    private fun initView(view: View) {
        mRvNewOffer = view.findViewById(R.id.rvNewOffers)
        mTvViewNewOffers = view.findViewById(R.id.tvViewallNewOffer)
        mIvSwap = view.findViewById(R.id.ivSwap)
        mSearch = view.findViewById(R.id.btnSearch)
        mTvAC = view.findViewById(R.id.tvAc)
        mTvNonAc = view.findViewById(R.id.tvNonAc)
        mTvSleeper = view.findViewById(R.id.tvSleeper)
        mTvSeater = view.findViewById(R.id.tvSeater)
        mEdDepartDate = view.findViewById(R.id.edOneWay)
        mEdFromCity = view.findViewById(R.id.edFromCity)
        mEdToCity = view.findViewById(R.id.edToCity)
        mIvDescrease = view.findViewById(R.id.ivDescrease)
        mIvIncrease = view.findViewById(R.id.ivIncrease)
        mTvCount = view.findViewById(R.id.tvCount)
        mView = view.findViewById(R.id.view2)
        btnFindInstantRoute = view.findViewById(R.id.btnFindInstantRoute)
        mRvRecentSearch = view.findViewById(R.id.rvRecentSearch)
        mLayRecentSearch = view.findViewById(R.id.layRecentSearch)
        mTvClearSearchData = view.findViewById(R.id.tvClearSearchData)
        mCvlayOfficeRide = view.findViewById(R.id.cvlayOfficeRide)
        mCvlayOneWayRide = view.findViewById(R.id.cvlayOneWayRide)
        mRadioGroupBookingType = view.findViewById(R.id.radioGroupBookingType)

        tvHomeAddress = view.findViewById(R.id.tvHomeAddress)
        tvOfficeAddress = view.findViewById(R.id.tvOfficeAddress)
        tvLeavingHomeAt = view.findViewById(R.id.tvLeavingHomeAt)
        tvLeavingOfficeAt = view.findViewById(R.id.tvLeavingOfficeAt)
        checkboxBookReturn = view.findViewById(R.id.checkboxBookReturn)
        btnfindRoutes = view.findViewById(R.id.btnfindRoutes)
        fabSwipeAddress = view.findViewById(R.id.fabSwipeAddress)
        mPbLoadData = view.findViewById(R.id.pbLoadData)

        fabSwipeAddress!!.clickWithThrottle { swipeAddress() }

        mIvDescrease!!.setVisibility(View.INVISIBLE)

        if (isPreference(context, Constants.IsCheckedOffice)) {
            mRadioGroupBookingType!!.check(R.id.radioBtnOfficeRide)
            mCvlayOfficeRide!!.visibility = View.VISIBLE
            mCvlayOneWayRide!!.visibility = View.GONE
            bookingType = "office"
            has_return = "1"
            checkboxBookReturn!!.isChecked = false
            getProfileDetails()
        } else {
            mRadioGroupBookingType!!.check(R.id.radioBtnOneWay)
            mCvlayOfficeRide!!.visibility = View.GONE
            mCvlayOneWayRide!!.visibility = View.VISIBLE
            bookingType = "oneway"
            has_return = "1"
        }
    }

    private fun swipeAddress() {
        try {
            if (!isSwap && officeRecord != null) {
                vibratePhone(requireActivity())
                isSwap = true
                tvOfficeAddress!!.setText(officeRecord!!.homeAddress)
                tvHomeAddress!!.setText(officeRecord!!.officeAddress)
                tvLeavingHomeAt!!.setText(officeRecord!!.officeTiming)
                tvLeavingOfficeAt!!.setText(officeRecord!!.homeTiming)

                homeAddress = officeRecord!!.officeAddress
                officeAddress = officeRecord!!.homeAddress
                homeLat = officeRecord!!.officeLat.toString()
                homeLng = officeRecord!!.officeLng.toString()
                officeLat = officeRecord!!.homeLat.toString()
                officeLng = officeRecord!!.homeLng.toString()
                officeLeaveTime = officeRecord!!.homeTiming
                homeLeaveTime = officeRecord!!.officeTiming
            } else {
                vibratePhone(requireActivity())
                isSwap = false
                tvOfficeAddress!!.setText(officeRecord!!.officeAddress)
                tvHomeAddress!!.setText(officeRecord!!.homeAddress)
                tvLeavingHomeAt!!.setText(officeRecord!!.homeTiming)
                tvLeavingOfficeAt!!.setText(officeRecord!!.officeTiming)

                officeAddress = officeRecord!!.officeAddress
                homeAddress = officeRecord!!.homeAddress
                officeLat = officeRecord!!.officeLat.toString()
                officeLng = officeRecord!!.officeLng.toString()
                homeLat = officeRecord!!.homeLat.toString()
                homeLng = officeRecord!!.homeLng.toString()
                homeLeaveTime = officeRecord!!.homeTiming
                officeLeaveTime = officeRecord!!.officeTiming
            }
        } catch (e: Exception) {
            Log.i(TAG, "swipeAddress: Error=${e.localizedMessage}")
        }
    }


    /* onClick listener */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.edOneWay -> {
                val datePickerDialogs = DatePickerDialog(
                    requireActivity(),
                    date,
                    mDepartDateCalendar!!.get(Calendar.YEAR),
                    mDepartDateCalendar!![Calendar.MONTH],
                    mDepartDateCalendar!![Calendar.DAY_OF_MONTH]
                )
                datePickerDialogs.datePicker.minDate = Date().time
                datePickerDialogs.show()
            }
            R.id.ivSwap -> {
                Log.i(TAG, "onClick: ivSwap Called isSwap=$isSwap")
                Log.i(TAG, "onClick: ivSwap Called pickUpData=${pickUpData?.title}")
                Log.i(TAG, "onClick: ivSwap Called dropData=${dropData?.title}")
                if (pickUpData != null && dropData != null) {
                    vibratePhone(requireContext())

                    var temp=pickUpData
                    pickUpData=dropData
                    dropData=temp
                    val startRotateAnimation =
                        AnimationUtils.loadAnimation(context, R.anim.anim_rotate)
                    mIvSwap!!.startAnimation(startRotateAnimation)
                    if (isSwap){
                        setPickUpDropData(pickUpData!!, dropData!!)
                        isSwap = false
                    }
                    else {
                        setPickUpDropData(pickUpData!!, dropData!!)
                        isSwap = true
                    }

                }
            }
            R.id.ivDescrease -> {
                mValue = mValue - 1
                mTvCount!!.text = mValue.toString()
                if (mValue <= 1) {
                    (Objects.requireNonNull(requireActivity()) as BaseActivity).invisibleView(
                        mIvDescrease
                    )
                } else {
                    (activity as BaseActivity?)!!.showView(mIvDescrease)
                    mTvCount!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    mTvCount!!.setTextColor(resources.getColor(R.color.colorPrimary))
                }
            }
            R.id.ivIncrease -> {
                mValue = mValue + 1
                if (mValue < 1) {
                    mValue = 1
                } else {
                    if (mValue == 1) (activity as BaseActivity?)!!.invisibleView(mIvDescrease) else (Objects.requireNonNull(
                        requireActivity()
                    ) as BaseActivity).showView(mIvDescrease)
                    mTvCount!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    mTvCount!!.text = mValue.toString()
                    mTvCount!!.setTextColor(resources.getColor(R.color.colorPrimary))
                }
            }
            R.id.tvAc -> mIsAcSelected = if (!mIsAcSelected) {
                enable(v as TextView)
                true
            } else {
                disable(v as TextView)
                false
            }
            R.id.tvNonAc -> mIsNonAcSelected = if (!mIsNonAcSelected) {
                enable(v as TextView)
                true
            } else {
                disable(v as TextView)
                false
            }
            R.id.tvSleeper -> mIsSleeperSelected = if (!mIsSleeperSelected) {
                enable(v as TextView)
                true
            } else {
                disable(v as TextView)
                false
            }
            R.id.tvSeater -> mIsSeaterSelected = if (!mIsSeaterSelected) {
                enable(v as TextView)
                true
            } else {
                disable(v as TextView)
                false
            }
            R.id.btnSearch -> {
                //    if (validate()) {
                mFrom = mEdFromCity!!.text.toString()
                mTo = mEdToCity!!.text.toString()
                val intent = Intent(activity, BusListActivity::class.java)
                intent.putExtra(Constants.intentdata.TRIP_KEY, mFrom + " To " + mTo)
                startActivity(intent)
            }
            R.id.btnfindRoutes -> {
                vibratePhone(requireContext())
                if (validateOffice())
                    searchRouts()
                /*//    if (validate()) {
                val intent = Intent(activity, BusListActivity::class.java)
                // intent.putExtra(Constants.intentdata.TRIP_KEY, mFrom + " To " + mTo)
                startActivity(intent)*/
            }
            R.id.btnFindInstantRoute -> {

                vibratePhone(requireContext())
                if (validate())
                    searchRouts()
                /*//    if (validate()) {
                val intent = Intent(activity, BusListActivity::class.java)
                // intent.putExtra(Constants.intentdata.TRIP_KEY, mFrom + " To " + mTo)
                startActivity(intent)*/
            }
            R.id.tvViewallNewOffer -> {
                vibratePhone(requireContext())
                if (mNewOfferList != null) {
                    val i = Intent(activity, OffersActivity::class.java)
                    i.putExtra(Constants.intentdata.OFFER, mNewOfferList)
                    startActivity(i)
                }
            }
            R.id.edFromCity -> {
                searchFragment =
                    SearchLocationDialogFragment(requireContext(), Constants.SEARCH_FOR_ROUTS)
                if (!searchFragment!!.isVisible) {
                    searchFragment!!.show(requireFragmentManager(), "SearchFragment")
                }
            }
            R.id.edToCity -> {
                searchFragment =
                    SearchLocationDialogFragment(requireContext(), Constants.SEARCH_FOR_ROUTS)
                if (!searchFragment!!.isVisible) {
                    searchFragment!!.show(requireFragmentManager(), "SearchFragment")
                }
            }
            R.id.tvClearSearchData -> {
                recentSearchViewModel!!.deleteAllRecentSearchData()
            }
        }
    }


    private fun getProfileDetails() {
        try {
            if (isNetworkAvailable(requireContext())) {
                LoadingDialog.showLoadingDialog(activity, "Loading...")
                RetrofitClient.getClient()
                    .getProfileDetails(getPreference(requireContext(), Constants.TOKEN))
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<UserProfileUpdateResponse?>() {
                        override fun onSuccess(data: UserProfileUpdateResponse) {
                            if (data.isStatus) {
                                try {
                                    mylog(TAG, "dataResponse=" + apiResponse(data))

                                    officeRecord = data.data

                                    tvOfficeAddress!!.setText(data.data.officeAddress)
                                    tvHomeAddress!!.setText(data.data.homeAddress)
                                    tvLeavingHomeAt!!.setText(data.data.homeTiming)
                                    tvLeavingOfficeAt!!.setText(data.data.officeTiming)

                                    officeAddress = data.data.officeAddress
                                    homeAddress = data.data.homeAddress
                                    officeLat = data.data.officeLat.toString()
                                    officeLng = data.data.officeLng.toString()
                                    homeLat = data.data.homeLat.toString()
                                    homeLng = data.data.homeLng.toString()
                                    homeLeaveTime = data.data.homeTiming
                                    officeLeaveTime = data.data.officeTiming

                                } catch (e: Exception) {
                                    mylog(
                                        TAG,
                                        "onSuccess:getProfileDetails Errrr=" + e.localizedMessage
                                    )
                                    LoadingDialog.cancelLoading()
                                }
                            } else toast(context, data.message)

                            LoadingDialog.cancelLoading()
                        }

                        override fun onError(e: Throwable) {
                            mylog(TAG, "onError: updateInformation=" + e.localizedMessage)
                            LoadingDialog.cancelLoading()
                            if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                    Constants.Forbidden
                                )
                            )
                                checkToken(
                                    requireContext(),
                                    ApiCallBack { success -> if (success) getProfileDetails()
                                    else sessionExpireDialog(requireActivity())})
                        }
                    })
            } else toast(requireContext(), getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "getProfileDetails: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }


    /* validation */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdFromCity!!.text)) {
            flag = false
            (Objects.requireNonNull(requireActivity()) as BaseActivity).showToast(getString(R.string.msg_from))
        } else if (TextUtils.isEmpty(mEdToCity!!.text)) {
            flag = false
            (Objects.requireNonNull(requireActivity()) as BaseActivity).showToast(getString(R.string.msg_to))
        } else if (dropData==null){
            flag=false
            mEdToCity!!.text = ""
            (Objects.requireNonNull(requireActivity()) as BaseActivity).showToast(getString(R.string.msg_to))
        }
        return flag
    }

    private fun validateOffice(): Boolean {
        var flag = true
        if (homeAddress.equals("") && officeAddress.equals("")) {
            flag = false
            (Objects.requireNonNull(requireActivity()) as BaseActivity).showToast(getString(R.string.msg_office_ride_address_required))
        } else if (homeLeaveTime.equals("") && officeLeaveTime.equals("")) {
            flag = false
            (Objects.requireNonNull(requireActivity()) as BaseActivity).showToast(getString(R.string.msg_office_ride_timing_required))
        }
        return flag
    }

    /* disable textview */
    private fun disable(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.textchild))
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        textView.context,
                        R.color.textchild
                    ), PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    /* enable textview */
    private fun enable(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.startcolor))
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        textView.context,
                        R.color.startcolor
                    ), PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    /* onClick listener */
    override fun onClick(recentSearchData: RecentSearchData) {

        pickUpLat = recentSearchData.pickUpLat
        pickUpLng = recentSearchData.pickUpLng
        dropLat = recentSearchData.dropLat
        dropLng = recentSearchData.dropLng
        pickUpAddress = recentSearchData.pickUpAddress
        dropAddress = recentSearchData.dropAddress
        bookingType = "oneway"
        has_return = "1"

        searchRouts()
    }

    /* change destination */
    fun ChangeDestination(result: String?) {
        if (mEdFromCity!!.length() == 0) {
            mEdFromCity!!.text = result
        } else {
            mEdToCity!!.text = result
        }
    }

    fun setLocationData(pickUpData: SearchedDataItem, dropData: SearchedDataItem) {
        try {
            mylog(TAG, "setLocationData: " + pickUpData.title)
            mylog(TAG, "setLocationData: " + dropData.title)
            mEdFromCity!!.text = pickUpData.title
            mEdToCity!!.text = dropData.title
            val intent = Intent(activity, BusListActivity::class.java)
            intent.putExtra(
                Constants.intentdata.TRIP_KEY,
                mEdFromCity!!.text.toString() + " To " + mEdToCity!!.text.toString()
            )
            startActivity(intent)
        } catch (e: Exception) {
            mylog(TAG, "setLocationData: ")
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(pusher: SetLocationsEvent) {
        try {
            mylog(TAG, "setLocationData: " + pusher.pickUpData.title)
            mylog(TAG, "setLocationData: " + pusher.dropData.title)

            setPickUpDropData(pusher.pickUpData, pusher.dropData)

            insertRecentSearchRecord()

        } catch (e: Exception) {
            mylog(TAG, "setLocationData: error=${e.localizedMessage}")
        }
    }

    private fun setPickUpDropData(pickUpData: SearchedDataItem, dropData: SearchedDataItem) {
        try {
            mEdFromCity!!.text = pickUpData.title
            mEdToCity!!.text = dropData.title
            pickUpAddress = pickUpData.title
            dropAddress = dropData.title
            pickUpLat = pickUpData!!.locationLatitude.toString()
            pickUpLng = pickUpData!!.locationLongitude.toString()
            dropLat = dropData!!.locationLatitude.toString()
            dropLng = dropData!!.locationLongitude.toString()

            this.pickUpData = pickUpData
            this.dropData = dropData

        } catch (e: Exception) {
            Log.i(TAG, "setPickUpDropData: Error=${e.localizedMessage}")
        }
    }

    private fun insertRecentSearchRecord() {
        try {
            if (pickUpData != null && dropData != null) {
                currentTime = convertDateToBeautify(Calendar.getInstance().time)
                var isDataAvailable = false

                if (mRecentSearchList!!.size != 0)
                    for (data in mRecentSearchList!!) {
                        if (pickUpAddress == data.pickUpAddress && dropAddress == data.dropAddress) {
                            isDataAvailable = true
                            break
                        }
                    }

                if (!isDataAvailable) {
                    recentSearchViewModel!!.insert(
                        RecentSearchData(
                            pickUpLat,
                            pickUpLng,
                            dropLat,
                            dropLng,
                            pickUpAddress,
                            dropAddress,
                            currentTime
                        )
                    )
                }


            }
        } catch (e: Exception) {
            mylog(TAG, "insertRecentSearchRecord: Error=${e.localizedMessage}")
        }
    }

    private fun searchRouts() {

        try {

            busIntent = Intent(activity, BusListActivity::class.java)

//             if (isSwap) busIntent.putExtra(Constants.intentdata.TRIP_KEY, dropData!!.title.subSequence(0, 7).toString() + " To " + pickUpData!!.title.subSequence(0, 7))
//              else busIntent.putExtra(Constants.intentdata.TRIP_KEY, pickUpData!!.title.subSequence(0, 7).toString() + " To " + dropData!!.title.subSequence(0, 7))


            if (bookingType.equals("oneway")) {

                /*  pickUpLat = "28.62278"
                  pickUpLng = "77.03388"
                  dropLat = "28.64860 "
                  dropLng = "77.11956"*/

                busIntent.putExtra(Constants.intentdata.FROM, pickUpAddress)
                busIntent.putExtra(Constants.intentdata.TO, dropAddress)


            } else {
                busIntent.putExtra(Constants.intentdata.FROM, homeAddress)
                busIntent.putExtra(Constants.intentdata.TO, officeAddress)

                pickUpLat = homeLat
                pickUpLng = homeLng
                dropLat = officeLat
                dropLng = officeLng

                /* pickUpLat = "28.62278"
                 pickUpLng = "77.03388"
                 dropLat = "28.64860 "
                 dropLng = "77.11956"*/
            }


            currentTime = convertDateToBeautify(Calendar.getInstance().time)
            savePreference(activity, Constants.BOOKING_DATE, currentTime)
            endDate=calculateNext3rdDate(Calendar.getInstance())
            savePreference(activity, Constants.BOOKING_END_DATE,endDate)

            mylog(TAG, currentTime + bookingType)

            LoadingDialog.showLoadingDialog(activity, "Loading...")

            homeFragmentViewModel!!.searchRoutes(
                activity,
                getPreference(activity, Constants.TOKEN),
                pickUpLat,
                pickUpLng,
                dropLat,
                dropLng,
                currentTime,
                endDate,
                bookingType,
                has_return
            )


        } catch (e: Exception) {
            mylog(TAG, "searchRouts: error=${e.localizedMessage}")
        }
    }


    override fun onResume() {
        super.onResume()
//        mEdToCity!!.text = getString(R.string.hint_drop_location)
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    companion object {
        const val mTitle = "Home"

        @JvmField
        var mFrom: String? = null

        @JvmField
        var mTo: String? = null

        @JvmField
        var pickUpAddress = ""

        @JvmField
        var pickUpLat = ""

        @JvmField
        var pickUpLng = ""

        @JvmField
        var dropAddress = ""

        @JvmField
        var dropLat = ""

        @JvmField
        var dropLng = ""

        @JvmField
        var bookingType = ""

        @JvmField
        var has_return = "1"
    }

}