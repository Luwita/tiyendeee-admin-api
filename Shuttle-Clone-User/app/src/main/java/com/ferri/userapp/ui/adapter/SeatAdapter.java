package com.ferri.userapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ferri.userapp.BaseActivity;
import com.ferri.userapp.R;
import com.ferri.userapp.model.SeatModel;
import com.ferri.userapp.model.SeatType;
import com.ferri.userapp.utils.Constants;
import com.ferri.userapp.utils.menu.AbstractItem;
import com.ferri.userapp.utils.menu.OnSeatSelected;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.ferri.userapp.utils.UtilMethodsKt.isPreference;
import static com.ferri.userapp.utils.UtilMethodsKt.toast;

public class SeatAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    /*variable declaration*/
    private OnSeatSelected mOnSeatSelected;
    private Context mCtx;
    private LayoutInflater mLayoutInflater;
    private List<AbstractItem> mItems;
    private List<SeatModel> mSeatItem;
    private onClickListener mListener;

    /*constructor*/
    public SeatAdapter(OnSeatSelected onSeatSelected, List<AbstractItem> aAbstractItems, Context aContext, List<SeatModel> aSeatModelList) {
        /* initialize parameter*/
        mOnSeatSelected = onSeatSelected;
        this.mCtx = aContext;
        mLayoutInflater = LayoutInflater.from(aContext);
        mItems = aAbstractItems;
        this.mSeatItem = aSeatModelList;

    }

    /*set onClick listener*/
    public void setOnClickListener(onClickListener mListener) {
        this.mListener = mListener;
    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /*item type*/
    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    /*view holder*/
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = new View(mCtx);
            return new EmptyViewHolder(itemView);
        }
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        int type = mItems.get(position).getType();

        final SeatModel seatModel = mSeatItem.get(position);

        if (type == AbstractItem.TYPE_EDGE) {

            final EdgeViewHolder holder = (EdgeViewHolder) viewHolder;

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onClick(seatModel);
                    }
                }
            });

            holder.mTvSeatNo.setText(mItems.get(position).getmLabel());
            if (seatModel.isSelected()) {
                ((BaseActivity) mCtx).showView(holder.mTvSeatNo);
            } else {
                ((BaseActivity) mCtx).invisibleView(holder.mTvSeatNo);
            }
           /* if ((seatModel.getSeatType()).equals(SeatType.EMPTY)) {

                holder.mIvSeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (isPreference(mCtx, Constants.IsCheckedOffice)) {
                            if (getSelectedItemCount() < 1) {
                                toggleSelection(position);
                                seatModel.setSelected(!seatModel.isSelected());
                                notifyItemChanged(position);
                                mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                            } else {
                                if (seatModel.isSelected()) {
                                    toggleSelection(position);
                                    seatModel.setSelected(!seatModel.isSelected());
                                    notifyItemChanged(position);
                                    mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                                } else toast(mCtx, "You are only allowed to book one seat at a time.");
                            }
                        }else {
                            if (getSelectedItemCount() < 3) {
                                toggleSelection(position);
                                seatModel.setSelected(!seatModel.isSelected());
                                notifyItemChanged(position);
                                mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                            } else {
                                if (seatModel.isSelected()) {
                                    toggleSelection(position);
                                    seatModel.setSelected(!seatModel.isSelected());
                                    notifyItemChanged(position);
                                    mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                                } else toast(mCtx, "You are only allowed to book three seat at a time.");
                            }
                        }
                    }
                });

                holder.mIvSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
            }*/

            if ((seatModel.getSeatType()).equals(SeatType.BOOKED)) {
                ((BaseActivity) mCtx).showView(holder.mIvSeatBooked);

                holder.mIvSeatBooked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast(mCtx, mCtx.getString(R.string.text_booked));
                    }
                });
            }
            if ((seatModel.getSeatType()).equals(SeatType.LADIES)) {
                ((BaseActivity) mCtx).showView(holder.mIvSeatLadies);

                    holder.mIvSeatLadies.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isPreference(mCtx, Constants.IsCheckedOffice)) {
                                if (getSelectedItemCount() < 1) {
                                    toggleSelection(position);
                                    seatModel.setSelected(!seatModel.isSelected());
                                    notifyItemChanged(position);
                                    mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                                } else {
                                    if (seatModel.isSelected()) {
                                        toggleSelection(position);
                                        seatModel.setSelected(!seatModel.isSelected());
                                        notifyItemChanged(position);
                                        mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                                    } else toast(mCtx, "You are only allowed to book one seat at a time.");
                                }
                            }else {
                                if (getSelectedItemCount() < 3) {
                                    toggleSelection(position);
                                    seatModel.setSelected(!seatModel.isSelected());
                                    notifyItemChanged(position);
                                    mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                                } else {
                                    if (seatModel.isSelected()) {
                                        toggleSelection(position);
                                        seatModel.setSelected(!seatModel.isSelected());
                                        notifyItemChanged(position);
                                        mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                                    } else toast(mCtx, "You are only allowed to book three seat at a time.");
                                }
                            }

                        }
                    });

                    holder.mIvSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);


            } else if ((seatModel.getSeatType()).equals(SeatType.EMPTY)) {
                ((BaseActivity) mCtx).hideView(holder.mIvSeatLadies);
                ((BaseActivity) mCtx).showView(holder.mIvSeat);
                holder.mIvSeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (isPreference(mCtx, Constants.IsCheckedOffice)) {
                            if (getSelectedItemCount() < 1) {
                                toggleSelection(position);
                                seatModel.setSelected(!seatModel.isSelected());
                                notifyItemChanged(position);
                                mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                            } else {
                                if (seatModel.isSelected()) {
                                    toggleSelection(position);
                                    seatModel.setSelected(!seatModel.isSelected());
                                    notifyItemChanged(position);
                                    mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                                } else toast(mCtx, "You are only allowed to book one seat at a time.");
                            }
                        }else {
                            if (getSelectedItemCount() < 3) {
                                toggleSelection(position);
                                seatModel.setSelected(!seatModel.isSelected());
                                notifyItemChanged(position);
                                mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                            } else {
                                if (seatModel.isSelected()) {
                                    toggleSelection(position);
                                    seatModel.setSelected(!seatModel.isSelected());
                                    notifyItemChanged(position);
                                    mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());
                                } else toast(mCtx, "You are only allowed to book three seat at a time.");
                            }
                        }
                    }
                });

                holder.mIvSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
            }
        }


    }

    /*onclick listener interface*/
    public interface onClickListener {

        void onClick(SeatModel seatModel);
    }

    /*view holder*/
    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIvSeatSelected;
        private ImageView mIvSeat, mIvSeatBooked, mIvSeatLadies;

        private TextView mTvSeatNo;

        EdgeViewHolder(View itemView) {
            super(itemView);
            mIvSeat = itemView.findViewById(R.id.ivSeat);
            mIvSeatSelected = itemView.findViewById(R.id.ivSeatSelected);
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo);
            mIvSeatLadies = itemView.findViewById(R.id.ivSeatLadies);
            mIvSeatBooked = itemView.findViewById(R.id.ivSeatBooked);
        }

    }


    /*view holder*/
    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }

}
