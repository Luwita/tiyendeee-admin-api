package com.ferri.driver.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ferri.driver.Activity.BusRoutesActivity
import com.ferri.driver.Model.StopsList
import com.ferri.driver.R


class ViewStopsAdapter(val context: BusRoutesActivity, val stopsData: List<StopsList?>?) :
    RecyclerView.Adapter<ViewStopsAdapter.ViewHolder>() {

    private var nextStopTitle: String? = ""

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgStopStatus = view.findViewById<ImageView>(R.id.imgStopStatus)
        val tvStopTitle = view.findViewById<TextView>(R.id.tvStopTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.stop_status_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvStopTitle.text = stopsData!!.get(position)!!.name

            imgStopStatus.setColorFilter(
                ContextCompat.getColor(context, R.color.darkgreen),
                android.graphics.PorterDuff.Mode.MULTIPLY
            )

            if (nextStopTitle.equals(stopsData!!.get(position)!!.name)) {
                tvStopTitle.setBackgroundColor(ContextCompat.getColor(context, R.color.darkgreen))
            } else {
                tvStopTitle.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent_color))
            }

            /* if (stopsData!!.get(position)!!.pickup!!)
                 imgStopStatus.setColorFilter(ContextCompat.getColor(context, R.color.darkgreen), android.graphics.PorterDuff.Mode.MULTIPLY)
             else if (stopsData!!.get(position)!!.drop!!)
                 imgStopStatus.setColorFilter(ContextCompat.getColor(context, R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY)*/
        }

    }

    override fun getItemCount(): Int {
        return stopsData!!.size
    }

    fun setNextStop(nextStopTitle: String) {
        this.nextStopTitle = nextStopTitle
    }
}