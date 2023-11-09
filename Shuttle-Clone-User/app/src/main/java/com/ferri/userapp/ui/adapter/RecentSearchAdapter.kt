package com.ferri.userapp.ui.adapter


import com.ferri.userapp.Db.RecentSearchData
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.ui.adapter.RecentSearchAdapter.RecentSearchViewHolder
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.ferri.userapp.R
import android.widget.TextView

class RecentSearchAdapter/* initialize parameter*/     /*constructor*/(
    private val mContext: Context,
    private val mRecentSearchList: List<RecentSearchData>
) : RecyclerView.Adapter<RecentSearchViewHolder>() {
    /*variable declaration*/
    private var mListener: onClickListener? = null

    /*set onClick listener*/
    fun setOnClickListener(mListener: onClickListener?) {
        this.mListener = mListener
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        return RecentSearchViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_recentsearch, null)
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        val recentSearchData = mRecentSearchList[position]
        val title = "${recentSearchData.pickUpAddress}  <font color=#3A8697>To</font></b>  ${recentSearchData.dropAddress}"

        holder.apply {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mTvDestination.setText(
                    Html.fromHtml(
                        title,
                        Html.FROM_HTML_MODE_COMPACT
                    )
                );
            } else {
                mTvDestination.setText(Html.fromHtml(title))
            }
            mTvDate.text = recentSearchData.date
            mBtnBook.setOnClickListener {
                if (mListener != null) {
                    notifyDataSetChanged()
                    mListener!!.onClick(recentSearchData)
                }
            }
        }

    }

    /*item count*/
    override fun getItemCount(): Int {
        return mRecentSearchList.size
    }

    /*onclick listener interface*/
    interface onClickListener {
        fun onClick(cardModel: RecentSearchData)
    }

    /*view holder*/
    inner class RecentSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val mTvDestination: TextView
         val mTvDate: TextView
         val mBtnBook: Button

        init {
            mTvDestination = itemView.findViewById(R.id.tvDestination)
            mTvDate = itemView.findViewById(R.id.tvDate)
            mBtnBook = itemView.findViewById(R.id.btnBook)
        }
    }
}