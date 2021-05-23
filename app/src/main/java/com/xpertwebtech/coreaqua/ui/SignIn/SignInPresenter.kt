package com.xpertwebtech.coreaqua.ui.SignIn

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import com.xpertwebtech.coreaqua.dataModel.UserDataClass
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignInPresenter(view:SignInActivity):BasePresenter<SignInView>(view) {
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

    fun hitLogInApi(email:String,userType:String,password:String)
    {
        WebServiceRequests.instance.userLogin(email,userType,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiObserver<UserDataClass>("",getView()){
                    override fun onResponse(t: UserDataClass) {
                        getView().getLogInResponse(t)
                    }

                })
    }
}