package com.ferri.userapp.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0013\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007H\u00c6\u0003J:\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR \u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001d"}, d2 = {"Lcom/ferri/userapp/model/BookingData;", "Ljava/io/Serializable;", "walletBalance", "", "getbookingData", "Lcom/ferri/userapp/model/GetbookingData;", "persistedPassenger", "", "Lcom/ferri/userapp/model/PersistedPassengerItem;", "(Ljava/lang/Integer;Lcom/ferri/userapp/model/GetbookingData;Ljava/util/List;)V", "getGetbookingData", "()Lcom/ferri/userapp/model/GetbookingData;", "getPersistedPassenger", "()Ljava/util/List;", "getWalletBalance", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Lcom/ferri/userapp/model/GetbookingData;Ljava/util/List;)Lcom/ferri/userapp/model/BookingData;", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
public final class BookingData implements java.io.Serializable {
    @com.google.gson.annotations.SerializedName(value = "walletBalance")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer walletBalance = null;
    @com.google.gson.annotations.SerializedName(value = "getbookingData")
    @org.jetbrains.annotations.Nullable
    private final com.ferri.userapp.model.GetbookingData getbookingData = null;
    @com.google.gson.annotations.SerializedName(value = "persistedPassenger")
    @org.jetbrains.annotations.Nullable
    private final java.util.List<com.ferri.userapp.model.PersistedPassengerItem> persistedPassenger = null;
    
    public BookingData(@org.jetbrains.annotations.Nullable
    java.lang.Integer walletBalance, @org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.GetbookingData getbookingData, @org.jetbrains.annotations.Nullable
    java.util.List<com.ferri.userapp.model.PersistedPassengerItem> persistedPassenger) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getWalletBalance() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.ferri.userapp.model.GetbookingData getGetbookingData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.ferri.userapp.model.PersistedPassengerItem> getPersistedPassenger() {
        return null;
    }
    
    public BookingData() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.ferri.userapp.model.GetbookingData component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.ferri.userapp.model.PersistedPassengerItem> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ferri.userapp.model.BookingData copy(@org.jetbrains.annotations.Nullable
    java.lang.Integer walletBalance, @org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.GetbookingData getbookingData, @org.jetbrains.annotations.Nullable
    java.util.List<com.ferri.userapp.model.PersistedPassengerItem> persistedPassenger) {
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