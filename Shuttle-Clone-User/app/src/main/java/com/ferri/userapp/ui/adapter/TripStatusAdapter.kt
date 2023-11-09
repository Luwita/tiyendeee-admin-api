package com.ferri.userapp.ui.adapter

import android.app.ActionBar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ferri.userapp.R
import com.ferri.userapp.ui.fragment.MyBookingFragment
import com.ferri.userapp.utils.clickWithThrottle

class TripStatusAdapter(val listner: MyBookingFragment, val tripStatusList: List<String>) : RecyclerView.Adapter<TripStatusAdapter.ViewHolder>()  {

    var selectedPos : Int = 0
    var row_index : Int = -1

    class ViewHolder(view : View):RecyclerView.ViewHolder(view) {
        val tvTripStatus=view.findViewById<TextView>(R.id.tvTripStatus)
        val cardView=view.findViewById<CardView>(R.id.cvTripStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.trip_status_item_adapter,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            tvTripStatus.text=tripStatusList[position]
        }

        Log.i("TripStatusAdapter", "onBindViewHolder: selectedPos=$selectedPos ,position=$position ")

        holder.cardView.setOnClickListener(View.OnClickListener {
            notifyItemChanged(selectedPos)
            selectedPos =position
            notifyItemChanged(selectedPos)
        })
        if (selectedPos === position) {

            holder.cardView.elevation = 10F
            val params = ActionBar.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10, 10, 10, 10)
            holder.cardView.layoutParams = params
            holder.cardView.setBackgroundResource(R.drawable.selected_circle_bg)
            holder.tvTripStatus.setTextColor(listner.resources.getColor(R.color.white))
            listner.selectedStatus(tripStatusList[position])
        } else {
            holder.cardView.elevation = 0F
            val params = ActionBar.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10, 10, 10, 10)
            holder.cardView.layoutParams = params
            holder.cardView.setBackgroundResource(R.drawable.circle_background)
            holder.tvTripStatus.setTextColor(listner.resources.getColor(R.color.dark_gray))
        }



    }

    override fun getItemCount(): Int {
        return tripStatusList.size
    }
}