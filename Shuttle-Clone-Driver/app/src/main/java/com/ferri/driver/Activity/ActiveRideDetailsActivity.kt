package com.ferri.driver.Activity

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.budiyev.android.codescanner.*
import com.ferri.driver.Adapters.BusStopsAdapter
import com.ferri.driver.Model.ScanTicketResponseModel
import com.ferri.driver.Model.TripsData
import com.ferri.driver.R
import com.ferri.driver.Util.*
import com.ferri.driver.ViewModel.MainViewModel
import com.ferri.driver.events.UpdateBookingStatusEvents
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.ncorti.slidetoact.SlideToActView
import com.nhaarman.supertooltips.ToolTip
import com.nhaarman.supertooltips.ToolTipRelativeLayout
import com.nhaarman.supertooltips.ToolTipView
import org.greenrobot.eventbus.EventBus


class ActiveRideDetailsActivity : BaseActivity(), ToolTipView.OnToolTipViewClickedListener {

    val TAG = "ActiveRideDetails"
    val REQUEST_CAMER_CODE = 210
    var rvBusStops: RecyclerView? = null
    var btnStartRide: SlideToActView? = null
    var btnNavigateRide: Button? = null
    var btnFinishRide: SlideToActView? = null
    var busStopsAdapter: BusStopsAdapter? = null
    var ivBack: ImageView? = null
    var ivNotification: ImageView? = null
    var fabScanTicket: FloatingActionButton? = null
    lateinit var scanTicketLayout: ToolTipRelativeLayout
    lateinit var scanTicketView: ToolTipView
    lateinit var tvTripStatus: TextView
    lateinit var tvBusCode: TextView
    lateinit var tvPassengers: TextView
    lateinit var tvCurrentTime: TextView
    lateinit var tvStartAt: TextView
    lateinit var tvEndAt: TextView
    var tripsData: TripsData? = null
    var mainViewModel: MainViewModel? = null
    val TRIP_STARTED = "STARTED"
    val TRIP_COMPLETED = "COMPLETED"
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_ride_details)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        try {
            if (intent != null) {
                tripsData = intent.getSerializableExtra("tripsData") as TripsData
            }
        } catch (e: Exception) {
            myLog(TAG, "onCreate: Error=${e.localizedMessage}")
        }


        initLayouts()
        setListners()
        doOperationOnLayouts()


    }

    private fun setListners() {

        updateView(tripsData!!.tripStatus)

        btnStartRide!!.onSlideCompleteListener = (object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(@NonNull view: SlideToActView) {
                getLatestLocation()
                vibratePhone(this@ActiveRideDetailsActivity)
                updateBookingStatus(TRIP_STARTED)
            }
        })

        btnNavigateRide!!.clickWithThrottle {
            vibratePhone(this@ActiveRideDetailsActivity)
            navigateRide()
        }


        btnFinishRide!!.onSlideCompleteListener = (object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(@NonNull view: SlideToActView) {
                getLatestLocation()
                vibratePhone(this@ActiveRideDetailsActivity)
                updateBookingStatus(TRIP_COMPLETED)
            }
        })


        ivNotification!!.setOnClickListener { startActivity(NotificationActivity::class.java) }
        ivBack!!.setOnClickListener { finish() }
        fabScanTicket!!.setOnClickListener {
            if (checkAndRequestCameraPermissions()) scanTicket()
        }
    }

    private fun getLatestLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                fusedLocationClient?.lastLocation!!.addOnSuccessListener { location: Location? ->
                    location?.let { it: Location ->
                        savePreference(
                            this,
                            AppConstants.DRIVER_LATITUDE,
                            location.latitude.toString()
                        )
                        savePreference(
                            this,
                            AppConstants.DRIVER_LONGITUDE,
                            location.longitude.toString()
                        )
                        Log.i(TAG, "setListners: location=${it.latitude}+${it.longitude}")
                    } ?: kotlin.run {
                        Log.i(TAG, "setListners: fusedLocationClient error")
                    }
                }
            } catch (e: Exception) {
                Log.i(TAG, "getLatestLocation: Error=${e.localizedMessage}")
            }

        }
    }

    private fun updateView(tripStatus: String?) {
        try {


            Log.i(TAG, "updateView: tripStatus=$tripStatus")
            when (tripStatus) {
                "ASSIGNED" -> {
                    savePreference(this, AppConstants.IS_TRIP_STARTED, false)
                    showView(btnStartRide!!)
                }
                "STARTED", "RIDING" -> {
                    showView(btnFinishRide!!)
                    showView(btnNavigateRide!!)
                    hideView(btnStartRide!!)
                    savePreference(this, AppConstants.IS_TRIP_STARTED, true)
                }
                else -> {
                    hideView(btnFinishRide!!)
                    hideView(btnNavigateRide!!)
                    hideView(btnStartRide!!)
                    savePreference(this, AppConstants.IS_TRIP_STARTED, false)
                    finish()
                }
            }
            tvTripStatus.text = tripStatus
        } catch (e: Exception) {
            Log.i(TAG, "updateView: Error=${e.localizedMessage}")
        }
    }


    private fun navigateRide() {
        try {
            val intent = Intent(this, BusRoutesActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("tripsData", tripsData)
            intent.putExtras(bundle)
            startActivity(intent)
        } catch (e: Exception) {
            Log.i(TAG, "navigateRide: Error=${e.localizedMessage}")
        }
    }

    private fun updateBookingStatus(tripStatus: String) {
        if (isInternetConnection(this)) {
            LoadingDialog.showLoadingDialog(this, "Please wait..")

            mainViewModel!!.updateTrackingStatus(
                getPreference(this, AppConstants.TOKEN).toString(),
                tripsData!!.assignId.toString(),
                tripStatus,
                getPreference(this, AppConstants.DRIVER_LATITUDE).toString(),
                getPreference(this, AppConstants.DRIVER_LONGITUDE).toString(),
                getPreference(this, AppConstants.DRIVER_ANGLE).toString()
            ).observe(this, Observer {
                LoadingDialog.cancelLoading()
                try {
                    if (it!=null) {
                        if (it.status!!) {
                            updateView(it.data!!.tripStatus)
                            EventBus.getDefault().post(UpdateBookingStatusEvents())
                        } else {
                            savePreference(this, AppConstants.IS_TRIP_STARTED, false)
                            alertDialog(this,it.message.toString())
                        }
                    }else sessionExpireDialog(this)
                } catch (e: Exception) {
                    Log.i(TAG, "startRide: Error=${e.localizedMessage}")
                }
            })
        } else toast(this)

    }

    /* init layout */
    private fun initLayouts() {
        rvBusStops = findViewById(R.id.rvBusStops)
        ivBack = findViewById(R.id.ivBack)
        btnStartRide = findViewById(R.id.btnStartRide) as SlideToActView
        btnFinishRide = findViewById(R.id.btnFinishRide) as SlideToActView
        ivNotification = findViewById(R.id.ivNotification)
        scanTicketLayout = findViewById(R.id.tooltipView)
        fabScanTicket = findViewById(R.id.fabScanTicket)

        tvTripStatus = findViewById(R.id.tvTripStatus)
        tvBusCode = findViewById(R.id.tvBusCode)
        tvPassengers = findViewById(R.id.tvPassengers)
        tvCurrentTime = findViewById(R.id.tvCurrentTime)
        tvStartAt = findViewById(R.id.tvStartAt)
        tvEndAt = findViewById(R.id.tvEndAt)
        btnNavigateRide = findViewById(R.id.btnNavigateRide)
    }

    /* add functionality to layout */
    private fun doOperationOnLayouts() {

        if (tripsData != null) {

            tvBusCode.text = tripsData!!.busModelNo
            tvTripStatus.text = tripsData!!.tripStatus
            tvPassengers.text = tripsData!!.passengerTotal
            tvCurrentTime.text = currentTime()
//            tvStartAt.text = tripsData!!.ticketStartAt
            tvStartAt.text = "${tripsData!!.createdTime} ,${tripsData!!.createdDate}"
//            tvEndAt.text = tripsData!!.ticketEndAt

            busStopsAdapter = BusStopsAdapter(baseContext, this, tripsData!!)
            rvBusStops!!.apply {
                layoutManager = LinearLayoutManager(this@ActiveRideDetailsActivity)
                setHasFixedSize(true)
                adapter = busStopsAdapter
            }
            RunLayoutAnimation(this, rvBusStops!!)

        }

        Handler().postDelayed({
            scanTicketToolTipView()
        }, 800)
    }

    private fun scanTicket() {
        try {
            val view: View = layoutInflater.inflate(R.layout.scan_ticket_dialog, null)
            val scanTicketDialog = Dialog(this, R.style.CustomBottomSheetDialogTheme)

            val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
            val btnCloseScanner = view.findViewById<Button>(R.id.btnCloseScanner)

            val codeScanner = CodeScanner(this, scannerView)

            // Parameters (default values)
            codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
            codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
            // ex. listOf(BarcodeFormat.QR_CODE)
            codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
            codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
            codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
            codeScanner.isFlashEnabled = false // Whether to enable flash or not
            codeScanner.startPreview()

            // Callbacks
            codeScanner.decodeCallback = DecodeCallback {
                runOnUiThread {
                    Log.i(TAG, "onCreate: Scan ticket response=${it.text}")
                    val ticketData = Gson().fromJson(it.text, ScanTicketResponseModel::class.java)
                    ticketScanned(ticketData, codeScanner, scanTicketDialog)
                }
            }
            codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
                runOnUiThread {
                    toast(this, "Camera initialization error: ${it.message}")
                }
            }

            scannerView.setOnClickListener {
                codeScanner.startPreview()
            }

            btnCloseScanner.setOnClickListener {
                if (scanTicketDialog != null)
                    scanTicketDialog?.dismiss()
                codeScanner.releaseResources()
            }


            scanTicketDialog?.setContentView(view)
            scanTicketDialog?.setCancelable(false)
            scanTicketDialog?.setCanceledOnTouchOutside(false)
            scanTicketDialog?.show()

        } catch (e: Exception) {
            myLog(TAG, "showscanTicketDialog: Error=" + e.localizedMessage)
        }
    }

    private fun ticketScanned(
        ticketData: ScanTicketResponseModel,
        codeScanner: CodeScanner,
        scanTicketDialog: Dialog
    ) {
        try {
            if (isInternetConnection(this!!)) {
                LoadingDialog.showLoadingDialog(this!!, "Please wait...")
                mainViewModel!!.updateTicketStatus(
                    getPreference(this!!, AppConstants.TOKEN)!!.toString(),
                    ticketData.pnrNo!!,
                    "ONBOARDED"
                ).observe(this, Observer {
                    LoadingDialog.cancelLoading()
                    codeScanner.stopPreview()
                    codeScanner.releaseResources()
                    scanTicketDialog.dismiss()
                    vibratePhone(this)
                    if (it != null) {
                        toast(this, it.message)
                    } else sessionExpireDialog(this)
                })

            } else toast(this)

        } catch (e: Exception) {
            myLog(TAG, e.localizedMessage)
            LoadingDialog.cancelLoading()
            codeScanner.stopPreview()
            codeScanner.releaseResources()
            scanTicketDialog.dismiss()
        }

    }


    fun checkAndRequestCameraPermissions(): Boolean {
        val camerapermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val listPermissionsNeeded = ArrayList<String>()
        if (camerapermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    listPermissionsNeeded.toTypedArray(),
                    REQUEST_CAMER_CODE
                )
            } else {
                try {
                    ActivityCompat.requestPermissions(
                        this as Activity,
                        listPermissionsNeeded.toTypedArray(),
                        REQUEST_CAMER_CODE
                    )
                } catch (e: Exception) {
                    myLog(TAG, "checkAndRequestCameraPermissions: Error=" + e.localizedMessage)
                }

            }
            return false
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CAMER_CODE -> {
                scanTicket()
            }
        }
    }


    private fun scanTicketToolTipView() {
        val toolTip: ToolTip = ToolTip()
            .withText("Scan Ticket")
            .withTextColor(resources.getColor(R.color.white))
            .withColor(resources.getColor(R.color.colorAccent))
            .withAnimationType(ToolTip.AnimationType.FROM_TOP)
        scanTicketView = scanTicketLayout.showToolTipForView(toolTip, fabScanTicket)
        scanTicketView.setOnToolTipViewClickedListener(this)
    }

    override fun onToolTipViewClicked(toolTipView: ToolTipView?) {
        if (scanTicketView == toolTipView) {
            scanTicketView.remove()
        }
    }
}