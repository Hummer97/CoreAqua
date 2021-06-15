package com.xpertwebtech.coreaqua.api

import com.rns.rnsecomapp.api.Constants
import com.xpertwebtech.coreaqua.dataModel.UserDataClass
import com.xpertwebtech.coreaqua.ui.AddressList.AddedAddressData
import com.xpertwebtech.coreaqua.ui.AddressList.AddressData
import com.xpertwebtech.coreaqua.ui.AddressList.AddressDeleteResponseData
import com.xpertwebtech.coreaqua.ui.DashBoard.ProductListData
import com.xpertwebtech.coreaqua.ui.MyOrderDetails.OrderStatusListingData
import com.xpertwebtech.coreaqua.ui.MyOrders.OrderListData
import com.xpertwebtech.coreaqua.ui.My_Plan.UserPlanData
import com.xpertwebtech.coreaqua.ui.PaykunPayment.BookedOrderResponse
import com.xpertwebtech.coreaqua.ui.ProductDetails.GeoLocationData
import com.xpertwebtech.coreaqua.ui.ProductDetails.ProductDetailsResponseData
import com.xpertwebtech.coreaqua.ui.Signup.*
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import com.xpertwebtech.coreaqua.ui.paymentDetails.OrderCountData
import com.xpertwebtech.coreaqua.ui.paymentDetails.SingleUserAddressData
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import kotlin.collections.HashMap

interface ApiService {
//
//    @GET(Constants.Partials.Category)
//    fun category(): Observable<CategoriesModel>

    @POST(Constants.Partials.User_Login)
    fun userLogin(@Body params:HashMap<String,Any>):Observable<UserDataClass>

    @POST(Constants.Partials.User_Registration)
    fun userRegistrationPart1(@Body params: HashMap<String, Any>):Observable<userRegisterpart1Data>

    @POST(Constants.Partials.User_Registration)
    fun userRegistrationPart2(@Body params: HashMap<String, Any>):Observable<userRegistrationPart2Data>

    @GET(Constants.Partials.Block_List)
    fun blockList():Observable<BlockListData>

    @GET(Constants.Partials.Sector_List)
    fun sectorListResponse(@Query("block") block:String):Observable<SectorListData>

    @GET(Constants.Partials.Product_List)
    fun getProductList():Observable<ProductListData>

    @GET(Constants.Partials.User_plan_list)
    fun getUserPlanList(@Query("user_id") user_id:String):Observable<UserPlanData>

    @GET(Constants.Partials.Wallet_history)
    fun getWalletHistoryList(@Query("user_id") user_id:String):Observable<WalletListData>

    @POST(Constants.Partials.User_select_order)
    fun userSelectPlan(@Body params: HashMap<String, Any>):Observable<ProductDetailsResponseData>

    @POST(Constants.Partials.key)
    fun getGeoLocation(@Query("key")key:String):Observable<GeoLocationData>

    @POST(Constants.Partials.User_Registration)
    fun getUpdatedProfile(@Body params: HashMap<String, Any>):Observable<UserDataClass>

    @POST(Constants.Partials.User_Registration)
    fun getUpdatedPassword(@Body params: HashMap<String, Any>):Observable<UserDataClass>

    @GET(Constants.Partials.user_saved_address_list)
    fun getSavedAddressList(@Query("user_id") user_id: String):Observable<AddressData>

    @POST(Constants.Partials.add_edit_address)
    fun setUserAddress(@Body params: HashMap<String, Any>):Observable<AddedAddressData>

    @GET(Constants.Partials.user_order_listing)
    fun getUserOrderList(@Query("user_id") user_id: String):Observable<OrderListData>

    @POST(Constants.Partials.saved_address_delete)
    fun getSavedAddressDeleteResponse(@Query("id") id:String):Observable<AddressDeleteResponseData>

    @POST(Constants.Partials.add_edit_address)
    fun getUpdatedUserAddress(@Body params: HashMap<String, Any>):Observable<AddedAddressData>

    @GET(Constants.Partials.user_address_details)
    fun getUserAddressDetails(@Query("id") address_id: String):Observable<SingleUserAddressData>

    @GET(Constants.Partials.user_order_count)
    fun getUserOrderCount(@Query("user_id") user_id: String):Observable<OrderCountData>

    @POST(Constants.Partials.user_order_booked)
    fun getUserOrderBooked(@Body params: HashMap<String, Any>):Observable<BookedOrderResponse>

    @GET(Constants.Partials.user_order_status)
    fun getUserOrderStatus():Observable<OrderStatusListingData>
}


