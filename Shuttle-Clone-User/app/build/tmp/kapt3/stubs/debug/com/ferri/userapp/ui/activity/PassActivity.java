package com.ferri.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/ferri/userapp/ui/activity/PassActivity;", "Lcom/ferri/userapp/BaseActivity;", "()V", "TAG", "", "bookingType", "busId", "dropId", "has_return", "layPassAlert", "Landroid/widget/LinearLayout;", "getLayPassAlert", "()Landroid/widget/LinearLayout;", "setLayPassAlert", "(Landroid/widget/LinearLayout;)V", "passesList", "", "Lcom/ferri/userapp/model/PassesList;", "pickupId", "routeId", "rvPasses", "Landroidx/recyclerview/widget/RecyclerView;", "getRvPasses", "()Landroidx/recyclerview/widget/RecyclerView;", "setRvPasses", "(Landroidx/recyclerview/widget/RecyclerView;)V", "seatNo", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class PassActivity extends com.ferri.userapp.BaseActivity {
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView rvPasses;
    @org.jetbrains.annotations.Nullable
    private android.widget.LinearLayout layPassAlert;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "PassActivity";
    @org.jetbrains.annotations.NotNull
    private java.lang.String seatNo = "";
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.ferri.userapp.model.PassesList> passesList;
    @org.jetbrains.annotations.NotNull
    private java.lang.String routeId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String pickupId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String dropId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String busId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String has_return = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String bookingType = "";
    
    public PassActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final androidx.recyclerview.widget.RecyclerView getRvPasses() {
        return null;
    }
    
    public final void setRvPasses(@org.jetbrains.annotations.Nullable
    androidx.recyclerview.widget.RecyclerView p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.widget.LinearLayout getLayPassAlert() {
        return null;
    }
    
    public final void setLayPassAlert(@org.jetbrains.annotations.Nullable
    android.widget.LinearLayout p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
}