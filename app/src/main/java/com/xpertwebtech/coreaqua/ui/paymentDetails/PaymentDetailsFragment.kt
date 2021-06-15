package com.xpertwebtech.coreaqua.ui.paymentDetails

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import com.xpertwebtech.coreaqua.ui.DemoActivity
import com.xpertwebtech.coreaqua.ui.PaykunPayment.BookedOrderResponse
import com.xpertwebtech.coreaqua.ui.PaykunPayment.PaymentActivity
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import kotlinx.android.synthetic.main.fragment_payment_details.*
import kotlinx.android.synthetic.main.popup_successful.*
import kotlinx.android.synthetic.main.popup_successful.view.*

class PaymentDetailsFragment : BaseFragment<PaymentDetailsView, PaymentDetailsPresenter>(),
    PaymentDetailsView {
    private lateinit var mDialog: AlertDialog
    private lateinit var mDialogBuilder: AlertDialog.Builder
    private lateinit var mName: String
    private lateinit var mPhone: String
    private lateinit var mEmail: String
    private lateinit var mUser_id: String
    private var mTotal_Amount: Int = 0
    private var mSecurity_Amount: Int = 0
    private var mUser_order_log: Int = 1
    private lateinit var selected_mode: String
    private lateinit var mProduct_Price: String
    private lateinit var mQty: String
    private var mSelectedAddressId: String = "null"
    private var mAddress: String = "null"
    private var mCity: String = "null"
    private var mState: String = "null"
    private var mPin: String = "null"
    private var mWallet_Amount: String = "0"
    private var mSecurity_qty: String = "0"
    private lateinit var sharedPrefManager: SharedPrefManager
    override fun getContentView(): Int {
        return R.layout.fragment_payment_details
    }

    override fun getPresenterClass(): Class<PaymentDetailsPresenter> {
        return PaymentDetailsPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        sharedPrefManager = SharedPrefManager.getInstance(requireContext())
        mUser_id = sharedPrefManager.user.id.toString()


        if (arguments != null) {
            mProduct_Price = PaymentDetailsFragmentArgs.fromBundle(requireArguments()).productPrice
            mSelectedAddressId = PaymentDetailsFragmentArgs.fromBundle(requireArguments()).addressId
            mQty = PaymentDetailsFragmentArgs.fromBundle(requireArguments()).qty

            payment_details_bottle_qty_value.text = mQty
            payment_details_bottle_price_value.text = mProduct_Price
        }

        presenter!!.hitGetWalletAmountApi(mUser_id)
        presenter!!.hitUserAddressDetailsApi(mSelectedAddressId)
        presenter!!.hitUserOrderCountApi(mUser_id)


        payment_details_submit_btn.setOnClickListener {
            var selectedOption: Int = payment_details_radio_group!!.checkedRadioButtonId
            selected_mode = if (selectedOption == -1) {
                Log.d("OD", "Please Select Delivery Day ")
                "null"
            } else
            {
                var selectedBtn = requireView().findViewById<RadioButton>(selectedOption)
                selectedBtn.text.toString()
            }
            if (selected_mode.equals("Online Payment")) {
//                presenter!!.hitGeoLocationApi("AIzaSyB3h-DQ2T_YnsMo7pIMtjRv42tsxlaAY-o")

//                CurrentAddressPopup()

                var intent = Intent(requireContext(), PaymentActivity::class.java)
                intent.putExtra("name",mName)
                intent.putExtra("mail",mEmail)
                intent.putExtra("phone",mPhone)
                intent.putExtra("amount",mTotal_Amount.toString())
                intent.putExtra("quantity",mQty.toString())
                intent.putExtra("address_id",mSelectedAddressId.toString())
                intent.putExtra("user_address",mAddress.toString())
                intent.putExtra("user_city",mCity.toString())
                intent.putExtra("user_state",mState.toString())
                intent.putExtra("user_pincode",mPin.toString())
                intent.putExtra("wallet_amount",mWallet_Amount.toString())
                intent.putExtra("bottle_amount",mSecurity_Amount.toString())
                intent.putExtra("bottle_quantity",mSecurity_qty.toString())
                startActivity(intent)

//                var intent = Intent(requireContext(), DemoActivity::class.java)
//                intent.putExtra("name",mName)
//                intent.putExtra("mail",mEmail)
//                intent.putExtra("phone",mPhone)
//                intent.putExtra("amount",mTotal_Amount.toString())
//                startActivity(intent)


            } else if (selected_mode.equals("Cash On Delivery")) {
                presenter!!.hitOfflineBookedOrderApi(mUser_id,"1",mQty,mTotal_Amount.toString(),mTotal_Amount.toString(),mSelectedAddressId,mName,mEmail,mPhone,mAddress,mCity,mState,mPin,"1","0","cod","0",mWallet_Amount,mSecurity_Amount.toString(),mSecurity_qty)
                Snackbar.make(
                    payment_details_submit_btn, "Cash On Delivery mode selected",
                    Snackbar.LENGTH_LONG
                ).show()
//                var action: NavDirections =
//                    ProductDetailsFragmentDirections.actionOrderDetailsFragmentToAddressListFragment(
//                        qty,
//                        mProduct_price,
//                        "ProductDetailsPage"
//                    )
//                navController.navigate(action)
//                Navigation.findNavController(product_details_amount_details_qty).navigate(R.id.action_orderDetailsFragment_to_addressListFragment)
            } else {
                Snackbar.make(
                    payment_details_submit_btn, "Something went wrong!!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        if(mUser_order_log <= 0)
        {

            var qty:Int = mQty.toInt()
            mSecurity_qty = qty.toString()
            var product_price:Int = mProduct_Price.toInt()
            mSecurity_Amount = qty*100;
            mTotal_Amount = (product_price*qty) + mSecurity_Amount
            payment_details_security_money_layout.visibility = View.VISIBLE
            payment_details_security_money_value.text = mSecurity_Amount.toString()
            payment_details_total_amount_value.text = mTotal_Amount.toString()
            if(mTotal_Amount.toInt() >=  mWallet_Amount.toInt())
            {
                mTotal_Amount = mTotal_Amount-mWallet_Amount.toInt()
            }
        }else{
            var qty:Int = mQty.toInt()
            var product_price:Int = mProduct_Price.toInt()
//            mSecurity_Amount = qty*100;
            mTotal_Amount = (product_price*qty)
            payment_details_security_money_layout.visibility = View.GONE
            payment_details_total_amount_value.text = mTotal_Amount.toString()
            if(mTotal_Amount.toInt() >=  mWallet_Amount.toInt())
            {
                mTotal_Amount = mTotal_Amount - mWallet_Amount.toInt()
            }

        }


    }


    override fun getUserAddressResponse(response: SingleUserAddressData) {
        if (response.status == 200) {
            mName = response.userAddress?.name.toString()
            mEmail = response.userAddress?.email.toString()
            mPhone = response.userAddress?.phone.toString()
            payment_details_billing_username.text = response.userAddress?.name
            var address = response.userAddress?.address
            var city = response.userAddress?.city
            var state = response.userAddress?.state
            var pin = response.userAddress?.pin
            mAddress = address.toString()
            mCity = city.toString()
            mState = state.toString()
            mPin = pin.toString()
            var mergeAddress = "$address, $city, $state, $pin"
            payment_details_billing_address.text = mergeAddress
            payment_details_billing_mail.text = response.userAddress?.email
            payment_details_billing_mobile_no.text = response.userAddress?.phone
        }
    }

    override fun getUserOrderCountResponse(response: OrderCountData) {
        if (response.status == 200)
        {
            mUser_order_log = response.totalOrders!!.toInt()
        }
    }

    override fun getOfflineBookedOrderResponse(response: BookedOrderResponse) {
        if(response.status == 200)
        {
            getSuccessPopup()
        }
    }

    override fun getWalletAmountResponse(response: WalletListData) {
        if(response.status == 200)
        {
            mWallet_Amount = response.walletAmount?.amount.toString()
        }
    }

    private fun getSuccessPopup() {
        mDialogBuilder = AlertDialog.Builder(requireContext())
        val li = layoutInflater
        val popupView: View = li.inflate(R.layout.popup_successful, null)
        mDialogBuilder.setView(popupView)
        mDialog = mDialogBuilder.create()
        mDialog.show()
        popupView.popup_successful_ok_btn.setOnClickListener {
            mDialog.dismiss()
            Navigation.findNavController(payment_details_billing_mail).navigate(R.id.action_paymentDetailsFragment_to_orderListFragment)
        }
    }

}