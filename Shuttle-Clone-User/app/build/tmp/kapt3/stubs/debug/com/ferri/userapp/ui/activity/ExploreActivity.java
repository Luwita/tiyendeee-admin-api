package com.ferri.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0014J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/ferri/userapp/ui/activity/ExploreActivity;", "Lcom/ferri/userapp/BaseActivity;", "()V", "TAG", "", "busRoutesAdapter", "Lcom/ferri/userapp/ui/adapter/BusRoutesAdapter;", "homeFragmentViewModel", "Lcom/ferri/userapp/ViewModel/HomeFragmentViewModel;", "notRoutesFoundLayout", "Landroid/widget/LinearLayout;", "routesRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "viewRouteStops", "stopsData", "Lcom/ferri/userapp/model/RoutesDataItem;", "app_debug"})
public final class ExploreActivity extends com.ferri.userapp.BaseActivity {
    @org.jetbrains.annotations.NotNull
    private java.lang.String TAG = "ExploreActivity";
    @org.jetbrains.annotations.Nullable
    private com.ferri.userapp.ViewModel.HomeFragmentViewModel homeFragmentViewModel;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView routesRecyclerView;
    @org.jetbrains.annotations.Nullable
    private com.ferri.userapp.ui.adapter.BusRoutesAdapter busRoutesAdapter;
    @org.jetbrains.annotations.Nullable
    private android.widget.LinearLayout notRoutesFoundLayout;
    
    public ExploreActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onStart() {
    }
    
    public final void viewRouteStops(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.model.RoutesDataItem stopsData) {
    }
}