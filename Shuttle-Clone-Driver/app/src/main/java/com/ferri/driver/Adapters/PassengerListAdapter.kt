package com.ferri.driver.Adapters

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ferri.driver.Activity.BaseActivity
import com.ferri.driver.Activity.StopsPassengerListActivity
import com.ferri.driver.Model.DataItem
import com.ferri.driver.R

class PassengerListAdapter(
    val mContext: Context,
    val data: List<DataItem?>?,
    val listner: StopsPassengerListActivity
) :
    RecyclerView.Adapter<PassengerListAdapter.ViewHolder>() {

    val TAG="PassengerListAdapter"

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val layPassengerItem = view.findViewById<LinearLayout>(R.id.layPassengerItem)
        val tvPassengerName = view.findViewById<TextView>(R.id.tvPassengerName)
        val tvSeatNo = view.findViewById<TextView>(R.id.tvSeatNo)
        val imgPassengerStatus = view.findViewById<ImageView>(R.id.imgPassengerStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.layout_passenger_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            try {
                val passengerdata = data!!.get(position)
                tvPassengerName.text = passengerdata!!.passengerdetails!![0].fullname

                var seats=""
                if(passengerdata!!.passengerdetails?.size!=0){
                    for (i in passengerdata.passengerdetails!!){
                        seats+=i.seat+" "
                    }
                }
                 tvSeatNo.text = seats

                if (passengerdata.travelStatus.equals("ONBOARDED"))
                    imgPassengerStatus.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.color_check
                        )
                    )
                else imgPassengerStatus.setBackgroundColor(
                    ContextCompat.getColor(
                        mContext,
                        R.color.dark_gray
                    )
                )

                layPassengerItem.setOnClickListener {
                    makeCall(passengerdata!!.passengerdetails!![0].customerPhone)
                }
            }catch (e:Exception){
                Log.i(TAG, "onBindViewHolder: Error=${e.localizedMessage}")}

        }

    }

    private fun makeCall(customerPhone: String?) {
        if (ActivityCompat.checkSelfPermission(
                listner,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                listner,
                arrayOf(
                    Manifest.permission.CALL_PHONE
                ),
                1
            )

        } else {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$customerPhone")

            listner.startActivity(callIntent)
        }
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}