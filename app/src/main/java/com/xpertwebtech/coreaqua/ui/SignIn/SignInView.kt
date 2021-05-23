package com.xpertwebtech.coreaqua.ui.SignIn

import com.xpertwebtech.coreaqua.base.BaseView
import com.xpertwebtech.coreaqua.dataModel.UserDataClass

interface SignInView:BaseView {
    fun getLogInResponse(response:UserDataClass)
}