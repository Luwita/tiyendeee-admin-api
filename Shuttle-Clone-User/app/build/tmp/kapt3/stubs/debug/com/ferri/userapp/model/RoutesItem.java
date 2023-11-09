package com.ferri.userapp.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\bA\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u001aJ\u000b\u00104\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010 J\u000b\u00106\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010?\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010 J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010C\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010 J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010E\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nH\u00c6\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010H\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010 J\u0090\u0002\u0010I\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010JJ\u0013\u0010K\u001a\u00020L2\b\u0010M\u001a\u0004\u0018\u00010NH\u00d6\u0003J\t\u0010O\u001a\u00020PH\u00d6\u0001J\t\u0010Q\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010!\u001a\u0004\b\"\u0010 R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001cR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001cR\u001a\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010!\u001a\u0004\b*\u0010 R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010!\u001a\u0004\b+\u0010 R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001cR\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001cR\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001cR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001c\u00a8\u0006R"}, d2 = {"Lcom/ferri/userapp/model/RoutesItem;", "Ljava/io/Serializable;", "dropStopName", "", "pickupStopLng", "", "pickupDistance", "dropStopLat", "routeBusId", "routeBusTimetable", "", "total_of_stops", "dropStopId", "pickupStopLat", "dropStopLng", "dropDistance", "pickupStopDepartureTime", "routeId", "pickupStopMinimumFareDrop", "dropStopMinimumFareDrop", "dropStopPricePerKmDrop", "dropStopArrivalTime", "pickupStopId", "pickupStopMinimumFarePickup", "id", "pickupStopName", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDropDistance", "()Ljava/lang/String;", "getDropStopArrivalTime", "getDropStopId", "getDropStopLat", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getDropStopLng", "getDropStopMinimumFareDrop", "getDropStopName", "getDropStopPricePerKmDrop", "getId", "getPickupDistance", "getPickupStopDepartureTime", "getPickupStopId", "getPickupStopLat", "getPickupStopLng", "getPickupStopMinimumFareDrop", "getPickupStopMinimumFarePickup", "getPickupStopName", "getRouteBusId", "getRouteBusTimetable", "()Ljava/util/List;", "getRouteId", "getTotal_of_stops", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/ferri/userapp/model/RoutesItem;", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
public final class RoutesItem implements java.io.Serializable {
    @com.google.gson.annotations.SerializedName(value = "drop_stop_name")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String dropStopName = null;
    @com.google.gson.annotations.SerializedName(value = "pickup_stop_lng")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double pickupStopLng = null;
    @com.google.gson.annotations.SerializedName(value = "pickup_distance")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String pickupDistance = null;
    @com.google.gson.annotations.SerializedName(value = "drop_stop_lat")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double dropStopLat = null;
    @com.google.gson.annotations.SerializedName(value = "route_busId")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String routeBusId = null;
    @com.google.gson.annotations.SerializedName(value = "route_bus_timetable")
    @org.jetbrains.annotations.Nullable
    private final java.util.List<java.lang.String> routeBusTimetable = null;
    @com.google.gson.annotations.SerializedName(value = "total_of_stops")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String total_of_stops = null;
    @com.google.gson.annotations.SerializedName(value = "drop_stop_id")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String dropStopId = null;
    @com.google.gson.annotations.SerializedName(value = "pickup_stop_lat")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double pickupStopLat = null;
    @com.google.gson.annotations.SerializedName(value = "drop_stop_lng")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double dropStopLng = null;
    @com.google.gson.annotations.SerializedName(value = "drop_distance")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String dropDistance = null;
    @com.google.gson.annotations.SerializedName(value = "pickup_stop_departure_time")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String pickupStopDepartureTime = null;
    @com.google.gson.annotations.SerializedName(value = "routeId")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String routeId = null;
    @com.google.gson.annotations.SerializedName(value = "pickup_stop_minimum_fare_drop")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String pickupStopMinimumFareDrop = null;
    @com.google.gson.annotations.SerializedName(value = "drop_stop_minimum_fare_drop")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String dropStopMinimumFareDrop = null;
    @com.google.gson.annotations.SerializedName(value = "drop_stop_price_per_km_drop")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String dropStopPricePerKmDrop = null;
    @com.google.gson.annotations.SerializedName(value = "drop_stop_arrival_time")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String dropStopArrivalTime = null;
    @com.google.gson.annotations.SerializedName(value = "pickup_stop_id")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String pickupStopId = null;
    @com.google.gson.annotations.SerializedName(value = "pickup_stop_minimum_fare_pickup")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String pickupStopMinimumFarePickup = null;
    @com.google.gson.annotations.SerializedName(value = "id")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String id = null;
    @com.google.gson.annotations.SerializedName(value = "pickup_stop_name")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String pickupStopName = null;
    
    public RoutesItem(@org.jetbrains.annotations.Nullable
    java.lang.String dropStopName, @org.jetbrains.annotations.Nullable
    java.lang.Double pickupStopLng, @org.jetbrains.annotations.Nullable
    java.lang.String pickupDistance, @org.jetbrains.annotations.Nullable
    java.lang.Double dropStopLat, @org.jetbrains.annotations.Nullable
    java.lang.String routeBusId, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> routeBusTimetable, @org.jetbrains.annotations.Nullable
    java.lang.String total_of_stops, @org.jetbrains.annotations.Nullable
    java.lang.String dropStopId, @org.jetbrains.annotations.Nullable
    java.lang.Double pickupStopLat, @org.jetbrains.annotations.Nullable
    java.lang.Double dropStopLng, @org.jetbrains.annotations.Nullable
    java.lang.String dropDistance, @org.jetbrains.annotations.Nullable
    java.lang.String pickupStopDepartureTime, @org.jetbrains.annotations.Nullable
    java.lang.String routeId, @org.jetbrains.annotations.Nullable
    java.lang.String pickupStopMinimumFareDrop, @org.jetbrains.annotations.Nullable
    java.lang.String dropStopMinimumFareDrop, @org.jetbrains.annotations.Nullable
    java.lang.String dropStopPricePerKmDrop, @org.jetbrains.annotations.Nullable
    java.lang.String dropStopArrivalTime, @org.jetbrains.annotations.Nullable
    java.lang.String pickupStopId, @org.jetbrains.annotations.Nullable
    java.lang.String pickupStopMinimumFarePickup, @org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.Nullable
    java.lang.String pickupStopName) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDropStopName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getPickupStopLng() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPickupDistance() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getDropStopLat() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRouteBusId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getRouteBusTimetable() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTotal_of_stops() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDropStopId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getPickupStopLat() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getDropStopLng() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDropDistance() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPickupStopDepartureTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRouteId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPickupStopMinimumFareDrop() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDropStopMinimumFareDrop() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDropStopPricePerKmDrop() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDropStopArrivalTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPickupStopId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPickupStopMinimumFarePickup() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPickupStopName() {
        return null;
    }
    
    public RoutesItem() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ferri.userapp.model.RoutesItem copy(@org.jetbrains.annotations.Nullable
    java.lang.String dropStopName, @org.jetbrains.annotations.Nullable
    java.lang.Double pickupStopLng, @org.jetbrains.annotations.Nullable
    java.lang.String pickupDistance, @org.jetbrains.annotations.Nullable
    java.lang.Double dropStopLat, @org.jetbrains.annotations.Nullable
    java.lang.String routeBusId, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> routeBusTimetable, @org.jetbrains.annotations.Nullable
    java.lang.String total_of_stops, @org.jetbrains.annotations.Nullable
    java.lang.String dropStopId, @org.jetbrains.annotations.Nullable
    java.lang.Double pickupStopLat, @org.jetbrains.annotations.Nullable
    java.lang.Double dropStopLng, @org.jetbrains.annotations.Nullable
    java.lang.String dropDistance, @org.jetbrains.annotations.Nullable
    java.lang.String pickupStopDepartureTime, @org.jetbrains.annotations.Nullable
    java.lang.String routeId, @org.jetbrains.annotations.Nullable
    java.lang.String pickupStopMinimumFareDrop, @org.jetbrains.annotations.Nullable
    java.lang.String dropStopMinimumFareDrop, @org.jetbrains.annotations.Nullable
    java.lang.String dropStopPricePerKmDrop, @org.jetbrains.annotations.Nullable
    java.lang.String dropStopArrivalTime, @org.jetbrains.annotations.Nullable
    java.lang.String pickupStopId, @org.jetbrains.annotations.Nullable
    java.lang.String pickupStopMinimumFarePickup, @org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.Nullable
    java.lang.String pickupStopName) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}