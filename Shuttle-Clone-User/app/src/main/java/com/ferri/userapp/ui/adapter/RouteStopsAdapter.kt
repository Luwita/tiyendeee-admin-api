package com.ferri.userapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.R
import com.ferri.userapp.model.StopsList
import com.ferri.userapp.ui.activity.ExploreActivity

class RouteStopsAdapter(val context: ExploreActivity, val stopsData: List<StopsList?>?) : RecyclerView.Adapter<RouteStopsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStopTitle=view.findViewById<TextView>(R.id.tvStopTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view :View=LayoutInflater.from(context).inflate(R.layout.layout_stops_adapt, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvStopTitle.text=stopsData!!.get(position)!!.name
        }

    }

    override fun getItemCount(): Int {
        return stopsData!!.size
    }
}