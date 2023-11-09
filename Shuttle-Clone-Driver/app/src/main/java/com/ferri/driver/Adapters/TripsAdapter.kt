package com.ferri.driver.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ferri.driver.Activity.ActiveRideDetailsActivity
import com.ferri.driver.Activity.BaseActivity
import com.ferri.driver.Fragments.HomeFragment
import com.ferri.driver.Model.TripsData
import com.ferri.driver.R
import com.ferri.driver.Util.currentTime

class TripsAdapter(
    val mContext: Context,
    val listener: HomeFragment,
    val tripList: TripsData?
) : RecyclerView.Adapter<TripsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cvTripsInfo = view.findViewById<CardView>(R.id.cvTripsInfo)
        val tvTripStatus = view.findViewById<TextView>(R.id.tvTripStatus)
        val tvBusCode = view.findViewById<TextView>(R.id.tvBusCode)
        val tvPassengers = view.findViewById<TextView>(R.id.tvPassengers)
        val tvCurrentTime = view.findViewById<TextView>(R.id.tvCurrentTime)
        val tvStartAt = view.findViewById<TextView>(R.id.tvStartAt)
        val tvEndAt = view.findViewById<TextView>(R.id.tvEndAt)
        val tvBusRoute = view.findViewById<TextView>(R.id.tvBusRoute)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.layout_trips_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val data = tripList!!
            tvBusCode.text = data.busModelNo

            (mContext as BaseActivity).showView(tvTripStatus)
            tvTripStatus.text = data.tripStatus


            /*if (data.tripStatus.equals("ASSIGNED")) {
                (mContext as BaseActivity).showView(tvTripStatus)
                tvTripStatus.text = data.tripStatus
            } else (mContext as BaseActivity).hideView(tvTripStatus)*/

            tvPassengers.text = data.passengerTotal
            tvBusRoute.text = data.routeName
            tvCurrentTime.text = currentTime()
            tvStartAt.text = "${data.createdTime} ,${data.createdDate}"
//            tvEndAt.text = data.ticketEndAt

            cvTripsInfo.setOnClickListener {
                val intent = Intent(mContext, ActiveRideDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("tripsData", tripList)
                intent.putExtras(bundle)
                mContext.startActivity(intent)
               /* if (data.tripStatus.equals("ASSIGNED")) {
                    val intent = Intent(mContext, ActiveRideDetailsActivity::class.java)
                    val bundle = Bundle()
                    bundle.putSerializable("tripsData", tripList)
                    intent.putExtras(bundle)
                    mContext.startActivity(intent)
                }*/
            }
        }
    }

    override fun getItemCount(): Int {
        return 1
    }
}