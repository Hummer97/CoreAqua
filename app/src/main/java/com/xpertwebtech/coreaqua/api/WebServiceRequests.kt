package com.rns.rnsecomapp.api

import com.xpertwebtech.coreaqua.api.ApiService
import com.xpertwebtech.coreaqua.api.MapApiClient
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
    fun getUpdatedProfile(id: String,name: String,phone: String):Observable<UserDataClass>
    {
        val params = HashMap<String,Any>()
        params["id"] = id
        params["name"] = name
        params["phone"] = phone
        return apiService!!.getUpdatedProfile(params)
    }
    fun getUpdatedPassword(id: String,password: String):Observable<UserDataClass>
    {
        val params = HashMap<String,Any>()
        params["id"] = id
        params["password"] = password
        return apiService!!.getUpdatedPassword(params)
    }

    fun getSavedAddressList(user_id: String):Observable<AddressData>
    {
        return apiService!!.getSavedAddressList(user_id)
    }

    fun setAddedAddress(name: String,phone:String,email:String,user_id: String,address: String,city:String,state:String,pin:String,type:String):Observable<AddedAddressData>
    {
        val params = HashMap<String,Any>()
        params["name"] = name
        params["phone"] = phone
        params["email"] = email
        params["user_id"] = user_id
        params["address"] = address
        params["city"] = city
        params["state"] = state
        params["pin"] = pin
        params["type"] = type

        return apiService!!.setUserAddress(params)
    }

    fun getUserOrderList(user_id: String):Observable<OrderListData>
    {
        return apiService!!.getUserOrderList(user_id)
    }

    fun getSavedAddressDeleteResponse(address_id:String):Observable<AddressDeleteResponseData>
    {
        return apiService!!.getSavedAddressDeleteResponse(address_id)
    }

    fun getUpdatedUserAddress(name: String,phone:String,email:String,user_id: String,address: String,city:String,state:String,pin:String,type:String,address_id: String):Observable<AddedAddressData>
    {
        val params = HashMap<String,Any>()
        params["name"] = name
        params["phone"] = phone
        params["email"] = email
        params["user_id"] = user_id
        params["address"] = address
        params["city"] = city
        params["state"] = state
        params["pin"] = pin
        params["type"] = type
        params["id"] = address_id
        return apiService!!.getUpdatedUserAddress(params)
    }

    fun getUserAddressDetails(address_id:String):Observable<SingleUserAddressData>
    {
        return apiService!!.getUserAddressDetails(address_id)
    }

    fun getUserOrderCount(user_id: String):Observable<OrderCountData>
    {
        return apiService!!.getUserOrderCount(user_id)
    }

    fun getUserOrderBooked(user_id: String,product_id: String,qty:String,amount:String,total_amount:String,address_id: String,user_name:String,email: String,phone: String,address: String,city: String,state: String,pin: String,order_status:String,transaction_id:String,payment_mode:String,payment_status:String,wallet_amount:String,bottle_amount:String,bottle_qty:String):Observable<BookedOrderResponse>
    {
        val params = HashMap<String,Any>()
        params["user_id"] = user_id
        params["product_id"] = product_id
        params["quantity"] = qty
        params["amount"] = amount
        params["total_amount"] = total_amount
        params["address_id"] = address_id
        params["user_name"] = user_name
        params["user_email"] = email
        params["user_phone"] = phone
        params["user_address"] = address
        params["user_city"] = city
        params["user_state"] = state
        params["user_pincode"] = pin
        params["order_status"] = order_status
        params["payment_transaction_id"] = transaction_id
        params["payment_mode"] = payment_mode
        params["payment_status"] = payment_status
        params["wallet_amount"] = wallet_amount
        params["bottle_amount"] = bottle_amount
        params["bottle_quantity"] = bottle_qty
        return apiService!!.getUserOrderBooked(params)
    }
    fun getUserOrderStatus():Observable<OrderStatusListingData>
    {
        return apiService!!.getUserOrderStatus()
    }
}
