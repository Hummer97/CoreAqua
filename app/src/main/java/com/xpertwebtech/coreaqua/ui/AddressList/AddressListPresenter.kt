package com.xpertwebtech.coreaqua.ui.AddressList

import com.rns.rnsecomapp.api.ApiObserver
import com.rns.rnsecomapp.api.WebServiceRequests
import com.xpertwebtech.coreaqua.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddressListPresenter(view:AddressListFragment):BasePresenter<AddressListView>(view) {
    override fun initObservable() {

    }

    override fun destroyObservables() {

    }

    override fun onViewReady() {

    }

    fun hitSavedAddressListApi(user_id:String)
    {
        WebServiceRequests.instance.getSavedAddressList(user_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<AddressData>("",getView()){
                override fun onResponse(t: AddressData) {
                    return getView().getSavedAddressApiResponse(t)
                }

            })
    }
    fun hitSetAddedAddressApi(name: String,phone:String,email:String,user_id: String,address: String,city:String,state:String,pin:String,type:String)
    {
        WebServiceRequests.instance.setAddedAddress(name, phone, email, user_id, address, city, state, pin, type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<AddedAddressData>("",getView()){
                override fun onResponse(t: AddedAddressData) {
                    return getView().getAddedAddressResponse(t)
                }

            })
    }

    fun hitAddressDeleteApi(address_id:String)
    {
        WebServiceRequests.instance.getSavedAddressDeleteResponse(address_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<AddressDeleteResponseData>("",getView()){
                override fun onResponse(t: AddressDeleteResponseData) {
                    return getView().getDeletedAddressResponse(t)
                }

            })
    }

    fun hitUpdatedAddressApi(name: String,phone:String,email:String,user_id: String,address: String,city:String,state:String,pin:String,type:String,address_id: String)
    {
        WebServiceRequests.instance.getUpdatedUserAddress(name, phone, email, user_id, address, city, state, pin, type, address_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<AddedAddressData>("",getView())
            {
                override fun onResponse(t: AddedAddressData) {
                    return getView().getUpdatedAddressResponse(t)
                }
            })

    }
}
