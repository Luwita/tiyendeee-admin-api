package com.ferri.userapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.model.BookingDataItem
import com.ferri.userapp.model.BookingDetailsItem
import com.ferri.userapp.ui.activity.BookedPassDetailsActivity
import com.ferri.userapp.ui.adapter.BookingTransactionHistoryAdapter.BookingTransactionHistoryViewHolder
import com.ferri.userapp.utils.*
import java.io.Serializable
import java.util.*

class BookingTransactionHistoryAdapter(
    private val mCtx: Context,
    private val mBookTransList: List<BookingDataItem>,
    private val mPassDetailsList: List<BookingDetailsItem>,
    private val callFrom: String,
) : RecyclerView.Adapter<BookingTransactionHistoryViewHolder>() {
    private val TAG = "BookingAdapter"
    private lateinit var bookingData:BookingDetailsItem
    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingTransactionHistoryViewHolder {
        return BookingTransactionHistoryViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.booking_trans_history_lay, null))
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder1: BookingTransactionHistoryViewHolder, position: Int) {
        try {

            if (callFrom.equals("BOOKING_DTL")){
                val bookingStatusData = mBookTransList[position]
                bookingData = bookingStatusData.bookingDetails!![0]

                (mCtx as BaseActivity).showView(holder1.mRlBookingPayment)

                holder1.mTvTitle.text=bookingStatusData.title
                holder1.mTvDate.text=bookingStatusData.paymentCreated
                holder1.mTvPaymentMode.text="Mode : ${bookingStatusData.method}"
                holder1.mTvAmount.text="₹${bookingStatusData.amount}"

                if (bookingStatusData.paymentStatus=="Completed"){
                    holder1.mIvPaymentStatus.setImageResource(R.drawable.ic_checkbox_circle_line)
                    holder1. mIvWhImg.setBackgroundTintList(
                        AppCompatResources.getColorStateList(
                            mCtx,
                            R.color.darkgreen
                        )
                    )
                }else {
                    holder1.mIvPaymentStatus.setImageResource(R.drawable.ic_more_fill)
                    holder1.mIvWhImg.setBackgroundTintList(
                        AppCompatResources.getColorStateList(
                            mCtx,
                            R.color.red
                        )
                    )
                }

                holder1.mRlShowMore.setOnClickListener {
                    if(!bookingStatusData.isPass!!){
                        (mCtx as BaseActivity).hideView(holder1.mRlShowMore)
                        mCtx.showView(holder1.mIvShowMore)
                    }else{
                        (mCtx as BaseActivity).showView(holder1.mTvViewPassesDetails)
                        mCtx.hideView(holder1.mIvShowMore)
                        mCtx.hideView(holder1.mRlShowMore)
                    }
                }


                holder1.mRlContent.setOnClickListener {
                    if(!bookingStatusData.isPass!!){
                        mCtx.showView(holder1.mRlShowMore)
                        mCtx.hideView(holder1.mIvShowMore)
                    }else{
                        mCtx.hideView(holder1.mIvShowMore)
                        mCtx.hideView(holder1.mRlShowMore)
                    }

                }

                if(bookingStatusData.isPass!!){
                    holder1.mTvBusPassesNo.text = bookingStatusData.noOfPasses.toString()
                    mCtx.showView(holder1.mLlBusPasses)
                    mCtx.showView(holder1.mTvViewPassesDetails)
                    mCtx.hideView(holder1.mIvShowMore)
                    mCtx.hideView(holder1.mTvConfirm)
                }else{
                    mCtx.hideView(holder1.mLlBusPasses)
                    mCtx.hideView(holder1.mTvViewPassesDetails)
                    mCtx.showView(holder1.mIvShowMore)
                    mCtx.showView(holder1.mTvConfirm)
                }
                mCtx.hideView(holder1.mTvDuration)
            }else{
                bookingData = mPassDetailsList!![position]
                (mCtx as BaseActivity).hideView(holder1.mRlBookingPayment)
                mCtx.hideView(holder1.mLlBusPasses)
                mCtx.hideView(holder1.mTvViewPassesDetails)
                mCtx.showView(holder1.mIvShowMore)
                mCtx.showView(holder1.mTvDuration)
                holder1.mTvDuration.text = convertDateToBeautify(bookingData.startDate!!.toString())
                holder1.mRlShowMore.setOnClickListener {
                    (mCtx as BaseActivity).hideView(holder1.mRlShowMore)
                    mCtx.showView(holder1.mIvShowMore)
                }

                holder1.mRlContent.setOnClickListener {
                    mCtx.showView(holder1.mRlShowMore)
                    mCtx.hideView(holder1.mIvShowMore)
                }
            }


            holder1.mTvViewPassesDetails.setOnClickListener {
                try {
                    vibratePhone(mCtx)
                    val intent = Intent(mCtx, BookedPassDetailsActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    val bundle = Bundle()
                    bundle.putSerializable("allData",mBookTransList as Serializable)
                    bundle.putSerializable("passData",mBookTransList[position].bookingDetails as Serializable)

                    intent.putExtras(bundle)
                    mCtx.startActivity(intent)
                } catch (e: Exception) {
                    Log.i("Booking", "onBindViewHolder: Error=${e.localizedMessage}")
                }
            }

            holder1.mTvDestination.text = "${bookingData.pickupName} To ${bookingData.dropoffTitle}"
            holder1.mTvStartTime.text = bookingData.startTime
            holder1.mTvSeatNo.text = bookingData.seatNos.toString() + ""
            holder1.mTvTotalTime.text = diffTime(bookingData.startTime!!, bookingData.dropTime!!)
            holder1.mTvEndTime.text = bookingData.dropTime
            holder1.mTvPNRNo.text = bookingData.pnrNo
            holder1.mTvBusNo.text = bookingData.busDetail?.code
            holder1.mTvBusName.text = bookingData.busDetail?.name
            holder1.mTvConfirm.text = bookingData.travelStatus

            when (bookingData.travelStatus) {
                Constants.SCHEDULED, Constants.ONBOARDED -> {
                    holder1.mTvConfirm.setTextColor(
                        mCtx.getResources().getColor(R.color.color_check)
                    )
                    holder1.mIvWhImg.setBackgroundTintList(
                        AppCompatResources.getColorStateList(
                            mCtx,
                            R.color.darkgreen
                        )
                    )
                }
                Constants.EXPIRED, Constants.CANCELLED -> {
                    holder1.mTvConfirm.setTextColor(mCtx.getResources().getColor(R.color.red))
                    holder1. mIvWhImg.setBackgroundTintList(
                        AppCompatResources.getColorStateList(
                            mCtx,
                            R.color.red
                        )
                    )
                }
                Constants.COMPLETED -> {
                    holder1.mTvConfirm.setTextColor(
                        mCtx.getResources().getColor(R.color.color_check)
                    )
                    holder1. mIvWhImg.setBackgroundTintList(
                        AppCompatResources.getColorStateList(
                            mCtx,
                            R.color.darkgreen
                        )
                    )
                }
                else->{
                    holder1.mTvConfirm.setTextColor(
                        mCtx.getResources().getColor(R.color.rating)
                    )
                    holder1. mIvWhImg.setBackgroundTintList(
                        AppCompatResources.getColorStateList(
                            mCtx,
                            R.color.rating
                        )
                    )
                }

            }




        } catch (e: Exception) {
            Log.i(TAG, "onBindViewHolder: Error=${e.localizedMessage}")
        }

    }

    /*item count*/
    override fun getItemCount(): Int {
        if (callFrom.equals("BOOKING_DTL"))
            return mBookTransList.size
        else return mPassDetailsList.size
    }

    /*view holder*/
    inner class BookingTransactionHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvDestination: TextView
        val mTvPaymentMode: TextView
        val mTvStartTime: TextView
        val mTvTotalTime: TextView
        val mTvEndTime: TextView
        val mIvWhImg: ImageView
        val mTvPNRNo: TextView
        val mTvDate: TextView
        val mTvTitle: TextView
        val mTvSeatNo: TextView
        val mTvAmount: TextView
        val mTvBusNo: TextView
        val mTvBusName: TextView
        val mRlShowMore: RelativeLayout
        val mRlContent: RelativeLayout
        val mRlBookingPayment: RelativeLayout
        val mIvShowMore: ImageView
        val mIvPaymentStatus: ImageView
        val mLlBusPasses: LinearLayout
        val mTvBusPassesNo: TextView
        val mTvViewPassesDetails: TextView
        val mTvConfirm: TextView
        val mTvDuration: TextView

        init {

            mIvWhImg = itemView.findViewById(R.id.ivWhImg)
            mTvDate = itemView.findViewById(R.id.tvDate)
            mTvTitle = itemView.findViewById(R.id.tvTitle)
            mTvAmount = itemView.findViewById(R.id.tvAmount)
            mTvPaymentMode = itemView.findViewById(R.id.tvPaymentMode)
            mIvPaymentStatus = itemView.findViewById(R.id.imPaymentStatus)
            mTvDestination = itemView.findViewById(R.id.tvDestination)
            mTvDuration = itemView.findViewById(R.id.tvDuration)
            mTvStartTime = itemView.findViewById(R.id.tvStartTime)
            mTvBusNo = itemView.findViewById(R.id.tvBusNo)
            mTvBusName = itemView.findViewById(R.id.tvBusName)
            mTvTotalTime = itemView.findViewById(R.id.tvTotalTime)
            mTvEndTime = itemView.findViewById(R.id.tvEndTime)
            mTvPNRNo = itemView.findViewById(R.id.tvPNRNo)
            mIvShowMore = itemView.findViewById(R.id.ivShowMore)
            mRlShowMore = itemView.findViewById(R.id.rlShowMore)
            mRlContent = itemView.findViewById(R.id.rlContent)
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo)
            mLlBusPasses = itemView.findViewById(R.id.ll_BusPasses)
            mTvBusPassesNo = itemView.findViewById(R.id.tvBusPasses)
            mTvViewPassesDetails = itemView.findViewById(R.id.tvViewPassesDetails)
            mTvConfirm = itemView.findViewById(R.id.tvConfirmed)
            mRlBookingPayment = itemView.findViewById(R.id.rlBookingPayment)
        }
    }
}