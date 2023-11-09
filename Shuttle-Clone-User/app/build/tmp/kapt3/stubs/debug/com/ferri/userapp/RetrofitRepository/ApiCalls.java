package com.ferri.userapp.RetrofitRepository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u00dc\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\'Jz\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\'Jz\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u0006H\'J2\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001e\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J&\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010$\u001a\u0004\u0018\u00010%H\'J\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J7\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010*\u001a\u0004\u0018\u00010+2\n\b\u0001\u0010,\u001a\u0004\u0018\u00010+H\'\u00a2\u0006\u0002\u0010-JC\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010*\u001a\u0004\u0018\u00010+2\n\b\u0001\u0010,\u001a\u0004\u0018\u00010+2\n\b\u0001\u00100\u001a\u0004\u0018\u00010\u0006H\'\u00a2\u0006\u0002\u00101J>\u00102\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u00103\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u00104\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u00105\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u00106\u001a\b\u0012\u0004\u0012\u0002070\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u00108\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'Jj\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010=\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010>\u001a\u00020\u00062\b\b\u0001\u0010?\u001a\u00020\u00062\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\'J2\u0010@\u001a\b\u0012\u0004\u0012\u00020A0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010,\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J&\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010E\u001a\u0004\u0018\u00010\u0006H\'J\u009e\u0001\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010=\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010G\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010H\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010I\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010>\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010J\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010K\u001a\u0004\u0018\u00010\u0006H\'JJ\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001e\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010J\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010K\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J2\u0010O\u001a\b\u0012\u0004\u0012\u00020P0\u00032\n\b\u0001\u0010Q\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010R\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010S\u001a\u0004\u0018\u00010\u0006H\'J$\u0010T\u001a\b\u0012\u0004\u0012\u00020U0\u00032\n\b\u0001\u0010Q\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010V\u001a\u00020\u0006H\'J&\u0010W\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010X\u001a\u0004\u0018\u00010\u0006H\'J>\u0010Y\u001a\b\u0012\u0004\u0012\u00020Z0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0006H\'J&\u0010[\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010$\u001a\u0004\u0018\u00010\\H\'J2\u0010]\u001a\b\u0012\u0004\u0012\u00020A0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010,\u001a\u0004\u0018\u00010\u0006H\'Jz\u0010^\u001a\b\u0012\u0004\u0012\u00020_0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010`\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010a\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010c\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010d\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\'JL\u0010e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001e\u001a\u0004\u0018\u00010\u00062\u0018\b\u0001\u0010f\u001a\u0012\u0012\u0004\u0012\u00020\u00060gj\b\u0012\u0004\u0012\u00020\u0006`h2\n\b\u0001\u0010i\u001a\u0004\u0018\u00010\u0006H\'J\u0092\u0001\u0010j\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010k\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010`\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010l\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010m\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010n\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010o\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010p\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010q\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010r\u001a\u0004\u0018\u00010\u0006H\'J&\u0010s\u001a\b\u0012\u0004\u0012\u00020t0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001e\u001a\u0004\u0018\u00010\u0006H\'J\u00e7\u0001\u0010u\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010v\u001a\u0004\u0018\u00010w2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010z\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010{\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010|\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010}\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010~\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010\u007f\u001a\u0004\u0018\u00010y2\u000b\b\u0001\u0010\u0080\u0001\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010y2\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010yH\'JV\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00062\f\b\u0001\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u0001H\'\u00a2\u0006\u0003\u0010\u0087\u0001JV\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00062\f\b\u0001\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u0001H\'\u00a2\u0006\u0003\u0010\u0087\u0001J0\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\t\b\u0001\u0010\u0080\u0001\u001a\u00020\u00062\b\b\u0001\u0010X\u001a\u00020+H\'JV\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00062\f\b\u0001\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u0001H\'\u00a2\u0006\u0003\u0010\u0087\u0001\u00a8\u0006\u008b\u0001"}, d2 = {"Lcom/ferri/userapp/RetrofitRepository/ApiCalls;", "", "addMoney", "Lio/reactivex/Single;", "Lcom/ferri/userapp/model/PaymentInitiateDataResponse;", "token", "", "amount", "addOfficeRideAddress", "Lcom/ferri/userapp/model/UserProfileUpdateResponse;", "home_lat", "home_lng", "home_address", "home_timing", "office_lat", "office_lng", "office_address", "office_timing", "busSeatsLayout", "Lcom/ferri/userapp/model/BusSeatsResponseModel;", "address", "route_id", "pickup_stop_id", "drop_stop_id", "type", "has_return", "currentDate", "endDate", "cancelBooking", "Lcom/ferri/userapp/model/DefaultResponse;", "pnr_no", "current_date", "checkWalletBalance", "Lcom/ferri/userapp/model/WalletBalanceResponseModel;", "createBooking", "Lcom/ferri/userapp/model/BookingResponseModel;", "data", "Lcom/ferri/userapp/model/BookingRequestData;", "exploreRoutes", "Lcom/ferri/userapp/model/ExploreRoutesResponseModel;", "getBookingHistory", "Lcom/ferri/userapp/model/BookingTransactionHistoryResponse;", "offset", "", "limit", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lio/reactivex/Single;", "getBookingList", "Lcom/ferri/userapp/model/BookingListResponseModel;", "travelStatus", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lio/reactivex/Single;", "getHelp", "contact", "helpemail", "description", "getOffers", "Lcom/ferri/userapp/model/OffersResponseModel;", "getProfileDetails", "getReferDetails", "Lcom/ferri/userapp/model/ReferCodeModel;", "getRouteFare", "Lcom/ferri/userapp/model/GeneratedFareResponseModel;", "bus_id", "seat_no", "start_date", "getSearchLocation", "Lcom/ferri/userapp/model/SearchLocationResponseModel;", "getWalletHistory", "Lcom/ferri/userapp/model/WalletHistoryResponseModel;", "logOutUser", "ctoken", "payForPass", "pass_id", "pass_no_of_rides", "pass_amount", "payment_mode", "date", "payRouteFee", "paymentSettings", "Lcom/ferri/userapp/model/RzPayDataResponseModel;", "refreshToken", "Lcom/ferri/userapp/model/RefreshTokenModel;", "phone", "csrfToken", "onModel", "registerUser", "Lcom/ferri/userapp/model/UserRegisterResponseModel;", "device_id", "resendOtp", "otp", "routeStops", "Lcom/ferri/userapp/model/RouteStopsResponseModel;", "saveSearchLocationData", "Lcom/ferri/userapp/model/SaveLocationRequestModel;", "searchLocation", "searchRoutes", "Lcom/ferri/userapp/model/SearchRoutesResponseModel;", "pickup_lat", "pickup_long", "drop_lat", "drop_long", "end_date", "setReminder", "every", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "time", "suggestRoute", "pickup_address", "pickup_lng", "drop_address", "drop_lng", "pickup_city", "pickup_state", "drop_city", "drop_state", "tripTracking", "Lcom/ferri/userapp/model/TripTrackingStatusResponse;", "updateUser", "file", "Lokhttp3/MultipartBody$Part;", "firstname", "Lokhttp3/RequestBody;", "lastname", "gender", "email", "referedby", "socialid", "mode", "device_token", "verifyBookingPayment", "paymentId", "orderId", "signature", "status", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/reactivex/Single;", "verifyPassPayment", "verifyUser", "verifyWalletAddPayment", "app_debug"})
public abstract interface ApiCalls {
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/register")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.UserRegisterResponseModel> registerUser(@retrofit2.http.Field(value = "phone")
    @org.jetbrains.annotations.Nullable
    java.lang.String phone, @retrofit2.http.Field(value = "device_id")
    @org.jetbrains.annotations.NotNull
    java.lang.String device_id);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/verify")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> verifyUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "device_token")
    @org.jetbrains.annotations.NotNull
    java.lang.String device_token, @retrofit2.http.Field(value = "otp")
    int otp);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/re-send")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> resendOtp(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "phone")
    @org.jetbrains.annotations.Nullable
    java.lang.String otp);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/refresh-token")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.RefreshTokenModel> refreshToken(@retrofit2.http.Field(value = "phone")
    @org.jetbrains.annotations.Nullable
    java.lang.String phone, @retrofit2.http.Field(value = "csrfToken")
    @org.jetbrains.annotations.Nullable
    java.lang.String csrfToken, @retrofit2.http.Field(value = "onModel")
    @org.jetbrains.annotations.Nullable
    java.lang.String onModel);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/help")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> getHelp(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "contact")
    @org.jetbrains.annotations.Nullable
    java.lang.String contact, @retrofit2.http.Field(value = "helpemail")
    @org.jetbrains.annotations.Nullable
    java.lang.String helpemail, @retrofit2.http.Field(value = "description")
    @org.jetbrains.annotations.Nullable
    java.lang.String description);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/payment/verify")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> verifyWalletAddPayment(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "paymentId")
    @org.jetbrains.annotations.Nullable
    java.lang.String paymentId, @retrofit2.http.Field(value = "orderId")
    @org.jetbrains.annotations.Nullable
    java.lang.String orderId, @retrofit2.http.Field(value = "signature")
    @org.jetbrains.annotations.Nullable
    java.lang.String signature, @retrofit2.http.Field(value = "status")
    @org.jetbrains.annotations.Nullable
    java.lang.Boolean status);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/pass-payment-verify")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> verifyPassPayment(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "paymentId")
    @org.jetbrains.annotations.Nullable
    java.lang.String paymentId, @retrofit2.http.Field(value = "orderId")
    @org.jetbrains.annotations.Nullable
    java.lang.String orderId, @retrofit2.http.Field(value = "signature")
    @org.jetbrains.annotations.Nullable
    java.lang.String signature, @retrofit2.http.Field(value = "status")
    @org.jetbrains.annotations.Nullable
    java.lang.Boolean status);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/payment-verify")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> verifyBookingPayment(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "paymentId")
    @org.jetbrains.annotations.Nullable
    java.lang.String paymentId, @retrofit2.http.Field(value = "orderId")
    @org.jetbrains.annotations.Nullable
    java.lang.String orderId, @retrofit2.http.Field(value = "signature")
    @org.jetbrains.annotations.Nullable
    java.lang.String signature, @retrofit2.http.Field(value = "status")
    @org.jetbrains.annotations.Nullable
    java.lang.Boolean status);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/addmoney")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.PaymentInitiateDataResponse> addMoney(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "amount")
    @org.jetbrains.annotations.Nullable
    java.lang.String amount);
    
    @retrofit2.http.GET(value = "users/walletcheck")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.WalletBalanceResponseModel> checkWalletBalance(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/trip-track")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.TripTrackingStatusResponse> tripTracking(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pnr_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/searchlocation")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.SearchLocationResponseModel> searchLocation(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "address")
    @org.jetbrains.annotations.Nullable
    java.lang.String address, @retrofit2.http.Field(value = "limit")
    @org.jetbrains.annotations.Nullable
    java.lang.String limit);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "searches/google")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.SearchLocationResponseModel> getSearchLocation(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "address")
    @org.jetbrains.annotations.Nullable
    java.lang.String address, @retrofit2.http.Field(value = "limit")
    @org.jetbrains.annotations.Nullable
    java.lang.String limit);
    
    @retrofit2.http.POST(value = "searches/savelocation")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> saveSearchLocationData(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Body
    @org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.SaveLocationRequestModel data);
    
    @retrofit2.http.GET(value = "routes/explore")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.ExploreRoutesResponseModel> exploreRoutes(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "routes/{id}")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.RouteStopsResponseModel> routeStops(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.Nullable
    java.lang.String address, @retrofit2.http.Field(value = "pickup_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_stop_id, @retrofit2.http.Field(value = "drop_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_stop_id);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "buses/{id}")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.BusSeatsResponseModel> busSeatsLayout(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.Nullable
    java.lang.String address, @retrofit2.http.Field(value = "route_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String route_id, @retrofit2.http.Field(value = "pickup_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_stop_id, @retrofit2.http.Field(value = "drop_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_stop_id, @retrofit2.http.Field(value = "type")
    @org.jetbrains.annotations.Nullable
    java.lang.String type, @retrofit2.http.Field(value = "has_return")
    @org.jetbrains.annotations.Nullable
    java.lang.String has_return, @retrofit2.http.Field(value = "current_date")
    @org.jetbrains.annotations.Nullable
    java.lang.String currentDate, @retrofit2.http.Field(value = "end_date")
    @org.jetbrains.annotations.Nullable
    java.lang.String endDate);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "routes/route-search")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.SearchRoutesResponseModel> searchRoutes(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pickup_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_lat, @retrofit2.http.Field(value = "pickup_long")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_long, @retrofit2.http.Field(value = "drop_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_lat, @retrofit2.http.Field(value = "drop_long")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_long, @retrofit2.http.Field(value = "current_date")
    @org.jetbrains.annotations.Nullable
    java.lang.String current_date, @retrofit2.http.Field(value = "end_date")
    @org.jetbrains.annotations.Nullable
    java.lang.String end_date, @retrofit2.http.Field(value = "type")
    @org.jetbrains.annotations.Nullable
    java.lang.String type, @retrofit2.http.Field(value = "has_return")
    @org.jetbrains.annotations.Nullable
    java.lang.String has_return);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/add-update-office-and-home")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.UserProfileUpdateResponse> addOfficeRideAddress(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "home_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String home_lat, @retrofit2.http.Field(value = "home_lng")
    @org.jetbrains.annotations.Nullable
    java.lang.String home_lng, @retrofit2.http.Field(value = "home_address")
    @org.jetbrains.annotations.Nullable
    java.lang.String home_address, @retrofit2.http.Field(value = "home_timing")
    @org.jetbrains.annotations.Nullable
    java.lang.String home_timing, @retrofit2.http.Field(value = "office_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String office_lat, @retrofit2.http.Field(value = "office_lng")
    @org.jetbrains.annotations.Nullable
    java.lang.String office_lng, @retrofit2.http.Field(value = "office_address")
    @org.jetbrains.annotations.Nullable
    java.lang.String office_address, @retrofit2.http.Field(value = "office_timing")
    @org.jetbrains.annotations.Nullable
    java.lang.String office_timing);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/payment-pass")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.PaymentInitiateDataResponse> payForPass(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "bus_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String bus_id, @retrofit2.http.Field(value = "route_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String route_id, @retrofit2.http.Field(value = "pickup_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_stop_id, @retrofit2.http.Field(value = "drop_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_stop_id, @retrofit2.http.Field(value = "pass_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pass_id, @retrofit2.http.Field(value = "pass_no_of_rides")
    @org.jetbrains.annotations.Nullable
    java.lang.String pass_no_of_rides, @retrofit2.http.Field(value = "pass_amount")
    @org.jetbrains.annotations.Nullable
    java.lang.String pass_amount, @retrofit2.http.Field(value = "seat_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String seat_no, @retrofit2.http.Field(value = "has_return")
    @org.jetbrains.annotations.Nullable
    java.lang.String has_return, @retrofit2.http.Field(value = "payment_mode")
    @org.jetbrains.annotations.Nullable
    java.lang.String payment_mode, @retrofit2.http.Field(value = "date")
    @org.jetbrains.annotations.Nullable
    java.lang.String date);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "fare/generate-seat-fare")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.GeneratedFareResponseModel> getRouteFare(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "bus_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String bus_id, @retrofit2.http.Field(value = "route_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String route_id, @retrofit2.http.Field(value = "pickup_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_stop_id, @retrofit2.http.Field(value = "drop_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_stop_id, @retrofit2.http.Field(value = "seat_no")
    @org.jetbrains.annotations.NotNull
    java.lang.String seat_no, @retrofit2.http.Field(value = "start_date")
    @org.jetbrains.annotations.NotNull
    java.lang.String start_date, @retrofit2.http.Field(value = "has_return")
    @org.jetbrains.annotations.Nullable
    java.lang.String has_return);
    
    @retrofit2.http.POST(value = "booking/create")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.BookingResponseModel> createBooking(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Body
    @org.jetbrains.annotations.Nullable
    com.ferri.userapp.model.BookingRequestData data);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/my-trips")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.BookingListResponseModel> getBookingList(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "offset")
    @org.jetbrains.annotations.Nullable
    java.lang.Integer offset, @retrofit2.http.Field(value = "limit")
    @org.jetbrains.annotations.Nullable
    java.lang.Integer limit, @retrofit2.http.Field(value = "travel_status")
    @org.jetbrains.annotations.Nullable
    java.lang.String travelStatus);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/payment")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.PaymentInitiateDataResponse> payRouteFee(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pnr_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no, @retrofit2.http.Field(value = "amount")
    @org.jetbrains.annotations.Nullable
    java.lang.String amount, @retrofit2.http.Field(value = "payment_mode")
    @org.jetbrains.annotations.Nullable
    java.lang.String payment_mode, @retrofit2.http.Field(value = "date")
    @org.jetbrains.annotations.Nullable
    java.lang.String date);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/setreminder")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> setReminder(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pnr_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no, @retrofit2.http.Field(value = "every")
    @org.jetbrains.annotations.NotNull
    java.util.ArrayList<java.lang.String> every, @retrofit2.http.Field(value = "time")
    @org.jetbrains.annotations.Nullable
    java.lang.String time);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "suggest/create")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> suggestRoute(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pickup_address")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_address, @retrofit2.http.Field(value = "pickup_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_lat, @retrofit2.http.Field(value = "pickup_lng")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_lng, @retrofit2.http.Field(value = "drop_address")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_address, @retrofit2.http.Field(value = "drop_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_lat, @retrofit2.http.Field(value = "drop_lng")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_lng, @retrofit2.http.Field(value = "pickup_city")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_city, @retrofit2.http.Field(value = "pickup_state")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_state, @retrofit2.http.Field(value = "drop_city")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_city, @retrofit2.http.Field(value = "drop_state")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_state);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/cancel")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> cancelBooking(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pnr_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no, @retrofit2.http.Field(value = "current_date")
    @org.jetbrains.annotations.Nullable
    java.lang.String current_date);
    
    @retrofit2.http.GET(value = "offers")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.OffersResponseModel> getOffers(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.GET(value = "users/wallet-transactions")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.WalletHistoryResponseModel> getWalletHistory(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.GET(value = "users/booking-transactions")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.BookingTransactionHistoryResponse> getBookingHistory(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Query(value = "offset")
    @org.jetbrains.annotations.Nullable
    java.lang.Integer offset, @retrofit2.http.Query(value = "limit")
    @org.jetbrains.annotations.Nullable
    java.lang.Integer limit);
    
    @retrofit2.http.PUT(value = "users/logout")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.DefaultResponse> logOutUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Header(value = "csrf-token")
    @org.jetbrains.annotations.Nullable
    java.lang.String ctoken);
    
    @retrofit2.http.GET(value = "users/me")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.UserProfileUpdateResponse> getProfileDetails(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.GET(value = "users/refercode")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.ReferCodeModel> getReferDetails(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.GET(value = "settings?type=payments")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.RzPayDataResponseModel> paymentSettings(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.Multipart
    @retrofit2.http.POST(value = "users/updateuser")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.ferri.userapp.model.UserProfileUpdateResponse> updateUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Part
    @org.jetbrains.annotations.Nullable
    okhttp3.MultipartBody.Part file, @retrofit2.http.Part(value = "firstname")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody firstname, @retrofit2.http.Part(value = "lastname")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody lastname, @retrofit2.http.Part(value = "gender")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody gender, @retrofit2.http.Part(value = "email")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody email, @retrofit2.http.Part(value = "referedby")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody referedby, @retrofit2.http.Part(value = "social_id")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody socialid, @retrofit2.http.Part(value = "mode")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody mode, @retrofit2.http.Part(value = "device_token")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody device_token, @retrofit2.http.Part(value = "home_address")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody home_address, @retrofit2.http.Part(value = "home_lat")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody home_lat, @retrofit2.http.Part(value = "home_lng")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody home_lng, @retrofit2.http.Part(value = "office_address")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody office_address, @retrofit2.http.Part(value = "office_lat")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody office_lat, @retrofit2.http.Part(value = "office_lng")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody office_lng, @retrofit2.http.Part(value = "home_timing")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody home_timing, @retrofit2.http.Part(value = "office_timing")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody office_timing);
}