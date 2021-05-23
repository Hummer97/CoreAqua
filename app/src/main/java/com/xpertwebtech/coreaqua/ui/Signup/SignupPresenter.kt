package com.xpertwebtech.coreaqua.ui.Signup

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupPresenter(view:SignupActivity):BasePresenter<SignupView>(view) {
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

//    fun hitBlockListApi()
//    {
//        WebServiceRequests.instance.blockListResponse().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : ApiObserver<BlockListData>("",getView())
//                {
//                    override fun onResponse(t: BlockListData) {
//                        getView().getBlockListResponse(t)
//                    }
//
//                })
//    }

//    fun hitSectorListApi(block:String)
//    {
//        WebServiceRequests.instance.sectorListResponse(block).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : ApiObserver<SectorListData>("",getView())
//                {
//                    override fun onResponse(t: SectorListData) {
//                        getView().getSectorListResponse(t)
//                    }
//
//                })
//    }

    fun hitUserRegistrationPart1Api(name:String,phone:String,email:String,user_type:String,password: String,used_refer_code: String)
    {
        WebServiceRequests.instance.userRegistrationPart1(name, phone, email, user_type, password, used_refer_code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiObserver<userRegisterpart1Data>("",getView())
                {
                    override fun onResponse(t: userRegisterpart1Data) {
                        getView().getUserRegistrationPart1Response(t)
                    }

                })
    }

//    fun hitUserRegistrationPart2Api(id: String,address:String,block:String,sector:String)
//    {
//        WebServiceRequests.instance.userRegistrationPart2(id, address, block, sector).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : ApiObserver<userRegistrationPart2Data>("",getView())
//                {
//                    override fun onResponse(t: userRegistrationPart2Data) {
//                        getView().getUserRegistrationPart2Response(t)
//                    }
//
//                })
//    }

}
