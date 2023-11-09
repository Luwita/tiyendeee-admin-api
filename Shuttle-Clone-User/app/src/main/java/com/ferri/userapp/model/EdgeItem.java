package com.ferri.userapp.model;

import com.ferri.userapp.utils.menu.AbstractItem;

public class EdgeItem extends AbstractItem {


    /*constructor*/
    public EdgeItem(String aValueOf) {
        super(aValueOf);
    }

    /*getter*/
    @Override
    public int getType() {
        return TYPE_EDGE;
    }

}
