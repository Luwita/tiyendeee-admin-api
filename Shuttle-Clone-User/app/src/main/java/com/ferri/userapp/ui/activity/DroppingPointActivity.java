package com.ferri.userapp.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ferri.userapp.BaseActivity;
import com.ferri.userapp.R;
import com.ferri.userapp.model.DroppingModel;
import com.ferri.userapp.ui.adapter.DroppingAdapter;
import com.ferri.userapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ferri.userapp.utils.UtilMethodsKt.goHome;

public class DroppingPointActivity extends BaseActivity implements View.OnClickListener {
    /*variable declaration*/
    public static String mPickup, mDropping;
    private TextView mTvPickup, mTvDropping;
    private RecyclerView mRvPickup, mRvDropping;
    private List<DroppingModel> mPickUpList, mDroppingList;
    private DroppingAdapter mPickupAdapter, mDroppingAdapter;
    private ImageView mIvBack, mIvHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropping_point);
        initLayouts();
        initializeListeners();

    }

    /* initialize listener */
    private void initializeListeners() {
        mTvPickup.setOnClickListener(this);
        mTvDropping.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mIvHome.setOnClickListener(this);

        mRvPickup.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRvDropping.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mPickUpList = new ArrayList<>();
        mDroppingList = new ArrayList<>();

        mPickUpList.add(new DroppingModel(getString(R.string.lbl_pickup1), getString(R.string.lbl_location1), getString(R.string.lbl_duration1)));
        mPickUpList.add(new DroppingModel(getString(R.string.lbl_pickup2), getString(R.string.lbl_location1), getString(R.string.lbl_duration1)));

        mDroppingList.add(new DroppingModel(getString(R.string.lbl_dropping1), getString(R.string.lbl_location1), getString(R.string.lbl_duration1)));
        mDroppingList.add(new DroppingModel(getString(R.string.lbl_droppin2), getString(R.string.lbl_location1), getString(R.string.lbl_duration1)));

        mPickupAdapter = new DroppingAdapter(this, mPickUpList);
        mDroppingAdapter = new DroppingAdapter(this, mDroppingList);

        mRvPickup.setAdapter(mPickupAdapter);
        mRvDropping.setAdapter(mDroppingAdapter);


        mPickupAdapter.setOnClickListener(new DroppingAdapter.onClickListener() {
            @Override
            public void onClick(int i1, final String name) {
                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {
                                                  mTvDropping.setBackground(getResources().getDrawable(R.drawable.bg_rightswitch_select));
                                                  mTvDropping.setTextColor(getResources().getColor(R.color.white));
                                                  mTvPickup.setTextColor(getResources().getColor(R.color.textheader));
                                                  mTvPickup.setBackground(getResources().getDrawable(R.drawable.bg_leftswitch));
                                                  hideView(mRvPickup);
                                                  showView(mRvDropping);
                                                  mPickup = name;
                                              }
                                          },
                        500);
            }

        });
        mDroppingAdapter.setOnClickListener(new DroppingAdapter.onClickListener() {
            @Override
            public void onClick(int i, final String name) {
                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {

                                                  mDropping = name;
//                                                  startActivity(PassengerDetailActivity.class);
                                                  Intent i = new Intent(getBaseContext(), BookingActivity.class);
                                                  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                  i.putExtra(Constants.intentdata.TRAVELLERNAME, getString(R.string.app_name));
                                                  i.putExtra(Constants.intentdata.TYPECOACH, getString(R.string.lbl_type1));
                                                  i.putExtra(Constants.intentdata.PRICE, getString(R.string.lbl_price2).replace("₹", ""));
                                                  i.putExtra(Constants.intentdata.HOLD, getString(R.string.lbl_hold));
                                                  startActivity(i);
                                              }
                                          },
                        500);
            }
        });
        RunLayoutAnimation(mRvPickup);
        RunLayoutAnimation(mRvDropping);

    }

    /* init layout */
    private void initLayouts() {
        mTvPickup = findViewById(R.id.tvPickup);
        mTvDropping = findViewById(R.id.tvDropping);
        mRvPickup = findViewById(R.id.rvPickup);
        mRvDropping = findViewById(R.id.rvDropping);
        mIvBack = findViewById(R.id.ivBack);
        mIvHome = findViewById(R.id.ivHome);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {

        if (v == mTvPickup) {
            mTvPickup.setBackground(getResources().getDrawable(R.drawable.bg_leftswitch_select));
            mTvPickup.setTextColor(getResources().getColor(R.color.white));

            mTvDropping.setTextColor(getResources().getColor(R.color.textheader));
            mTvDropping.setBackground(getResources().getDrawable(R.drawable.bg_rightswitch));

            hideView(mRvDropping);
            showView(mRvPickup);
            RunLayoutAnimation(mRvPickup);


        } else if (v == mTvDropping) {
            mTvDropping.setBackground(getResources().getDrawable(R.drawable.bg_rightswitch_select));
            mTvDropping.setTextColor(getResources().getColor(R.color.white));
            mTvPickup.setTextColor(getResources().getColor(R.color.textheader));
            mTvPickup.setBackground(getResources().getDrawable(R.drawable.bg_leftswitch));

            hideView(mRvPickup);
            showView(mRvDropping);
            RunLayoutAnimation(mRvDropping);

        } else if (v == mIvBack) onBackPressed();
        else if (v == mIvHome) goHome(getApplicationContext());
    }
}
