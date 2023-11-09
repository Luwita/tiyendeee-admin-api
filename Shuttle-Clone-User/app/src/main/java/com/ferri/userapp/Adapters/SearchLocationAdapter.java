package com.ferri.userapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.ferri.userapp.R;
import com.ferri.userapp.model.SearchedDataItem;

import java.util.List;

public class SearchLocationAdapter extends RecyclerView.Adapter<SearchLocationAdapter.ViewHolder> {

    private Context mContext;
    private  List<SearchedDataItem> searchedDataItems;

    public SearchLocationAdapter(Context mContext, List<SearchedDataItem> searchedDataItems) {
        this.mContext = mContext;
        this.searchedDataItems = searchedDataItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.search_result_adapter_layout,parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(searchedDataItems.get(position).getTitle());
        holder.tvAddress.setText(searchedDataItems.get(position).getLocationAddress());
    }

    @Override
    public int getItemCount() {
        return searchedDataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle,tvAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.location_title_tv);
            tvAddress=itemView.findViewById(R.id.location_desc_tv);
        }
    }
}
