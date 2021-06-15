package com.rns.rnsecomapp.api

class Constants {
    companion object {
        var DeviceName =""
//        const val BASE_URL = "https://expertwebtech.com/water/public/"
        const val BASE_URL = "https://coreaqua.co.in/water/public/"
        const val MAP_BASE_URL = "https://www.googleapis.com/"
        var DeviceId: String = ""
        var HEADER_TOKEN = "Basic AR-AUG-ARST-BIZBR-2019OLLY"
        const val LOCATION_REQUEST = 1000
        const val GPS_REQUEST = 1

    }



 internal object Partials {
//    const val Category = "api/categories"
     const val User_Login = "api/user-login"
     const val User_Registration = "api/user-registration"
     const val Block_List = "api/block-listing"
     const val Sector_List = "api/sector-listing"
     const val Product_List = "api/product"
     const val User_plan_list = "api/user-plan-listing"
     const val Wallet_history = "api/user-wallet-history-list"
     const val User_select_order ="api/user-select-order"
     const val key = "geolocation/v1/geolocate"
     const val user_saved_address_list = "api/user-address-listing"
     const val add_edit_address = "api/user-address-add-edit"
     const val user_order_listing="api/user-order-listing"
     const val saved_address_delete = "api/user-address-delete"
     const val user_address_details = "api/user-address-details"
     const val user_order_count = "api/user-order-count"
     const val user_order_booked = "api/user-order-booked"
     const val user_order_status = "api/order-status-listing"

 }


    internal object Keys {
//        val clientMachineName ="ClientMachineName"
//        const val Password = "Password"



    }


}