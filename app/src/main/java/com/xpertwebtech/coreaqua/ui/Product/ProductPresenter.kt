package com.xpertwebtech.coreaqua.ui.Product

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import com.xpertwebtech.coreaqua.ui.DashBoard.ProductListData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductPresenter(view:ProductFragment): BasePresenter<ProductView>(view){
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

    fun hitGetProductListApi()
    {
        WebServiceRequests.instance.getProductList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<ProductListData>("",getView()){
                override fun onResponse(t: ProductListData) {
                    getView().getProductListResponse(t)
                }

            })
    }
}