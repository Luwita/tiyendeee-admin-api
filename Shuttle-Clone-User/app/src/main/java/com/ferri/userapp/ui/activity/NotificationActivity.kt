package com.ferri.userapp.ui.activity

import com.ferri.userapp.BaseActivity
import com.ferri.userapp.ui.adapter.NotificationAdapter
import android.widget.TextView
import com.ferri.userapp.model.BookingModel
import android.widget.RelativeLayout
import android.os.Bundle
import com.ferri.userapp.R
import com.ferri.userapp.utils.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View.OnTouchListener
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NotificationActivity : BaseActivity(), View.OnClickListener,
    NotificationAdapter.onClickListener {
    /*variable declaration*/
    private var mTvDestination: TextView? = null
    private var mTvDuration: TextView? = null
    private var mTvStartTime: TextView? = null
    private var mTvTotalTime: TextView? = null
    private var mTvEndTime: TextView? = null
    private var mTvTicketNo: TextView? = null
    private var mTvPNRNo: TextView? = null
    private var mTvTotalFare: TextView? = null
    private var mTvSeatNo: TextView? = null
    private var mRvNotification: RecyclerView? = null
    private var mNotificationList: MutableList<BookingModel>? = null
    private var mIvBack: ImageView? = null
    private var mIvClose: ImageView? = null
    private val mNotificationAdapter: NotificationAdapter? = null
    private var mRlMain: RelativeLayout? = null
    private var mDialog: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)


        initLayouts()
        initializeLayouts()
        enableSwipeToDeleteAndUndo()

    }



    /* init layout */
    private fun initLayouts() {
        mRvNotification = findViewById(R.id.rvNotification)
        mIvBack = findViewById(R.id.ivBack)
        mRlMain = findViewById(R.id.rlMain)
    }

    /* initialize listener */
    private fun initializeLayouts() {
        mIvBack!!.setOnClickListener(this)
        mNotificationList = ArrayList()
        mRvNotification!!.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        mNotificationList!!.add(
            BookingModel(
                getString(R.string.lbl_DelhiToMubai),
                getString(R.string.lbl_booking_duration1),
                getString(R.string.lbl_may),
                getString(R.string.lbl_booking_starttime1),
                getString(R.string.lbl_booking_totaltime1),
                getString(R.string.lbl_booking_endtime1),
                getString(R.string.lbl_booking_SeatNo1),
                getString(R.string.lbl_booking_passengername1),
                getString(R.string.lbl_booking_ticketno1),
                getString(R.string.lbl_booking_pnr1),
                getString(R.string.lbl_booking_totalfare1),
                getString(R.string.text_confirmed)
            )
        )
        mNotificationList!!.add(
            BookingModel(
                getString(R.string.lbl_MumbaiToPune),
                getString(R.string.lbl_booking_duration2),
                getString(R.string.lbl_may),
                getString(R.string.lbl_booking_starttime2),
                getString(R.string.lbl_booking_totaltime1),
                getString(R.string.lbl_booking_endtime2),
                getString(R.string.lbl_booking_SeatNo1),
                getString(R.string.lbl_booking_passengername1),
                getString(R.string.lbl_booking_ticketno2),
                getString(R.string.lbl_booking_pnr21),
                getString(R.string.lbl_booking_totalfare2),
                getString(R.string.text_confirmed)
            )
        )

        /*  mNotificationAdapter = new NotificationAdapter(this, mNotificationList);
        mRvNotification.setAdapter(mNotificationAdapter);
        mNotificationAdapter.setOnClickListener(this);
        RunLayoutAnimation(mRvNotification);*/
    }

    /* swipe to delete & undo */
    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback: SwipeToDeleteCallback = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val notificationModel: BookingModel
                notificationModel = mNotificationAdapter!!.data[position]
                mNotificationAdapter.removeItem(position)
                val snackbar =
                    Snackbar.make(mRlMain!!, getString(R.string.text_remove), Snackbar.LENGTH_LONG)
                snackbar.setAction(getString(R.string.text_undo)) {
                    mNotificationAdapter.restoreItem(notificationModel, position)
                    mRvNotification!!.scrollToPosition(position)
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(mRvNotification)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressed()
    }

    /* onClick listener & open mDialog*/
    @SuppressLint("ClickableViewAccessibility")
    override fun onClick(notificationModel: BookingModel) {
        val dialogView = View.inflate(this, R.layout.layout_notification, null)
        mDialog = Dialog(this)
        mDialog!!.setContentView(dialogView)
        mDialog!!.setCancelable(true)
        Objects.requireNonNull(mDialog!!.window)!!.setBackgroundDrawable(ColorDrawable(0))
        mDialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
        mTvDestination = mDialog!!.findViewById(R.id.tvDestination)
        mTvDuration = mDialog!!.findViewById(R.id.tvDuration)
        mTvStartTime = mDialog!!.findViewById(R.id.tvStartTime)
        mTvTotalTime = mDialog!!.findViewById(R.id.tvTotalTime)
        mTvEndTime = mDialog!!.findViewById(R.id.tvEndTime)
        mTvTicketNo = mDialog!!.findViewById(R.id.tvPassengersNo)
        mTvPNRNo = mDialog!!.findViewById(R.id.tvPNRNo)
        mTvTotalFare = mDialog!!.findViewById(R.id.tvTotalFare)
        mIvClose = mDialog!!.findViewById(R.id.ivClose)
        mTvSeatNo = mDialog!!.findViewById(R.id.tvSeatNo)
        mTvDestination!!.setText(notificationModel.destination)
        mTvDuration!!.setText(
            String.format(
                "%s %s",
                notificationModel.duration,
                notificationModel.month
            )
        )
        mTvStartTime!!.setText(notificationModel.startTime)
        mTvSeatNo!!.setText(notificationModel.seatNo)
        mTvTotalTime!!.setText(
            String.format(
                "%s %s",
                notificationModel.totalTime,
                this.getString(R.string.text_hour)
            )
        )
        mTvEndTime!!.setText(notificationModel.endTime)
        mTvTicketNo!!.setText(notificationModel.ticketNo)
        mTvPNRNo!!.setText(notificationModel.pnrNo)
        mTvTotalFare!!.setText(
            String.format(
                "%s%s",
                this.getString(R.string.rs),
                notificationModel.totalFare
            )
        )
        mIvClose!!.setOnTouchListener(OnTouchListener { v, event ->
            mDialog!!.hide()
            false
        })
        mDialog!!.show()
    }

    /* onBackPressed*/
    override fun onBackPressed() {
        if (mDialog != null && mDialog!!.isShowing) {
            mDialog!!.dismiss()
            mDialog = null
        }
        super.onBackPressed()
    }
}