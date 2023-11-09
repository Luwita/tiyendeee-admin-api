package com.ferri.userapp.utils.menu;

import com.ferri.userapp.model.BusSeatsItem;
import com.ferri.userapp.model.SeatModel;

import java.util.List;

public abstract class AbstractItem {

    /*variable declaration*/

    public static final int TYPE_CENTER = 0;
    public static final int TYPE_EDGE = 1;
    public static final int TYPE_EMPTY = 2;
    public List<SeatModel> mSeatModelList;
    public List<BusSeatsItem> mBusSeatsItem;
    private String mLabel;

    /*constructor*/

    public AbstractItem(String aLabel) {
        this.mLabel = aLabel;
    }

    public AbstractItem(List<SeatModel> aSeatModelList) {
        this.mSeatModelList =aSeatModelList;
    }
    public AbstractItem(List<SeatModel> aSeatModelList,List<BusSeatsItem> mBusSeatsItem) {
        this.mSeatModelList =aSeatModelList;
        this.mBusSeatsItem =mBusSeatsItem;
    }

    /*getter*/

    public String getmLabel() {
        return mLabel;
    }

    abstract public int getType();

}
