package com.ferri.userapp.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\u000eJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\tH\u00c6\u0003Jb\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\tH\u00c6\u0001\u00a2\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020\u0005H\u00d6\u0001J\t\u0010)\u001a\u00020\tH\u00d6\u0001R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\r\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0018\u0010\f\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006*"}, d2 = {"Lcom/ferri/userapp/model/InitiateData;", "", "paymentSettings", "Lcom/ferri/userapp/model/PaymentSettings;", "amount", "", "notes", "Lcom/ferri/userapp/model/Notes;", "orderId", "", "prefill", "Lcom/ferri/userapp/model/Prefill;", "paymentMode", "name", "(Lcom/ferri/userapp/model/PaymentSettings;Ljava/lang/Integer;Lcom/ferri/userapp/model/Notes;Ljava/lang/String;Lcom/ferri/userapp/model/Prefill;Ljava/lang/String;Ljava/lang/String;)V", "getAmount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getName", "()Ljava/lang/String;", "getNotes", "()Lcom/ferri/userapp/model/Notes;", "getOrderId", "getPaymentMode", "getPaymentSettings", "()Lcom/ferri/userapp/model/PaymentSettings;", "getPrefill", "()Lcom/ferri/userapp/model/Prefill;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Lcom/ferri/userapp/model/PaymentSettings;Ljava/lang/Integer;Lcom/ferri/userapp/model/Notes;Ljava/lang/String;Lcom/ferri/userapp/model/Prefill;Ljava/lang/String;Ljava/lang/String;)Lcom/ferri/userapp/model/InitiateData;", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class InitiateData {
    @com.google.gson.annotations.SerializedName(value = "payment_settings")
    @org.jetbrains.annotations.Nullable
    private final com.ferri.userapp.model.PaymentSettings paymentSettings = null;
    @com.google.gson.annotations.SerializedName(value = "amount")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer amount = null;
    @com.google.gson.annotations.SerializedName(value = "notes")
    @org.jetbrains.annotations.Nullable
    private final com.ferri.userapp.model.Notes notes = null;
    @com.google.gson.annotations.SerializedName(value = "orderId")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String orderId = null;
    @com.google.gson.annotations.SerializedName(value = "prefill")
    @org.jetbrains.annotations.Nullable
    private final com.ferri.userapp.model.Prefill prefill = null;
    @com.google.gson.annotations.SerializedName(value = "payment_mode")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String paymentMode = null;
    @com.google.gson.annotations.SerializedName(value = "name")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String name = null;
    
    public InitiateData(@org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.PaymentSettings paymentSettings, @org.jetbrains.annotations.Nullable
    java.lang.Integer amount, @org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.Notes notes, @org.jetbrains.annotations.Nullable
    java.lang.String orderId, @org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.Prefill prefill, @org.jetbrains.annotations.Nullable
    java.lang.String paymentMode, @org.jetbrains.annotations.Nullable
    java.lang.String name) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.ferri.userapp.model.PaymentSettings getPaymentSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getAmount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.ferri.userapp.model.Notes getNotes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOrderId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.ferri.userapp.model.Prefill getPrefill() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPaymentMode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getName() {
        return null;
    }
    
    public InitiateData() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.ferri.userapp.model.PaymentSettings component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.ferri.userapp.model.Notes component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.ferri.userapp.model.Prefill component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ferri.userapp.model.InitiateData copy(@org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.PaymentSettings paymentSettings, @org.jetbrains.annotations.Nullable
    java.lang.Integer amount, @org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.Notes notes, @org.jetbrains.annotations.Nullable
    java.lang.String orderId, @org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.Prefill prefill, @org.jetbrains.annotations.Nullable
    java.lang.String paymentMode, @org.jetbrains.annotations.Nullable
    java.lang.String name) {
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