package com.xpertwebtech.coreaqua.ui.My_Plan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_plan.*

class MyPlanFragment : BaseFragment<MyPlanView,MyPlanPresenter>(),MyPlanView {
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var mUserID:String
    override fun getContentView(): Int {
        return R.layout.fragment_my_plan
    }

    override fun getPresenterClass(): Class<MyPlanPresenter> {
        return MyPlanPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        sharedPrefManager = SharedPrefManager.getInstance(requireContext())
        mUserID = sharedPrefManager.user.id.toString()


        presenter?.hitUserPlanListApi(mUserID)
    }

    override fun getUserPlanListResponse(response: UserPlanData) {
        if(response.status == 200)
        {
            my_plan_product_not_exist_layout.visibility = View.GONE
            my_plan_product_layout.visibility = View.VISIBLE
//            my_plan_rc.adapter = MyPlanAdapter(requireContext())
            my_plan_title.text = response.userOrder?.productName
            my_plan_day_type.text = response.userOrder?.dayType
            my_plan_start_date.text = response.userOrder?.startDate
            my_plan_end_date.text = response.userOrder?.endDate
            my_plan_qty.text = "Qty: "+response.userOrder?.quantity
        }
        else
        {
            my_plan_product_layout.visibility = View.GONE
            my_plan_product_not_exist_layout.visibility = View.VISIBLE
        }
    }
}