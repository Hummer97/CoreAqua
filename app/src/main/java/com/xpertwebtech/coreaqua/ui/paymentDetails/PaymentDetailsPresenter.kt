package com.xpertwebtech.coreaqua.ui.paymentDetails

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import com.xpertwebtech.coreaqua.ui.PaykunPayment.BookedOrderResponse
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PaymentDetailsPresenter(view:PaymentDetailsFragment):BasePresenter<PaymentDetailsView>(view) {
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

    fun hitUserAddressDetailsApi(address_id:String)
    {
        WebServiceRequests.instance.getUserAddressDetails(address_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<SingleUserAddressData>("",getView()){
                override fun onResponse(t: SingleUserAddressData) {
                    return getView().getUserAddressResponse(t)
                }
            })
    }

    fun hitUserOrderCountApi(user_id:String)
    {
        WebServiceRequests.instance.getUserOrderCount(user_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<OrderCountData>("",getView())
            {
                override fun onResponse(t: OrderCountData) {
                    return getView().getUserOrderCountResponse(t)
                }
            })
    }

    fun hitOfflineBookedOrderApi(user_id: String,product_id: String,qty:String,amount:String,total_amount:String,address_id: String,user_name:String,email: String,phone: String,address: String,city: String,state: String,pin: String,order_status:String,transaction_id:String,payment_mode:String,payment_status:String,wallet_amount:String,bottle_amount:String,bottle_qty:String)
    {
        WebServiceRequests.instance.getUserOrderBooked(user_id, product_id, qty, amount, total_amount, address_id, user_name, email, phone, address, city, state, pin, order_status, transaction_id, payment_mode, payment_status, wallet_amount, bottle_amount, bottle_qty)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<BookedOrderResponse>("",getView())
            {
                override fun onResponse(t: BookedOrderResponse) {
                    return getView().getOfflineBookedOrderResponse(t)
                }
            })
    }

    fun hitGetWalletAmountApi(user_id:String)
    {
        WebServiceRequests.instance.getWalletHistoryList(user_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<WalletListData>("",getView())
            {
                override fun onResponse(t: WalletListData) {
                    getView().getWalletAmountResponse(t)
                }

            })
    }

}
