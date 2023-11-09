package com.ferri.userapp.ui.adapter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001a\u001bB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u0018\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010H\u0017J\u0010\u0010\u0019\u001a\u00020\u00122\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001c"}, d2 = {"Lcom/ferri/userapp/ui/adapter/NewOfferAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ferri/userapp/ui/adapter/NewOfferAdapter$NewofferViewHolder;", "mContext", "Landroid/content/Context;", "mOfferList", "Ljava/util/ArrayList;", "Lcom/ferri/userapp/model/OffersData;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "getMContext", "()Landroid/content/Context;", "mListener", "Lcom/ferri/userapp/ui/adapter/NewOfferAdapter$onClickListener;", "getMOfferList", "()Ljava/util/ArrayList;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnClickListener", "NewofferViewHolder", "onClickListener", "app_debug"})
public final class NewOfferAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.ferri.userapp.ui.adapter.NewOfferAdapter.NewofferViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context mContext = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.ArrayList<com.ferri.userapp.model.OffersData> mOfferList = null;
    @org.jetbrains.annotations.Nullable
    private com.ferri.userapp.ui.adapter.NewOfferAdapter.onClickListener mListener;
    
    public NewOfferAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.ferri.userapp.model.OffersData> mOfferList) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getMContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<com.ferri.userapp.model.OffersData> getMOfferList() {
        return null;
    }
    
    public final void setOnClickListener(@org.jetbrains.annotations.Nullable
    com.ferri.userapp.ui.adapter.NewOfferAdapter.onClickListener mListener) {
    }
    
    @java.lang.Override
    @android.annotation.SuppressLint(value = {"InflateParams"})
    @org.jetbrains.annotations.NotNull
    public com.ferri.userapp.ui.adapter.NewOfferAdapter.NewofferViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.adapter.NewOfferAdapter.NewofferViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/ferri/userapp/ui/adapter/NewOfferAdapter$NewofferViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "ivImageSrc", "Landroid/widget/ImageView;", "getIvImageSrc", "()Landroid/widget/ImageView;", "mLlOffer", "Landroid/widget/LinearLayout;", "getMLlOffer", "()Landroid/widget/LinearLayout;", "mRlOfferBackground", "Landroidx/cardview/widget/CardView;", "getMRlOfferBackground", "()Landroidx/cardview/widget/CardView;", "mTvSpecial", "Landroid/widget/TextView;", "getMTvSpecial", "()Landroid/widget/TextView;", "app_debug"})
    public static final class NewofferViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvSpecial = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView ivImageSrc = null;
        @org.jetbrains.annotations.NotNull
        private final androidx.cardview.widget.CardView mRlOfferBackground = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.LinearLayout mLlOffer = null;
        
        public NewofferViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvSpecial() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getIvImageSrc() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.cardview.widget.CardView getMRlOfferBackground() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.LinearLayout getMLlOffer() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/ferri/userapp/ui/adapter/NewOfferAdapter$onClickListener;", "", "onClick", "", "i", "", "app_debug"})
    public static abstract interface onClickListener {
        
        public abstract void onClick(int i);
    }
}