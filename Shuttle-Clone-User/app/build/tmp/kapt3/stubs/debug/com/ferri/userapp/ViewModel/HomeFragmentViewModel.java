package com.ferri.userapp.ViewModel;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u00b8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002JZ\u0010\u0005\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010;\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u0004JZ\u0010C\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004J,\u0010L\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010M\u001a\u00020\u00042\b\u0010J\u001a\u0004\u0018\u00010\u0004J$\u0010N\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\b\u0010O\u001a\u0004\u0018\u00010PJ\u001a\u0010\u0016\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u0004J=\u0010Q\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\b\u0010R\u001a\u0004\u0018\u00010S2\b\u0010T\u001a\u0004\u0018\u00010S2\b\u0010U\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010VJ\u001a\u0010 \u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u0004JR\u0010\u0019\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u00042\u0006\u0010W\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u0004J.\u0010Y\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\b\u0010Z\u001a\u0004\u0018\u00010\u00042\b\u0010T\u001a\u0004\u0018\u00010\u0004J\b\u0010[\u001a\u000207H\u0014Jr\u0010!\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u00042\u0006\u0010\\\u001a\u00020\u00042\u0006\u0010]\u001a\u00020\u00042\u0006\u0010^\u001a\u00020\u00042\u0006\u0010W\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u00042\u0006\u0010_\u001a\u00020\u00042\u0006\u0010`\u001a\u00020\u0004J:\u0010a\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010M\u001a\u00020\u00042\u0006\u0010b\u001a\u00020\u00042\u0006\u0010_\u001a\u00020\u00042\u0006\u0010`\u001a\u00020\u0004J2\u0010&\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010c\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u0004J$\u0010d\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\b\u0010e\u001a\u0004\u0018\u00010fJ.\u0010g\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\b\u0010Z\u001a\u0004\u0018\u00010\u00042\b\u0010T\u001a\u0004\u0018\u00010\u0004JZ\u0010h\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010i\u001a\u00020\u00042\u0006\u0010j\u001a\u00020\u00042\u0006\u0010k\u001a\u00020\u00042\u0006\u0010l\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u0004JB\u00102\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010M\u001a\u00020\u00042\u0016\u0010m\u001a\u0012\u0012\u0004\u0012\u00020\u00040nj\b\u0012\u0004\u0012\u00020\u0004`o2\u0006\u0010p\u001a\u00020\u0004J$\u00104\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010\u00042\b\u0010M\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\tR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\tR\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\tR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\tR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\tR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\tR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\tR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\tR\u001f\u0010)\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020+\u0018\u00010*0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\tR\u001f\u0010-\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020+\u0018\u00010*0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\tR\u0017\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\tR\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u00140\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\tR\u0017\u00104\u001a\b\u0012\u0004\u0012\u0002050\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010\t\u00a8\u0006q"}, d2 = {"Lcom/ferri/userapp/ViewModel/HomeFragmentViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "TAG", "", "addOfficeRideAddress", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ferri/userapp/model/UserProfileUpdateResponse;", "getAddOfficeRideAddress", "()Landroidx/lifecycle/MutableLiveData;", "bookingList", "Lcom/ferri/userapp/model/BookingListResponseModel;", "getBookingList", "bookingResponse", "Lcom/ferri/userapp/model/BookingResponseModel;", "getBookingResponse", "busSeatsResponse", "Lcom/ferri/userapp/model/BusSeatsResponseModel;", "getBusSeatsResponse", "cancelBooking", "Lcom/ferri/userapp/model/DefaultResponse;", "getCancelBooking", "exploreRoutes", "Lcom/ferri/userapp/model/ExploreRoutesResponseModel;", "getExploreRoutes", "getRouteFare", "Lcom/ferri/userapp/model/GeneratedFareResponseModel;", "getGetRouteFare", "isDataSaved", "", "offers", "Lcom/ferri/userapp/model/OffersResponseModel;", "getOffers", "payForPass", "Lcom/ferri/userapp/model/PaymentInitiateDataResponse;", "getPayForPass", "paymentResponse", "getPaymentResponse", "routeStops", "Lcom/ferri/userapp/model/RouteStopsResponseModel;", "getRouteStops", "savedSearchLocationList", "", "Lcom/ferri/userapp/model/SearchedDataItem;", "getSavedSearchLocationList", "searchLocationList", "getSearchLocationList", "searchedRoutes", "Lcom/ferri/userapp/model/SearchRoutesResponseModel;", "getSearchedRoutes", "setReminder", "getSetReminder", "trackRoutes", "Lcom/ferri/userapp/model/TripTrackingStatusResponse;", "getTrackRoutes", "", "mContext", "Landroid/content/Context;", "token", "home_lat", "home_lng", "home_address", "home_timing", "office_lat", "office_lng", "office_address", "office_timing", "busSeats", "bus_id", "route_id", "pickup_stop_id", "drop_stop_id", "type", "has_return", "currentDate", "endDate", "cancelBookings", "pnr_no", "createBooking", "bookingData", "Lcom/ferri/userapp/model/BookingRequestData;", "getBookings", "offset", "", "limit", "travelStatus", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "seat_no", "start_date", "getSavedSearchLocation", "location", "onCleared", "pass_id", "pass_no_of_rides", "pass_amount", "payment_mode", "bookingDate", "payRouteFee", "amount", "id", "saveSearchLocation", "locationList", "Lcom/ferri/userapp/model/SaveLocationRequestModel;", "searchLocation", "searchRoutes", "pickUpLat", "pickUpLng", "dropLat", "dropLng", "every", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "time", "app_debug"})
public final class HomeFragmentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "HomeFragmentViewModel";
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.ferri.userapp.model.SearchedDataItem>> searchLocationList = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.ferri.userapp.model.SearchedDataItem>> savedSearchLocationList = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.SearchRoutesResponseModel> searchedRoutes = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.RouteStopsResponseModel> routeStops = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.TripTrackingStatusResponse> trackRoutes = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.BusSeatsResponseModel> busSeatsResponse = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.ExploreRoutesResponseModel> exploreRoutes = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.GeneratedFareResponseModel> getRouteFare = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.BookingResponseModel> bookingResponse = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.PaymentInitiateDataResponse> paymentResponse = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.BookingListResponseModel> bookingList = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.UserProfileUpdateResponse> addOfficeRideAddress = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.DefaultResponse> setReminder = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.PaymentInitiateDataResponse> payForPass = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.DefaultResponse> cancelBooking = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isDataSaved = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.OffersResponseModel> offers = null;
    
    public HomeFragmentViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.ferri.userapp.model.SearchedDataItem>> getSearchLocationList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.ferri.userapp.model.SearchedDataItem>> getSavedSearchLocationList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.SearchRoutesResponseModel> getSearchedRoutes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.RouteStopsResponseModel> getRouteStops() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.TripTrackingStatusResponse> getTrackRoutes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.BusSeatsResponseModel> getBusSeatsResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.ExploreRoutesResponseModel> getExploreRoutes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.GeneratedFareResponseModel> getGetRouteFare() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.BookingResponseModel> getBookingResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.PaymentInitiateDataResponse> getPaymentResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.BookingListResponseModel> getBookingList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.UserProfileUpdateResponse> getAddOfficeRideAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.DefaultResponse> getSetReminder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.PaymentInitiateDataResponse> getPayForPass() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.DefaultResponse> getCancelBooking() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isDataSaved() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.ferri.userapp.model.OffersResponseModel> getOffers() {
        return null;
    }
    
    public final void searchLocation(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    java.lang.String location, @org.jetbrains.annotations.Nullable
    java.lang.String limit) {
    }
    
    public final void getSavedSearchLocation(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    java.lang.String location, @org.jetbrains.annotations.Nullable
    java.lang.String limit) {
    }
    
    public final void saveSearchLocation(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.SaveLocationRequestModel locationList) {
    }
    
    public final void getOffers(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token) {
    }
    
    public final void searchRoutes(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String pickUpLat, @org.jetbrains.annotations.NotNull
    java.lang.String pickUpLng, @org.jetbrains.annotations.NotNull
    java.lang.String dropLat, @org.jetbrains.annotations.NotNull
    java.lang.String dropLng, @org.jetbrains.annotations.NotNull
    java.lang.String currentDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String has_return) {
    }
    
    public final void addOfficeRideAddress(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String home_lat, @org.jetbrains.annotations.NotNull
    java.lang.String home_lng, @org.jetbrains.annotations.NotNull
    java.lang.String home_address, @org.jetbrains.annotations.NotNull
    java.lang.String home_timing, @org.jetbrains.annotations.NotNull
    java.lang.String office_lat, @org.jetbrains.annotations.NotNull
    java.lang.String office_lng, @org.jetbrains.annotations.NotNull
    java.lang.String office_address, @org.jetbrains.annotations.NotNull
    java.lang.String office_timing) {
    }
    
    public final void payForPass(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String bus_id, @org.jetbrains.annotations.NotNull
    java.lang.String route_id, @org.jetbrains.annotations.NotNull
    java.lang.String pickup_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String drop_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String pass_id, @org.jetbrains.annotations.NotNull
    java.lang.String pass_no_of_rides, @org.jetbrains.annotations.NotNull
    java.lang.String pass_amount, @org.jetbrains.annotations.NotNull
    java.lang.String seat_no, @org.jetbrains.annotations.NotNull
    java.lang.String has_return, @org.jetbrains.annotations.NotNull
    java.lang.String payment_mode, @org.jetbrains.annotations.NotNull
    java.lang.String bookingDate) {
    }
    
    public final void routeStops(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String pickup_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String drop_stop_id) {
    }
    
    public final void trackRoutes(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no) {
    }
    
    public final void busSeats(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String bus_id, @org.jetbrains.annotations.NotNull
    java.lang.String route_id, @org.jetbrains.annotations.NotNull
    java.lang.String pickup_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String drop_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String has_return, @org.jetbrains.annotations.NotNull
    java.lang.String currentDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate) {
    }
    
    public final void getRouteFare(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String bus_id, @org.jetbrains.annotations.NotNull
    java.lang.String route_id, @org.jetbrains.annotations.NotNull
    java.lang.String pickup_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String drop_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String seat_no, @org.jetbrains.annotations.NotNull
    java.lang.String start_date, @org.jetbrains.annotations.NotNull
    java.lang.String has_return) {
    }
    
    public final void createBooking(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.BookingRequestData bookingData) {
    }
    
    public final void payRouteFee(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String pnr_no, @org.jetbrains.annotations.NotNull
    java.lang.String amount, @org.jetbrains.annotations.NotNull
    java.lang.String payment_mode, @org.jetbrains.annotations.NotNull
    java.lang.String bookingDate) {
    }
    
    public final void setReminder(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String pnr_no, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<java.lang.String> every, @org.jetbrains.annotations.NotNull
    java.lang.String time) {
    }
    
    public final void exploreRoutes(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token) {
    }
    
    public final void getBookings(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    java.lang.Integer offset, @org.jetbrains.annotations.Nullable
    java.lang.Integer limit, @org.jetbrains.annotations.Nullable
    java.lang.String travelStatus) {
    }
    
    public final void cancelBookings(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String pnr_no, @org.jetbrains.annotations.Nullable
    java.lang.String currentDate) {
    }
    
    @java.lang.Override
    protected void onCleared() {
    }
}