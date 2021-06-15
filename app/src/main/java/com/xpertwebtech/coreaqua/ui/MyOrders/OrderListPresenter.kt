package com.xpertwebtech.coreaqua.ui.MyOrders

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OrderListPresenter(view:OrderListFragment):BasePresenter<OrderListView>(view) {
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

    fun hitUserOrderListApi(user_id:String)
    {
        WebServiceRequests.instance.getUserOrderList(user_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<OrderListData>("",getView())
            {
                override fun onResponse(t: OrderListData) {
                    return getView().getUserOrderListResponse(t)
                }
            })
    }

}
