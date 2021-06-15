package com.xpertwebtech.coreaqua.ui.MyOrderDetails

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyOrderDetailsPresenter(view:MyOrderDetailsFragment):BasePresenter<MyOrderDetailsView>(view) {
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

//    fun hitOrderStatusApi()
//    {
//        WebServiceRequests.instance.getUserOrderStatus().subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : ApiObserver<OrderStatusListingData>("",getView())
//            {
//                override fun onResponse(t: OrderStatusListingData) {
//                    return getView().getOrderStatusResponse(t)
//                }
//            })
//    }

}
