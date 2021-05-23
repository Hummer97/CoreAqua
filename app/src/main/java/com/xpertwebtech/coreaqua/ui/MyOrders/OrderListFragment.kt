package com.xpertwebtech.coreaqua.ui.MyOrders

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xpertwebtech.coreaqua.Interface.RecyclerViewClickInterface
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_order_list.*

class OrderListFragment : BaseFragment<OrderListView,OrderListPresenter>(),OrderListView {
    override fun getContentView(): Int {
        return R.layout.fragment_order_list
    }

    override fun getPresenterClass(): Class<OrderListPresenter> {
        return OrderListPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
            order_list_rc.adapter = OrderListAdapter(requireContext(),object : RecyclerViewClickInterface{
                override fun OnItemClick(position: Int) {
                    Navigation.findNavController(order_list_rc).navigate(R.id.action_orderListFragment_to_myOrderDetailsFragment)
                }

                override fun OnItemLongClick(position: Int) {

                }

            })
            order_list_rc.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
    }

}