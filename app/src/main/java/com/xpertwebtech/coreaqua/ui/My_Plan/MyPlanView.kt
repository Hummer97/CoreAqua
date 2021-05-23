package com.xpertwebtech.coreaqua.ui.My_Plan

import com.xpertwebtech.coreaqua.base.BaseView

interface MyPlanView:BaseView {
    fun getUserPlanListResponse(response: UserPlanData)
}