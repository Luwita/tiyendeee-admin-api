package com.ferri.userapp.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.RetrofitRepository.ApiCallBack
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import com.ferri.userapp.model.DefaultResponse
import com.ferri.userapp.model.InitiateData
import com.ferri.userapp.model.PaymentInitiateDataResponse
import com.ferri.userapp.model.WalletBalanceResponseModel
import com.ferri.userapp.utils.*
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.shortLinkAsync
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class WalletActivity : BaseActivity(), View.OnClickListener, PaymentResultWithDataListener {
    private val TAG = "WalletActivity"

    /*variable declaration*/
    private var mIvBack: ImageView? = null
    private var mIvFaceBook: ImageView? = null
    private var mIvWhatsapp: ImageView? = null
    private var mIvGoogle: ImageView? = null
    private var mIvTwitter: ImageView? = null
    private var mIvNotification: ImageView? = null
    private var edAmount: EditText? = null
    private var tvWalletBalance: TextView? = null
    private var tvResponse: TextView? = null
    private var mTvWalletHistory: TextView? = null
    private var btnAdd1: Button? = null
    private var btnAdd2: Button? = null
    private var btnAdd3: Button? = null
    private var btnAddMoney: Button? = null
    private var paymentData: PaymentData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        initLayouts()
        initializeListeners()

        checkWalletBalance()


    }

    private fun checkWalletBalance() {
        try {
            if (isNetworkAvailable(this)) {
                RetrofitClient.getClient().checkWalletBalance(getPreference(this, Constants.TOKEN))
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object :
                        DisposableSingleObserver<WalletBalanceResponseModel?>() {
                        override fun onSuccess(response: WalletBalanceResponseModel) {
                            mylog(TAG, "checkWalletBalance: Response=${Gson().toJson(response)}")
                            if (response.status!!) {
                                tvWalletBalance!!.text = "₹" + response.data!!.amount
                            } else {
                                toast(this@WalletActivity, response.message)
                                tvWalletBalance!!.text = "₹0.0"
                            }
                        }

                        override fun onError(e: Throwable) {
                            mylog(TAG, "onError: checkWalletBalance=" + e.localizedMessage)
                            LoadingDialog.cancelLoading()
                            if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                    Constants.Forbidden
                                )
                            )
                                checkToken(
                                    applicationContext,
                                    ApiCallBack { success -> if (success) checkWalletBalance()
                                    else sessionExpireDialog(this@WalletActivity)})
                        }
                    })
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: java.lang.Exception) {
            mylog(TAG, "checkWalletBalance: Error=" + e.localizedMessage)
        }

    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mIvFaceBook!!.setOnClickListener(this)
        mIvWhatsapp!!.setOnClickListener(this)
        mIvGoogle!!.setOnClickListener(this)
        mIvTwitter!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        btnAdd1!!.setOnClickListener(this)
        btnAdd2!!.setOnClickListener(this)
        btnAdd3!!.setOnClickListener(this)
        btnAddMoney!!.setOnClickListener(this)
        mTvWalletHistory!!.setOnClickListener(this)
        SetNotificationImage(mIvNotification)

        tvResponse!!.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Response", tvResponse!!.text.toString())
            clipboard.setPrimaryClip(clip)

            toast(this, "Response Copied")
        }
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mIvFaceBook = findViewById(R.id.ivFaceBook)
        mIvWhatsapp = findViewById(R.id.ivWhatsapp)
        mIvGoogle = findViewById(R.id.ivGoogle)
        mIvTwitter = findViewById(R.id.ivTwitter)
        mIvNotification = findViewById(R.id.ivNotification)
        edAmount = findViewById(R.id.edAmount)
        tvWalletBalance = findViewById(R.id.tvWalletBalance)
        btnAdd1 = findViewById(R.id.btnAdd1)
        btnAdd2 = findViewById(R.id.btnAdd2)
        btnAdd3 = findViewById(R.id.btnAdd3)
        btnAddMoney = findViewById(R.id.btnAddMoney)
        tvResponse = findViewById(R.id.tvResponse)
        mTvWalletHistory = findViewById(R.id.tvWalletHistory)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) {
            this!!.hideKeyboard(findViewById(R.id.rootView))
            onBackPressed()
        } else if (v === mIvFaceBook) createDynamicLink("Default") else if (v === mIvWhatsapp) createDynamicLink(
            "Whatsapp"
        ) else if (v === mIvGoogle) createDynamicLink("Default") else if (v === mIvTwitter) createDynamicLink(
            "Default"
        )
        else if (v === btnAdd1) edAmount!!.setText("100") else if (v === btnAdd2) edAmount!!.setText(
            "200"
        ) else if (v === btnAdd3) edAmount!!.setText("500") else if (v === btnAddMoney) addMoney() else if (v === mIvNotification) startActivity(
            NotificationActivity::class.java
        ) else if (v === mTvWalletHistory) startActivity(TransactionHistoryActivity::class.java)
    }

    private fun addMoney() {
        try {
            if (!edAmount!!.text.toString().isEmpty()) {

                if (edAmount!!.text.toString().trim().toInt() >= 50) {

                    if (isNetworkAvailable(this)) {
                        LoadingDialog.showLoadingDialog(this, "Loading...")
                        RetrofitClient.getClient().addMoney(
                            getPreference(this, Constants.TOKEN),
                            edAmount!!.text.toString().trim()
                        )
                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object :
                                DisposableSingleObserver<PaymentInitiateDataResponse?>() {
                                override fun onSuccess(response: PaymentInitiateDataResponse) {
                                    mylog(TAG, "addMoney: Response=${Gson().toJson(response)}")
                                    LoadingDialog.cancelLoading()
                                    if (response.status!!) {
                                        makePayment(response.data!!)
                                    } else {
                                        toast(this@WalletActivity, response.message)
                                        tvWalletBalance!!.text = "₹0.0"
                                    }
                                }

                                override fun onError(e: Throwable) {
                                    mylog(TAG, "onError: checkWalletBalance=" + e.localizedMessage)
                                    LoadingDialog.cancelLoading()
                                    if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                            Constants.Forbidden
                                        )
                                    )
                                        checkToken(
                                            applicationContext,
                                            ApiCallBack { success -> if (success) checkWalletBalance() })
                                }
                            })
                    } else toast(this, getString(R.string.txt_Network))
                } else {
                    edAmount!!.error = "You can add minimum Rs50."
                    edAmount!!.requestFocus()
                }
            } else edAmount!!.error = "Please enter Amount."
        } catch (e: Exception) {
            mylog(TAG, "addMoney: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
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
            checkout.open(this@WalletActivity, options)

        } catch (e: Exception) {
            mylog(TAG, "makePayment: Error=" + e.localizedMessage)
        }
    }


    override fun onPaymentSuccess(stringData: String?, response: PaymentData?) {
        mylog(TAG, "onPaymentSuccess: Response=$stringData")
        mylog(TAG, "onPaymentSuccess: Response=${Gson().toJson(response)}")
        mylog(TAG, "onPaymentSuccess: paymentId=${response!!.paymentId}")
        mylog(TAG, "onPaymentSuccess: orderId=${response!!.orderId}")
        mylog(TAG, "onPaymentSuccess: signature=${response!!.signature}")

        tvResponse!!.text =
            "paymentId=${response!!.paymentId} orderId=${response!!.orderId} signature=${response!!.signature}"

        verifyPayment(response, true)
    }

    private fun verifyPayment(paymentData: PaymentData, status: Boolean) {
        try {
            if (paymentData != null) {

                if (isNetworkAvailable(this)) {
                    LoadingDialog.showLoadingDialog(this, "Verifying Payment...")
                    RetrofitClient.getClient().verifyWalletAddPayment(
                        getPreference(this, Constants.TOKEN),
                        paymentData!!.paymentId,
                        paymentData!!.orderId,
                        paymentData!!.signature,
                        status
                    )
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                            override fun onSuccess(response: DefaultResponse) {
                                LoadingDialog.cancelLoading()
                                mylog(TAG, "verifyPayment: Response=${Gson().toJson(response)}")
                                if (response.isStatus!!) {
                                    checkWalletBalance()
                                    edAmount!!.setText("")
                                }
                                successFailureDialog(
                                    this@WalletActivity,
                                    response.isStatus!!,
                                    response.message
                                )
                            }

                            override fun onError(e: Throwable) {
                                mylog(TAG, "onError: verifyPayment=" + e.localizedMessage)
                                LoadingDialog.cancelLoading()
                                toast(this@WalletActivity, e.localizedMessage)
                                if (e.localizedMessage.equals(Constants.Unauthorized) || e.localizedMessage.equals(
                                        Constants.Forbidden
                                    )
                                )
                                    checkToken(
                                        applicationContext,
                                        ApiCallBack { success ->
                                            if (success) verifyPayment(
                                                paymentData,
                                                status
                                            )
                                        })
                            }
                        })
                } else toast(this, getString(R.string.txt_Network))

            } else successFailureDialog(this@WalletActivity, false, "Something went wrong.")
        } catch (e: Exception) {
            mylog(TAG, "verifyPayment: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }

    }

    override fun onPaymentError(p0: Int, stringData: String?, response: PaymentData?) {
        mylog(TAG, "onPaymentError: Response=$p0")
        mylog(TAG, "onPaymentError: Response=$stringData")
        mylog(TAG, "onPaymentError: Response=${Gson().toJson(response)}")
        verifyPayment(response!!, false)
    }

    private fun createDynamicLink(shareOn: String) {

        vibratePhone(this)

        val sharelink = "https://ferriuser.page.link/?" +
                "link=http://shuttle.theferri.com?referral=" + getPreference(
            this,
            Constants.REFERRAL_CODE
        ) +
                "&apn=" + packageName +
                "&st=" + "User Share & Earn" +
                "&sd=" + "User joins, your earn 20, Rider joins, your earn 20" +
                "&si=" + "http://shuttle.theferri.com/uploads/ferri-shuttle.png"


        Firebase.dynamicLinks.shortLinkAsync {
            longLink = Uri.parse(sharelink)
        }.addOnSuccessListener { result ->
            // Short link created
            val shortLink = result.shortLink
            val flowchartLink = result.previewLink
            Log.d(TAG, "shortLink: " + shortLink)

            Log.i(TAG, "createDynamicLink: shortLink=$shortLink")
            Log.i(TAG, "createDynamicLink: flowchartLink=$flowchartLink")
            shareInviteLink(shortLink.toString(), shareOn)

        }.addOnFailureListener {
            Log.d(
                TAG,
                "createDynamicLink: something went wrong in shortLinkTask create error=${it.localizedMessage}"
            )
            shareInviteLink("", shareOn)
        }

        /* FirebaseDynamicLinks.getInstance().createDynamicLink()
                 .setLink(Uri.parse(sharelink))
                 .setDomainUriPrefix("ferriuser.page.link")
                 .buildShortDynamicLink()
                 .addOnCompleteListener(this) { task ->
                     if (task.isSuccessful) {
                         // Short link created
                         val shortLink = task.result.shortLink
                         val flowchartLink = task.result.previewLink
                         Log.i(TAG, "createDynamicLink: shortLink=$shortLink")
                         shareInviteLink(shortLink.toString(), shareOn)
                     } else {
                         Log.i(TAG, "createDynamicLink: error=${task.exception?.localizedMessage}")
                     }
                 }*/
    }


    fun shareInviteLink(linkUrl: String?, shareOn: String) {
        var shareMessage = ""
        try {

            shareMessage =
                "\n\nHello there, India’s first Govt fare ride is here with 100% lowest rate commute.\nI’m loving this app,\nTake a ride with Ferri today. \n\n" +
                        "(CLICK HERE & INSTALL Ferri NOW !)   \n\n" + getInvitationMessage(linkUrl)

            Log.i(TAG, "shareInviteLink: shareMessage=$shareMessage")
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            if (shareOn.equals("Whatsapp")) shareIntent.setPackage("com.whatsapp")
            val bm = BitmapFactory.decodeResource(resources, R.drawable.share_screen)
            val path = MediaStore.Images.Media.insertImage(contentResolver, bm, "", null)
            val screenshotUri = Uri.parse(path)

            shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
            shareIntent.type = "image/*"

            startActivity(Intent.createChooser(shareIntent, "Share with"))

        } catch (e: Exception) {
            Log.i(TAG, "shareInviteLink: Error=${e.localizedMessage}")
            shareWithNoImage(shareMessage, shareOn)
        }
    }

    private fun shareWithNoImage(shareMessage: String, shareOn: String) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            if (shareOn.equals("Whatsapp")) shareIntent.setPackage("com.whatsapp")
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Share with"))
        } catch (e: Exception) {
            Log.i(TAG, "shareWithNoImage: Error=${e.localizedMessage}")
        }
    }

    private fun getInvitationMessage(linkUrl: String?): Any? {

        if (linkUrl != null) {
            val playStoreLink =
                linkUrl + "\n"
            val tst = "Get benefits with this referral code: "
            if (!getPreference(this, Constants.REFERRAL_CODE).equals("")) {
                return playStoreLink + tst + getPreference(this, Constants.REFERRAL_CODE)
            }
            return playStoreLink
        } else {
            val playStoreLink =
                "https://play.google.com/store/apps/details?id=" + packageName + "\n"
            val tst = "Get benefits with this referral code: "
            if (!getPreference(this, Constants.REFERRAL_CODE).equals("")) {
                return playStoreLink + tst + getPreference(this, Constants.REFERRAL_CODE)
            }
            return playStoreLink
        }
    }


}