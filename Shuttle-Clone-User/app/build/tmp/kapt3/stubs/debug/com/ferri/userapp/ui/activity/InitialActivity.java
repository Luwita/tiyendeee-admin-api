package com.ferri.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u001a\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u001eH\u0002J\b\u0010$\u001a\u00020\u001eH\u0002J\b\u0010%\u001a\u00020\u001eH\u0002J\u0010\u0010&\u001a\u00020\u001e2\u0006\u0010\'\u001a\u00020\u0010H\u0016J\u0012\u0010(\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0018\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020\rH\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/ferri/userapp/ui/activity/InitialActivity;", "Lcom/ferri/userapp/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "EXTRA_CIRCULAR_REVEAL_X", "", "getEXTRA_CIRCULAR_REVEAL_X", "()Ljava/lang/String;", "EXTRA_CIRCULAR_REVEAL_Y", "getEXTRA_CIRCULAR_REVEAL_Y", "TAG", "getTAG", "revealX", "", "revealY", "rootLayout", "Landroid/view/View;", "getRootLayout", "()Landroid/view/View;", "setRootLayout", "(Landroid/view/View;)V", "tvAppVersion", "Landroid/widget/TextView;", "tvHelp", "tvLogout", "tvPoweredBy", "tvProfileSettings", "tvSiteLink", "tvSuggestRoutes", "explodeAnim", "", "savedInstanceState", "Landroid/os/Bundle;", "intent", "Landroid/content/Intent;", "initLayouts", "initializeListeners", "logOut", "onClick", "v", "onCreate", "revealActivity", "x", "y", "app_debug"})
public final class InitialActivity extends com.ferri.userapp.BaseActivity implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvSuggestRoutes;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvProfileSettings;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvHelp;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvLogout;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvAppVersion;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvSiteLink;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvPoweredBy;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "InitialActivity";
    @org.jetbrains.annotations.Nullable
    private android.view.View rootLayout;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    @org.jetbrains.annotations.NotNull
    private final java.lang.String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    private int revealX = 0;
    private int revealY = 0;
    
    public InitialActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTAG() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.view.View getRootLayout() {
        return null;
    }
    
    public final void setRootLayout(@org.jetbrains.annotations.Nullable
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getEXTRA_CIRCULAR_REVEAL_X() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getEXTRA_CIRCULAR_REVEAL_Y() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeListeners() {
    }
    
    private final void initLayouts() {
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    private final void logOut() {
    }
    
    private final void explodeAnim(android.os.Bundle savedInstanceState, android.content.Intent intent) {
    }
    
    protected final void revealActivity(int x, int y) {
    }
}