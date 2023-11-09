package com.ferri.userapp.ui.adapter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0010\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001b\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lcom/ferri/userapp/ui/adapter/ViewStopsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ferri/userapp/ui/adapter/ViewStopsAdapter$ViewHolder;", "context", "Lcom/ferri/userapp/ui/activity/BusRoutesActivity;", "stopsData", "", "Lcom/ferri/userapp/model/StopsItem;", "(Lcom/ferri/userapp/ui/activity/BusRoutesActivity;Ljava/util/List;)V", "getContext", "()Lcom/ferri/userapp/ui/activity/BusRoutesActivity;", "getStopsData", "()Ljava/util/List;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"})
public final class ViewStopsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.ferri.userapp.ui.adapter.ViewStopsAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final com.ferri.userapp.ui.activity.BusRoutesActivity context = null;
    @org.jetbrains.annotations.Nullable
    private final java.util.List<com.ferri.userapp.model.StopsItem> stopsData = null;
    
    public ViewStopsAdapter(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.activity.BusRoutesActivity context, @org.jetbrains.annotations.Nullable
    java.util.List<com.ferri.userapp.model.StopsItem> stopsData) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ferri.userapp.ui.activity.BusRoutesActivity getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.ferri.userapp.model.StopsItem> getStopsData() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.ferri.userapp.ui.adapter.ViewStopsAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.adapter.ViewStopsAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/ferri/userapp/ui/adapter/ViewStopsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "imgStopStatus", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "getImgStopStatus", "()Landroid/widget/ImageView;", "tvStopTitle", "Landroid/widget/TextView;", "getTvStopTitle", "()Landroid/widget/TextView;", "app_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final android.widget.ImageView imgStopStatus = null;
        private final android.widget.TextView tvStopTitle = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View view) {
            super(null);
        }
        
        public final android.widget.ImageView getImgStopStatus() {
            return null;
        }
        
        public final android.widget.TextView getTvStopTitle() {
            return null;
        }
    }
}