package com.ferri.userapp.ui.adapter

import com.ferri.userapp.model.OffersData
import com.ferri.userapp.ui.adapter.ViewOfferAdapter.NewOfferViewHolder
import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.ferri.userapp.R
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ferri.userapp.utils.toast
import java.util.*

class ViewOfferAdapter(
    private val mContext: Context, private val mOfferList: ArrayList<OffersData>
) : RecyclerView.Adapter<NewOfferViewHolder>() {
    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOfferViewHolder {
        return NewOfferViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_viewoffer, null)
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder: NewOfferViewHolder, position: Int) {
        val code = mOfferList[position].code
        holder.mTvSpecial.text = code

        val androidColors = mContext.resources.getIntArray(R.array.androidcolors)
        val randomAndroidColor = androidColors[Random().nextInt(androidColors.size)]
        holder.mRlOfferBackground.setBackgroundColor(randomAndroidColor)

        Glide.with(mContext).load(mOfferList[position].picture).placeholder(R.drawable.loading_line_vector).into( holder.mIvImageSrc!!)

        holder.mLlOffer.setOnClickListener {
            val clipboard = mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Response", code)
            clipboard.setPrimaryClip(clip)
            toast(mContext, "Copied")
        }
    }

    /*item count*/
    override fun getItemCount(): Int {
        return mOfferList.size
    }

    /*view holder*/
class NewOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvSpecial: TextView
        val mIvImageSrc: ImageView
        val mRlOfferBackground: CardView
        val mLlOffer: LinearLayout

        init {
            mTvSpecial = itemView.findViewById(R.id.tvSpecial)
            mIvImageSrc = itemView.findViewById(R.id.ivImageSrc)
            mRlOfferBackground = itemView.findViewById(R.id.rlOfferBackground)
            mLlOffer = itemView.findViewById(R.id.llOffer)
        }
    }
}