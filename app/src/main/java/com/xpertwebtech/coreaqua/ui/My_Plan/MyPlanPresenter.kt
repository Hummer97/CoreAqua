package com.xpertwebtech.coreaqua.ui.My_Plan

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyPlanPresenter(view:MyPlanFragment):BasePresenter<MyPlanView>(view) {
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

    fun hitUserPlanListApi(user_id:String)
    {
        WebServiceRequests.instance.getUserPlanList(user_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiObserver<UserPlanData>("",getView()){
                    override fun onResponse(t: UserPlanData) {
                        getView().getUserPlanListResponse(t)
                    }

                })
    }
}