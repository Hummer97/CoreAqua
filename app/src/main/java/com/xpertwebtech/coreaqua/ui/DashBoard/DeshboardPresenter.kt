package com.xpertwebtech.coreaqua.ui.DashBoard

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DeshboardPresenter(view:DeshboardFragment):BasePresenter<DeshboardView>(view) {
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