package com.xpertwebtech.coreaqua.ui.AddressList

import com.xpertwebtech.coreaqua.base.BaseView

interface AddressListView:BaseView {

    fun getSavedAddressApiResponse(response: AddressData)
    fun getAddedAddressResponse(response: AddedAddressData)
    fun getDeletedAddressResponse(response:AddressDeleteResponseData)
    fun getUpdatedAddressResponse(response: AddedAddressData)

}
