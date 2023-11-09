package com.ferri.userapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.model.EdgeItem
import com.ferri.userapp.model.EmptyItem
import com.ferri.userapp.model.SeatModel
import com.ferri.userapp.model.SeatType
import com.ferri.userapp.ui.adapter.SeatAdapter
import com.ferri.userapp.ui.adapter.SleeperAdapter
import com.ferri.userapp.utils.Constants
import com.ferri.userapp.utils.Constants.IsCheckedOffice
import com.ferri.userapp.utils.goHome
import com.ferri.userapp.utils.isPreference
import com.ferri.userapp.utils.menu.AbstractItem
import com.ferri.userapp.utils.menu.CenterItem
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class OldBookingActivity : BaseActivity(), View.OnClickListener {
    private var mSeatNo = 0
    private var mSeatNoSleeper = 0
    private var mRvViewSeats: RecyclerView? = null
    private var mIvAvailable: ImageView? = null
    private var mIcBook: ImageView? = null
    private var mIcSelect: ImageView? = null
    private var mIvLadies: ImageView? = null
    private var mIvHome: ImageView? = null
    private var mLinear: LinearLayout? = null
    private var mLlDack: LinearLayout? = null
    private var mLlDynamic: LinearLayout? = null
    private var paymentProcessDialog: BottomSheetDialog? = null
    private var mAbstractItemsList: MutableList<AbstractItem>? = null
    private var mSeatModelsItemsList: MutableList<SeatModel>? = null
    private var mBtnBook: Button? = null
    private var mTvLower: TextView? = null
    private var mTvUpper: TextView? = null
    private var mIvBack: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        initLayouts()
        initializeLayouts()
        initializeSeats()
    }

    /* initialize seats */
    private fun initializeSeats() {
        if (intent.getStringExtra(Constants.intentdata.TYPECOACH)?.contains(getString(R.string.text_sleeper))!!) {
            showView(mLlDack)
            mIvAvailable!!.setImageDrawable(resources.getDrawable(R.drawable.ic_sleeper))
            mIcBook!!.setImageDrawable(resources.getDrawable(R.drawable.ic_sleeper))
            mIcSelect!!.setImageDrawable(resources.getDrawable(R.drawable.ic_sleeper))
            mIvLadies!!.setImageDrawable(resources.getDrawable(R.drawable.ic_sleeper))
            mIvAvailable!!.setColorFilter(ContextCompat.getColor(this, R.color.view_color))
            mIcBook!!.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray))
            mIcSelect!!.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
            mIvLadies!!.setColorFilter(ContextCompat.getColor(this, R.color.pink))
            val mSleeperColumns = 4
            for (i in 0..27) {
                mSeatModelsItemsList!!.add(SeatModel(SeatType.EMPTY))
                mSeatModelsItemsList!!.add(SeatModel(SeatType.EMPTY))
                mSeatModelsItemsList!!.add(SeatModel(SeatType.EMPTY))
                mSeatModelsItemsList!!.add(SeatModel(SeatType.EMPTY))
                mSeatModelsItemsList!!.add(SeatModel(SeatType.BOOKED))
                mSeatModelsItemsList!!.add(SeatModel(SeatType.LADIES))
                if (i % mSleeperColumns == 0 || i % mSleeperColumns == 3) {
                    mSeatNoSleeper++
                    mAbstractItemsList!!.add(EdgeItem(mSeatNoSleeper.toString()))
                } else if (i % mSleeperColumns == 2) {
                    mSeatNoSleeper++
                    mAbstractItemsList!!.add(CenterItem(mSeatNoSleeper.toString()))
                } else {
                    mAbstractItemsList!!.add(EmptyItem(mSeatModelsItemsList))
                }
            }
            val mManager1 = GridLayoutManager(this, mSleeperColumns)
            mRvViewSeats!!.layoutManager = mManager1
            val adapter = SleeperAdapter({ count, label ->
                if (count == 0) {
                    hideView(mLinear)
                } else {
                    mCountSeat = count
                    showView(mLinear)
                    mSb!!.append("$label ")
                    val mTvTotalPrice = findViewById<TextView>(R.id.txtTicketPrice)
                    mTvTotalPrice.text = String.format("%s%s", getString(R.string.rs), (intent.getStringExtra(Constants.intentdata.PRICE)!!.toInt() * count).toString())
                    mTotal = intent.getStringExtra(Constants.intentdata.PRICE)!!.toInt() * count + 5
                    (findViewById<View>(R.id.tvTotal) as TextView).text = String.format("%s%s", getString(R.string.rs), (intent.getStringExtra(Constants.intentdata.PRICE)!!.toInt() * count + 5).toString())
                }
            }, mAbstractItemsList, this, mSeatModelsItemsList)
            mRvViewSeats!!.adapter = adapter
        } else {
            mIvAvailable!!.setColorFilter(ContextCompat.getColor(this, R.color.view_color))
            mIcBook!!.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray))
            mIcSelect!!.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
            mIvLadies!!.setColorFilter(ContextCompat.getColor(this, R.color.pink))
            for (i in 0..39) {
                mSeatModelsItemsList!!.add(SeatModel(SeatType.EMPTY))
                mSeatModelsItemsList!!.add(SeatModel(SeatType.LADIES))
                mSeatModelsItemsList!!.add(SeatModel(SeatType.BOOKED))
                mSeatModelsItemsList!!.add(SeatModel(SeatType.EMPTY))
                val mCOLUMNS = 5
                if (i % mCOLUMNS == 0 || i % mCOLUMNS == 4) {
                    mSeatNo++
                    mAbstractItemsList!!.add(EdgeItem(mSeatNo.toString()))
                } else if (i % mCOLUMNS == 1 || i % mCOLUMNS == 3) {
                    mSeatNo++
                    mAbstractItemsList!!.add(CenterItem(mSeatNo.toString()))
                } else {
                    mAbstractItemsList!!.add(EmptyItem(mSeatModelsItemsList))
                }
                val mManager = GridLayoutManager(this, mCOLUMNS)
                mRvViewSeats!!.layoutManager = mManager
                val adapter = SeatAdapter({ count, label ->
                    if (count == 0) {
                        hideView(mLinear)
                    } else {
                        mCountSeat = count
                        showView(mLinear)
                        mSb!!.append("$label ")
                        val mTvTotalPrice = findViewById<TextView>(R.id.txtTicketPrice)
                        mTvTotalPrice.text = String.format("%s%s", getString(R.string.rs), (intent.getStringExtra(Constants.intentdata.PRICE)!!.toInt() * count).toString())
                        mTotal = intent.getStringExtra(Constants.intentdata.PRICE)!!.toInt() * count + 5
                        (findViewById<View>(R.id.tvTotal) as TextView).text = String.format("%s%s", getString(R.string.rs), (intent.getStringExtra(Constants.intentdata.PRICE)!!.toInt() * count + 5).toString())
                    }
                }, mAbstractItemsList, this, mSeatModelsItemsList)
                mRvViewSeats!!.adapter = adapter
            }
        }
        mSeatNo = 0
        mSeatNoSleeper = 0
    }

    /* initialize */
    private fun initializeLayouts() {
        mBtnBook!!.setOnClickListener(this)
        mTvLower!!.setOnClickListener(this)
        mTvUpper!!.setOnClickListener(this)
        mIvBack!!.setOnClickListener(this)
        mBtnBook!!.stateListAnimator = null
        val mCount = intent.getStringExtra(Constants.intentdata.HOLD)!!.toInt()
        var i = 0
        while (i < mCount) {
            i++
            val view1 = layoutInflater.inflate(R.layout.layout_hold, mLlDynamic, false)
            val mIvHold = view1.findViewById<ImageView>(R.id.ivHold)
            mIvHold.setOnClickListener { showToast(getString(R.string.text_city)) }
            mLlDynamic!!.addView(view1)
        }
    }

    /* bind view ids */
    private fun initLayouts() {
        mBtnBook = findViewById(R.id.btnBook)
        mLinear = findViewById(R.id.llOffer)
        mIvAvailable = findViewById(R.id.ivAvailable)
        mIvLadies = findViewById(R.id.icLadies)
        mIcBook = findViewById(R.id.icBook)
        mIcSelect = findViewById(R.id.icSelect)
        mIvHome = findViewById(R.id.ivHome)
        mAbstractItemsList = ArrayList()
        mSeatModelsItemsList = ArrayList()
        mSb = StringBuffer()
        mRvViewSeats = findViewById(R.id.rvSeat)
        mLlDack = findViewById(R.id.lvDack)
        mTvLower = findViewById(R.id.tvLower)
        mTvUpper = findViewById(R.id.tvUpper)
        mLlDynamic = findViewById(R.id.llDynamicContent)
        mIvBack = findViewById(R.id.ivBack)
    }

    /* on click listener */
    override fun onClick(v: View) {
        if (v === mBtnBook) selectPaymentProcess()  else if (v === mTvUpper) {
            mTvUpper!!.background = resources.getDrawable(R.drawable.bg_leftswitch_select)
            mTvUpper!!.setTextColor(resources.getColor(R.color.white))
            mTvLower!!.setTextColor(resources.getColor(R.color.textheader))
            mTvLower!!.background = resources.getDrawable(R.drawable.bg_rightswitch)
        } else if (v === mTvLower) {
            mTvLower!!.background = resources.getDrawable(R.drawable.bg_rightswitch_select)
            mTvLower!!.setTextColor(resources.getColor(R.color.white))
            mTvUpper!!.setTextColor(resources.getColor(R.color.textheader))
            mTvUpper!!.background = resources.getDrawable(R.drawable.bg_leftswitch)
        } else if (v === mIvBack) onBackPressed() else if (v === mIvHome) goHome(applicationContext)
    }

    companion object {
        /*variable declaration*/
        @JvmField
        var mCountSeat = 0
        @JvmField
        var mTotal = 0
        @JvmField
        var mSb: StringBuffer? = null
    }

    private fun selectPaymentProcess() {
        val view: View = layoutInflater.inflate(R.layout.layout_payment_process_type, null)
        paymentProcessDialog = BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme)

        if (mCountSeat>1) view.findViewById<LinearLayout>(R.id.layBuyPass).visibility=View.GONE

        view.findViewById<TextView>(R.id.tvTotalFare).setText("Proceed to pay Rs "+ mTotal +" for this ride")


        view.findViewById<LinearLayout>(R.id.layPayPerRide).setOnClickListener {
            paymentProcessDialog?.dismiss()
            if (isPreference(this, IsCheckedOffice)){
                startActivity(Intent(this, ConfirmPaymentActivity::class.java))
            }else startActivity(PassengerDetailActivity::class.java)


        }

        view.findViewById<LinearLayout>(R.id.layBuyPass).setOnClickListener {
            startActivity(PassActivity::class.java)
        }

        paymentProcessDialog?.setContentView(view)
        paymentProcessDialog?.setCancelable(false)
        paymentProcessDialog?.setCanceledOnTouchOutside(true)
        paymentProcessDialog?.show()

    }
}