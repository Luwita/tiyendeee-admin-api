package com.ferri.driver.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferri.driver.Adapters.PassengerListAdapter
import com.ferri.driver.R
import com.ferri.driver.Util.RunLayoutAnimation

class PassengerListFragment : Fragment() {
    companion object {
        var mTitle = "Passenger List"
    }

    var rvPassengerList: RecyclerView? = null
    var passengerListAdapter: PassengerListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_passenger_list, container, false)

        initView(view)
        setListener()

        return view
    }


    private fun initView(view: View) {
        rvPassengerList = view.findViewById(R.id.rvPassengerList)
    }

    private fun setListener() {
       /* passengerListAdapter = PassengerListAdapter(activity!!, data)
        rvPassengerList!!.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = passengerListAdapter
        }
        RunLayoutAnimation(activity,rvPassengerList!!)*/
    }


}