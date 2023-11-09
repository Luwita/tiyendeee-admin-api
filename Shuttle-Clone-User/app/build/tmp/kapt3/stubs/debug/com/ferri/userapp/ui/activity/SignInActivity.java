package com.ferri.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 %2\u00020\u00012\u00020\u0002:\u0001%B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\tH\u0002J\b\u0010\u0014\u001a\u00020\tH\u0003J\"\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0012\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\tH\u0014J\b\u0010\"\u001a\u00020\tH\u0002J\b\u0010#\u001a\u00020$H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/ferri/userapp/ui/activity/SignInActivity;", "Lcom/ferri/userapp/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "TAG", "", "apiClient", "Lcom/google/android/gms/common/api/GoogleApiClient;", "fCMToken", "", "getFCMToken", "()Lkotlin/Unit;", "mBtnContinue", "Landroid/widget/Button;", "mEdMobileNumber", "Landroid/widget/EditText;", "mIvFacebook", "Landroid/widget/ImageView;", "mIvGoogle", "initLayouts", "initializeListeners", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "requestHint", "validate", "", "Companion", "app_debug"})
public final class SignInActivity extends com.ferri.userapp.BaseActivity implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.Nullable
    private android.widget.Button mBtnContinue;
    @org.jetbrains.annotations.Nullable
    private android.widget.EditText mEdMobileNumber;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvFacebook;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvGoogle;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "SignInActivity";
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.common.api.GoogleApiClient apiClient;
    private static final int RESOLVE_HINT = 1040;
    @org.jetbrains.annotations.NotNull
    public static final com.ferri.userapp.ui.activity.SignInActivity.Companion Companion = null;
    
    public SignInActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onStart() {
    }
    
    private final void requestHint() {
    }
    
    @java.lang.Override
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
    
    private final kotlin.Unit getFCMToken() {
        return null;
    }
    
    private final void initLayouts() {
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void initializeListeners() {
    }
    
    private final boolean validate() {
        return false;
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/ferri/userapp/ui/activity/SignInActivity$Companion;", "", "()V", "RESOLVE_HINT", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}