package com.ferri.userapp.Adapters;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 42\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u000245B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010$\u001a\u00020%H\u0016J\u001c\u0010&\u001a\u00020\'2\n\u0010(\u001a\u00060\u0002R\u00020\u00002\u0006\u0010)\u001a\u00020%H\u0016J\u001c\u0010*\u001a\u00060\u0002R\u00020\u00002\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020%H\u0016J$\u0010.\u001a\u00020\'2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\tJ\u0010\u00102\u001a\u00020\'2\b\u00103\u001a\u0004\u0018\u00010\tR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001c\u0010\u0011\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u0010\n\u0002\b\u0014\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\u00a8\u00066"}, d2 = {"Lcom/ferri/userapp/Adapters/ApiLocationSearchResultAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ferri/userapp/Adapters/ApiLocationSearchResultAdapter$ViewHolder;", "mContaxt", "Landroid/content/Context;", "listner", "Lcom/ferri/userapp/ui/fragment/SearchLocationDialogFragment;", "(Landroid/content/Context;Lcom/ferri/userapp/ui/fragment/SearchLocationDialogFragment;)V", "SET_ADDRESS_FOR", "", "getSET_ADDRESS_FOR", "()Ljava/lang/String;", "setSET_ADDRESS_FOR", "(Ljava/lang/String;)V", "SET_RESULT_TO", "getSET_RESULT_TO", "setSET_RESULT_TO", "TAG", "getTAG", "setTAG", "TAG$1", "getListner", "()Lcom/ferri/userapp/ui/fragment/SearchLocationDialogFragment;", "setListner", "(Lcom/ferri/userapp/ui/fragment/SearchLocationDialogFragment;)V", "getMContaxt", "()Landroid/content/Context;", "setMContaxt", "(Landroid/content/Context;)V", "searchedDataItem", "", "Lcom/ferri/userapp/model/SearchedDataItem;", "getSearchedDataItem", "()Ljava/util/List;", "setSearchedDataItem", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "searchedDataItems", "setResultTo", "setAddressFor", "setResultTO", "resultTO", "Companion", "ViewHolder", "app_debug"})
public final class ApiLocationSearchResultAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.ferri.userapp.Adapters.ApiLocationSearchResultAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private android.content.Context mContaxt;
    @org.jetbrains.annotations.NotNull
    private com.ferri.userapp.ui.fragment.SearchLocationDialogFragment listner;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.ferri.userapp.model.SearchedDataItem> searchedDataItem;
    @org.jetbrains.annotations.Nullable
    private java.lang.String SET_RESULT_TO = "";
    @org.jetbrains.annotations.Nullable
    private java.lang.String SET_ADDRESS_FOR = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String TAG$1 = "";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "LocationSearchResultAdapter";
    @org.jetbrains.annotations.NotNull
    public static final com.ferri.userapp.Adapters.ApiLocationSearchResultAdapter.Companion Companion = null;
    
    public ApiLocationSearchResultAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context mContaxt, @org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.fragment.SearchLocationDialogFragment listner) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getMContaxt() {
        return null;
    }
    
    public final void setMContaxt(@org.jetbrains.annotations.NotNull
    android.content.Context p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ferri.userapp.ui.fragment.SearchLocationDialogFragment getListner() {
        return null;
    }
    
    public final void setListner(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.fragment.SearchLocationDialogFragment p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.ferri.userapp.model.SearchedDataItem> getSearchedDataItem() {
        return null;
    }
    
    public final void setSearchedDataItem(@org.jetbrains.annotations.NotNull
    java.util.List<com.ferri.userapp.model.SearchedDataItem> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSET_RESULT_TO() {
        return null;
    }
    
    public final void setSET_RESULT_TO(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSET_ADDRESS_FOR() {
        return null;
    }
    
    public final void setSET_ADDRESS_FOR(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTAG() {
        return null;
    }
    
    public final void setTAG(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.ferri.userapp.Adapters.ApiLocationSearchResultAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.Adapters.ApiLocationSearchResultAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    public final void setResultTO(@org.jetbrains.annotations.Nullable
    java.lang.String resultTO) {
    }
    
    public final void setData(@org.jetbrains.annotations.NotNull
    java.util.List<com.ferri.userapp.model.SearchedDataItem> searchedDataItems, @org.jetbrains.annotations.NotNull
    java.lang.String setResultTo, @org.jetbrains.annotations.NotNull
    java.lang.String setAddressFor) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/ferri/userapp/Adapters/ApiLocationSearchResultAdapter$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\n\u00a8\u0006\u001a"}, d2 = {"Lcom/ferri/userapp/Adapters/ApiLocationSearchResultAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ferri/userapp/Adapters/ApiLocationSearchResultAdapter;Landroid/view/View;)V", "primaryLocation_tv", "Landroid/widget/TextView;", "getPrimaryLocation_tv", "()Landroid/widget/TextView;", "setPrimaryLocation_tv", "(Landroid/widget/TextView;)V", "rootLaout", "Landroid/widget/LinearLayout;", "getRootLaout", "()Landroid/widget/LinearLayout;", "setRootLaout", "(Landroid/widget/LinearLayout;)V", "search_result_icon", "Landroid/widget/ImageView;", "getSearch_result_icon", "()Landroid/widget/ImageView;", "setSearch_result_icon", "(Landroid/widget/ImageView;)V", "secondary_location_tv", "getSecondary_location_tv", "setSecondary_location_tv", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private android.widget.TextView primaryLocation_tv;
        @org.jetbrains.annotations.NotNull
        private android.widget.TextView secondary_location_tv;
        @org.jetbrains.annotations.NotNull
        private android.widget.ImageView search_result_icon;
        @org.jetbrains.annotations.NotNull
        private android.widget.LinearLayout rootLaout;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getPrimaryLocation_tv() {
            return null;
        }
        
        public final void setPrimaryLocation_tv(@org.jetbrains.annotations.NotNull
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getSecondary_location_tv() {
            return null;
        }
        
        public final void setSecondary_location_tv(@org.jetbrains.annotations.NotNull
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getSearch_result_icon() {
            return null;
        }
        
        public final void setSearch_result_icon(@org.jetbrains.annotations.NotNull
        android.widget.ImageView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.LinearLayout getRootLaout() {
            return null;
        }
        
        public final void setRootLaout(@org.jetbrains.annotations.NotNull
        android.widget.LinearLayout p0) {
        }
    }
}