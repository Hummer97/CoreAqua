package com.xpertwebtech.coreaqua.ui.MyOrders

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xpertwebtech.coreaqua.Interface.RecyclerViewClickInterface
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_order_list.*

class OrderListFragment : BaseFragment<OrderListView,OrderListPresenter>(),OrderListView {
    private lateinit var mUser_Id: String
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var navController: NavController

    override fun getContentView(): Int {
        return R.layout.fragment_order_list
    }

    override fun getPresenterClass(): Class<OrderListPresenter> {
        return OrderListPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        sharedPrefManager = SharedPrefManager.getInstance(requireContext())
        mUser_Id = sharedPrefManager.user.id.toString()
        navController = Navigation.findNavController(requireView())
        presenter!!.hitUserOrderListApi(mUser_Id)

    }

    override fun getUserOrderListResponse(response: OrderListData) {
        if(response.status == 200)
        {
            if (response.userOrderListing != null)
            {
                order_list_rc.adapter = OrderListAdapter(requireContext(),response,object : RecyclerViewClickInterface{
                    override fun OnItemClick(position: Int) {
                        var userName:String = response.userOrderListing.get(position).userName.toString()
                        var phone:String = response.userOrderListing.get(position).userPhone.toString()
                        var email:String = response.userOrderListing.get(position).userEmail.toString()
                        var address:String = response.userOrderListing.get(position).userAddress.toString()
                        var city:String = response.userOrderListing.get(position).userCity.toString()
                        var state:String = response.userOrderListing.get(position).userState.toString()
                        var pin:String = response.userOrderListing.get(position).userPincode.toString()
                        var total_amount:String = response.userOrderListing.get(position).totalAmount.toString()
                        var qty:String = response.userOrderListing.get(position).quantity.toString()
                        var payment_mode:String = response.userOrderListing.get(position).paymentMode.toString()
                        var transationID:String = response.userOrderListing.get(position).paymentTransactionId.toString()
                        var order_Status:String = response.userOrderListing.get(position).statusName.toString()
                        var main_address:String = "$address, $city, $state,$pin"
                        var action:NavDirections = OrderListFragmentDirections.actionOrderListFragmentToMyOrderDetailsFragment(userName,phone,email,main_address,total_amount,qty,payment_mode,transationID,order_Status)
                        navController.navigate(action)
//                        Navigation.findNavController(order_list_rc).navigate(R.id.action_orderListFragment_to_myOrderDetailsFragment)
                    }

                    override fun OnItemLongClick(position: Int) {

                    }

                })
                order_list_rc.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
            }
        }
    }

}