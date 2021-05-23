package com.xpertwebtech.coreaqua.ui.ProductDetails

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import com.xpertwebtech.coreaqua.ui.DashBoard.ProductListData
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailsPresenter(view:ProductDetailsFragment):BasePresenter<ProductDetailsView>(view) {
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

    fun hitGetWalletBalanceApi(user_id:String)
    {
        WebServiceRequests.instance.getWalletHistoryList(user_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiObserver<WalletListData>("",getView())
                {
                    override fun onResponse(t: WalletListData) {
                        getView().getWalletBalanceResponse(t)
                    }
                })
    }
    fun hitGetUserSelectedPlanApi(user_id:String, product_id:String, quantity:String, day_type:String, start_date:String, end_date:String)
    {
        WebServiceRequests.instance.userSelectedPlan(user_id, product_id, quantity, day_type, start_date, end_date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiObserver<ProductDetailsResponseData>("",getView())
                {
                    override fun onResponse(t: ProductDetailsResponseData) {
                        getView().getOrderSelectedPlanResponse(t)
                    }

                })
    }


}
