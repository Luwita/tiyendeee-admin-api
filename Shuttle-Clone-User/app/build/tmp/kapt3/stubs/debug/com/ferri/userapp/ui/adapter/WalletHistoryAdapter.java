package com.ferri.userapp.ui.adapter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/ferri/userapp/ui/adapter/WalletHistoryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ferri/userapp/ui/adapter/WalletHistoryAdapter$WalletHistoryViewHolder;", "mContext", "Landroid/content/Context;", "mWalletHistoryList", "Ljava/util/ArrayList;", "Lcom/ferri/userapp/model/WalletData;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "WalletHistoryViewHolder", "app_debug"})
public final class WalletHistoryAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.ferri.userapp.ui.adapter.WalletHistoryAdapter.WalletHistoryViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context mContext = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.ArrayList<com.ferri.userapp.model.WalletData> mWalletHistoryList = null;
    
    public WalletHistoryAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.ferri.userapp.model.WalletData> mWalletHistoryList) {
        super();
    }
    
    @java.lang.Override
    @android.annotation.SuppressLint(value = {"InflateParams"})
    @org.jetbrains.annotations.NotNull
    public com.ferri.userapp.ui.adapter.WalletHistoryAdapter.WalletHistoryViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.adapter.WalletHistoryAdapter.WalletHistoryViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e\u00a8\u0006\u0015"}, d2 = {"Lcom/ferri/userapp/ui/adapter/WalletHistoryAdapter$WalletHistoryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mIvPaymentStatus", "Landroid/widget/ImageView;", "getMIvPaymentStatus", "()Landroid/widget/ImageView;", "mIvWhImg", "getMIvWhImg", "mTvAmount", "Landroid/widget/TextView;", "getMTvAmount", "()Landroid/widget/TextView;", "mTvDate", "getMTvDate", "mTvPaymentMode", "getMTvPaymentMode", "mTvTitle", "getMTvTitle", "app_debug"})
    public static final class WalletHistoryViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvDate = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvTitle = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvAmount = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvPaymentMode = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView mIvWhImg = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView mIvPaymentStatus = null;
        
        public WalletHistoryViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvAmount() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvPaymentMode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getMIvWhImg() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getMIvPaymentStatus() {
            return null;
        }
    }
}