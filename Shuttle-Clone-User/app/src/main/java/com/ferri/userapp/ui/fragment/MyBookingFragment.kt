package com.ferri.userapp.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.ViewModel.HomeFragmentViewModel
import com.ferri.userapp.model.GetbookingData
import com.ferri.userapp.ui.activity.DashboardActivity
import com.ferri.userapp.ui.adapter.BookingHistoryAdapter
import com.ferri.userapp.ui.adapter.CancelAdapter
import com.ferri.userapp.ui.adapter.TripStatusAdapter
import com.ferri.userapp.utils.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class MyBookingFragment : Fragment() {

    private var mPbLoadData: ProgressBar? = null
    private var mRvBookingsHistory: RecyclerView? = null
    private var mLayNoDataAvailable: LinearLayout? = null
    private var mRvTripStatus: RecyclerView? = null
    private var mBookingHistoryList: MutableList<GetbookingData>? = ArrayList()
    private var homeFragmentViewModel: HomeFragmentViewModel? = null
    private var setReminderDialog: BottomSheetDialog? = null
    private var bookingHistoryAdapter: BookingHistoryAdapter? = null
    private var refundAlert = ""
    private var offSet = 0
    private var travelStatus = "SCHEDULED"
    private var limit = 10
    private val REQUEST_PHONE_CALL=4544

    val tripStatus=listOf( "SCHEDULED", "CANCELLED", "ONBOARDED", "COMPLETED","EXPIRED")

    private var loadingBookingItems = false

    val daysList = ArrayList<String>()
    private var TAG = "MyBookingFragment"

    /* create view */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mybooking, null)
        initView(view)
        initializeListeners()
        handleViewModel()
        return view
    }

    private fun handleViewModel() {
        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        loadDataToAdapter()

        mylog(TAG, "token=${getPreference(activity, Constants.TOKEN)}")

        homeFragmentViewModel!!.bookingList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                mPbLoadData!!.visibility=View.GONE
                try {
                    if (it != null) {
                        if (it.status!! && it.data != null) {
                            try {

                                if (it.data.size==0&&offSet==0)
                                    mBookingHistoryList!!.clear()

                                mBookingHistoryList!!.addAll(it.data)

                                refundAlert = it.refundAlert.toString()

                                mylog(TAG, "handleViewModel: mBookingHistoryList size=${mBookingHistoryList!!.size} ")

                                loadMoreData()

                            } catch (e: Exception) {
                                mylog(TAG, e.localizedMessage)
                            }
                        }
                    } else toast(activity, getString(R.string.txt_Something_wrong))
                } catch (e: Exception) {
                    toast(requireActivity(), getString(R.string.txt_Something_wrong))
                }

            })

        homeFragmentViewModel!!.cancelBooking.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                LoadingDialog.cancelLoading()
                try {
                    if (it != null) {
                        toast(this.activity, it.message)
                        if (it.isStatus!!) {
                            getBookingList()
                        }
                    } else toast(this.activity, this.getString(R.string.txt_Something_wrong))
                } catch (e: Exception) {
                    toast(requireActivity(), getString(R.string.txt_Something_wrong))
                }

            })

        homeFragmentViewModel!!.setReminder.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                LoadingDialog.cancelLoading()
                try {
                    if (it != null) {
                        if (it.isStatus!!) {
                            if (setReminderDialog != null) {
                                setReminderDialog!!.dismiss()
                            }
                            toast(this.activity, it.message)
                        }
                    } else toast(this.activity, this.getString(R.string.txt_Something_wrong))
                } catch (e: Exception) {
                    toast(requireActivity(), getString(R.string.txt_Something_wrong))
                }

            })

    }

    private fun loadDataToAdapter() {
        offSet=0
        mBookingHistoryList?.clear()
        bookingHistoryAdapter = BookingHistoryAdapter(requireActivity(), this!!, mBookingHistoryList!!)
        mRvBookingsHistory!!.adapter = bookingHistoryAdapter
        (activity as DashboardActivity?)!!.RunLayoutAnimation(mRvBookingsHistory)
        
    }

    private fun loadMoreData() {
        if (mBookingHistoryList!!.size!=0) mLayNoDataAvailable!!.isVisible=false
        else mLayNoDataAvailable!!.isVisible=true

        bookingHistoryAdapter!!.notifyDataSetChanged()
    }


    /* init view */
    @SuppressLint("ResourceType")
    private fun initView(view: View) {
        mRvBookingsHistory = view.findViewById(R.id.rvBookingsHistory)
        mPbLoadData = view.findViewById(R.id.pbLoadData)
        mRvTripStatus = view.findViewById(R.id.rvTripStatus)
        mLayNoDataAvailable = view.findViewById(R.id.layNoDataAvailable)
    }

    /* initialize listener */
    @SuppressLint("ResourceType")
    private fun initializeListeners() {

        mRvTripStatus!!.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter=TripStatusAdapter(this@MyBookingFragment,tripStatus)
        }

        val llmComplete = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mRvBookingsHistory!!.layoutManager = llmComplete
        
        mRvBookingsHistory!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val currentItems = llmComplete!!.getChildCount()
                val totalItems = llmComplete.getItemCount()
                val scrollOutItems = llmComplete.findFirstVisibleItemPosition()

                if (dy > 0 && loadingBookingItems && (currentItems + scrollOutItems == totalItems)) {
                    loadingBookingItems = false
                    offSet = totalItems + 1
                    getBookingList()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    loadingBookingItems = true
                }
            }
        })



    }



    private fun getBookingList() {
        try {
            mPbLoadData!!.visibility = View.VISIBLE
            homeFragmentViewModel!!.getBookings(
                this.context,
                getPreference(this.context, Constants.TOKEN), offSet, limit,travelStatus
            )
        } catch (e: Exception) {
            mylog(TAG, "getBookingList: Error=${e.localizedMessage}")
        }
    }


    fun setReminder(pnrNo: String?, startDate: String) {

        try {
            val view: View = layoutInflater.inflate(R.layout.set_reminder_layout, null)
            setReminderDialog =
                BottomSheetDialog(this.requireActivity(), R.style.CustomBottomSheetDialogTheme)

            view.findViewById<TextView>(R.id.tvTitle).setText("Set Reminder")
            val cvDays = view.findViewById<CardView>(R.id.cvDays)
            val rgReminderTime = view.findViewById<RadioGroup>(R.id.rgReminderTime)
            val btnSetReminder = view.findViewById<Button>(R.id.btnSetReminder)
            val chkMon = view.findViewById<CheckBox>(R.id.chkMon)
            val chkTue = view.findViewById<CheckBox>(R.id.chkTue)
            val chkWed = view.findViewById<CheckBox>(R.id.chkWed)
            val chkThu = view.findViewById<CheckBox>(R.id.chkThu)
            val chkFri = view.findViewById<CheckBox>(R.id.chkFri)
            val chkSat = view.findViewById<CheckBox>(R.id.chkSat)
            val chkSun = view.findViewById<CheckBox>(R.id.chkSun)
            view.findViewById<ImageView>(R.id.imgCancel)
                .setOnClickListener { setReminderDialog!!.dismiss() }
            val currentDate = convertDateToBeautify(Calendar.getInstance().time)

            var reminderTime = "10"
            daysList.clear()
            val size = daysList.size

            rgReminderTime!!.setOnCheckedChangeListener { radioGroup, isCheckedID ->
                when (isCheckedID) {
                    R.id.radioBtn10min -> reminderTime = "10"
                    R.id.radioBtn20min -> reminderTime = "20"
                    R.id.radioBtn30min -> reminderTime = "30"
                    R.id.radioBtn1hr -> reminderTime = "60"
                }
            }

            chkMon.setOnCheckedChangeListener { compoundButton, b ->
                updateDaysList(size, "monday")
            }
            chkTue.setOnCheckedChangeListener { compoundButton, b ->
                updateDaysList(size, "tuesday")
            }
            chkWed.setOnCheckedChangeListener { compoundButton, b ->
                updateDaysList(size, "wednesday")
            }
            chkThu.setOnCheckedChangeListener { compoundButton, b ->
                updateDaysList(size, "thursday")
            }
            chkFri.setOnCheckedChangeListener { compoundButton, b ->
                updateDaysList(size, "friday")
            }
            chkSat.setOnCheckedChangeListener { compoundButton, b ->
                updateDaysList(size, "saturday")
            }
            chkSun.setOnCheckedChangeListener { compoundButton, b ->
                updateDaysList(size, "sunday")
            }


            if (currentDate.equals(startDate)) {
                (this.activity as BaseActivity).hideView(cvDays)
            } else {
                val days = getCountOfDays(currentDate, startDate)
            }


            setReminderDialog!!.setOnShowListener(object : DialogInterface.OnShowListener {
                override fun onShow(dialog: DialogInterface) {
                    val d = setReminderDialog as BottomSheetDialog
                    val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
                    val behavior = BottomSheetBehavior.from<View?>(bottomSheet!!)
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED

                    behavior.setBottomSheetCallback(object :
                        BottomSheetBehavior.BottomSheetCallback() {
                        override fun onStateChanged(bottomSheet: View, newState: Int) {
                            if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                            }
                        }

                        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                    })
                }
            })

            btnSetReminder.setOnClickListener {
                LoadingDialog.showLoadingDialog(activity, "Loading...")
                homeFragmentViewModel!!.setReminder(
                    activity,
                    getPreference(activity, Constants.TOKEN),
                    pnrNo!!,
                    daysList,
                    reminderTime
                )
            }

            setReminderDialog!!.setContentView(view)
            setReminderDialog!!.setCancelable(false)
            setReminderDialog!!.setCanceledOnTouchOutside(true)
            setReminderDialog!!.show()
        } catch (e: Exception) {
            mylog(TAG, "viewRouteStops: Error=" + e.localizedMessage)
        }
    }


    private fun updateDaysList(count: Int, label: String) {
        try {
            if (daysList!!.contains(label)) daysList!!.remove(label)
            else daysList!!.add(count, label)

            mylog(TAG, "updateDaysList: daysList=$daysList")
        } catch (e: Exception) {
            mylog(TAG, "updateDaysList: error=${e.localizedMessage}")
        }

    }

    fun cancelBooking(pnrNo: String?) {
        LoadingDialog.showLoadingDialog(activity, "Loading...")
        homeFragmentViewModel!!.cancelBookings(
            activity,
            getPreference(activity, Constants.TOKEN),
            pnrNo!!,
            convertDateToBeautify(Calendar.getInstance().time)
        )
    }

    fun cancelBookingDialog(pnrNo: String?) {
        try {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.booking_cancel_dialog)
            dialog.setCancelable(true)
            dialog.window?.setBackgroundDrawable(ColorDrawable(0))

            val tvMsg = dialog.findViewById<TextView>(R.id.tvMsg)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvMsg.setText(
                    Html.fromHtml(
                        refundAlert,
                        Html.FROM_HTML_MODE_COMPACT
                    )
                );
            } else {
                tvMsg.setText(Html.fromHtml(refundAlert))
            }

            dialog.findViewById<View>(R.id.btnYes).setOnClickListener {
                if (dialog.isShowing) dialog.dismiss()
                cancelBooking(pnrNo)
            }
            dialog.findViewById<View>(R.id.btnNo).setOnClickListener {
                if (dialog.isShowing) dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()

        } catch (e: Exception) {
            mylog(TAG, "cancelBookingDialog: Error=${e.localizedMessage}")
        }
    }

    fun callDriver(driverNo: String?) {
        try {
            if (checkCallPermission()){
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$driverNo"))
                startActivity(intent);

            }
        }catch (e:Exception){
            Log.i(TAG, "callDriver: Error=${e.localizedMessage}")}

    }

    private fun checkCallPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL);
            return false
        }
        else return true

    }

    fun selectedStatus(status: String) {
        offSet=0
        mBookingHistoryList?.clear()
        travelStatus=status
        mPbLoadData!!.visibility=View.VISIBLE
        getBookingList()
        (activity as DashboardActivity?)!!.RunLayoutAnimation(mRvBookingsHistory)

        Log.i(TAG, "selectedStatus: travelStatus=$travelStatus")
    }


    companion object {
        /*variable declaration*/
        const val mTitle = "My Tickets"
    }
}