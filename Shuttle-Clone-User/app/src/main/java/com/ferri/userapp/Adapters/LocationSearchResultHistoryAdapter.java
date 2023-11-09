package com.ferri.userapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ferri.userapp.R;
import com.ferri.userapp.model.AddressSearchData;
import com.ferri.userapp.ui.events.ApiSearchResultEvents;
import com.ferri.userapp.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class LocationSearchResultHistoryAdapter extends RecyclerView.Adapter<LocationSearchResultHistoryAdapter.ViewHolder> {

    private static String TAG = "LocationSearchResultAdapter";

    Context mContaxt;
    List<AddressSearchData> searchResultModelList;
    String SET_RESULT_TO,SET_ADDRESS_FOR;
    Boolean isCalledFromMain;

    public LocationSearchResultHistoryAdapter(Context applicationContext, List<AddressSearchData> searchResultModelList, String SET_RESULT_TO, String SET_ADDRESS_FOR) {
        this.mContaxt = applicationContext;
        this.searchResultModelList = searchResultModelList;
        this.SET_RESULT_TO = SET_RESULT_TO;
        this.SET_ADDRESS_FOR = SET_ADDRESS_FOR;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationSearchResultHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContaxt).inflate(R.layout.search_result_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationSearchResultHistoryAdapter.ViewHolder holder, final int position) {

        try {
            if (searchResultModelList.size() != 0) {
                holder.primaryLocation_tv.setText(searchResultModelList.get(position).getName());
                holder.secondary_location_tv.setText(searchResultModelList.get(position).getSub_name());

                holder.rootLaout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EventBus.getDefault().post(new ApiSearchResultEvents(Constants.SET_ADDRESS_EVENT,searchResultModelList.get(position).getName(),searchResultModelList.get(position).getSub_name(),searchResultModelList.get(position).getLat(),
                                searchResultModelList.get(position).getLng(),SET_RESULT_TO,SET_ADDRESS_FOR));

                       /* EventBus.getDefault().post(new SearchResultEvents
                                (AppConstants.SET_ADDRESS_EVENT,searchResultModelList.get(position).getPrimaryLocation(),searchResultModelList.get(position).getSecondaryLocation(),SET_RESULT_TO,
                                        searchResultModelList.get(position).getPlaceId(),isCalledFromMain));*/
                    }
                });

            }

        } catch (Exception e) {
            Log.i(TAG, "onBindViewHolder: LocationSearchResultAdapter Error=" + e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return searchResultModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView primaryLocation_tv, secondary_location_tv;
        LinearLayout rootLaout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            primaryLocation_tv = itemView.findViewById(R.id.location_title_tv);
            secondary_location_tv = itemView.findViewById(R.id.location_desc_tv);
            rootLaout = itemView.findViewById(R.id.adpt_search_result_rootlayout);
        }
    }
}
