package com.ferri.driver.Activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.driver.Adapters.PassengerListAdapter
import com.ferri.driver.Model.DataItem
import com.ferri.driver.R
import com.ferri.driver.Util.*
import com.ferri.driver.ViewModel.MainViewModel

class StopsPassengerListActivity : BaseActivity() {

    val TAG = "StopsPassengerList"
    var rvPassengerList: RecyclerView? = null
    var passengerListAdapter: PassengerListAdapter? = null
    var ivBack: ImageView? = null
    var ivNotification: ImageView? = null
    var tvStopName: TextView? = null
    var tvStopTiming: TextView? = null
    var tvNoOfPassenger: TextView? = null
    var layNoPassengerAvailable: LinearLayout? = null
    var mainViewModel: MainViewModel? = null
    var stopId = ""
    var routeId = ""
    var bookingDate = ""
    var stopName = ""
    var stopTime = ""
    var passengers = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopes_passenger_list)

        try {
            if (intent != null) {
                stopId = intent.getStringExtra("stopId").toString()
                routeId = intent.getStringExtra("routeId").toString()
                bookingDate = intent.getStringExtra("bookingDate").toString()
                stopName = intent.getStringExtra("stopName").toString()
                stopTime = intent.getStringExtra("stopTime").toString()
                passengers = intent.getStringExtra("passengers").toString()
            }
        } catch (e: Exception) {
            myLog(TAG, "onCreate: Error=${e.localizedMessage}")
        }

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initLayouts()
        handelViewModel()
        doOperationOnLayouts()

    }

    /* init layout */
    private fun initLayouts() {
        rvPassengerList = findViewById(R.id.rvPassengerList)
        ivBack = findViewById(R.id.ivBack)
        ivNotification = findViewById(R.id.ivNotification)
        layNoPassengerAvailable = findViewById(R.id.layNoPassengerAvailable)

        tvStopName = findViewById(R.id.tvStopName)
        tvStopTiming = findViewById(R.id.tvStopTiming)
        tvNoOfPassenger = findViewById(R.id.tvNoOfPassenger)
    }

    /* add functionality to layout */
    private fun doOperationOnLayouts() {
        try {
            tvStopName!!.text = stopName
            tvStopTiming!!.text = stopTime
            tvNoOfPassenger!!.text = passengers



            ivBack!!.setOnClickListener { finish() }
            ivNotification!!.setOnClickListener { startActivity(NotificationActivity::class.java) }
        } catch (e: Exception) {
            Log.i(TAG, "doOperationOnLayouts: Error=${e.localizedMessage}")
        }

    }

    private fun handelViewModel() {

        if (isInternetConnection(this)) {
            LoadingDialog.showLoadingDialog(this, "Please wait...")
            mainViewModel!!.stopsDetails(
                getPreference(this, AppConstants.TOKEN)!!,
                stopId,
                routeId,
                bookingDate
            )
                .observe(this, androidx.lifecycle.Observer {
                    LoadingDialog.cancelLoading()
                    try {
                        if (it!=null) {
                            if (it.status!!)setDataToAdapter(it!!.data)
                            else alertDialog(this, it.message.toString())
                        }else sessionExpireDialog(this)

                    } catch (e: Exception) {
                        myLog(TAG, "handelViewModel: Errr=${e.localizedMessage}")
                    }
                })
        }else toast(this)

    }

    private fun setDataToAdapter(data: List<DataItem?>?) {
        try {
            if (data!!.size > 0) {
                passengerListAdapter = PassengerListAdapter(baseContext, data, this)
                rvPassengerList!!.apply {
                    layoutManager = LinearLayoutManager(this@StopsPassengerListActivity)
                    setHasFixedSize(true)
                    adapter = passengerListAdapter
                }
                RunLayoutAnimation(this, rvPassengerList!!)
            } else {
                showView(layNoPassengerAvailable!!)
                hideView(rvPassengerList!!)
            }
        } catch (e: Exception) {
            Log.i(TAG, "setDataToAdapter: Error=${e.localizedMessage}")
        }

    }

}