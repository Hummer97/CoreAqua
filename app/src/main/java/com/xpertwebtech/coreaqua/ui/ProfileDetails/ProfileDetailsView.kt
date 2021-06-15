package com.xpertwebtech.coreaqua.ui.ProfileDetails

import com.xpertwebtech.coreaqua.base.BaseView
import com.xpertwebtech.coreaqua.dataModel.UserDataClass

interface ProfileDetailsView:BaseView {
    fun getUpdatedProfileResponse(response:UserDataClass)
    fun getUpdatedPasswordResponse(response:UserDataClass)
}