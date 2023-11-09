package com.ferri.driver.Fragments

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.driver.Activity.BaseActivity
import com.ferri.driver.Activity.MainActivity
import com.ferri.driver.Adapters.TripsAdapter
import com.ferri.driver.Model.TripsData
import com.ferri.driver.R
import com.ferri.driver.Services.BackGroundLocationService
import com.ferri.driver.Util.*
import com.ferri.driver.ViewModel.MainViewModel
import com.ferri.driver.events.ActionEvents
import com.ferri.driver.events.UpdateBookingStatusEvents
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class HomeFragment : Fragment() {

    companion object {
        var mTitle = "Home"
    }

    var TAG="HomeFragment"
    var rvTrips: RecyclerView? = null
    var tripsAdapter: TripsAdapter? = null
    var mainViewModel: MainViewModel? = null
    var mContext: Context? = null
    var layNoTripsAvailable: LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mContext = this.context


        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        initView(view)

        handelViewModel()
        return view
    }


    private fun initView(view: View) {
        rvTrips = view.findViewById(R.id.rvTrips)
        layNoTripsAvailable = view.findViewById(R.id.layNoTripsAvailable)
    }


    private fun handelViewModel() {
        try {
            if (isInternetConnection(requireActivity())) {
                LoadingDialog.showLoadingDialog(mContext, "Loading...")
                mainViewModel!!.myTrips(getPreference(mContext, AppConstants.TOKEN)!!)
                    .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        if (it!=null) {
                            if (it.status!! && it.data != null) {
                                (mContext as BaseActivity).hideView(layNoTripsAvailable!!)
                                (mContext as BaseActivity).showView(rvTrips!!)
                                setListener(it!!.data)
                                savePreference(mContext, AppConstants.ASSIGNED_ID, it.data.assignId)
                                savePreference(mContext, AppConstants.IS_BOOKING_ASSIGNED, true)
                                startLocationService()
                            } else {
                                (mContext as BaseActivity).showView(layNoTripsAvailable!!)
                                (mContext as BaseActivity).hideView(rvTrips!!)
                                savePreference(mContext, AppConstants.IS_BOOKING_ASSIGNED, false)
                                savePreference(mContext, AppConstants.ASSIGNED_ID, "")
                                stopLocationService()
                                alertDialog(mContext as BaseActivity,it.message.toString())
                            }
                        }else sessionExpireDialog(mContext!!)

                    })
            }else toast(mContext)
        } catch (e: Exception) {
            savePreference(mContext, AppConstants.IS_BOOKING_ASSIGNED, false)
            Log.i(TAG, "handelViewModel: Errr=${e.localizedMessage}")
        }
    }

    private fun startLocationService() {
        if (!isMyServiceRunning(BackGroundLocationService::class.java,mContext as MainActivity)) {
            ContextCompat.startForegroundService(
                mContext as MainActivity,
                Intent( mContext as MainActivity, BackGroundLocationService::class.java)
            )
        }
    }
    private fun stopLocationService() {
        try {
            if (isMyServiceRunning(BackGroundLocationService::class.java,requireActivity())) {
                BackGroundLocationService().stopService(Intent(mContext as MainActivity, BackGroundLocationService::class.java))
            }
        }catch (e:Exception){
            Log.i(TAG, "stopLocationService: Error=${e.localizedMessage}")}

    }


    private fun setListener(data: TripsData?) {
        tripsAdapter = TripsAdapter(activity!!, this,data)
        rvTrips!!.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = tripsAdapter
        }
        RunLayoutAnimation(activity, rvTrips!!)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(pusher: UpdateBookingStatusEvents) {
        handelViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}