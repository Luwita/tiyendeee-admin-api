package com.ferri.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J6\u0010\u0018\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0004J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\b\u0010$\u001a\u00020!H\u0014J\b\u0010%\u001a\u00020!H\u0016J\u0010\u0010&\u001a\u00020!2\u0006\u0010\'\u001a\u00020\nH\u0016J\b\u0010(\u001a\u00020!H\u0014J\b\u0010)\u001a\u00020!H\u0014J\b\u0010*\u001a\u00020!H\u0014J\b\u0010+\u001a\u00020!H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/ferri/userapp/ui/activity/BusRoutesActivity;", "Lcom/ferri/userapp/BaseActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "TAG", "", "dropId", "homeFragmentViewModel", "Lcom/ferri/userapp/ViewModel/HomeFragmentViewModel;", "map", "Lcom/google/android/gms/maps/GoogleMap;", "mapView", "Lcom/google/android/gms/maps/MapView;", "markers", "", "Lcom/google/android/gms/maps/model/Marker;", "getMarkers", "()Ljava/util/List;", "pickupId", "routeId", "rvBusRoutesStops", "Landroidx/recyclerview/widget/RecyclerView;", "tvRouteStopTitle", "Landroid/widget/TextView;", "createMarker", "latitude", "", "longitude", "title", "snippet", "iconResID", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onLowMemory", "onMapReady", "gmap", "onPause", "onResume", "onStart", "onStop", "app_debug"})
public final class BusRoutesActivity extends com.ferri.userapp.BaseActivity implements com.google.android.gms.maps.OnMapReadyCallback {
    @org.jetbrains.annotations.NotNull
    private java.lang.String TAG = "BusRoutesActivity";
    @org.jetbrains.annotations.Nullable
    private com.ferri.userapp.ViewModel.HomeFragmentViewModel homeFragmentViewModel;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView tvRouteStopTitle;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView rvBusRoutesStops;
    @org.jetbrains.annotations.NotNull
    private java.lang.String routeId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String pickupId = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String dropId = "";
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.maps.MapView mapView;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.google.android.gms.maps.model.Marker> markers = null;
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.maps.GoogleMap map;
    
    public BusRoutesActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.google.android.gms.maps.model.Marker> getMarkers() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onMapReady(@org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.GoogleMap gmap) {
    }
    
    @org.jetbrains.annotations.Nullable
    protected final com.google.android.gms.maps.model.Marker createMarker(double latitude, double longitude, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String snippet, int iconResID) {
        return null;
    }
    
    @java.lang.Override
    protected void onStart() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @java.lang.Override
    protected void onStop() {
    }
    
    @java.lang.Override
    protected void onPause() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    @java.lang.Override
    public void onLowMemory() {
    }
}