package com.xpertwebtech.coreaqua.ui.PaykunPayment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.paykun.sdk.PaykunApiCall
import com.paykun.sdk.eventbus.Events
import com.paykun.sdk.eventbus.GlobalBus
import com.paykun.sdk.helper.PaykunHelper
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseActivity
import com.xpertwebtech.coreaqua.ui.MainActivity
import com.xpertwebtech.coreaqua.ui.MyOrders.OrderListFragment
import com.xpertwebtech.coreaqua.ui.paymentDetails.PaymentDetailsFragment
import kotlinx.android.synthetic.main.popup_successful.*
import kotlinx.android.synthetic.main.popup_successful.view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.json.JSONException
import org.json.JSONObject

class PaymentActivity : BaseActivity<PaymentView,PaymentPresenter>(),PaymentView{
    private lateinit var mDialog: AlertDialog
    private lateinit var mDialogBuilder: AlertDialog.Builder
    private lateinit var myFragment: OrderListFragment

    //    private String Testacceskey="35147FA826E0A0ABFB9A9D844CEF3E49";
    //    private String testmerchentid="255265780610472";
    //    private String TestacceskeyLIVE="6A75D3707CC25C07001C004D34286CD3";
    //    private String testmerchentidLIVE="837773351399967";
    private lateinit var mSharedPrefManager: SharedPrefManager
    private val merchantIdLive ="973782451302708" // merchant id for live mode application id  = com.paykunsandbox.live

    private val accessTokenLive ="6E51763DF010B981F214533F294D2A0C" // access token for live mode application id  = com.paykunsandbox.live


    private val merchantIdSandbox ="986071909814792" // merchant id for sandbox mode application id = com.paykun.sandbox

    private val accessTokenSandbox ="53F878D3595BA4C3619E215FEF41FEA7" // access token for sandbox application id = com.paykun.sandbox


    //    private String customerName="Bhavik",customerPhone="8256400020",customerEmail="bhavik.makvana@paykun.com";
    //    private String productName="Paykun Test Product",orderNo="7895812590123",amount="10";
    private lateinit var mUserId: String//    private String customerName="Bhavik",customerPhone="8256400020",customerEmail="bhavik.makvana@paykun.com";

    //    private String productName="Paykun Test Product",orderNo="7895812590123",amount="10";
    private lateinit var customerName: String//    private String customerName="Bhavik",customerPhone="8256400020",customerEmail="bhavik.makvana@paykun.com";

    //    private String productName="Paykun Test Product",orderNo="7895812590123",amount="10";
    private lateinit var customerPhone: String//    private String customerName="Bhavik",customerPhone="8256400020",customerEmail="bhavik.makvana@paykun.com";

    //    private String productName="Paykun Test Product",orderNo="7895812590123",amount="10";
    private lateinit var customerEmail: String
    private lateinit var productName: String
    private lateinit var orderNo:String
    private lateinit var amount:String
    private lateinit var Package_Id:String
    private lateinit var mQty:String
    private lateinit var mSecurity_Amount:String
    private lateinit var mAddress:String
    private lateinit var mCity:String
    private lateinit var mPin:String
    private lateinit var mSecurity_qty:String
    private lateinit var mSelectedAddressId:String
    private lateinit var mState:String
    private lateinit var mTotal_Amount:String
    private lateinit var mWallet_Amount:String
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    override fun getContentView(): Int {
        return R.layout.activity_payment
    }

