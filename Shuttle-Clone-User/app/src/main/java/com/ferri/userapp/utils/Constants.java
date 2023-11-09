package com.ferri.userapp.utils;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
    /*variable declaration*/
    public static final String NA = "N/A";
    public static final String BASE_URL = "CHANGE_WITH_YOUR_API_URL"; // YOUR API URL
    public static final String PICKUP = "PICKUP";
    public static final String DROP = "DROP";
    public static final String IsCheckedOffice = "IsCheckedOffice";
    public static final boolean ByPassLogin = false;

    /*Date format*/
    public static class DateFormat {
        public static String dd_MM = "dd-MMM";
        public static String dd_MM_yyyy = "dd - MMM - yyyy";
        public static String yyyy_MM_dd = "yyyy-MM-dd";
        public static final SimpleDateFormat DAY_MONTH_FORMATTER = new SimpleDateFormat(dd_MM, Locale.getDefault());
        public static final SimpleDateFormat DAY_MONTH_YEAR_FORMATTER = new SimpleDateFormat(dd_MM_yyyy, Locale.getDefault());
        public static final SimpleDateFormat YEAR_MONTH_DAY_FORMATTER = new SimpleDateFormat(yyyy_MM_dd, Locale.getDefault());
    }

    /*intent data*/
    public static class intentdata {
        public static String CARDDETAIL = "carddetail";
        public static String TRAVELLERNAME = "TravellerName";
        public static String TYPECOACH = "typecoach";
        public static String PRICE = "price";
        public static String HOLD = "hold";
        public static String PACKAGE = "package";
        public static String OFFER = "offer";
        public static String TRIP_KEY = "trip_key";
        public static String FROM = "from";
        public static String TO = "to";
        public static String SEARCH_BUS = "search_bus";
        public static String PACKAGE_NAME = "package_name";
        public static String CARDFLAG = "cardflag";
    }

    //SharedPreferences
    public static String TOKEN = "TOKEN";
    public static String csrfTOKEN = "csrfTOKEN";
    public static String deviceToken = "deviceToken";
    public static String DEVICE_ID = "deviceId";
    public static String OnMODEL = "User";
    public static String IsUserRegistered = "IsUserRegistered";
    public static String IsUserUpdatingFirstTime = "IsUserUpdatingFirstTime";
    public static String FirstTimeUser = "FirstTimeUser";
    public static String REGISTER_WITH_SOCIAL = "REGISTER_WITH_SOCIAL";
    public static String SOCIAL_USER_DETAILS = "SOCIAL_USER_DETAILS";
    public static String PHONE_NO = "PHONE_NO";
    public static String EMAIL = "EMAIL";
    public static String GANDER = "GANDER";
    public static String REFERRAL_CODE = "REFERRAL_CODE";
    public static String USER_NAME = "USER_NAME";
    public static String SET_ADDRESS_EVENT = "XX_SET_ADDRESS_XX";
    public static String SEARCH_FOR_ROUTS = "SEARCH_FOR_ROUTS";
    public static String UPDATE_OFFICE = "UPDATE_OFFICE";
    public static String UPDATE_HOME = "UPDATE_HOME";
    public static String SUGGEST_PICK = "SUGGEST_PICK";
    public static String SUGGEST_DROP = "SUGGEST_DROP";
    public static String CURRENT_CITY = "CURRENT_CITY";
    public static String CURRENT_STATE = "CURRENT_STATE";
    public static String Unauthorized = "HTTP 401 Unauthorized";
    public static String Forbidden = "HTTP 403 Forbidden";

    public static String EMPTY = "empty";
    public static String BOOKED = "booked";
    public static String BOOKING_DATE = "BOOKING_DATE";
    public static String BOOKING_END_DATE = "BOOKING_END_DATE";

    public static String ROUTE_ID = "routeId";
    public static String PICKUP_ID = "pickupId";
    public static String DROP_ID = "dropId";
    public static String BUS_ID = "busId";
    public static String BOOKING_TYPE = "bookingType";
    public static String HAS_RETURN = "has_return";
    public static String SEAT_NO = "seatNo";

    public static String OFFICE_PICKUP_ADD = "OFFICE_PICKUP_ADD";
    public static String OFFICE_PICKUP_TIME = "OFFICE_PICKUP_TIME";
    public static String OFFICE_DROP_ADD = "OFFICE_DROP_ADD";
    public static String OFFICE_DROP_TIME = "OFFICE_DROP_TIME";
    public static String OFFICE_BOOKING_DATE = "OFFICE_BOOKING_DATE";
    public static String OFFICE_BUS_NAME = "OFFICE_BUS_NAME";
    public static String WALLET_BALANCE = "WALLET_BALANCE";

    //Trip Status
    public static String ONBOARDED = "ONBOARDED";
    public static String COMPLETED = "COMPLETED";
    public static String EXPIRED = "EXPIRED";
    public static String SCHEDULED = "SCHEDULED";
    public static String CANCELLED = "CANCELLED";
    public static String DROPPED = "DROPPED";


}
