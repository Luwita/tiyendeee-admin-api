package com.ferri.userapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.ferri.userapp.BaseActivity;
import com.ferri.userapp.R;
import com.ferri.userapp.model.OffersData;
import com.ferri.userapp.ui.adapter.NewOfferAdapter;
import com.ferri.userapp.ui.adapter.ViewOfferAdapter;
import com.ferri.userapp.utils.Constants;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OffersActivity extends BaseActivity implements View.OnClickListener {
    /*variable declaration*/
    private RecyclerView mRvOffer;
    private ArrayList<OffersData> mOfferList;
    private ImageView mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        initLayouts();
        initializeListeners();

    }

    /* initialize listener */
    private void initializeListeners() {
        mIvBack.setOnClickListener(this);
        mOfferList = (ArrayList<OffersData>)getIntent().getSerializableExtra(Constants.intentdata.OFFER);

        mRvOffer.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRvOffer.setAdapter(new ViewOfferAdapter(OffersActivity.this, mOfferList));
        RunLayoutAnimation(mRvOffer);
    }

    /* init layout */
    private void initLayouts() {
        mIvBack = findViewById(R.id.ivBack);
        mRvOffer = findViewById(R.id.rvOffer);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack) onBackPressed();
    }
}
