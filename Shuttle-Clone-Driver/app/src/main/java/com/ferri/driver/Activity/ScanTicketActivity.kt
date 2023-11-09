package com.ferri.driver.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.ferri.driver.Model.ScanTicketResponseModel
import com.ferri.driver.R
import com.ferri.driver.Util.*
import com.ferri.driver.ViewModel.MainViewModel
import com.google.gson.Gson

class ScanTicketActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner
    val TAG="ScanTicketActivity"
    private var mainViewModel: MainViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_ticket)
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                toast(this, "Scan result: ${it.text}")
                Log.i(TAG, "onCreate: Scan ticket response=${it.text}")
               val ticketData= Gson().fromJson(it.text,ScanTicketResponseModel::class.java)
                ticketScanned(ticketData)
                
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
    }

    private fun ticketScanned(ticketData: ScanTicketResponseModel) {
        try {
            if (isInternetConnection(this!!)) {
                LoadingDialog.showLoadingDialog(this!!, "Please wait...")
                mainViewModel!!.updateTicketStatus(
                    getPreference(this!!, AppConstants.TOKEN)!!,
                    ticketData.pnrNo!!,
                    ticketData.travelStatus!!
                ).observe(this, Observer {
                  LoadingDialog.cancelLoading()
                    if (it!=null){
                        toast(this,it.message)
                    }else sessionExpireDialog(this)
                })

            } else toast(this)

        } catch (e: Exception) {
            myLog(TAG, e.localizedMessage)
        }

    }



    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}