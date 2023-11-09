package com.ferri.userapp.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010!\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0011\u0010%\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010)\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0017J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u008c\u0001\u0010+\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010,J\u0013\u0010-\u001a\u00020\u000e2\b\u0010.\u001a\u0004\u0018\u00010/H\u00d6\u0003J\t\u00100\u001a\u00020\u0003H\u00d6\u0001J\t\u00101\u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\r\u0010\u0017R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u001b\u0010\u0013R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b \u0010\u0013\u00a8\u00062"}, d2 = {"Lcom/ferri/userapp/model/BookingDataItem;", "Ljava/io/Serializable;", "amount", "", "method", "", "paymentStatus", "bookingDetails", "", "Lcom/ferri/userapp/model/BookingDetailsItem;", "title", "type", "noOfPasses", "isPass", "", "paymentCreated", "status", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "getAmount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBookingDetails", "()Ljava/util/List;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getMethod", "()Ljava/lang/String;", "getNoOfPasses", "getPaymentCreated", "getPaymentStatus", "getStatus", "getTitle", "getType", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lcom/ferri/userapp/model/BookingDataItem;", "equals", "other", "", "hashCode", "toString", "app_debug"})
public final class BookingDataItem implements java.io.Serializable {
    @com.google.gson.annotations.SerializedName(value = "amount")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer amount = null;
    @com.google.gson.annotations.SerializedName(value = "method")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String method = null;
    @com.google.gson.annotations.SerializedName(value = "payment_status")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String paymentStatus = null;
    @com.google.gson.annotations.SerializedName(value = "booking_details")
    @org.jetbrains.annotations.Nullable
    private final java.util.List<com.ferri.userapp.model.BookingDetailsItem> bookingDetails = null;
    @com.google.gson.annotations.SerializedName(value = "title")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String title = null;
    @com.google.gson.annotations.SerializedName(value = "type")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer type = null;
    @com.google.gson.annotations.SerializedName(value = "no_of_pasess")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer noOfPasses = null;
    @com.google.gson.annotations.SerializedName(value = "is_pass")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Boolean isPass = null;
    @com.google.gson.annotations.SerializedName(value = "payment_created")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String paymentCreated = null;
    @com.google.gson.annotations.SerializedName(value = "status")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String status = null;
    
    public BookingDataItem(@org.jetbrains.annotations.Nullable
    java.lang.Integer amount, @org.jetbrains.annotations.Nullable
    java.lang.String method, @org.jetbrains.annotations.Nullable
    java.lang.String paymentStatus, @org.jetbrains.annotations.Nullable
    java.util.List<com.ferri.userapp.model.BookingDetailsItem> bookingDetails, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.Integer type, @org.jetbrains.annotations.Nullable
    java.lang.Integer noOfPasses, @org.jetbrains.annotations.Nullable
    java.lang.Boolean isPass, @org.jetbrains.annotations.Nullable
    java.lang.String paymentCreated, @org.jetbrains.annotations.Nullable
    java.lang.String status) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getAmount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMethod() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPaymentStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.ferri.userapp.model.BookingDetailsItem> getBookingDetails() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getNoOfPasses() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean isPass() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPaymentCreated() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getStatus() {
        return null;
    }
    
    public BookingDataItem() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
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
    public final java.util.List<com.ferri.userapp.model.BookingDetailsItem> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ferri.userapp.model.BookingDataItem copy(@org.jetbrains.annotations.Nullable
    java.lang.Integer amount, @org.jetbrains.annotations.Nullable
    java.lang.String method, @org.jetbrains.annotations.Nullable
    java.lang.String paymentStatus, @org.jetbrains.annotations.Nullable
    java.util.List<com.ferri.userapp.model.BookingDetailsItem> bookingDetails, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.Integer type, @org.jetbrains.annotations.Nullable
    java.lang.Integer noOfPasses, @org.jetbrains.annotations.Nullable
    java.lang.Boolean isPass, @org.jetbrains.annotations.Nullable
    java.lang.String paymentCreated, @org.jetbrains.annotations.Nullable
    java.lang.String status) {
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