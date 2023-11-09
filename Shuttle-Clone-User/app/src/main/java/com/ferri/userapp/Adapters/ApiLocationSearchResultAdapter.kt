package com.ferri.userapp.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ferri.userapp.R
import com.ferri.userapp.model.SearchedDataItem
import com.ferri.userapp.ui.fragment.SearchLocationDialogFragment

class ApiLocationSearchResultAdapter(
    var mContaxt: Context,
    var listner: SearchLocationDialogFragment
) : RecyclerView.Adapter<ApiLocationSearchResultAdapter.ViewHolder>() {

    var searchedDataItem: MutableList<SearchedDataItem> = ArrayList()
    var SET_RESULT_TO: String? = ""
    var SET_ADDRESS_FOR: String? = ""
    var TAG = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContaxt)
            .inflate(R.layout.search_result_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            searchedDataItem?.let {
                if (searchedDataItem.size > 0) {
                    holder.primaryLocation_tv.text = searchedDataItem[position].title
                    holder.secondary_location_tv.text = searchedDataItem[position].locationAddress

                    if (searchedDataItem[position].type.equals("location")) {
                        holder.search_result_icon.setBackgroundResource(R.drawable.bg_circle_shape)
                        Glide.with(mContaxt).asDrawable().load(R.drawable.ic_bus_stop_24)
                            .into(holder.search_result_icon)
                    } else {
                        holder.search_result_icon.setBackgroundResource(R.drawable.bg_circle_tint)
                        Glide.with(mContaxt).asDrawable().load(R.drawable.ic_location_24)
                            .into(holder.search_result_icon)
                    }

                    holder.rootLaout.setOnClickListener {
                            listner.setSearchResult(
                                searchedDataItem[position],
                                SET_RESULT_TO!!,
                                SET_ADDRESS_FOR
                            )
                    }
                }
            }
        } catch (e: Exception) {
            Log.i(TAG, "onBindViewHolder: LocationSearchResultAdapter Error=" + e.message)
        }
    }

    override fun getItemCount(): Int {
        val size = searchedDataItem.size
        return if (size > 0) size else 0
    }

    fun setResultTO(resultTO: String?) {
        SET_RESULT_TO = resultTO
    }

    fun setData(
        searchedDataItems: MutableList<SearchedDataItem>,
        setResultTo: String,
        setAddressFor: String
    ) {
        searchedDataItem = searchedDataItems
        SET_RESULT_TO = setResultTo
        SET_ADDRESS_FOR = setAddressFor
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var primaryLocation_tv: TextView
        var secondary_location_tv: TextView
        var search_result_icon: ImageView
        var rootLaout: LinearLayout

        init {
            primaryLocation_tv = itemView.findViewById(R.id.location_title_tv)
            secondary_location_tv = itemView.findViewById(R.id.location_desc_tv)
            search_result_icon = itemView.findViewById(R.id.search_result_icon)
            rootLaout = itemView.findViewById(R.id.adpt_search_result_rootlayout)
        }
    }

    companion object {
        private const val TAG = "LocationSearchResultAdapter"
    }

}