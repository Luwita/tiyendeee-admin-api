package com.ferri.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u00020\u00012\u00020\u0002:\u0001&B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0013H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0002J\b\u0010\u0018\u001a\u00020\u0011H\u0002J\b\u0010\u0019\u001a\u00020\u0011H\u0002J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0017J\u0012\u0010\u001d\u001a\u00020\u00112\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0012\u0010 \u001a\u00020\u00112\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002J\u0018\u0010#\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u0013J\u0018\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/ferri/userapp/ui/activity/ReferEarnActivity;", "Lcom/ferri/userapp/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "mIvBack", "Landroid/widget/ImageView;", "mIvFaceBook", "mIvGoogle", "mIvNotification", "mIvTwitter", "mIvWhatsapp", "mTvCode", "Landroid/widget/TextView;", "mTvLink", "mTvRfrAmount", "tvReferPolicy", "createDynamicLink", "", "shareOn", "", "getInvitationMessage", "", "linkUrl", "getReferDetails", "initLayouts", "initializeListeners", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setDetails", "response", "Lcom/ferri/userapp/model/Data;", "shareInviteLink", "shareWithNoImage", "shareMessage", "Companion", "app_debug"})
public final class ReferEarnActivity extends com.ferri.userapp.BaseActivity implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvLink;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvCode;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvReferPolicy;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvRfrAmount;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvBack;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvFaceBook;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvWhatsapp;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvGoogle;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvTwitter;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView mIvNotification;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "ReferEarnActivity";
    @org.jetbrains.annotations.NotNull
    public static final com.ferri.userapp.ui.activity.ReferEarnActivity.Companion Companion = null;
    
    public ReferEarnActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initLayouts() {
    }
    
    private final void getReferDetails() {
    }
    
    private final void setDetails(com.ferri.userapp.model.Data response) {
    }
    
    private final void initializeListeners() {
    }
    
    @java.lang.Override
    @androidx.annotation.RequiresApi(api = android.os.Build.VERSION_CODES.M)
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    private final void createDynamicLink(java.lang.String shareOn) {
    }
    
    public final void shareInviteLink(@org.jetbrains.annotations.Nullable
    java.lang.String linkUrl, @org.jetbrains.annotations.NotNull
    java.lang.String shareOn) {
    }
    
    private final void shareWithNoImage(java.lang.String shareMessage, java.lang.String shareOn) {
    }
    
    private final java.lang.Object getInvitationMessage(java.lang.String linkUrl) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/ferri/userapp/ui/activity/ReferEarnActivity$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}