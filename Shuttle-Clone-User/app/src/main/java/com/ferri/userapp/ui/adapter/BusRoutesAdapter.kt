package com.ferri.userapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.R
import com.ferri.userapp.model.RoutesDataItem
import com.ferri.userapp.ui.activity.ExploreActivity

class BusRoutesAdapter(val context: ExploreActivity, val routesData: List<RoutesDataItem?>?) : RecyclerView.Adapter<BusRoutesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cvBusRoutes=view.findViewById<CardView>(R.id.cvBusRoutes)
        val tvRouteTitle=view.findViewById<TextView>(R.id.tvRouteTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view :View=LayoutInflater.from(context).inflate(R.layout.layout_routes_adapt, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvRouteTitle.text=routesData!!.get(position)!!.routeTitle
        }

        holder.cvBusRoutes.setOnClickListener {
            context.viewRouteStops(routesData!!.get(position)!!)
        }
    }

    override fun getItemCount(): Int {
        return routesData!!.size
    }
}