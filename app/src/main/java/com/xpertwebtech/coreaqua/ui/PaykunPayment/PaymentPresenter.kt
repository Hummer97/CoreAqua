package com.xpertwebtech.coreaqua.ui.PaykunPayment

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PaymentPresenter(view:PaymentActivity):BasePresenter<PaymentView>(view){
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

    fun hitBookedOrderApi(user_id: String,product_id: String,qty:String,amount:String,total_amount:String,address_id: String,user_name:String,email: String,phone: String,address: String,city: String,state: String,pin: String,order_status:String,transaction_id:String,payment_mode:String,payment_status:String,wallet_amount:String,bottle_amount:String,bottle_qty:String)
    {
        WebServiceRequests.instance.getUserOrderBooked(user_id, product_id, qty, amount, total_amount, address_id, user_name, email, phone, address, city, state, pin, order_status, transaction_id, payment_mode, payment_status, wallet_amount, bottle_amount, bottle_qty)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<BookedOrderResponse>("",getView())
            {
                override fun onResponse(t: BookedOrderResponse) {
                    return getView().getBookedOrderResponse(t)
                }
            })
    }

}