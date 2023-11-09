package com.ferri.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u00012\u00020\u0002:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/ferri/userapp/ui/activity/PassDetailsActivity;", "Lcom/ferri/userapp/BaseActivity;", "Lcom/razorpay/PaymentResultListener;", "()V", "TAG", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPaymentError", "i", "", "s", "onPaymentSuccess", "Companion", "app_debug"})
public final class PassDetailsActivity extends com.ferri.userapp.BaseActivity implements com.razorpay.PaymentResultListener {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "PassDetailsActivity";
    @kotlin.jvm.JvmField
    @org.jetbrains.annotations.Nullable
    public static com.ferri.userapp.model.PassesList passDetails;
    @org.jetbrains.annotations.NotNull
    public static final com.ferri.userapp.ui.activity.PassDetailsActivity.Companion Companion = null;
    
    public PassDetailsActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onPaymentSuccess(@org.jetbrains.annotations.NotNull
    java.lang.String s) {
    }
    
    @java.lang.Override
    public void onPaymentError(int i, @org.jetbrains.annotations.NotNull
    java.lang.String s) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/ferri/userapp/ui/activity/PassDetailsActivity$Companion;", "", "()V", "passDetails", "Lcom/ferri/userapp/model/PassesList;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}