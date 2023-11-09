package com.ferri.driver.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ferri.driver.Activity.StopsPassengerListActivity
import com.ferri.driver.Activity.ActiveRideDetailsActivity
import com.ferri.driver.Model.StopsList
import com.ferri.driver.Model.TripsData
import com.ferri.driver.R

class BusStopsAdapter(
    val mContext: Context,
    val listner: ActiveRideDetailsActivity,
    val tripsData: TripsData
) : RecyclerView.Adapter<BusStopsAdapter.ViewHolder>() {

    val stops = tripsData.stops

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cvBusStops = view.findViewById<CardView>(R.id.cvBusStops)
        val tvStopName = view.findViewById<TextView>(R.id.tvStopName)
        val tvStopTiming = view.findViewById<TextView>(R.id.tvStopTiming)
        val tvNoOfPassenger = view.findViewById<TextView>(R.id.tvNoOfPassenger)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_bus_stops_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val data = stops!!.get(position)
            tvStopName.text = data.name
            tvNoOfPassenger.text = data.passengerCount

            var stopTime=""

            if (position==0)stopTime= data.departureTime.toString()
            else stopTime= data.arrivalTime.toString()

            tvStopTiming.text = stopTime
            cvBusStops.setOnClickListener {
                try {
                    val intent = Intent(mContext, StopsPassengerListActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("stopId", stops!!.get(position).id)
                    intent.putExtra("routeId", tripsData.routeId)
                    intent.putExtra("bookingDate", tripsData.createdDate)
                    intent.putExtra("stopName", data.name)
                    intent.putExtra("stopTime", stopTime)
                    intent.putExtra("passengers", data.passengerCount)
                    mContext.startActivity(intent)
                }catch (e:Exception){
                    Log.i("BusStopsAdpt", "onBindViewHolder: Error=${e.localizedMessage}")}

            }
        }

    }

    override fun getItemCount(): Int {
        return stops!!.size
    }
}