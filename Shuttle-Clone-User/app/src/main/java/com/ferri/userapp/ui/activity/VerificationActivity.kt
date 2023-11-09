package com.ferri.userapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.RetrofitRepository.RetrofitClient
import com.ferri.userapp.model.DefaultResponse
import com.ferri.userapp.model.UserDetail
import com.ferri.userapp.ui.activity.ProfileSettingsActivity
import com.ferri.userapp.utils.*
import com.ferri.userapp.utils.Constants.ByPassLogin
import com.ferri.userapp.utils.Receiver.SmsBroadcastReceiver
import com.google.android.gms.auth.api.phone.SmsRetriever
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern

class VerificationActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mEdDigit1: EditText? = null
    private var mEdDigit2: EditText? = null
    private var mEdDigit3: EditText? = null
    private var mEdDigit4: EditText? = null
    private var mLlVerify: LinearLayout? = null
    private var mTvResend: TextView? = null
    private var mTvTimer: TextView? = null
    private var mIvBack: ImageView? = null
    private lateinit var mEds: Array<EditText?>
    private var otp = ""
    private var userDetail: UserDetail? = null
    private val TAG = "VerificationActivity"
    var smsBroadcastReceiver: SmsBroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        initLayouts()
        initializeListeners()
        val intent = intent
        if (intent != null) {
            if (!ByPassLogin) {
                otp = intent.getIntExtra("otp", 0).toString()
                val bundle = intent.extras
                userDetail = bundle?.getSerializable("userDetail") as UserDetail
            }
//            showToast("Your OTP is $otp")
        }
        startSmsUserConsent()
    }

    private fun startSmsUserConsent() {
        val client = SmsRetriever.getClient(this)
        //We can add sender phone number or leave it blank
        // I'm adding null here
        client.startSmsUserConsent(null).addOnSuccessListener {  }.addOnFailureListener { }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_USER_CONSENT) {
            if (resultCode == RESULT_OK && data != null) {
                //That gives all message to us.
                // We need to get the code from inside with regex
                val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
//                Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                /* textViewMessage.setText( String.format("%s - %s", getString(R.string.received_message), message));*/

                getOtpFromMessage(message.toString())
            }
        }
    }

    private fun getOtpFromMessage(message: String) {
        mEdDigit1!!.setText("")
        mEdDigit2!!.setText("")
        mEdDigit3!!.setText("")
        mEdDigit4!!.setText("")

        // This will match any 4 digit number in the message
        val pattern: Pattern = Pattern.compile("(|^)\\d{4}")
        val matcher: Matcher = pattern.matcher(message)
        if (matcher.find()) {

            try {
                mEdDigit1!!.setText(matcher.group(0)[0] + "")
                mEdDigit2!!.setText(matcher.group(0)[1] + "")
                mEdDigit3!!.setText(matcher.group(0)[2] + "")
                mEdDigit4!!.setText(matcher.group(0)[3] + "")

                Log.i(TAG, "getOtpFromMessage: " + matcher.group())
                Log.i(TAG, "getOtpFromMessage: " + matcher.group(0)[0])
                Log.i(TAG, "getOtpFromMessage: " + matcher.group(0)[1])
                Log.i(TAG, "getOtpFromMessage: " + matcher.group(0)[2])
                Log.i(TAG, "getOtpFromMessage: " + matcher.group(0)[3])

//                edtEnterOTP.setText(matcher.group(0))
            } catch (e: Exception) {
                Log.i(TAG, "getOtpFromMessage: Error=" + e.localizedMessage)
            }


        }
    }

    /* init layout */
    private fun initLayouts() {
        mEdDigit1 = findViewById(R.id.edDigit1)
        mEdDigit2 = findViewById(R.id.edDigit2)
        mEdDigit3 = findViewById(R.id.edDigit3)
        mEdDigit4 = findViewById(R.id.edDigit4)
        mLlVerify = findViewById(R.id.llVerify)
        mTvResend = findViewById(R.id.tvResend)
        mTvTimer = findViewById(R.id.tvTimer)
        mEds = arrayOf(mEdDigit1, mEdDigit2, mEdDigit3, mEdDigit4)
        mIvBack = findViewById(R.id.ivBack)
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mEdDigit1!!.setOnKeyListener(PinOnKeyListener(0))
        mEdDigit2!!.setOnKeyListener(PinOnKeyListener(1))
        mEdDigit3!!.setOnKeyListener(PinOnKeyListener(2))
        mEdDigit4!!.setOnKeyListener(PinOnKeyListener(3))
        mEdDigit1!!.addTextChangedListener(CodeTextWatcher(0))
        mEdDigit2!!.addTextChangedListener(CodeTextWatcher(1))
        mEdDigit3!!.addTextChangedListener(CodeTextWatcher(2))
        mEdDigit4!!.addTextChangedListener(CodeTextWatcher(3))
        mLlVerify!!.setOnClickListener(this)
        startTimer()
        mEdDigit4!!.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                /*if (validate()) {
                        if (otp.equals(getEdtOTP())) {
                            if (flag == 1) startActivity(DashboardActivity.class);
                            else startActivity(ProfileSettingsActivity.class);
                        } else showToast("OTP didn't match.");
                    }*/
                true
            } else false
        }
        mTvResend!!.setOnClickListener { reSendOTP() }
    }

    private fun reSendOTP() {
        try {
            if (isNetworkAvailable(this)) {
                Log.i(TAG, "verifyOTP: OTP=$otp")
                LoadingDialog.showLoadingDialog(this, "Please wait...")
                RetrofitClient.getClient().resendOtp(getPreference(this, Constants.TOKEN), userDetail!!.phone.toString() + "").subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                            override fun onSuccess(defaultResponse: DefaultResponse) {
                                mylog(TAG, "onSuccess: MSG=" + defaultResponse.message)
                                mylog(TAG, "onSuccess: Status=" + defaultResponse.isStatus)
                                LoadingDialog.cancelLoading()
                                if (defaultResponse.isStatus) {
                                  //  toast(this@VerificationActivity, "Your Otp is " + defaultResponse.otp)
                                    startTimer()
                                } else toast(this@VerificationActivity, defaultResponse.message)
                            }

                            override fun onError(e: Throwable) {
                                mylog(TAG, "onError: verifyOTP Error=" + e.localizedMessage)
                                LoadingDialog.cancelLoading()
                            }
                        })
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "reSendOTP: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }

    private fun startTimer() {
        try {
            object : CountDownTimer(60000, 1000) {
                // adjust the milli seconds here
                @SuppressLint("DefaultLocale")
                override fun onTick(millisUntilFinished: Long) {
                    mTvTimer!!.text = String.format("%d seconds left",
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                }

                override fun onFinish() {
                    hideView(mTvTimer)
                    showView(mTvResend)
                }
            }.start()
        } catch (e: Exception) {
            Log.i(TAG, "startTimer: Error=" + e.localizedMessage)
        }
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) {
            onBackPressed()
        }
        if (v === mLlVerify) {
            if (validate()) {
                if (!ByPassLogin){
                   verifyOTP(edtOTP.toInt())
                }else{
                    savePreference(this@VerificationActivity, Constants.IsUserRegistered, true)
                    startActivity(DashboardActivity::class.java)
                    finishAffinity()
                }
            }
        }
    }

    private fun verifyOTP(OTP: Int) {
        try {
            if (isNetworkAvailable(this)) {
                Log.i(TAG, "verifyOTP: OTP=$otp")
                LoadingDialog.showLoadingDialog(this, "Please wait...")
                RetrofitClient.getClient().verifyUser(getPreference(this, Constants.TOKEN),getPreference(this, Constants.deviceToken), OTP).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<DefaultResponse?>() {
                            override fun onSuccess(defaultResponse: DefaultResponse) {
                                mylog(TAG, "onSuccess: MSG=" + defaultResponse.message)
                                mylog(TAG, "onSuccess: Status=" + defaultResponse.isStatus)
                                LoadingDialog.cancelLoading()
                                if (defaultResponse.isStatus) {
                                    toast(this@VerificationActivity, defaultResponse.message)
                                    checkUserDetails()
                                } else toast(this@VerificationActivity, defaultResponse.message)
                            }

                            override fun onError(e: Throwable) {
                                mylog(TAG, "onError: verifyOTP Error=" + e.localizedMessage)
                                LoadingDialog.cancelLoading()
                            }
                        })
            } else toast(this, getString(R.string.txt_Network))
        } catch (e: Exception) {
            mylog(TAG, "verifyOTP: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
        }
    }

    private fun checkUserDetails() {
        try {
            if (userDetail != null) {
                if (userDetail!!.firstname.trim { it <= ' ' } != "" && userDetail!!.lastname != "" && userDetail!!.email.trim { it <= ' ' } != "") {
                    savePreference(this@VerificationActivity, Constants.IsUserRegistered, true)
                    savePreference(this@VerificationActivity, Constants.PHONE_NO, userDetail!!.phone.toString() + "")
                    savePreference(this@VerificationActivity, Constants.EMAIL, userDetail!!.email + "")
                    savePreference(this@VerificationActivity, Constants.GANDER, userDetail!!.gender + "")
                    savePreference(this@VerificationActivity, Constants.USER_NAME, userDetail!!.firstname +" "+userDetail!!.lastname)

                    startActivity(DashboardActivity::class.java)
                    finishAffinity()
                } else {
                    savePreference(this@VerificationActivity, Constants.IsUserUpdatingFirstTime, true)
                    savePreference(this@VerificationActivity, Constants.PHONE_NO, userDetail!!.phone.toString() + "")
                    val intent = Intent(this, ProfileSettingsActivity::class.java)
                    intent.putExtra("phone", userDetail!!.phone)
                    startActivity(intent)
                }
            } else toast(this, "User Details are Null.")
        } catch (e: Exception) {
            mylog(TAG, "checkUserDetails: Errror=" + e.localizedMessage)
        }
    }

    /* Validation */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdDigit1!!.text)) {
            flag = false
            showToast(getString(R.string.msg_code))
        } else if (TextUtils.isEmpty(mEdDigit2!!.text)) {
            flag = false
            showToast(getString(R.string.msg_code))
        } else if (TextUtils.isEmpty(mEdDigit3!!.text)) {
            flag = false
            showToast(getString(R.string.msg_code))
        } else if (TextUtils.isEmpty(mEdDigit4!!.text)) {
            flag = false
            showToast(getString(R.string.msg_code))
        }
        return flag
    }

    private val edtOTP: String
        private get() {
            val otp = mEdDigit1!!.text.toString() + mEdDigit2!!.text.toString() + mEdDigit3!!.text.toString() + mEdDigit4!!.text.toString()
            mylog(TAG, "getEdtOTP: otp==$otp")
            return otp
        }

    /* back space key handler*/
    inner class PinOnKeyListener internal constructor(private val mCurrentIndex: Int) : View.OnKeyListener {
        override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (mEds[mCurrentIndex]!!.text.toString().isEmpty() && mCurrentIndex != 0) {
                    mEds[mCurrentIndex - 1]!!.requestFocus()
                }
            }
            return false
        }
    }

    /* implement TextWatcher class*/
    inner class CodeTextWatcher internal constructor(private val mCurrentIndex: Int) : TextWatcher {
        private var mIsFirst = false
        private var mIsLast = false
        private var mNewString = ""
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            mNewString = s.subSequence(start, start + count).toString().trim { it <= ' ' }
        }

        override fun afterTextChanged(s: Editable) {
            var text = mNewString
            if (text.length > 1) text = text[0].toString()
            mEds[mCurrentIndex]!!.removeTextChangedListener(this)
            mEds[mCurrentIndex]!!.setText(text)
            mEds[mCurrentIndex]!!.setSelection(text.length)
            mEds[mCurrentIndex]!!.addTextChangedListener(this)
            if (text.length == 1) moveToNext() else if (text.length == 0) moveToPrevious()
        }

        private fun moveToNext() {
            if (!mIsLast) mEds[mCurrentIndex + 1]!!.requestFocus()
            if (isAllEditTextsFilled && mIsLast) {
                mEds[mCurrentIndex]!!.clearFocus()
                hideKeyboard()
            }
        }

        private fun moveToPrevious() {
            if (!mIsFirst) mEds[mCurrentIndex - 1]!!.requestFocus()
        }

        private val isAllEditTextsFilled: Boolean
            private get() {
                for (editText in mEds) if (editText!!.text.toString().trim { it <= ' ' }.length == 0) return false
                return true
            }

        private fun hideKeyboard() {
            if (currentFocus != null) {
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        }

        init {
            if (mCurrentIndex == 0) mIsFirst = true else if (mCurrentIndex == mEds.size - 1) mIsLast = true
        }
    }


    private fun registerBroadcastReceiver() {
        smsBroadcastReceiver = SmsBroadcastReceiver()
        smsBroadcastReceiver!!.smsBroadcastReceiverListener =
                object : SmsBroadcastReceiver.SmsBroadcastReceiverListener {
                    override fun onSuccess(intent: Intent) {
                        startActivityForResult(intent, REQ_USER_CONSENT)
                    }

                    override fun onFailure() {}
                }
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(smsBroadcastReceiver, intentFilter)
    }


    override fun onStart() {
        super.onStart()
        registerBroadcastReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(smsBroadcastReceiver)
    }

    companion object {
        private const val REQ_USER_CONSENT = 200
    }
}