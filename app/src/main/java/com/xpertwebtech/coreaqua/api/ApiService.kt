package com.xpertwebtech.coreaqua.api

import com.rns.rnsecomapp.api.Constants
import com.xpertwebtech.coreaqua.dataModel.UserDataClass
import com.xpertwebtech.coreaqua.ui.DashBoard.ProductListData
import com.xpertwebtech.coreaqua.ui.My_Plan.UserPlanData
import com.xpertwebtech.coreaqua.ui.ProductDetails.GeoLocationData
import com.xpertwebtech.coreaqua.ui.ProductDetails.ProductDetailsResponseData
import com.xpertwebtech.coreaqua.ui.Signup.*
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
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
}


