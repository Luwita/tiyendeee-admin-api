package com.ferri.userapp.ui.adapter;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0019B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u001c\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010H\u0017R\u000e\u0010\r\u001a\u00020\u000bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/ferri/userapp/ui/adapter/BookingTransactionHistoryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ferri/userapp/ui/adapter/BookingTransactionHistoryAdapter$BookingTransactionHistoryViewHolder;", "mCtx", "Landroid/content/Context;", "mBookTransList", "", "Lcom/ferri/userapp/model/BookingDataItem;", "mPassDetailsList", "Lcom/ferri/userapp/model/BookingDetailsItem;", "callFrom", "", "(Landroid/content/Context;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "TAG", "bookingData", "getItemCount", "", "onBindViewHolder", "", "holder1", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BookingTransactionHistoryViewHolder", "app_debug"})
public final class BookingTransactionHistoryAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.ferri.userapp.ui.adapter.BookingTransactionHistoryAdapter.BookingTransactionHistoryViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context mCtx = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.ferri.userapp.model.BookingDataItem> mBookTransList = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.ferri.userapp.model.BookingDetailsItem> mPassDetailsList = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String callFrom = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "BookingAdapter";
    private com.ferri.userapp.model.BookingDetailsItem bookingData;
    
    public BookingTransactionHistoryAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context mCtx, @org.jetbrains.annotations.NotNull
    java.util.List<com.ferri.userapp.model.BookingDataItem> mBookTransList, @org.jetbrains.annotations.NotNull
    java.util.List<com.ferri.userapp.model.BookingDetailsItem> mPassDetailsList, @org.jetbrains.annotations.NotNull
    java.lang.String callFrom) {
        super();
    }
    
    @java.lang.Override
    @android.annotation.SuppressLint(value = {"InflateParams"})
    @org.jetbrains.annotations.NotNull
    public com.ferri.userapp.ui.adapter.BookingTransactionHistoryAdapter.BookingTransactionHistoryViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.ferri.userapp.ui.adapter.BookingTransactionHistoryAdapter.BookingTransactionHistoryViewHolder holder1, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b!\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0017\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0011\u0010\u001f\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0011\u0010!\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0011\u0010#\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0011\u0010%\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0011\u0010\'\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0011\u0010)\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001cR\u0011\u0010+\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0011\u0010-\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0011\u0010/\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001cR\u0011\u00101\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001cR\u0011\u00103\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001cR\u0011\u00105\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001cR\u0011\u00107\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010\u001cR\u0011\u00109\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010\u001c\u00a8\u0006;"}, d2 = {"Lcom/ferri/userapp/ui/adapter/BookingTransactionHistoryAdapter$BookingTransactionHistoryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ferri/userapp/ui/adapter/BookingTransactionHistoryAdapter;Landroid/view/View;)V", "mIvPaymentStatus", "Landroid/widget/ImageView;", "getMIvPaymentStatus", "()Landroid/widget/ImageView;", "mIvShowMore", "getMIvShowMore", "mIvWhImg", "getMIvWhImg", "mLlBusPasses", "Landroid/widget/LinearLayout;", "getMLlBusPasses", "()Landroid/widget/LinearLayout;", "mRlBookingPayment", "Landroid/widget/RelativeLayout;", "getMRlBookingPayment", "()Landroid/widget/RelativeLayout;", "mRlContent", "getMRlContent", "mRlShowMore", "getMRlShowMore", "mTvAmount", "Landroid/widget/TextView;", "getMTvAmount", "()Landroid/widget/TextView;", "mTvBusName", "getMTvBusName", "mTvBusNo", "getMTvBusNo", "mTvBusPassesNo", "getMTvBusPassesNo", "mTvConfirm", "getMTvConfirm", "mTvDate", "getMTvDate", "mTvDestination", "getMTvDestination", "mTvDuration", "getMTvDuration", "mTvEndTime", "getMTvEndTime", "mTvPNRNo", "getMTvPNRNo", "mTvPaymentMode", "getMTvPaymentMode", "mTvSeatNo", "getMTvSeatNo", "mTvStartTime", "getMTvStartTime", "mTvTitle", "getMTvTitle", "mTvTotalTime", "getMTvTotalTime", "mTvViewPassesDetails", "getMTvViewPassesDetails", "app_debug"})
    public final class BookingTransactionHistoryViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvDestination = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvPaymentMode = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvStartTime = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvTotalTime = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvEndTime = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView mIvWhImg = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvPNRNo = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvDate = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvTitle = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvSeatNo = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvAmount = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvBusNo = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvBusName = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.RelativeLayout mRlShowMore = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.RelativeLayout mRlContent = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.RelativeLayout mRlBookingPayment = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView mIvShowMore = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView mIvPaymentStatus = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.LinearLayout mLlBusPasses = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvBusPassesNo = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvViewPassesDetails = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvConfirm = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView mTvDuration = null;
        
        public BookingTransactionHistoryViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvDestination() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvPaymentMode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvStartTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvTotalTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvEndTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getMIvWhImg() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvPNRNo() {
            return null;
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
        public final android.widget.TextView getMTvSeatNo() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvAmount() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvBusNo() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvBusName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.RelativeLayout getMRlShowMore() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.RelativeLayout getMRlContent() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.RelativeLayout getMRlBookingPayment() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getMIvShowMore() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getMIvPaymentStatus() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.LinearLayout getMLlBusPasses() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvBusPassesNo() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvViewPassesDetails() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvConfirm() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getMTvDuration() {
            return null;
        }
    }
}