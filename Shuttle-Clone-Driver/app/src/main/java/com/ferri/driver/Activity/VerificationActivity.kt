package com.ferri.driver.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Debug
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ferri.driver.BuildConfig
import com.ferri.driver.Model.UserDetail
import com.ferri.driver.R
import com.ferri.driver.Util.*
import com.ferri.driver.ViewModel.MainViewModel
import java.util.concurrent.TimeUnit

class VerificationActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mEdDigit1: EditText? = null
    private var mEdDigit2: EditText? = null
    private var mEdDigit3: EditText? = null
    private var mEdDigit4: EditText? = null
    private var mLlVerify: LinearLayout? = null
    private var mTvResend: TextView? = null
    private var mTvTimer: TextView? = null
    private var tvOTP: TextView? = null
    private var mIvBack: ImageView? = null
    private lateinit var mEds: Array<EditText?>
    private var otp = ""
    private var phone = ""
    private var userDetail: UserDetail? = null
    private val TAG = "VerificationActivity"
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        initLayouts()
        initializeListeners()
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
        tvOTP = findViewById(R.id.tvOTP)

        if (intent != null) {
            otp = intent.getIntExtra("otp", 0).toString()
            phone = intent.getStringExtra("phone").toString()
            if (BuildConfig.DEBUG){
                tvOTP!!.text = "Your OTP is $otp"
                myLog(TAG, "Your OTP is $otp")
                myLog(TAG, "Your PhoneNo is $phone")
            }

        }
    }

    /* initialize listener */
    private fun initializeListeners() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
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
                if (validate()) {
                    verifyOTP(getOtp())
                }
                true
            } else false
        }

        mTvResend!!.setOnClickListener {
            reSendOTP()
        }
    }

    private fun startTimer() {
        object : CountDownTimer(6000, 1000) {
            // adjust the milli seconds here
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                mTvTimer!!.text = String.format(
                    "%d seconds left",
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(
                                    millisUntilFinished
                                )
                            )
                )
            }

            override fun onFinish() {
                hideView(mTvTimer!!)
                showView(mTvResend!!)
            }
        }.start()
    }

    private fun reSendOTP() {
        try {
            if (isInternetConnection(this)) {
                LoadingDialog.showLoadingDialog(this, "Please wait...")
                mainViewModel!!.resendOtp(getPreference(this, AppConstants.TOKEN)!!, phone.trim()).observe(this,
                    Observer {
                        LoadingDialog.cancelLoading()
                        if (it != null) {
                            if (it.isStatus) {
                                if (BuildConfig.DEBUG){
                                    toast(this, "Your Otp is ${it.otp}")
                                    tvOTP!!.text = "Your OTP is ${it.otp}"
                                }
                                startTimer()
                            }else alertDialog(this, it.message.toString())
                        } else sessionExpireDialog(this)
                    })

            } else toast(this)

        } catch (e: java.lang.Exception) {
            Log.i(TAG, "reSendOTP: Error=${e.localizedMessage}")
        }
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) {
            onBackPressed()
        }
        if (v === mLlVerify) {
            if (validate()) {
                verifyOTP(getOtp())
            }
        }
    }

    private fun verifyOTP(OTP: Int) {
        try {
            if (isInternetConnection(this)) {
                LoadingDialog.showLoadingDialog(this, "Please wait...")
                mainViewModel!!.verifyOTP(getPreference(this, AppConstants.TOKEN)!!,getPreference(this, AppConstants.DEVICE_TOKEN).toString(), OTP)
                    .observe(this, androidx.lifecycle.Observer {
                        LoadingDialog.cancelLoading()
                        if (it != null) {
                            toast(this, it.message)
                            if (it.isStatus!!) {
                                savePreference(this, AppConstants.IsDriverLogIn, true)
                                savePreference(this, AppConstants.PHONE_NO, phone)
                                if (!checkAndRequestPermissions(this)) startActivity(
                                    PermissionActivity::class.java
                                )
                                else {
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finishAffinity()
                                }

                            }else alertDialog(this,it.message.toString())
                        } else sessionExpireDialog(this)
                    })

            } else toast(this)

        } catch (e: Exception) {
            Log.i(TAG, "verifyOTP: Error=" + e.localizedMessage)
            LoadingDialog.cancelLoading()
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


    private fun getOtp(): Int {
        val otp =
            mEdDigit1!!.text.toString() + mEdDigit2!!.text.toString() + mEdDigit3!!.text.toString() + mEdDigit4!!.text.toString()
        Log.i(TAG, "getEdtOTP: otp==$otp")
        return otp.toInt()
    }

    /* back space key handler*/
    inner class PinOnKeyListener internal constructor(private val mCurrentIndex: Int) :
        View.OnKeyListener {
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
                for (editText in mEds) if (editText!!.text.toString()
                        .trim { it <= ' ' }.length == 0
                ) return false
                return true
            }

        private fun hideKeyboard() {
            if (currentFocus != null) {
                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        }

        init {
            if (mCurrentIndex == 0) mIsFirst =
                true else if (mCurrentIndex == mEds.size - 1) mIsLast = true
        }
    }
}