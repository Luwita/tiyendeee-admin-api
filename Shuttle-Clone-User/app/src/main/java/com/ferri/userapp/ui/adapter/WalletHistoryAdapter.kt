package com.ferri.userapp.ui.adapter

import com.ferri.userapp.ui.adapter.WalletHistoryAdapter.WalletHistoryViewHolder
import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.ferri.userapp.R
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.model.WalletData
import java.util.*

class WalletHistoryAdapter(
    private val mContext: Context, private val mWalletHistoryList: ArrayList<WalletData>
) : RecyclerView.Adapter<WalletHistoryViewHolder>() {
    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletHistoryViewHolder {
        return WalletHistoryViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_wallet_history, null)
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder: WalletHistoryViewHolder, position: Int) {

       holder.apply {
           mTvTitle.text=mWalletHistoryList[position].title
           mTvDate.text=mWalletHistoryList[position].paymentCreated
           mTvPaymentMode.text="Mode : ${mWalletHistoryList[position].method}"
           mTvAmount.text="₹${mWalletHistoryList[position].amount}"

           if (mWalletHistoryList[position].paymentStatus=="Completed")mIvPaymentStatus.setImageResource(R.drawable.ic_checkbox_circle_line)
           else mIvPaymentStatus.setImageResource(R.drawable.ic_more_fill)

           if (mWalletHistoryList[position].type==0){
               mIvWhImg.setImageResource(R.drawable.ic_payment_received_24)
               mIvWhImg.setBackgroundTintList(getColorStateList(mContext, R.color.darkgreen))
           }else {
               mIvWhImg.setImageResource(R.drawable.ic_payment_made_24)
               mIvWhImg.setBackgroundTintList(getColorStateList(mContext, R.color.red))
           }
       }

    }

    /*item count*/
    override fun getItemCount(): Int {
        return mWalletHistoryList.size
    }

    /*view holder*/
class WalletHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvDate: TextView
        val mTvTitle: TextView
        val mTvAmount: TextView
        val mTvPaymentMode: TextView
        val mIvWhImg: ImageView
        val mIvPaymentStatus: ImageView


        init {
            mIvWhImg = itemView.findViewById(R.id.ivWhImg)
            mTvDate = itemView.findViewById(R.id.tvDate)
            mTvTitle = itemView.findViewById(R.id.tvTitle)
            mTvAmount = itemView.findViewById(R.id.tvAmount)
            mTvPaymentMode = itemView.findViewById(R.id.tvPaymentMode)
            mIvPaymentStatus = itemView.findViewById(R.id.imPaymentStatus)
        }
    }
}