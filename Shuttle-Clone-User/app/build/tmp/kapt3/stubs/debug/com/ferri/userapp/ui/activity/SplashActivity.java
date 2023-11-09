package com.ferri.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u0016\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u0000J\b\u0010\u001d\u001a\u00020\u0018H\u0002J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\b\u0010\u001f\u001a\u00020\u0018H\u0002J\u0012\u0010 \u001a\u00020\u00182\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u0018H\u0002J\b\u0010$\u001a\u00020\u0018H\u0002J\u000e\u0010%\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0012R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006&"}, d2 = {"Lcom/ferri/userapp/ui/activity/SplashActivity;", "Lcom/ferri/userapp/BaseActivity;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "appUpdateManager", "Lcom/google/android/play/core/appupdate/AppUpdateManager;", "getAppUpdateManager", "()Lcom/google/android/play/core/appupdate/AppUpdateManager;", "appUpdateManager$delegate", "Lkotlin/Lazy;", "isUpdateStatusCalled", "", "mIVLogo", "Landroid/widget/ImageView;", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "checkAppUpdate", "", "checkForAppUpdate", "explodeBaseActivity", "view", "activity", "goAhead", "initLayouts", "initializeListeners", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openIntroSlider", "showUpdateDialog", "signInActivity", "app_debug"})
public final class SplashActivity extends com.ferri.userapp.BaseActivity {
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIVLogo;
    @org.jetbrains.annotations.Nullable
    private android.view.View rootView;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "SplashActivity";
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy appUpdateManager$delegate = null;
    private boolean isUpdateStatusCalled = false;
    
    public SplashActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.view.View getRootView() {
        return null;
    }
    
    public final void setRootView(@org.jetbrains.annotations.Nullable
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTAG() {
        return null;
    }
    
    private final com.google.android.play.core.appupdate.AppUpdateManager getAppUpdateManager() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initLayouts() {
    }
    
    private final void initializeListeners() {
    }
    
    private final void checkForAppUpdate() {
    }
    
    private final void checkAppUpdate() {
    }
    
    private final void showUpdateDialog() {
    }
    
    private final void openIntroSlider() {
    }
    
    private final void goAhead() {
    }
    
    public final void signInActivity(@org.jetbrains.annotations.NotNull
    android.view.View view) {
    }
    
    public final void explodeBaseActivity(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.activity.SplashActivity activity) {
    }
}