package com.ferri.userapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;


public class LoadingDialog {

    static ProgressDialog progressDialog;
    private static String TAG = "LoadingDialog";


    public static void showLoadingDialog(Context context, String message) {

        try {
            if (!(progressDialog != null && progressDialog.isShowing())) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage(message);

                progressDialog.setCancelable(true);
                progressDialog.setCanceledOnTouchOutside(false);

                progressDialog.show();
            }
        } catch (Exception e) {
            Log.i(TAG, "showLoadingDialog: ");
        }
    }

    public static void cancelLoading() {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.cancel();
        } catch (Exception e) {
            Log.i(TAG, "cancelLoading: ");
        }

    }


}
