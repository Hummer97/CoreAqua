package com.xpertwebtech.coreaqua.ui.MyOrderDetails

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_order_details.*


class MyOrderDetailsFragment : BaseFragment<MyOrderDetailsView, MyOrderDetailsPresenter>(),MyOrderDetailsView {
    override fun getContentView(): Int {
        return R.layout.fragment_my_order_details
    }

    override fun getPresenterClass(): Class<MyOrderDetailsPresenter> {
        return MyOrderDetailsPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        val list0: MutableList<String> = ArrayList()
        list0.add("Order Confirm")
        list0.add("Dispatch")
        list0.add("Delivered")
        mSetpview0.setStepsViewIndicatorComplectingPosition(list0.size - 2) //设置完成的步数
            .reverseDraw(false) //default is true
            .setStepViewTexts(list0) //总步骤
            .setLinePaddingProportion(0.85f) //设置indicator线与线间距的比例系数
            .setStepsViewIndicatorCompletedLineColor(
                ContextCompat.getColor(
                    requireContext()!!,
                    R.color.blue
                )
            ) //设置StepsViewIndicator完成线的颜色
            .setStepsViewIndicatorUnCompletedLineColor(
                ContextCompat.getColor(
                    requireContext()!!,
                    R.color.blue
                )
            ) //设置StepsViewIndicator未完成线的颜色
            .setStepViewComplectedTextColor(
                ContextCompat.getColor(
                    requireContext()!!,
                    R.color.blue
                )
            ) //设置StepsView text完成线的颜色
            .setStepViewUnComplectedTextColor(
                ContextCompat.getColor(
                    requireContext()!!,
                    R.color.textSecondaryColor
                )
            ) //设置StepsView text未完成线的颜色
            .setStepsViewIndicatorCompleteIcon(
                ContextCompat.getDrawable(
                    requireContext()!!,
                    R.drawable.ic_baseline_done_24
                )
            ) //设置StepsViewIndicator CompleteIcon
            .setStepsViewIndicatorDefaultIcon(
                ContextCompat.getDrawable(
                    requireContext()!!,
                    R.drawable.default_icon
                )
            ) //设置StepsViewIndicator DefaultIcon
            .setStepsViewIndicatorAttentionIcon(
                ContextCompat.getDrawable(
                    requireContext()!!,
                    R.drawable.attention
                )
            ) //设置StepsViewIndicator AttentionIcon

    }

}