package com.ferri.userapp.ui.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import com.ferri.userapp.ViewModel.HomeFragmentViewModel
import com.ferri.userapp.model.*
import com.ferri.userapp.ui.activity.PassDetailsActivity.Companion.passDetails
import com.ferri.userapp.utils.*
import com.google.gson.Gson
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject


class ConfirmPaymentActivity : BaseActivity(), PaymentResultWithDataListener {

    private val TAG = "ConfirmPaymentActivity"
    private var COMING_FROM = ""
    private var fareData: FareData? = null
    private var bookingData: BookingData? = null
    private var homeFragmentViewModel: HomeFragmentViewModel? = null
    var passengerDetailsItem: ArrayList<PassengerDetailsItem> = ArrayList()

    private var tvBookingDate: TextView? = null
    private var tvSlocation: TextView? = null
    private var tvDlocation: TextView? = null
    private var tvStartTime: TextView? = null
    private var tvEndTime: TextView? = null
    private var tvBusDtl: TextView? = null
    private var tvSeatNo: TextView? = null
    private var tvBaseDiscountFare: TextView? = null
    private var checkboxWallet: CheckBox? = null
    private var tvWalletBalance: TextView? = null
    private var edPromoCode: EditText? = null
    private var btnApplyPromoCode: Button? = null
    private var tvTotalFare: TextView? = null
    private var btnConfirmPay: Button? = null

    private var layRemoveCode: LinearLayout? = null
    private var layApplyCode: LinearLayout? = null
    private var tvPromoCode: TextView? = null
    private var btnRemovePromoCode: Button? = null

