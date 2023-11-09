package com.ferri.userapp.ui.adapter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u0016\u0017B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u001c\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0017J\u0010\u0010\u0015\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/ferri/userapp/ui/adapter/RecentSearchAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ferri/userapp/ui/adapter/RecentSearchAdapter$RecentSearchViewHolder;", "mContext", "Landroid/content/Context;", "mRecentSearchList", "", "Lcom/ferri/userapp/Db/RecentSearchData;", "(Landroid/content/Context;Ljava/util/List;)V", "mListener", "Lcom/ferri/userapp/ui/adapter/RecentSearchAdapter$onClickListener;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnClickListener", "RecentSearchViewHolder", "onClickListener", "app_debug"})
public final class RecentSearchAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.ferri.userapp.ui.adapter.RecentSearchAdapter.RecentSearchViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context mContext = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.ferri.userapp.Db.RecentSearchData> mRecentSearchList = null;
    @org.jetbrains.annotations.Nullable
    private com.ferri.userapp.ui.adapter.RecentSearchAdapter.onClickListener mListener;
    
    public RecentSearchAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.util.List<com.ferri.userapp.Db.RecentSearchData> mRecentSearchList) {
        super();
    }
    
    public final void setOnClickListener(@org.jetbrains.annotations.Nullable
    com.ferri.userapp.ui.adapter.RecentSearchAdapter.onClickListener mListener) {
    }
    
    @java.lang.Override
    @android.annotation.SuppressLint(value = {"InflateParams"})
    @org.jetbrains.annotations.NotNull
    public com.ferri.userapp.ui.adapter.RecentSearchAdapter.RecentSearchViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.adapter.RecentSearchAdapter.RecentSearchViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u000f"}, d2 = {"Lcom/ferri/userapp/ui/adapter/RecentSearchAdapter$RecentSearchViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ferri/userapp/ui/adapter/RecentSearchAdapter;Landroid/view/View;)V", "mBtnBook", "Landroid/widget/Button;", "getMBtnBook", "()Landroid/widget/Button;", "mTvDate", "Landroid/widget/TextView;", "getMTvDate", "()Landroid/widget/TextView;", "mTvDestination", "getMTvDestination", "app_debug"})
    public final class RecentSearchViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvDestination = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvDate = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.Button mBtnBook = null;
        
        public RecentSearchViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvDestination() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.Button getMBtnBook() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/ferri/userapp/ui/adapter/RecentSearchAdapter$onClickListener;", "", "onClick", "", "cardModel", "Lcom/ferri/userapp/Db/RecentSearchData;", "app_debug"})
    public static abstract interface onClickListener {
        
        public abstract void onClick(@org.jetbrains.annotations.NotNull
        com.ferri.userapp.Db.RecentSearchData cardModel);
    }
}