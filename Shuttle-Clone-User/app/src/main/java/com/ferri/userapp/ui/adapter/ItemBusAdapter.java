package com.ferri.userapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ferri.userapp.R;
import com.ferri.userapp.model.BusModel;
import com.ferri.userapp.model.RoutesData;
import com.ferri.userapp.model.RoutesItem;
import com.ferri.userapp.ui.activity.BookingActivity;
import com.ferri.userapp.ui.activity.BusRoutesActivity;
import com.ferri.userapp.ui.activity.DroppingPointActivity;
import com.ferri.userapp.utils.Constants;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.ferri.userapp.utils.UtilMethodsKt.diffTime;
import static com.ferri.userapp.utils.UtilMethodsKt.mylog;

public class ItemBusAdapter extends RecyclerView.Adapter<ItemBusAdapter.BusitemViewHolder> {
    /*variable declaration*/
    private Context mContext;
    private List<RoutesItem> mBusList;
    private String bookingType,has_return,dayOfWeek;
    private Boolean isBusAvailable=false;

    /*constructor*/

    public ItemBusAdapter(Context aCtx, List<RoutesItem> aBusList,String bookingType,String has_return,String dayOfWeek) {
        /* initialize parameter*/
        this.mContext = aCtx;
        this.mBusList = aBusList;
        this.bookingType = bookingType;
        this.has_return = has_return;
        this.dayOfWeek = dayOfWeek;
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ItemBusAdapter.BusitemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemBusAdapter.BusitemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_available_bus, null));
    }

    /*bind viewholder*/
    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ItemBusAdapter.BusitemViewHolder holder1, int position) {
        try {

            final RoutesItem mBusModel = mBusList.get(position);
            holder1.mTvTravellerName.setText(mContext.getString(R.string.app_name));
            holder1.mTvStartTime.setText(mBusModel.getPickupStopDepartureTime());
            holder1.mTvTypeCoach.setText("AC");
            holder1.mTvEndTime.setText(mBusModel.getDropStopArrivalTime());
//            holder1.mTvTotalDuration.setText("2");
            holder1.mTvTotalDuration.setText(diffTime(mBusModel.getPickupStopDepartureTime(), mBusModel.getDropStopArrivalTime()));

//        holder1.mTvStartTimeAA.setText(mBusModel.getmStartTimeAA());
//        holder1.mTvEndTimeAA.setText(mBusModel.getmEndTimeAA());
            holder1.mTvHold.setText(String.format(mContext.getString(R.string.text_addhold), mBusModel.getTotal_of_stops()));
//        holder1.mTvPrice.setText(String.format("$ %s", mBusModel.getPrice()));
            holder1.mTvRatingBar.setText(mBusModel.getPickupStopName() + " To " + mBusModel.getDropStopName());

            if (!isRouteActive(dayOfWeek,mBusModel.getRouteBusTimetable())){
                isBusAvailable=false;
                holder1.mRelativeLayout.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
                holder1.mTvBusNotAvailable.setVisibility(View.VISIBLE);
            }else{
                holder1.mRelativeLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                holder1.mTvBusNotAvailable.setVisibility(View.GONE);
                isBusAvailable=true;}

            holder1.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isBusAvailable) {
                        Intent i = new Intent(mContext, BookingActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("routeId", mBusModel.getRouteId());
                        i.putExtra("busId", mBusModel.getRouteBusId());
                        i.putExtra("pickupId", mBusModel.getPickupStopId());
                        i.putExtra("dropId", mBusModel.getDropStopId());
                        i.putExtra("stops", mBusModel.getTotal_of_stops());
                        i.putExtra("bookingType", bookingType);
                        i.putExtra("has_return", has_return);
                        mContext.startActivity(i);
                    }
                }
            });

            holder1.mTvPrice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, BusRoutesActivity.class);
                    i.putExtra("routeId", mBusModel.getRouteId());
                    i.putExtra("pickupId", mBusModel.getPickupStopId());
                    i.putExtra("dropId", mBusModel.getDropStopId());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                }
            });
        } catch (Exception e) {
            mylog("ItemBusAdapter", "onBindViewHolder: Errrror=" + e.getLocalizedMessage());
        }
    }



    /*item count*/
    @Override
    public int getItemCount() {
        return mBusList.size();
    }

    /*view holder*/
    class BusitemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTravellerName,mTvBusNotAvailable, mTvStartTime, mTvEndTime, mTvTotalDuration, mTvHold, mTvPrice, mTvTypeCoach, mTvRatingBar, mTvStartTimeAA, mTvEndTimeAA;
        private RelativeLayout mRelativeLayout;

        BusitemViewHolder(View itemView) {
            super(itemView);
            mTvTravellerName = itemView.findViewById(R.id.tvTravellerName);
            mTvStartTime = itemView.findViewById(R.id.tvStartTime);
            mTvEndTime = itemView.findViewById(R.id.tvEndTime);
            mTvTotalDuration = itemView.findViewById(R.id.tvTotalDuration);
            mTvHold = itemView.findViewById(R.id.tvHold);
            mTvRatingBar = itemView.findViewById(R.id.tvRatingbar);
            mTvPrice = itemView.findViewById(R.id.tvPrice);
            mTvTypeCoach = itemView.findViewById(R.id.tvTypeCoach);
            mRelativeLayout = itemView.findViewById(R.id.rlMain);
            mTvStartTimeAA = itemView.findViewById(R.id.tvStartTimeAA);
            mTvEndTimeAA = itemView.findViewById(R.id.tvEndTimeAA);
            mTvBusNotAvailable = itemView.findViewById(R.id.tvBusNotAvailable);
        }

    }

    private boolean isRouteActive(String dayOfWeek, List<String> routeBusTimetable) {
        if (routeBusTimetable.contains(dayOfWeek)) {
            Log.i("ItemBusAdaptor", "checkIsDayValid: valid date="+dayOfWeek);
            return true;
        } else {
            Log.i("ItemBusAdaptor", "checkIsDayValid: invalid ="+dayOfWeek);
            return false;
        }
    }


}