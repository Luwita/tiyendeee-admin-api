package com.ferri.userapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.ferri.userapp.BaseActivity;
import com.ferri.userapp.R;


public class SetReminderActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);

        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        findViewById(R.id.btnSetReminder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
               /* Intent i = new Intent(SetReminderActivity.this, DashboardActivity.class);
               // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);*/
            }
        });


    }
}