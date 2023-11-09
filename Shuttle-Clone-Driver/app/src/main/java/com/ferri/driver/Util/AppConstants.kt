package com.ferri.driver.Util

import java.text.SimpleDateFormat
import java.util.*

object AppConstants {
    /*variable declaration*/
    const val NA = "N/A"

    const val BASE_URL = "CHANGE_WITH_YOUR_API_URL" // YOUR API URL


    //SharedPreferences
    var TOKEN = "TOKEN"
    var ASSIGNED_ID = "ASSIGNED_ID"
    var csrfTOKEN = "csrfTOKEN"
    var BASEURL = "BASEURL"
    var onModel = "Driver"
    var PHONE_NO = "PHONE_NO"
    var IsDriverLogIn = "IsDriverLogIn"
    var DEVICE_TOKEN = "DEVICE_TOKEN"
    var IsUserUpdatingFirstTime = "IsUserUpdatingFirstTime"
    var FirstTimeUser = "FirstTimeUser"
    var USER_REG_COMPLETE = "NO"
    var Unauthorized = "HTTP 401 Unauthorized"
    var Forbidden = "HTTP 403 Forbidden"
    var FRG_SERVICE_NF_ID = 21031
    var MIN_DIST_FOR_LOCATION_UPDATE = 0.0
    var DRIVER_LATITUDE = "DRIVER_LATITUDE"
    var DRIVER_LONGITUDE = "DRIVER_LONGITUDE"
    var DRIVER_ANGLE = "DRIVER_ANGLE"
    var IS_BOOKING_ASSIGNED = "IS_BOOKING_ASSIGNED"
    var IS_TRIP_STARTED = "IS_TRIP_STARTED"

    /*Date format*/
    object DateFormat {
        var dd_MM = "dd-MMM"
        var dd_MM_yyyy = "dd - MMM - yyyy"
        val DAY_MONTH_FORMATTER = SimpleDateFormat(dd_MM, Locale.getDefault())
        val DAY_MONTH_YEAR_FORMATTER = SimpleDateFormat(dd_MM_yyyy, Locale.getDefault())
    }

    /*intent data*/
    object intentdata {
        var CARDDETAIL = "carddetail"
        var TRAVELLERNAME = "TravellerName"
        var TYPECOACH = "typecoach"
        var PRICE = "price"
        var HOLD = "hold"
        var PACKAGE = "package"
        var OFFER = "offer"
        var TRIP_KEY = "trip_key"
        var SEARCH_BUS = "search_bus"
        var PACKAGE_NAME = "package_name"
        var CARDFLAG = "cardflag"
    }
}