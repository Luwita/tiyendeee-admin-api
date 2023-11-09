package com.ferri.userapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.ui.fragment.FragmentOffers
import com.ferri.userapp.ui.fragment.HomeFragmentNewest
import com.ferri.userapp.ui.fragment.MoreFragment
import com.ferri.userapp.ui.fragment.MyBookingFragment
import com.ferri.userapp.utils.checkAndRequestPermissions
import com.ferri.userapp.utils.mylog

class DashboardActivity : BaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mTvTitle: TextView? = null
    private var mIvNotification: ImageView? = null
    private var mIvHome: ImageView? = null
    private var mIvPackages: ImageView? = null
    private var mIvBooking: ImageView? = null
    private var mIvOther: ImageView? = null
    private val mHomeFragmentNewest = HomeFragmentNewest()
    private val mFragmentOffers = FragmentOffers()
    private val mMyBookingFragment = MyBookingFragment()
    private val mMoreFragment = MoreFragment()
    private var mLlHome: LinearLayout? = null
    private var mLllPackages: LinearLayout? = null
    private var mLlBooking: LinearLayout? = null
    private var mLlMore: LinearLayout? = null

    var rootLayout: View? = null
    val TAG = "DashboardActivity"
    val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
    val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
    private var revealX = 0
    private var revealY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initLayouts()
        initializeListeners()
        setSelected(mIvHome)
        loadFragment(mHomeFragmentNewest)

        Handler().postDelayed({
            if (!checkAndRequestPermissions(this)) {
                val intent = Intent(this, PermissionActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent)
            }
        }, 4000)

        try {
            if (intent != null) {
                val openMyBookings=intent.getStringExtra("openBookingList")+""
                Log.i(TAG, "onCreate: ==$openMyBookings")
                if (openMyBookings.equals("YES")){
                    Handler().postDelayed({
                        updateUi()
                        if (!mMyBookingFragment.isVisible) {
                            mTvTitle!!.text = MyBookingFragment.mTitle
                            loadFragment(mMyBookingFragment)
                        }
                        setSelected(mIvBooking)
                        mIvBooking!!.setImageResource(R.drawable.ic_booking_fill)
                    },1000)

                }
            }

        } catch (e: Exception) {
            Log.i(TAG, "onCreate: Error=${e.localizedMessage}")
        }

        // explode animation on activity start.
        explodeAnim(savedInstanceState, intent)

    }

    /* init layout */
    @SuppressLint("ClickableViewAccessibility")
    fun initLayouts() {
        mTvTitle = findViewById(R.id.tvTitle)
        mIvNotification = findViewById(R.id.ivNotification)
        mLlHome = findViewById(R.id.llHome)
        mLllPackages = findViewById(R.id.llPackage)
        mLlBooking = findViewById(R.id.llBooking)
        mLlMore = findViewById(R.id.llMore)
        mIvHome = findViewById(R.id.ivHome)
        mIvPackages = findViewById(R.id.ivPackages)
        mIvBooking = findViewById(R.id.ivBooking)
        mIvOther = findViewById(R.id.ivMore)
        rootLayout = findViewById(R.id.rlMain)
        mTvTitle!!.setText(HomeFragmentNewest.mTitle)
    }

    /* initialize listener */
    fun initializeListeners() {
        mIvNotification!!.setOnClickListener(this)
        mLlHome!!.setOnClickListener(this)
        mLllPackages!!.setOnClickListener(this)
        mLlBooking!!.setOnClickListener(this)
        mLlMore!!.setOnClickListener(this)
        SetNotificationImage(mIvNotification)
    }

    /* set selected item in bottom navigation */
    private fun setSelected(mBarImg: ImageView?) {
        mBarImg!!.background = resources.getDrawable(R.drawable.bg_tint_icon)
    }

    /* Update UI */
    private fun updateUi() {
        mIvHome!!.setImageResource(R.drawable.ic_home)
        mIvHome!!.background = null
        mIvPackages!!.setImageResource(R.drawable.ic_package)
        mIvPackages!!.background = null
        mIvBooking!!.setImageResource(R.drawable.ic_booking)
        mIvBooking!!.background = null
        mIvOther!!.setImageResource(R.drawable.ic_fill)
        mIvOther!!.background = null
    }

    /* onBack press */
    override fun onBackPressed() {
        super.onBackPressed()
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvNotification) {
            startActivity(NotificationActivity::class.java)
            return
        }
        updateUi()
        when (v.id) {
            R.id.llHome -> {
                if (!mHomeFragmentNewest.isVisible) {
                    mTvTitle!!.text = HomeFragmentNewest.mTitle
                    loadFragment(mHomeFragmentNewest)
                }
                setSelected(mIvHome)
                mIvHome!!.setImageResource(R.drawable.ic_home_fill)
            }
            R.id.llPackage -> {
                if (!mFragmentOffers.isVisible) {
                    mTvTitle!!.text = FragmentOffers.mTitle
                    loadFragment(mFragmentOffers)
                }
                setSelected(mIvPackages)
                mIvPackages!!.setImageResource(R.drawable.ic_package_fill)
            }
            R.id.llBooking -> {
                if (!mMyBookingFragment.isVisible) {
                    mTvTitle!!.text = MyBookingFragment.mTitle
                    loadFragment(mMyBookingFragment)
                }
                setSelected(mIvBooking)
                mIvBooking!!.setImageResource(R.drawable.ic_booking_fill)
            }
            R.id.llMore -> {
                if (!mMoreFragment.isVisible) {
                    mTvTitle!!.text = MoreFragment.mTitle
                    loadFragment(mMoreFragment)
                }
                setSelected(mIvOther)
                mIvOther!!.setImageResource(R.drawable.ic_more_fill2)
            }
        }
    }

    /* get Activity result */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            101 -> {
                if (resultCode == RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    if (mHomeFragmentNewest.isVisible) {
                        mHomeFragmentNewest.ChangeDestination(result!![0])
                    } else {
                        loadFragment(mHomeFragmentNewest)
                    }
                }
            }
        }
    }

    // explode animation on activity start.
    private fun explodeAnim(savedInstanceState: Bundle?, intent: Intent) {
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)
        ) {
            rootLayout?.setVisibility(View.INVISIBLE)
            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0)
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0)
            val viewTreeObserver: ViewTreeObserver = rootLayout!!.getViewTreeObserver()
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object :
                        ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        revealActivity(revealX, revealY)
                        rootLayout!!.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                    }
                })
            }
        } else {
            rootLayout!!.setVisibility(View.VISIBLE)
        }
    }

    // explode animation.
    protected fun revealActivity(x: Int, y: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val finalRadius = (Math.max(
                    rootLayout!!.getWidth(),
                    rootLayout!!.getHeight()
            ) * 1.1f)

            // create the animator for this view (the start radius is zero)
            val circularReveal =
                    ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0f, finalRadius)
            circularReveal.duration = 800
            circularReveal.interpolator = AccelerateInterpolator()

            // make the view visible and start the animation
            rootLayout!!.setVisibility(View.VISIBLE)
            circularReveal.start()
        } else {
            finish()
        }
    }
}