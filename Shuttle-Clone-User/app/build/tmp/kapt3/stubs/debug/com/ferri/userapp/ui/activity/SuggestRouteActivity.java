package com.ferri.userapp.ui.activity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J,\u0010\u001c\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u00052\u0006\u0010!\u001a\u00020\"H\u0004J\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\b\u0010\'\u001a\u00020$H\u0014J\u0010\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020*H\u0007J\b\u0010+\u001a\u00020$H\u0016J\u0010\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020\u000fH\u0016J\b\u0010.\u001a\u00020$H\u0014J\b\u0010/\u001a\u00020$H\u0014J\b\u00100\u001a\u00020$H\u0014J\b\u00101\u001a\u00020$H\u0014J(\u00102\u001a\u00020$2\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u0005H\u0002J\b\u00107\u001a\u00020$H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lcom/ferri/userapp/ui/activity/SuggestRouteActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "TAG", "", "drop_city", "drop_state", "homeAddress", "homeLat", "homeLng", "mEdFromCity", "Landroid/widget/TextView;", "mEdToCity", "map", "Lcom/google/android/gms/maps/GoogleMap;", "mapView", "Lcom/google/android/gms/maps/MapView;", "markers", "", "Lcom/google/android/gms/maps/model/Marker;", "getMarkers", "()Ljava/util/List;", "officeAddress", "officeLat", "officeLng", "pickup_city", "pickup_state", "createMarker", "latitude", "", "longitude", "title", "iconResID", "Lcom/google/android/gms/maps/model/BitmapDescriptor;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onEventMainThread", "pusher", "Lcom/ferri/userapp/ui/events/SuggestRouteLocationEvent;", "onLowMemory", "onMapReady", "gmap", "onPause", "onResume", "onStart", "onStop", "updateOnMap", "address", "lat", "lng", "updateFor", "updateSuggestedRoute", "app_debug"})
public final class SuggestRouteActivity extends androidx.appcompat.app.AppCompatActivity implements com.google.android.gms.maps.OnMapReadyCallback {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "SuggestRouteActivity";
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.maps.MapView mapView;
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.maps.GoogleMap map;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.google.android.gms.maps.model.Marker> markers = null;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mEdFromCity;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView mEdToCity;
    @org.jetbrains.annotations.NotNull
    private java.lang.String officeAddress = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String officeLat = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String officeLng = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String homeAddress = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String homeLat = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String homeLng = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String pickup_city = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String pickup_state = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String drop_city = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String drop_state = "";
    
    public SuggestRouteActivity() {
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
    
    private final void updateSuggestedRoute() {
    }
    
    @java.lang.Override
    public void onMapReady(@org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.GoogleMap gmap) {
    }
    
    @org.jetbrains.annotations.Nullable
    protected final com.google.android.gms.maps.model.Marker createMarker(double latitude, double longitude, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.model.BitmapDescriptor iconResID) {
        return null;
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.events.SuggestRouteLocationEvent pusher) {
    }
    
    private final void updateOnMap(java.lang.String address, java.lang.String lat, java.lang.String lng, java.lang.String updateFor) {
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