    override fun getPresenterClass(): Class<PaymentPresenter> {
        return PaymentPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {

        fragmentManager = supportFragmentManager
        fragmentTransaction  = fragmentManager.beginTransaction()
         myFragment = OrderListFragment()



        var bundle :Bundle ?=intent.extras
        amount = bundle!!.getString("amount").toString()
        customerName = bundle.getString("name").toString()
        customerPhone = bundle.getString("phone").toString()
        customerEmail = bundle.getString("mail").toString()
        mQty = bundle.getString("quantity").toString()
        mTotal_Amount = bundle.getString("amount").toString()
        mSelectedAddressId = bundle.getString("address_id").toString()
        mAddress = bundle.getString("user_address").toString()
        mCity = bundle.getString("user_city").toString()
        mState = bundle.getString("user_state").toString()
        mPin = bundle.getString("user_pincode").toString()
        mWallet_Amount = bundle.getString("wallet_amount").toString()
        mSecurity_Amount = bundle.getString("bottle_amount").toString()
        mSecurity_qty = bundle.getString("bottle_quantity").toString()
        productName = "Core Aqua water"
        mSharedPrefManager = SharedPrefManager.getInstance(applicationContext)
        mUserId = mSharedPrefManager.getUser().id.toString()

//        mError = findViewById(R.id.payment_gateway_email_error_txt);
//        mEmailID = findViewById(R.id.payment_gateway_email);
//        mSubmitBtn = findViewById(R.id.payment_gateway_email_submit_btn);
//        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = mEmailID.getText().toString();
//                if(emailValidate(email))
//                {
//                    customerEmail = email;
//                    getPaymentGateway();
//                }
//
//            }
//        });
        getPaymentGateway()
    }

//    private boolean emailValidate(String Email) {
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        if(Email.equals(""))
//        {
//            mError.setVisibility(View.VISIBLE);
//            mError.setText("* Please Fill the Email id.");
//            return false;
//        }
//        else if (!Email.matches(emailPattern))
//        {
//            mError.setVisibility(View.VISIBLE);
//            mError.setText("* Invalid email address");
//            return false;
//        }
//        else
//        {
//            mError.setVisibility(View.GONE);
//            return true;
//        }
//    }

    //    private boolean emailValidate(String Email) {
    //        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //        if(Email.equals(""))
    //        {
    //            mError.setVisibility(View.VISIBLE);
    //            mError.setText("* Please Fill the Email id.");
    //            return false;
    //        }
    //        else if (!Email.matches(emailPattern))
    //        {
    //            mError.setVisibility(View.VISIBLE);
    //            mError.setText("* Invalid email address");
    //            return false;
    //        }
    //        else
    //        {
    //            mError.setVisibility(View.GONE);
    //            return true;
    //        }
    //    }
    private fun getPaymentGateway() {
        val `object` = JSONObject()
        try {
            `object`.put("merchant_id", merchantIdSandbox)
            `object`.put("access_token", accessTokenSandbox)
            `object`.put("customer_name", customerName)
            `object`.put("customer_email", customerEmail)
            `object`.put("customer_phone", customerPhone)
            `object`.put("product_name", productName)
            `object`.put(
                "order_no",
                System.currentTimeMillis()
            ) // order no. should have 10 to 30 character in numeric format
            `object`.put("amount", amount) // minimum amount should be 10
            `object`.put("isLive", false) // need to send false if you are in sandbox mode
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        PaykunApiCall.Builder(this@PaymentActivity)
            .sendJsonObject(`object`) // Paykun api to initialize your payment and send info.
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun getResults(message: Events.PaymentMessage) {
        if (message.results.equals(PaykunHelper.MESSAGE_SUCCESS, ignoreCase = true)) {
            // do your stuff here
            // message.getTransactionId() will return your failed or succeed transaction id
            /* if you want to get your transaction detail call message.getTransactionDetail()
             *  getTransactionDetail return all the field from server and you can use it here as per your need
             *  For Example you want to get Order id from detail use message.getTransactionDetail().order.orderId */
            if (!TextUtils.isEmpty(message.transactionId)) {
//                val b = Bundle()
                val transactionId = message.transactionDetail.paymentId
                val PaymentStatus = message.results
//                b.putString("transactionId", transactionId);
//                b.putString("PaymentStatus", PaymentStatus);
//                b.putString("root", "paymentPage");
//                myFragment.setArguments(b);
//                fragmentTransaction.add(R.id.nav_host_fragment, myFragment).commit();
                presenter!!.hitBookedOrderApi(mUserId,"1",mQty,mTotal_Amount,mTotal_Amount,mSelectedAddressId,customerName,customerEmail,customerPhone,mAddress,mCity,mState,mPin,"1",transactionId,"online",PaymentStatus,mWallet_Amount,mSecurity_Amount,mSecurity_qty)

                //                buyPackage(transactionId,PaymentStatus);
                Toast.makeText(
                    this@PaymentActivity,
                    "Your Transaction is succeed with transaction id : " + message.transactionId,
                    Toast.LENGTH_SHORT
                ).show()
                Log.v(
                    " order id ",
                    " getting order id value : " + message.transactionDetail.order.orderId + "getting Payment Status: " + message.results
                )
            }
        } else if (message.results.equals(PaykunHelper.MESSAGE_FAILED, ignoreCase = true)) {
            // do your stuff here
            Toast.makeText(this@PaymentActivity, "Your Transaction is failed", Toast.LENGTH_SHORT)
                .show()
            var intent: Intent = Intent(this@PaymentActivity,MainActivity::class.java)
            startActivity(intent)
        } else if (message.results.equals(PaykunHelper.MESSAGE_SERVER_ISSUE, ignoreCase = true)) {
            // do your stuff here
            Toast.makeText(
                this@PaymentActivity,
                PaykunHelper.MESSAGE_SERVER_ISSUE,
                Toast.LENGTH_SHORT
            ).show()
        } else if (message.results.equals(
                PaykunHelper.MESSAGE_ACCESS_TOKEN_MISSING,
                ignoreCase = true
            )
        ) {
            // do your stuff here
            Toast.makeText(this@PaymentActivity, "Access Token missing", Toast.LENGTH_SHORT).show()
        } else if (message.results.equals(
                PaykunHelper.MESSAGE_MERCHANT_ID_MISSING,
                ignoreCase = true
            )
        ) {
            // do your stuff here
            Toast.makeText(this@PaymentActivity, "Merchant Id is missing", Toast.LENGTH_SHORT)
                .show()
        } else if (message.results.equals(
                PaykunHelper.MESSAGE_INVALID_REQUEST,
                ignoreCase = true
            )
        ) {
            Toast.makeText(this@PaymentActivity, "Invalid Request", Toast.LENGTH_SHORT).show()
        } else if (message.results.equals(
                PaykunHelper.MESSAGE_NETWORK_NOT_AVAILABLE,
                ignoreCase = true
            )
        ) {
            Toast.makeText(this@PaymentActivity, "Network is not available", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onStart() {
        super.onStart()
        // Register this activity to listen to event.
        GlobalBus.getBus().register(this)
    }

    override fun onStop() {
        super.onStop()
        // Unregister from activity
        GlobalBus.getBus().unregister(this)
    }

    override fun getBookedOrderResponse(response: BookedOrderResponse) {
        if (response.status == 200)
        {
//            getSuccessPopup()
//            fragmentTransaction.add(R.id.nav_host_fragment, myFragment).commit()

            var intent: Intent = Intent(this@PaymentActivity,MainActivity::class.java)
            startActivity(intent)

        }
    }

//    private void buyPackage(String transactionId,String PaymentStatus) {
//        Api service = RetrofitClientInstance.getRetrofitInstance().create(Api.class);
//        Call<PackageSaveClass> call = service.buyPackageResponse(mUserId,Package_Id,transactionId,PaymentStatus);
//        call.enqueue(new Callback<PackageSaveClass>() {
//            @Override
//            public void onResponse(Call<PackageSaveClass> call, Response<PackageSaveClass> response) {
//                if(response.isSuccessful())
//                {
//                    Snackbar.make(mEmailID, response.body().getMessage(), Snackbar.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(), Splashscreen.class);
//                    startActivity(intent);
//
//                }
//                else
//                {
//                    Snackbar.make(mEmailID, "Something went wrong!", Snackbar.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PackageSaveClass> call, Throwable t) {
//                Snackbar.make(mEmailID, t.getMessage(), Snackbar.LENGTH_LONG).show();
//            }
//        });
//
//    }

    //-------------------------------------------------------------------Upload PDF Code---------------------------------------------------------------
//    private fun getSuccessPopup() {
//        mDialogBuilder = AlertDialog.Builder(applicationContext)
//        val li = layoutInflater
//        val popupView: View = li.inflate(R.layout.popup_successful, null)
//        mDialogBuilder.setView(popupView)
//        mDialog = mDialogBuilder.create()
//        mDialog.show()
//        popupView.popup_successful_ok_btn.setOnClickListener {
//            mDialog.dismiss()
//            fragmentTransaction.add(R.id.nav_host_fragment, myFragment).commit()
//        }
//    }

}