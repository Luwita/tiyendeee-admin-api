package com.ferri.userapp.utils;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\u0000\u00aa\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u001a\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a\u0010\u0010\r\u001a\u00020\u00012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u001a\u000e\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012\u001a\u0010\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u001a\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005\u001a\u0016\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u000e\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001c\u001a\u000e\u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\"\u001a\u0010\u0010 \u001a\u0004\u0018\u00010\u00012\u0006\u0010!\u001a\u00020\u0001\u001a\u0016\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u0001\u001a\u001c\u0010&\u001a\u0004\u0018\u00010\u00012\b\u0010\'\u001a\u0004\u0018\u00010\u00012\b\u0010(\u001a\u0004\u0018\u00010\u0001\u001a\u0010\u0010)\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005\u001a \u0010*\u001a\u0004\u0018\u00010\u00012\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010\u0015\u001a\u00020\u0005\u001a\u0010\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u000201\u001a\u001a\u00102\u001a\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u00103\u001a\u0004\u0018\u00010\u0001\u001a \u00104\u001a\u0004\u0018\u00010\u00012\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010\u0015\u001a\u00020\u0005\u001a\u0010\u00105\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u001a\u000e\u00106\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005\u001a\u001a\u00107\u001a\u00020\u00142\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u00103\u001a\u0004\u0018\u00010\u0001\u001a\u0010\u00108\u001a\u0004\u0018\u0001092\u0006\u0010:\u001a\u00020;\u001a\u0018\u0010<\u001a\u00020\u00032\b\u0010=\u001a\u0004\u0018\u00010\u00012\u0006\u0010>\u001a\u00020\u0001\u001a\"\u0010?\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u00103\u001a\u0004\u0018\u00010\u00012\u0006\u0010@\u001a\u00020\u0014\u001a$\u0010?\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u00103\u001a\u0004\u0018\u00010\u00012\b\u0010@\u001a\u0004\u0018\u00010\u0001\u001a\u000e\u0010A\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0016\u0010B\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u0001\u001a\u000e\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\u0001\u001a\u000e\u0010G\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a \u0010H\u001a\u0004\u0018\u0001092\u0006\u0010:\u001a\u00020I2\u0006\u0010J\u001a\u00020\u00052\u0006\u0010K\u001a\u00020\u0001\u001a\u0014\u0010L\u001a\u00020\u00032\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00030N\u001a\'\u0010O\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010P\u001a\u0004\u0018\u00010\u00142\u0006\u0010Q\u001a\u00020\u0001\u00a2\u0006\u0002\u0010R\u001a\u000e\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020\u0001\u001a\u001c\u0010V\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010W\u001a\u0004\u0018\u00010\u0001\u001a\u000e\u0010X\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\"\u0010Y\u001a\u00020\u0003*\u00020Z2\b\b\u0002\u0010[\u001a\u00020\\2\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00030N\u001a\u0012\u0010^\u001a\u00020\u0003*\u00020\u00052\u0006\u0010_\u001a\u00020Z\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006`"}, d2 = {"TAG", "", "RunLayoutAnimation", "", "context", "Landroid/content/Context;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "animateMarker", "destination", "Landroid/location/Location;", "marker", "Lcom/google/android/gms/maps/model/Marker;", "apiResponse", "dataClass", "", "calculateNext3rdDate", "mDepartDateCalendar", "Ljava/util/Calendar;", "checkAndRequestPermissions", "", "mContext", "checkNetwork", "checkToken", "apiCallBack", "Lcom/ferri/userapp/RetrofitRepository/ApiCallBack;", "closeKeyboard", "computeRotation", "", "fraction", "start", "end", "convertDateToBeautify", "putdate", "Ljava/util/Date;", "diffTime", "time1", "time2", "getCountOfDays", "createdDateString", "bookingDateString", "getDeviceId", "getLocationName", "latitude", "", "longitude", "getMarkerIconFromDrawable", "Lcom/google/android/gms/maps/model/BitmapDescriptor;", "drawable", "Landroid/graphics/drawable/Drawable;", "getPreference", "key", "getZoneName", "goHome", "isNetworkAvailable", "isPreference", "makePayment", "Lorg/json/JSONObject;", "data", "Lcom/ferri/userapp/model/InitiateData;", "mylog", "logActivity", "log", "savePreference", "value", "sessionExpireDialog", "setClipboard", "text", "setTextImage", "Lcom/amulyakhare/textdrawable/TextDrawable;", "name", "showKeyboard", "startPassPayment", "Lcom/ferri/userapp/model/RzPayData;", "application", "amount", "subscribeOnBackground", "function", "Lkotlin/Function0;", "successFailureDialog", "isSuccess", "msg", "(Landroid/content/Context;Ljava/lang/Boolean;Ljava/lang/String;)V", "svgFromString", "Landroid/graphics/drawable/PictureDrawable;", "png_string", "toast", "aContent", "vibratePhone", "clickWithThrottle", "Landroid/view/View;", "debounceTime", "", "action", "hideKeyboard", "view", "app_debug"})
public final class UtilMethodsKt {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "UtilMethods";
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String getPreference(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return null;
    }
    
    public static final void savePreference(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String key, @org.jetbrains.annotations.Nullable
    java.lang.String value) {
    }
    
    public static final boolean isPreference(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return false;
    }
    
    public static final void savePreference(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String key, boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final com.amulyakhare.textdrawable.TextDrawable setTextImage(@org.jetbrains.annotations.NotNull
    java.lang.String name) {
        return null;
    }
    
    public static final void toast(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String aContent) {
    }
    
    public static final void goHome(@org.jetbrains.annotations.Nullable
    android.content.Context context) {
    }
    
    public static final void mylog(@org.jetbrains.annotations.Nullable
    java.lang.String logActivity, @org.jetbrains.annotations.NotNull
    java.lang.String log) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String apiResponse(@org.jetbrains.annotations.Nullable
    java.lang.Object dataClass) {
        return null;
    }
    
    public static final void showKeyboard(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    public static final void closeKeyboard(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    public static final void clickWithThrottle(@org.jetbrains.annotations.NotNull
    android.view.View $this$clickWithThrottle, long debounceTime, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> action) {
    }
    
    public static final void checkToken(@org.jetbrains.annotations.NotNull
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    com.ferri.userapp.RetrofitRepository.ApiCallBack apiCallBack) {
    }
    
    public static final void sessionExpireDialog(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable
    public static final java.lang.String getLocationName(double latitude, double longitude, @org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public static final java.lang.String getZoneName(double latitude, double longitude, @org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return null;
    }
    
    public static final boolean isNetworkAvailable(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return false;
    }
    
    public static final boolean checkNetwork(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return false;
    }
    
    public static final void setClipboard(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String text) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String convertDateToBeautify(@org.jetbrains.annotations.NotNull
    java.util.Date putdate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String calculateNext3rdDate(@org.jetbrains.annotations.NotNull
    java.util.Calendar mDepartDateCalendar) {
        return null;
    }
    
    public static final void vibratePhone(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable
    public static final java.lang.String convertDateToBeautify(@org.jetbrains.annotations.NotNull
    java.lang.String putdate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String diffTime(@org.jetbrains.annotations.NotNull
    java.lang.String time1, @org.jetbrains.annotations.NotNull
    java.lang.String time2) {
        return null;
    }
    
    public static final void RunLayoutAnimation(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView recyclerView) {
    }
    
    @org.jetbrains.annotations.Nullable
    public static final java.lang.String getCountOfDays(@org.jetbrains.annotations.Nullable
    java.lang.String createdDateString, @org.jetbrains.annotations.Nullable
    java.lang.String bookingDateString) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public static final org.json.JSONObject startPassPayment(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.model.RzPayData data, @org.jetbrains.annotations.NotNull
    android.content.Context application, @org.jetbrains.annotations.NotNull
    java.lang.String amount) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public static final org.json.JSONObject makePayment(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.model.InitiateData data) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public static final com.google.android.gms.maps.model.BitmapDescriptor getMarkerIconFromDrawable(@org.jetbrains.annotations.NotNull
    android.graphics.drawable.Drawable drawable) {
        return null;
    }
    
    public static final void animateMarker(@org.jetbrains.annotations.NotNull
    android.location.Location destination, @org.jetbrains.annotations.Nullable
    com.google.android.gms.maps.model.Marker marker) {
    }
    
    public static final float computeRotation(float fraction, float start, float end) {
        return 0.0F;
    }
    
    public static final void hideKeyboard(@org.jetbrains.annotations.NotNull
    android.content.Context $this$hideKeyboard, @org.jetbrains.annotations.NotNull
    android.view.View view) {
    }
    
    public static final boolean checkAndRequestPermissions(@org.jetbrains.annotations.Nullable
    android.content.Context mContext) {
        return false;
    }
    
    public static final void successFailureDialog(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.Boolean isSuccess, @org.jetbrains.annotations.NotNull
    java.lang.String msg) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final android.graphics.drawable.PictureDrawable svgFromString(@org.jetbrains.annotations.NotNull
    java.lang.String png_string) {
        return null;
    }
    
    public static final void subscribeOnBackground(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> function) {
    }
    
    @org.jetbrains.annotations.Nullable
    public static final java.lang.String getDeviceId(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
}