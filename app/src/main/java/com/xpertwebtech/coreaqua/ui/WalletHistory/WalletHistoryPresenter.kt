package com.xpertwebtech.coreaqua.ui.WalletHistory

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WalletHistoryPresenter(view:WalletHistoryFragment):BasePresenter<WalletHistoryView>(view) {
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

    fun hitGetWalletHistoryList(user_id:String)
    {
        WebServiceRequests.instance.getWalletHistoryList(user_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<WalletListData>("",getView())
            {
                override fun onResponse(t: WalletListData) {
                    getView().getWalletHistoryListResponse(t)
                }

            })
    }
}