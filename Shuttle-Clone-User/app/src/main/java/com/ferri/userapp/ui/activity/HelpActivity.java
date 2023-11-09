package com.ferri.userapp.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ferri.userapp.BaseActivity;
import com.ferri.userapp.R;
import com.ferri.userapp.RetrofitRepository.ApiCallBack;
import com.ferri.userapp.RetrofitRepository.RetrofitClient;
import com.ferri.userapp.model.DefaultResponse;
import com.ferri.userapp.utils.Constants;
import com.ferri.userapp.utils.LoadingDialog;

import androidx.annotation.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.ferri.userapp.utils.UtilMethodsKt.checkToken;
import static com.ferri.userapp.utils.UtilMethodsKt.getPreference;
import static com.ferri.userapp.utils.UtilMethodsKt.isNetworkAvailable;
import static com.ferri.userapp.utils.UtilMethodsKt.sessionExpireDialog;
import static com.ferri.userapp.utils.UtilMethodsKt.toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelpActivity extends BaseActivity implements View.OnClickListener {
    /*variable declaration*/
    private ImageView mIvBack, mIvNotification;
    private EditText mEdContact, mEdEmail, mEdMessage;
    private Button mBtnSubmit;
    private String TAG = "HelpActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        initLayouts();
        initialzeListeners();
    }

    /* initialize listener */
    private void initialzeListeners() {
        mIvBack.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        mIvNotification.setOnClickListener(this);
        setTypeFace(mEdContact);
    }

    /* init layout */
    private void initLayouts() {
        mIvBack = findViewById(R.id.ivBack);
        mEdContact = findViewById(R.id.edContact);
        mEdEmail = findViewById(R.id.edEmail);
        mEdMessage = findViewById(R.id.edMessage);
        mIvNotification = findViewById(R.id.ivNotification);
        mBtnSubmit = findViewById(R.id.btnSubmit);
        mBtnSubmit.setStateListAnimator(null);
        SetNotificationImage(mIvNotification);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack) onBackPressed();
        else if (v == mBtnSubmit) {
            if (validate()) {
                getHelp();
            }
        } else if (v == mIvNotification) startActivity(NotificationActivity.class);
    }

    private void getHelp() {
        try {
            if (isNetworkAvailable(this)) {
                LoadingDialog.showLoadingDialog(this, "Please wait...");
                RetrofitClient.getClient().getHelp(getPreference(this, Constants.TOKEN), mEdContact.getText().toString(), mEdEmail.getText().toString(), mEdMessage.getText().toString())
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<DefaultResponse>() {
                            @Override
                            public void onSuccess(@NonNull DefaultResponse data) {
                                LoadingDialog.cancelLoading();
                                if (data.isStatus()) {
                                    toast(HelpActivity.this, data.getMessage());

                                    mEdContact.setText("");
                                    mEdEmail.setText("");
                                    mEdMessage.setText("");

                                } else
                                    toast(HelpActivity.this, data.getMessage());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.i(TAG, "onError: updateInformation=" + e.getLocalizedMessage());
                                LoadingDialog.cancelLoading();
                                if (e.getLocalizedMessage().equals(Constants.Unauthorized)||e.getLocalizedMessage().equals(Constants.Forbidden))
                                    checkToken(getApplicationContext(), new ApiCallBack() {
                                        @Override
                                        public void onResponse(boolean success) {
                                            if (success) getHelp();
                                            else sessionExpireDialog(HelpActivity.this);
                                        }
                                    });
                            }
                        });
            } else toast(this, getString(R.string.txt_Network));

        } catch (Exception e) {
            Log.i(TAG, "getProfileDetails: Error=" + e.getLocalizedMessage());
            LoadingDialog.cancelLoading();
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /* validations */
    private boolean validate() {
        boolean flag = true;
        if (mEdContact.getText().equals("")&&String.valueOf(mEdContact.getText()).length()!=10) {
            flag = false;
            showToast(getString(R.string.msg_valid_mobile_number));
        } else  if (mEdEmail.getText().equals("")&&!isEmailValid(String.valueOf(mEdEmail.getText()))) {
            flag = false;
            showToast(getString(R.string.msg_email_valid));
        }else if (mEdMessage.getText().equals("")) {
            flag = false;
            showToast(getString(R.string.msg_description));
        }
        return flag;
    }
}