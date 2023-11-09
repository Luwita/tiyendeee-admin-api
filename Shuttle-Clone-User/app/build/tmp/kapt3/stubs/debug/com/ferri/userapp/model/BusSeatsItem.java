package com.ferri.userapp.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003JJ\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00d6\u0001J\t\u0010 \u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012\u00a8\u0006!"}, d2 = {"Lcom/ferri/userapp/model/BusSeatsItem;", "Ljava/io/Serializable;", "bus", "", "seatNo", "", "type", "isladies", "", "seatStatus", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)V", "getBus", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIsladies", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getSeatNo", "()Ljava/lang/String;", "getSeatStatus", "getType", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/ferri/userapp/model/BusSeatsItem;", "equals", "other", "", "hashCode", "toString", "app_debug"})
public final class BusSeatsItem implements java.io.Serializable {
    @com.google.gson.annotations.SerializedName(value = "bus")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer bus = null;
    @com.google.gson.annotations.SerializedName(value = "seat_no")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String seatNo = null;
    @com.google.gson.annotations.SerializedName(value = "type")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String type = null;
    @com.google.gson.annotations.SerializedName(value = "is_ladies")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Boolean isladies = null;
    @com.google.gson.annotations.SerializedName(value = "seat_status")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String seatStatus = null;
    
    public BusSeatsItem(@org.jetbrains.annotations.Nullable
    java.lang.Integer bus, @org.jetbrains.annotations.Nullable
    java.lang.String seatNo, @org.jetbrains.annotations.Nullable
    java.lang.String type, @org.jetbrains.annotations.Nullable
    java.lang.Boolean isladies, @org.jetbrains.annotations.Nullable
    java.lang.String seatStatus) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getBus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSeatNo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getIsladies() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSeatStatus() {
        return null;
    }
    
    public BusSeatsItem() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ferri.userapp.model.BusSeatsItem copy(@org.jetbrains.annotations.Nullable
    java.lang.Integer bus, @org.jetbrains.annotations.Nullable
    java.lang.String seatNo, @org.jetbrains.annotations.Nullable
    java.lang.String type, @org.jetbrains.annotations.Nullable
    java.lang.Boolean isladies, @org.jetbrains.annotations.Nullable
    java.lang.String seatStatus) {
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