    private var totalFare = 0
    private var seatNO = ""
    private var paymentMode = "UPI"
    private var WALLET = "WALLET"
    private var RAZPAY = "UPI"
    private var FREE = "FREE"
    private var pnrNumber = ""
    private var walletBalance = 0
    private var promoCode = ""
    private var discountAmount = "0"
    private var bookingDate = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_payment)
        Checkout.preload(applicationContext)
        findViewById<View>(R.id.ivBack).setOnClickListener { onBackPressed() }

        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)


        initLayouts()
        initializeListeners()
        handelViewModel()

        try {
            if (intent != null) {
                COMING_FROM = intent.getStringExtra("comingFrom").toString()
                mylog(TAG, "onCreate: COMING_FROM=" + COMING_FROM)
                bookingDate= getPreference(this,Constants.BOOKING_DATE)
                if (COMING_FROM.equals("Booking")) {
                    fareData = intent.getSerializableExtra("bookingFare") as FareData
                    seatNO = intent.getStringExtra("seatNo").toString()
                    passengerDetailsItem.add(
                        PassengerDetailsItem(
                            seatNO,
                            getPreference(this, Constants.GANDER),
                            getPreference(this, Constants.USER_NAME),
                            ""
                        )
                    )
                    createBookingRequest(passengerDetailsItem, fareData, "")
                } else if (COMING_FROM.equals("Pass")) {
                    seatNO = intent.getStringExtra("seatNo").toString()
                    showPassData()
                } else {
                    bookingData = intent.getSerializableExtra("bookingData") as BookingData
                    fareData = intent.getSerializableExtra("bookingFare") as FareData
                    addPassengerDetails(bookingData!!.persistedPassenger)
                    showData(bookingData!!)
                }
            }
        } catch (e: Exception) {
            mylog(TAG, "initLayouts: Error=${e.localizedMessage} ")
        }

    }

    private fun addPassengerDetails( passengers: List<PersistedPassengerItem?>?) {
        try {
            for (pas in passengers!!){
                passengerDetailsItem.add(
                    PassengerDetailsItem(
                        pas?.seat,
                        pas?.gender,
                        pas?.fullname,
                        pas?.age
                    )
                )
            }
        }catch (e:Exception){
            Log.i(TAG, "addPassengerDetails: Error=${e.localizedMessage}")}
    }

    private fun showPassData() {
        try {
            tvBookingDate!!.text =
                convertDateToBeautify(getPreference(this, Constants.OFFICE_BOOKING_DATE))
            tvSlocation!!.text = getPreference(this, Constants.OFFICE_PICKUP_ADD)
            tvDlocation!!.text = getPreference(this, Constants.OFFICE_DROP_ADD)
            tvStartTime!!.text = getPreference(this, Constants.OFFICE_PICKUP_TIME)
            tvEndTime!!.text = getPreference(this, Constants.OFFICE_DROP_TIME)
            tvBusDtl!!.text = getPreference(this, Constants.OFFICE_BUS_NAME)
            tvSeatNo!!.text = seatNO
            tvWalletBalance!!.text = "₹ " + getPreference(this, Constants.WALLET_BALANCE)
            tvTotalFare!!.text = "₹ " + passDetails!!.totalFare
            totalFare = passDetails!!.totalFare!!.toInt()
            walletBalance = getPreference(this, Constants.WALLET_BALANCE).toInt()
            hideView(layApplyCode)
        } catch (e: Exception) {
            Log.i(TAG, "showPassData: Error=${e.localizedMessage}")
        }
    }

    private fun payForPass() {
        try {
            if (isNetworkAvailable(this!!)) {
                LoadingDialog.showLoadingDialog(this, "Please wait...")
                homeFragmentViewModel!!.payForPass(
                    this,
                    getPreference(this, Constants.TOKEN),
                    getPreference(this, Constants.BUS_ID),
                    getPreference(this, Constants.ROUTE_ID),
                    getPreference(this, Constants.PICKUP_ID),
                    getPreference(this, Constants.DROP_ID),
                    passDetails!!.passId.toString(),
                    passDetails!!.passNoOfRides.toString(),
                    passDetails!!.totalFare.toString(),
                    seatNO,
                    getPreference(this, Constants.HAS_RETURN),
                    paymentMode,
                    bookingDate
                )
            }else toast(this)
        } catch (e: java.lang.Exception) {
            Log.i(TAG, "payForPass: Error=${e.localizedMessage}")
        }
    }


    private fun initializeListeners() {
        btnConfirmPay!!.clickWithThrottle {
            if (checkData()) {
                if (!COMING_FROM.equals("Pass"))
                    payRideFee()
                else payForPass()
            }
        }

        btnRemovePromoCode!!.clickWithThrottle {
            hideView(layRemoveCode)
            showView(layApplyCode)
            promoCode = ""
            edPromoCode!!.setText("")
            createBookingRequest(passengerDetailsItem, fareData, "")
        }

        btnApplyPromoCode!!.clickWithThrottle {
            promoCode = edPromoCode!!.text.toString()
            if (!promoCode.equals(""))
                createBookingRequest(passengerDetailsItem, fareData, promoCode)
            else {
                edPromoCode!!.error = "Field should not be empty"
                edPromoCode!!.requestFocus()
            }
        }

        checkboxWallet!!.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) paymentMode = WALLET
            else paymentMode = RAZPAY
        }
    }

    private fun payRideFee() {
        try {
            if (isNetworkAvailable(this!!)) {
                LoadingDialog.showLoadingDialog(this, "Please wait...")
//                if(totalFare.toString().equals(0)) paymentMode=FREE
                Log.i(TAG, "payRideFee: paymentMode=$paymentMode")
                Log.i(TAG, "payRideFee: totalFare=$totalFare")
                Log.i(TAG, "payRideFee: discountAmount=$discountAmount")
                homeFragmentViewModel!!.payRouteFee(
                    this,
                    getPreference(this, Constants.TOKEN),
                    pnrNumber,
                    totalFare.toString(),
                    paymentMode,
                    bookingDate
                )
            }else toast(this)
        } catch (e: java.lang.Exception) {
            mylog(TAG, "payRideFee: Error=${e.localizedMessage}")
        }
    }

    private fun checkData(): Boolean {
        if (paymentMode.equals(WALLET)) {
            if (totalFare > walletBalance) {
                toast(this, "Not sufficient wallet balance")
                return false
            } else return true
        }

        if (!totalFare.equals("0")) return true
        else return false

        if (!COMING_FROM.equals("Pass"))
            if (pnrNumber.equals("")) return false
        return true
    }

    private fun initLayouts() {
        tvBookingDate = findViewById(R.id.tvBookingDate)
        tvSlocation = findViewById(R.id.tvSlocation)
        tvDlocation = findViewById(R.id.tvDlocation)
        tvStartTime = findViewById(R.id.tvStartTime)
        tvEndTime = findViewById(R.id.tvEndTime)
        tvBusDtl = findViewById(R.id.tvBusDtl)
        tvSeatNo = findViewById(R.id.tvSeatNo)
        checkboxWallet = findViewById(R.id.checkboxWallet)
        tvWalletBalance = findViewById(R.id.tvWalletBalance)
        edPromoCode = findViewById(R.id.edPromoCode)
        btnApplyPromoCode = findViewById(R.id.btnApplyPromoCode)
        tvTotalFare = findViewById(R.id.tvTotalFare)
        tvBaseDiscountFare = findViewById(R.id.tvBaseDiscountFare)
        btnConfirmPay = findViewById(R.id.btnConfirmPay)
        layRemoveCode = findViewById(R.id.layRemoveCode)
        layApplyCode = findViewById(R.id.layApplyCode)
        tvPromoCode = findViewById(R.id.tvPromoCode)
        btnRemovePromoCode = findViewById(R.id.btnRemovePromoCode)
        edPromoCode!!.setFilters(arrayOf<InputFilter>(AllCaps()))

    }


    private fun createBookingRequest(
        passengerDetailsItem: ArrayList<PassengerDetailsItem>,
        fareData: FareData?,
        offer_code: String?
    ) {
        try {
            if (isNetworkAvailable(this!!)) {
                LoadingDialog.showLoadingDialog(this, "Please wait...")
                homeFragmentViewModel!!.createBooking(
                    this,
                    getPreference(this, Constants.TOKEN),
                    BookingRequestData(passengerDetailsItem, offer_code,fareData!!)
                )
            }else toast(this)
        } catch (e: java.lang.Exception) {
            mylog(TAG, "createBookingRequest: Error=${e.localizedMessage}")
        }
    }

    private fun handelViewModel() {
        homeFragmentViewModel!!.bookingResponse.observe(this, Observer {
            LoadingDialog.cancelLoading()
            try {
                if (it.status!!) {
                    showData(it.data!!)
                }else toast(this,it.message)

            } catch (e: Exception) {
                mylog(TAG, "handelViewModel: Error=${e.localizedMessage}")
            }
        })

        homeFragmentViewModel!!.paymentResponse.observe(this, Observer {
            LoadingDialog.cancelLoading()
            try {
                if (it.status!!) {
                    when (it.data!!.paymentMode) {
                        WALLET,FREE -> {
                            paymentResponse(it.status, it.message!!)
                        }
                        RAZPAY -> {
                            makePayment(it.data!!)
                        }
                    }

                } else toast(this, it.message)
            } catch (e: Exception) {
                mylog(TAG, "handelViewModel: Error=${e.localizedMessage}")
            }
        })

        homeFragmentViewModel!!.payForPass.observe(this, Observer {
            LoadingDialog.cancelLoading()
            try {
                if (it.status!!) {
                    when (it.data!!.paymentMode) {
                        WALLET -> {
                            paymentResponse(it.status, it.message!!)
                        }
                        RAZPAY -> {
                            makePayment (it.data!!)
                        }
                    }

                } else toast(this, it.message)
            } catch (e: Exception) {
                mylog(TAG, "handelViewModel: Error=${e.localizedMessage}")
            }
        })
    }

    private fun paymentResponse(status: Boolean, message: String) {
        successFailureDialog(this@ConfirmPaymentActivity, status, message)
    }

    fun makePayment(data: InitiateData) {
        try {
            // initialize Razorpay account.
            val checkout = Checkout()
            checkout.setKeyID(data.paymentSettings!!.key)

            // set image
            checkout.setImage(R.drawable.ic_app_logo)
            val options = JSONObject()
            options.put("name", data.paymentSettings!!.textName)
            options.put("description", data.paymentSettings!!.description)
            options.put("image", data.paymentSettings!!.logo)
            options.put("order_id", data.orderId);//from response of step 3.
            options.put("theme.color", data.paymentSettings!!.themeColor)
            options.put("currency", data.paymentSettings!!.currency)
            options.put("amount", data.amount) //pass amount in currency subunits
            options.put("prefill.email", data.prefill!!.email)
            options.put("prefill.contact", data.prefill!!.contact)
            options.put("send_sms_hash", true)
            options.put("allow_rotation", false)
            val retryObj = JSONObject()
            retryObj.put("enabled", false)
            retryObj.put("max_count", 0)
            options.put("retry", retryObj)

            checkout.open(this,options)

        } catch (e: Exception) {
            mylog(TAG, "makePayment: Error=" + e.localizedMessage)
        }
    }

    private fun showData(bookingData: BookingData) {
        try {
            Log.i(TAG, "showData: bookingData=${Gson().toJson(bookingData)}")
            if (bookingData != null) {
                val details = bookingData.getbookingData!!
                tvBookingDate!!.text = convertDateToBeautify(details.startDate!!)
                tvSlocation!!.text = details.pickupName
                tvDlocation!!.text = details.dropName
                tvStartTime!!.text = details.startTime
                tvEndTime!!.text = details.dropTime
                tvBusDtl!!.text = details.busName
                tvSeatNo!!.text = details.seatNos.toString()
                tvWalletBalance!!.text = "₹ " + bookingData.walletBalance
                tvTotalFare!!.text = "₹ " + details.finalTotalFare
                totalFare = details.finalTotalFare!!.toInt()
                walletBalance = bookingData.walletBalance!!
                pnrNumber = details.pnrNo!!
                discountAmount= details.discount.toString()

                if (!discountAmount.equals("0")) {
                    showView(tvBaseDiscountFare)
                    tvBaseDiscountFare!!.text = "₹ " + details.finalTotalFare
                    tvBaseDiscountFare!!.paintFlags = tvBaseDiscountFare!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                    try {
                        val amount = details.finalTotalFare.toInt() - details.discount!!.toInt()
                        tvTotalFare!!.text = "₹ $amount"
                        totalFare = amount
                        if (totalFare==0) {
                            checkboxWallet!!.isEnabled=false
                            paymentMode=FREE
                        }
                    } catch (e: Exception) {
                        Log.i(TAG, "showData: discount Error=${e.localizedMessage}")
                    }

                    if (!promoCode.equals("")) {
                        hideView(layApplyCode)
                        showView(layRemoveCode)
                        tvPromoCode!!.text = "Applied : $promoCode"
                    }
                } else {
                    paymentMode=RAZPAY
                    checkboxWallet!!.isEnabled=true
                    hideView(tvBaseDiscountFare)
                    if (!promoCode.equals("")) {
                        toast(this, "Promo Code is not valid.")
                        promoCode=""
                        edPromoCode!!.setText("")
                    }
                }

            }
        } catch (e: Exception) {
            mylog(TAG, "showData: Error=${e.localizedMessage}")
        }

    }


    override fun onPaymentSuccess(p0: String?, paymentData: PaymentData?) {
        Log.i(TAG, "onPaymentSuccess: Response =${Gson().toJson(paymentData)}")
        verifyPayment(paymentData!!, true)
    }

    override fun onPaymentError(p0: Int, p1: String?, paymentData: PaymentData?) {
        Log.i(TAG, "onPaymentError: Response=$p1")
        Log.i(TAG, "onPaymentError: Response =${Gson().toJson(paymentData)}")
        verifyPayment(paymentData!!, false)
    }

    private fun verifyPayment(paymentData: PaymentData, isSuccess: Boolean) {
        try {
            if (paymentData != null) {

                if (isNetworkAvailable(this)) {
                    LoadingDialog.showLoadingDialog(this, "Verifying Payment...")

                    if (!COMING_FROM.equals("Pass")) {
                        RetrofitClient.getClient().verifyBookingPayment(
                            getPreference(this, Constants.TOKEN),
                            paymentData!!.paymentId,
                            paymentData!!.orderId,
                            paymentData!!.signature,
                            isSuccess
                        )
                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                                override fun onSuccess(response: DefaultResponse) {
                                    LoadingDialog.cancelLoading()
                                    mylog(TAG, "verifyPayment: Response=${Gson().toJson(response)}")
                                    successFailureDialog(
                                        this@ConfirmPaymentActivity,
                                        response.isStatus!!,
                                        response.message
                                    )
                                    Checkout.clearUserData(applicationContext)
                                }

                                override fun onError(e: Throwable) {
                                    mylog(TAG, "onError: verifyPayment=" + e.localizedMessage)
                                    LoadingDialog.cancelLoading()
                                    toast(this@ConfirmPaymentActivity, e.localizedMessage)
//                                    checkToken(applicationContext, ApiCallBack { success -> if (success) verifyPayment() })
                                }
                            })
                    } else {
                        RetrofitClient.getClient().verifyPassPayment(
                            getPreference(this, Constants.TOKEN),
                            paymentData!!.paymentId,
                            paymentData!!.orderId,
                            paymentData!!.signature,
                            isSuccess
                        )
                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                                override fun onSuccess(response: DefaultResponse) {
                                    LoadingDialog.cancelLoading()
                                    mylog(
                                        TAG,
                                        "verifyPassPayment: Response=${Gson().toJson(response)}"
                                    )
                                    successFailureDialog(
                                        this@ConfirmPaymentActivity,
                                        response.isStatus!!,
                                        response.message
                                    )
                                    Checkout.clearUserData(applicationContext)
                                }

                                override fun onError(e: Throwable) {
                                    mylog(TAG, "onError: verifyPassPayment=" + e.localizedMessage)
                                    LoadingDialog.cancelLoading()
                                    toast(this@ConfirmPaymentActivity, e.localizedMessage)
//                                    checkToken(applicationContext, ApiCallBack { success -> if (success) verifyPayment() })
                                }
                            })
                    }


                } else toast(this, getString(R.string.txt_Network))

            } else successFailureDialog(this@ConfirmPaymentActivity, false, "Something went wrong.")
        } catch (e: Exception) {
            mylog(TAG, "verifyPayment: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }

    }

    fun successFailureDialog(context: Context?, isSuccess: Boolean?, msg: String) {
        try {

            val dialog = Dialog(context!!)
            if (isSuccess!!) dialog.setContentView(R.layout.dialog_success)
            else dialog.setContentView(R.layout.dialog_failure)
            dialog.setCancelable(true)
            dialog.window?.setBackgroundDrawable(ColorDrawable(0))
            dialog.findViewById<TextView>(R.id.tvMsg).text = msg
            dialog.findViewById<View>(R.id.btnClose).setOnClickListener {
                if (isSuccess) {
                    val i = Intent(this, DashboardActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    i.putExtra("openBookingList", "YES")
                    startActivity(i)
                }

                if (dialog.isShowing) dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()

        } catch (e: Exception) {
            mylog(TAG, "successFailureDialog: Error=${e.localizedMessage}")
        }
    }

}