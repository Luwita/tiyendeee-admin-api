package com.ferri.driver.Activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.ferri.driver.R
import com.ferri.driver.Util.AppConstants
import com.ferri.driver.Util.savePreference

class SelectionActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mBtnContinue: Button? = null
    private var btnSoftUiContinue: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_selection)
        initLayouts()
        initializeListeners()
    }

    /* init layout */
    private fun initLayouts() {
        mBtnContinue = findViewById(R.id.btnContinue)
        btnSoftUiContinue = findViewById(R.id.btnSoftUiContinue)
    }

    /* initialize listener */
    @SuppressLint("ClickableViewAccessibility")
    private fun initializeListeners() {
        mBtnContinue!!.setOnClickListener(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBtnContinue!!.stateListAnimator = null
            btnSoftUiContinue!!.stateListAnimator = null
        }
        btnSoftUiContinue!!.setOnClickListener(this)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mBtnContinue) {
            startActivity(SignInActivity::class.java)
            savePreference(this@SelectionActivity, AppConstants.FirstTimeUser, "NO")
            finish()
        }
        if (v === btnSoftUiContinue) {
            /*startActivity(QIBusSoftUISoftUISplashActivity.class);
            finish();*/
        }
    }
}