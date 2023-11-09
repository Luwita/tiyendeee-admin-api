package com.ferri.userapp.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\'\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\bH\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0006H\u00d6\u0001R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lcom/ferri/userapp/model/BookingRequestData;", "", "passengerDetailsItem", "", "Lcom/ferri/userapp/model/PassengerDetailsItem;", "date", "", "fareData", "Lcom/ferri/userapp/model/FareData;", "(Ljava/util/List;Ljava/lang/String;Lcom/ferri/userapp/model/FareData;)V", "getDate", "()Ljava/lang/String;", "getFareData", "()Lcom/ferri/userapp/model/FareData;", "getPassengerDetailsItem", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class BookingRequestData {
    @com.google.gson.annotations.SerializedName(value = "passengerDetailsItem")
    @org.jetbrains.annotations.Nullable
    private final java.util.List<com.ferri.userapp.model.PassengerDetailsItem> passengerDetailsItem = null;
    @com.google.gson.annotations.SerializedName(value = "offer_code")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String date = null;
    @com.google.gson.annotations.SerializedName(value = "fareData")
    @org.jetbrains.annotations.NotNull
    private final com.ferri.userapp.model.FareData fareData = null;
    
    public BookingRequestData(@org.jetbrains.annotations.Nullable
    java.util.List<com.ferri.userapp.model.PassengerDetailsItem> passengerDetailsItem, @org.jetbrains.annotations.Nullable
    java.lang.String date, @org.jetbrains.annotations.NotNull
    com.ferri.userapp.model.FareData fareData) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.ferri.userapp.model.PassengerDetailsItem> getPassengerDetailsItem() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ferri.userapp.model.FareData getFareData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.ferri.userapp.model.PassengerDetailsItem> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ferri.userapp.model.FareData component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ferri.userapp.model.BookingRequestData copy(@org.jetbrains.annotations.Nullable
    java.util.List<com.ferri.userapp.model.PassengerDetailsItem> passengerDetailsItem, @org.jetbrains.annotations.Nullable
    java.lang.String date, @org.jetbrains.annotations.NotNull
    com.ferri.userapp.model.FareData fareData) {
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