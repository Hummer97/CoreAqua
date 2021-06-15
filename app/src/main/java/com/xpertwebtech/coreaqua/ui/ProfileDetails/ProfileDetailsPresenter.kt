package com.xpertwebtech.coreaqua.ui.ProfileDetails

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import com.xpertwebtech.coreaqua.dataModel.UserDataClass
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileDetailsPresenter(view:ProfileDetailsFragment):BasePresenter<ProfileDetailsView>(view) {
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

    fun hitUpdatedProfileApi(id: String,name: String,phone: String)
    {
        WebServiceRequests.instance.getUpdatedProfile(id, name, phone).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<UserDataClass>("",getView()){
                override fun onResponse(t: UserDataClass) {
                    return getView().getUpdatedProfileResponse(t)
                }

            })
    }
    fun hitUpdatePasswordApi(id: String,password: String)
    {
        WebServiceRequests.instance.getUpdatedPassword(id,password).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<UserDataClass>("",getView()){
                override fun onResponse(t: UserDataClass) {
                    return getView().getUpdatedPasswordResponse(t)
                }

            })
    }
}