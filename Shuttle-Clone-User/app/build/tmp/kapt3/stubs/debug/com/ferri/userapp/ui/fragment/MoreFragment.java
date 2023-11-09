package com.ferri.userapp.ui.fragment;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 )2\u00020\u00012\u00020\u0002:\u0001)B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0019H\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001bH\u0016J&\u0010!\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010(\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/ferri/userapp/ui/fragment/MoreFragment;", "Landroidx/fragment/app/Fragment;", "Landroid/view/View$OnClickListener;", "()V", "isRegisteredWithSocial", "", "mFlag", "", "mGoogleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "mTvBookingHistory", "Landroid/widget/TextView;", "mTvCards", "mTvExploreRoutes", "mTvHelp", "mTvLogout", "mTvPass", "mTvPoweredBy", "mTvProfileSettings", "mTvReferEarn", "mTvSetting", "mTvSuggestRoutes", "mTvTransactionHistory", "mTvWallet", "initLayouts", "", "view", "Landroid/view/View;", "initializeGoogleLogin", "initializeListeners", "logOut", "onClick", "v", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "signOut", "Companion", "app_debug"})
public final class MoreFragment extends androidx.fragment.app.Fragment implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvProfileSettings;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvWallet;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvCards;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvReferEarn;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvHelp;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvLogout;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvSetting;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvTransactionHistory;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvBookingHistory;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvPass;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvPoweredBy;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvSuggestRoutes;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mTvExploreRoutes;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String mFlag = "1";
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.auth.api.signin.GoogleSignInClient mGoogleSignInClient;
    private boolean isRegisteredWithSocial = false;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String mTitle = "More";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "MoreFragment";
    @org.jetbrains.annotations.NotNull
    public static final com.ferri.userapp.ui.fragment.MoreFragment.Companion Companion = null;
    
    public MoreFragment() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void initializeGoogleLogin() {
    }
    
    private final void initializeListeners() {
    }
    
    private final void initLayouts(android.view.View view) {
    }
    
    @java.lang.Override
    public void onClick(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    private final void logOut() {
    }
    
    private final void signOut() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/ferri/userapp/ui/fragment/MoreFragment$Companion;", "", "()V", "TAG", "", "mTitle", "getMTitle", "()Ljava/lang/String;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getMTitle() {
            return null;
        }
    }
}