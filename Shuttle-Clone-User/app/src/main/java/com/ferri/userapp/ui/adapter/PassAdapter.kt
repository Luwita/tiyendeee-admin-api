package com.ferri.userapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ferri.userapp.R
import com.ferri.userapp.model.PassesList
import com.ferri.userapp.ui.activity.PassDetailsActivity
import java.io.Serializable

class PassAdapter(
    val context: Context,
    val passesList: List<PassesList>?
) :RecyclerView.Adapter<PassAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) :RecyclerView.ViewHolder(view) {
        val tvNoOfRides=view.findViewById<TextView>(R.id.tvNoOfRides)
        val tvNoOfDays=view.findViewById<TextView>(R.id.tvNoOfDays)
        val tvFarePerRide=view.findViewById<TextView>(R.id.tvFarePerRide)
        val tvActualFarePerRide=view.findViewById<TextView>(R.id.tvActualFarePerRide)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.adp_pass_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return passesList?.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            tvActualFarePerRide.text= "₹${passesList!![position].totalFare}"
            tvNoOfRides.text= "${passesList!![position].passNoOfRides} Rides"
            tvNoOfDays.text= "${passesList!![position].passNoOfValidDays} Days validity"
            tvFarePerRide.text= "₹${passesList!![position].totalsingleFare}/ride"

            view.setOnClickListener {
                val intent=Intent(context,PassDetailsActivity::class.java)
                intent.putExtra("passDetails",passesList[position] as Serializable)
                context.startActivity(intent)
            }
        }

    }
}

