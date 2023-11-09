package com.ferri.userapp.ui.adapter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/ferri/userapp/ui/adapter/ViewOfferAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ferri/userapp/ui/adapter/ViewOfferAdapter$NewOfferViewHolder;", "mContext", "Landroid/content/Context;", "mOfferList", "Ljava/util/ArrayList;", "Lcom/ferri/userapp/model/OffersData;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "NewOfferViewHolder", "app_debug"})
public final class ViewOfferAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.ferri.userapp.ui.adapter.ViewOfferAdapter.NewOfferViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context mContext = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.ArrayList<com.ferri.userapp.model.OffersData> mOfferList = null;
    
    public ViewOfferAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.ferri.userapp.model.OffersData> mOfferList) {
        super();
    }
    
    @java.lang.Override
    @android.annotation.SuppressLint(value = {"InflateParams"})
    @org.jetbrains.annotations.NotNull
    public com.ferri.userapp.ui.adapter.ViewOfferAdapter.NewOfferViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.adapter.ViewOfferAdapter.NewOfferViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/ferri/userapp/ui/adapter/ViewOfferAdapter$NewOfferViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mIvImageSrc", "Landroid/widget/ImageView;", "getMIvImageSrc", "()Landroid/widget/ImageView;", "mLlOffer", "Landroid/widget/LinearLayout;", "getMLlOffer", "()Landroid/widget/LinearLayout;", "mRlOfferBackground", "Landroidx/cardview/widget/CardView;", "getMRlOfferBackground", "()Landroidx/cardview/widget/CardView;", "mTvSpecial", "Landroid/widget/TextView;", "getMTvSpecial", "()Landroid/widget/TextView;", "app_debug"})
    public static final class NewOfferViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvSpecial = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView mIvImageSrc = null;
        @org.jetbrains.annotations.NotNull
        private final androidx.cardview.widget.CardView mRlOfferBackground = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.LinearLayout mLlOffer = null;
        
        public NewOfferViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvSpecial() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getMIvImageSrc() {
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
}