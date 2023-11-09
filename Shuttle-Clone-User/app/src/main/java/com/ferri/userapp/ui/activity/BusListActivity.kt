package com.ferri.userapp.ui.activity

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.ViewModel.HomeFragmentViewModel
import com.ferri.userapp.model.RoutesData
import com.ferri.userapp.ui.adapter.ItemBusAdapter
import com.ferri.userapp.ui.fragment.HomeFragmentNewest.Companion.bookingType
import com.ferri.userapp.ui.fragment.HomeFragmentNewest.Companion.dropLat
import com.ferri.userapp.ui.fragment.HomeFragmentNewest.Companion.dropLng
import com.ferri.userapp.ui.fragment.HomeFragmentNewest.Companion.has_return
import com.ferri.userapp.ui.fragment.HomeFragmentNewest.Companion.pickUpLat
import com.ferri.userapp.ui.fragment.HomeFragmentNewest.Companion.pickUpLng
import com.ferri.userapp.utils.*
import java.util.*

class BusListActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mRvBuses: RecyclerView? = null
    private val TAG = "BusListActivity"
    private var mIvBack: ImageView? = null
    private var mIvFilter: ImageView? = null
    private var mIvPrevious: ImageView? = null
    private var mIvNext: ImageView? = null
    private var mIvHome: ImageView? = null
    private var mTvDate: TextView? = null
    private var mTvTitle: TextView? = null
    private var mTvFrom: TextView? = null
    private var mTvTo: TextView? = null
    private var mTvAvailableBus: TextView? = null
    private var mDepartDateCalendar: Calendar? = null
    private var routesData: RoutesData? = null
    private var validBookingDate = 0
    private var homeFragmentViewModel: HomeFragmentViewModel? = null
    private val routeTimeTable = arrayListOf<String>()
    private var DAY_OF_WEEK = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_list)
        initLayouts()
        initializeListeners()

        handelViewModel()
    }

    private fun handelViewModel() {
        try {
            homeFragmentViewModel!!.searchedRoutes.observe(this, androidx.lifecycle.Observer {
                LoadingDialog.cancelLoading()
                try {
                    if (it != null && it.status!!) {
                        showView(mRvBuses)
                        mTvAvailableBus!!.text =
                            routesData!!.routes!!.size.toString() + " Bus Available"
                        mRvBuses!!.adapter =
                            ItemBusAdapter(
                                this,
                                routesData!!.routes,
                                bookingType,
                                has_return,
                                DAY_OF_WEEK
                            )
                        RunLayoutAnimation(mRvBuses)
                        routeTimeTable.addAll(it.data!!.routes!![0]!!.routeBusTimetable!!)
                    } else {
                        mTvAvailableBus!!.text = "No Bus Available"
                        toast(this, it.message)
                        hideView(mRvBuses)
                    }
                } catch (e: Exception) {
                    toast(this, getString(R.string.txt_Something_wrong))
                }

            })
        } catch (e: Exception) {
            Log.i(TAG, "handelViewModel: Errr=${e.localizedMessage}")
        }
    }

    /* initialize listener */
    private fun initializeListeners() {
        try {
            mIvBack!!.setOnClickListener(this)
            mIvFilter!!.setOnClickListener(this)
            mIvPrevious!!.setOnClickListener(this)
            mIvNext!!.setOnClickListener(this)
            val mFrom = intent.getStringExtra(Constants.intentdata.FROM)
            val mTo = intent.getStringExtra(Constants.intentdata.TO)
            Log.i(TAG, "initializeListeners: mTitle=$mFrom To $mTo")
            routesData = intent.getSerializableExtra("routes") as RoutesData
            mTvTitle!!.text = getString(R.string.text_bus_list)
            if (mFrom != null && mTo != null) {
                mTvFrom!!.text = mFrom
                mTvTo!!.text = mTo
            }

            routeTimeTable.addAll(routesData!!.routes!![0]!!.routeBusTimetable!!)

            mDepartDateCalendar = Calendar.getInstance()
            mTvDate!!.text =
                Constants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar!!.getTime())
            DAY_OF_WEEK = mDepartDateCalendar!!.getDisplayName(
                Calendar.DAY_OF_WEEK,
                Calendar.LONG_FORMAT,
                Locale.US
            ).lowercase()

            calculateNext3rdDate(mDepartDateCalendar!!)

            mRvBuses!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            mTvAvailableBus!!.text = routesData!!.routes!!.size.toString() + " Bus Available"
            mRvBuses!!.adapter =
                ItemBusAdapter(this, routesData!!.routes, bookingType, has_return, DAY_OF_WEEK)
            RunLayoutAnimation(mRvBuses)


        } catch (e: Exception) {
            mylog(TAG, "initializeListeners: Error=" + e.localizedMessage)
        }
    }


    /* init layout */
    private fun initLayouts() {
        mRvBuses = findViewById(R.id.rvBus)
        mIvBack = findViewById(R.id.ivBack)
        mIvFilter = findViewById(R.id.ivFilter)
        mIvHome = findViewById(R.id.ivHome)
        mIvPrevious = findViewById(R.id.ivPrevious)
        mIvNext = findViewById(R.id.ivNext)
        mTvDate = findViewById(R.id.tvDate)
        mTvTitle = findViewById(R.id.tvTitle)
        mTvFrom = findViewById(R.id.tvFrom)
        mTvTo = findViewById(R.id.tvTo)
        mTvAvailableBus = findViewById(R.id.tvAvailableBus)

        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)

    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) {
            onBackPressed()
        } else if (v === mIvPrevious) {

            if (validBookingDate != 0) {
                mDepartDateCalendar!!.add(Calendar.DATE, -1)
                mTvDate!!.text =
                    Constants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar!!.time)
                DAY_OF_WEEK = mDepartDateCalendar!!.getDisplayName(
                    Calendar.DAY_OF_WEEK,
                    Calendar.LONG_FORMAT,
                    Locale.US
                ).lowercase()



                loadRoutes(
                    Constants.DateFormat.YEAR_MONTH_DAY_FORMATTER.format(mDepartDateCalendar!!.time),
                    calculateNext3rdDate(mDepartDateCalendar!!)
                )

                validBookingDate--
            }
        } else if (v === mIvNext) {
            if (validBookingDate != 3) {
                mDepartDateCalendar!!.add(Calendar.DATE, 1)
                mTvDate!!.text =
                    Constants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar!!.time)
                DAY_OF_WEEK = mDepartDateCalendar!!.getDisplayName(
                    Calendar.DAY_OF_WEEK,
                    Calendar.LONG_FORMAT,
                    Locale.US
                ).lowercase()


                loadRoutes(Constants.DateFormat.YEAR_MONTH_DAY_FORMATTER.format(mDepartDateCalendar!!.time), calculateNext3rdDate(mDepartDateCalendar!!))
                validBookingDate++
            }
        } else if (v === mIvFilter) {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_filter)
            dialog.setCancelable(true)
            Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(0))
            val tvMax = dialog.findViewById<TextView>(R.id.endprice)
            val mBtnApply = dialog.findViewById<Button>(R.id.btnApply)
            val mIvClose = dialog.findViewById<ImageView>(R.id.ivClose)
            mBtnApply.stateListAnimator = null
            mBtnApply.setOnClickListener { dialog.dismiss() }
            val rangeSeekbar1: AppCompatSeekBar = dialog.findViewById(R.id.rangeSeekbar1)
            rangeSeekbar1.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                var progressChangedValue = 100
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    progressChangedValue = progress
                    tvMax.text = progressChangedValue.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    tvMax.text = progressChangedValue.toString()
                }
            })
            mIvClose.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }



    private fun loadRoutes(currentDate: String, endDate: String) {
        LoadingDialog.showLoadingDialog(this, "Loading...")
        Log.i(
            TAG,
            "loadRoutes: ${pickUpLat + pickUpLng + dropLat + dropLng + currentDate +  endDate + bookingType + has_return}"
        )
        savePreference(this, Constants.BOOKING_DATE, currentDate)
        savePreference(this, Constants.BOOKING_END_DATE, endDate)
        homeFragmentViewModel!!.searchRoutes(
            this,
            getPreference(this, Constants.TOKEN),
            pickUpLat,
            pickUpLng,
            dropLat,
            dropLng,
            currentDate,
            endDate,
            bookingType,
            has_return
        )
    }
}