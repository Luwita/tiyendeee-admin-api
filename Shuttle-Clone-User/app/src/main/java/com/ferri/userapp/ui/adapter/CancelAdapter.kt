package com.ferri.userapp.ui.adapter

import com.ferri.userapp.model.GetbookingData
import com.ferri.userapp.ui.adapter.CancelAdapter.CancelViewHolder
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.ferri.userapp.R
import com.ferri.userapp.BaseActivity
import android.widget.TextView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.ui.fragment.MyBookingFragment
import com.ferri.userapp.utils.convertDateToBeautify
import com.ferri.userapp.utils.diffTime

class CancelAdapter(
    private val mCtx: Context,
    private val mCancelList: List<GetbookingData>,
    val listner: MyBookingFragment
) : RecyclerView.Adapter<CancelViewHolder>() {
    private val TAG = "CancelAdapter"
    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancelViewHolder {
        return CancelViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_cancel, null))
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder1: CancelViewHolder, position: Int) {
        val bookingData = mCancelList[position]
        holder1.mTvDestination.text = "${bookingData.pickupName} To ${bookingData.dropName}"
        holder1.mTvDuration.text = convertDateToBeautify(bookingData.startDate!!)
        holder1.mTvStartTime.text = bookingData.startTime
        holder1.mTvSeatNo.text = bookingData.seatNos.toString() + ""
        holder1.mTvTotalTime.text = diffTime(bookingData.startTime!!, bookingData.dropTime!!)
        holder1.mTvEndTime.text = bookingData.dropTime
        holder1.mTvPassengersNo.text = bookingData.passengers
        holder1.mTvPNRNo.text = bookingData.pnrNo

        if (!bookingData.discount.equals("0")) {
            holder1.mTvTotalFareBeforeDiscount!!.text = String.format("%s%s", mCtx.getString(R.string.rs), bookingData.finalTotalFare)
            holder1.mTvTotalFareBeforeDiscount!!.paintFlags = holder1.mTvTotalFareBeforeDiscount!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            try {
                (mCtx as BaseActivity).showView(holder1.mTvTotalFareBeforeDiscount)
                val amount = bookingData.finalTotalFare!!.toInt() - bookingData.discount!!.toInt()
                holder1.mTvTotalFare!!.text=String.format("%s%s", mCtx.getString(R.string.rs), amount)
            } catch (e: Exception) {
                Log.i(TAG, "showData: discount Error=${e.localizedMessage}")
            }
        } else {
            holder1.mTvTotalFare.text = String.format("%s%s", mCtx.getString(R.string.rs), bookingData.finalTotalFare)
        }

        holder1.mRlContent.setOnClickListener(
            View.OnClickListener {
                (mCtx as BaseActivity).showView(holder1.mRlShowMore)
                mCtx.hideView(holder1.mIVShowMore)
                mCtx.hideView(holder1.mTvCancel)
            })
        holder1.mRlShowMore.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                (mCtx as BaseActivity).showView(holder1.mIVShowMore)
                mCtx.showView(holder1.mTvCancel)
                mCtx.hideView(holder1.mRlShowMore)
                mCtx.fadeOutIn(holder1.mIVShowMore)
                mCtx.fadeOutIn(holder1.mTvCancel)
            }
        })

    }

    /*item count*/
    override fun getItemCount(): Int {
        return mCancelList.size
    }

    /*view holder*/
    inner class CancelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvDestination: TextView
        val mTvDuration: TextView
        val mTvStartTime: TextView
        val mTvTotalTime: TextView
        val mTvEndTime: TextView
        val mTvPassengersNo: TextView
        val mTvPNRNo: TextView
        val mTvTotalFare: TextView
        val mTvTotalFareBeforeDiscount: TextView
        val mTvSeatNo: TextView
        val mTvCancel: TextView
        val mRlShowMore: RelativeLayout
        val mRlContent: RelativeLayout
        val mIVShowMore: ImageView

        init {
            mTvDestination = itemView.findViewById(R.id.tvDestination)
            mTvDuration = itemView.findViewById(R.id.tvDuration)
            mTvStartTime = itemView.findViewById(R.id.tvStartTime)
            mTvTotalTime = itemView.findViewById(R.id.tvTotalTime)
            mTvEndTime = itemView.findViewById(R.id.tvEndTime)
            mTvPassengersNo = itemView.findViewById(R.id.tvPassengersNo)
            mTvPNRNo = itemView.findViewById(R.id.tvPNRNo)
            mTvTotalFareBeforeDiscount = itemView.findViewById(R.id.tvTotalFareBeforeDiscount)
            mTvTotalFare = itemView.findViewById(R.id.tvTotalFare)
            mIVShowMore = itemView.findViewById(R.id.ivShowMore)
            mRlShowMore = itemView.findViewById(R.id.rlShowMore)
            mRlContent = itemView.findViewById(R.id.rlContent)
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo)
            mTvCancel = itemView.findViewById(R.id.tvCancelled)
        }
    }
}