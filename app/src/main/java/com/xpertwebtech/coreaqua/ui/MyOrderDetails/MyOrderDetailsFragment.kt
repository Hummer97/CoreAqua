package com.xpertwebtech.coreaqua.ui.MyOrderDetails

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.base.BaseFragment
import com.xpertwebtech.coreaqua.ui.AddressList.AddressListFragmentArgs
import kotlinx.android.synthetic.main.fragment_my_order_details.*


class MyOrderDetailsFragment : BaseFragment<MyOrderDetailsView, MyOrderDetailsPresenter>(),MyOrderDetailsView {

    private lateinit var mUserName:String
    private lateinit var mPhone:String
    private lateinit var mEmail:String
    private lateinit var mAddress:String
    private lateinit var mTotal_amount:String
    private lateinit var mQty:String
    private lateinit var mPayment_mode:String
    private lateinit var mTransationID:String
    private lateinit var mOrder_Status:String
    override fun getContentView(): Int {
        return R.layout.fragment_my_order_details
    }

    override fun getPresenterClass(): Class<MyOrderDetailsPresenter> {
        return MyOrderDetailsPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {

        if(arguments !=null)
        {
            mUserName = MyOrderDetailsFragmentArgs.fromBundle(requireArguments()).userName
            mPhone = MyOrderDetailsFragmentArgs.fromBundle(requireArguments()).phone
            mEmail = MyOrderDetailsFragmentArgs.fromBundle(requireArguments()).email
            mAddress = MyOrderDetailsFragmentArgs.fromBundle(requireArguments()).address
            mTotal_amount = MyOrderDetailsFragmentArgs.fromBundle(requireArguments()).totalAmount
            mQty = MyOrderDetailsFragmentArgs.fromBundle(requireArguments()).qty
            mPayment_mode = MyOrderDetailsFragmentArgs.fromBundle(requireArguments()).paymentMode
            mTransationID = MyOrderDetailsFragmentArgs.fromBundle(requireArguments()).transactionId
            mOrder_Status = MyOrderDetailsFragmentArgs.fromBundle(requireArguments()).orderStatus
        }

        order_details_user_name.text = mUserName
        order_details_order_status.text = mOrder_Status
        order_details_mobile_no.text = mPhone
        order_details_billing_mail.text = mEmail
        order_details_billing_address.text = mAddress
        order_details_total_amount_value.text = mTotal_amount
        order_details_bottle_qty_value.text = mQty
        order_details_payment_mode_value.text = mPayment_mode
        order_details_transaction_id_value.text = mTransationID.toString()
        order_details_order_status.text = mOrder_Status
    }


}