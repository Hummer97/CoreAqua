package com.rns.rnsecomapp.api

import com.xpertwebtech.coreaqua.api.ApiService
import com.xpertwebtech.coreaqua.api.MapApiClient
import com.xpertwebtech.coreaqua.dataModel.UserDataClass
import com.xpertwebtech.coreaqua.ui.DashBoard.ProductListData
import com.xpertwebtech.coreaqua.ui.My_Plan.UserPlanData
import com.xpertwebtech.coreaqua.ui.ProductDetails.GeoLocationData
import com.xpertwebtech.coreaqua.ui.ProductDetails.ProductDetailsResponseData
import com.xpertwebtech.coreaqua.ui.Signup.*
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import io.reactivex.Observable

class WebServiceRequests {
    companion object {
        private var apiService: ApiService? = null
        private var mapApiService: ApiService? = null
        val instance = WebServiceRequests()
    }



    init {
        if (apiService == null) {
            apiService = ApiClient.getClient()!!.create(ApiService::class.java)
            mapApiService = MapApiClient.getClient()!!.create(ApiService::class.java)
        }
    }

//    fun categoary(): Observable<CategoriesModel> {
//        return apiService!!.category()
//    }

    fun userLogin(email:String,user_type:String,password:String):Observable<UserDataClass>
    {
        val params = HashMap<String,Any>()
        params["email"] = email
        params["user_type"] = user_type
        params["password"] = password
        return apiService!!.userLogin(params)
    }

    fun userRegistrationPart1(name:String,phone:String,email:String,user_type:String,password: String,used_refer_code: String): Observable<userRegisterpart1Data> {
        val params = HashMap<String,Any>()
        params["name"] = name
        params["phone"] = phone
        params["email"] = email
        params["user_type"] = user_type
        params["password"] = password
        params["used_refer_code"] = used_refer_code
        return apiService!!.userRegistrationPart1(params)
    }

    fun userRegistrationPart2(id: String,address:String,block:String,sector:String): Observable<userRegistrationPart2Data> {
        val params = HashMap<String,Any>()
        params["id"] = id
        params["address"] = address
        params["block"] = block
        params["sector"] = sector
        return apiService!!.userRegistrationPart2(params)
    }

    fun blockListResponse():Observable<BlockListData>
    {
        return apiService!!.blockList()
    }

    fun sectorListResponse(block: String):Observable<SectorListData>
    {
        return apiService!!.sectorListResponse(block)
    }

    fun getProductList():Observable<ProductListData>
    {
        return apiService!!.getProductList()
    }

    fun getUserPlanList(user_id:String):Observable<UserPlanData>
    {
        return apiService!!.getUserPlanList(user_id)
    }

    fun getWalletHistoryList(user_id:String):Observable<WalletListData>
    {
        return apiService!!.getWalletHistoryList(user_id)
    }

    fun userSelectedPlan(user_id:String, product_id:String, quantity:String, day_type:String, start_date:String, end_date:String):Observable<ProductDetailsResponseData>
    {
        val params = HashMap<String,Any>()
        params["user_id"] = user_id
        params["product_id"] = product_id
        params["quantity"] = quantity
        params["day_type"] = day_type
        params["start_date"] = start_date
        params["end_date"] = end_date
        return apiService!!.userSelectPlan(params)
    }

    fun getGeoLocation(key:String):Observable<GeoLocationData>
    {
        return mapApiService!!.getGeoLocation(key)
    }
}
