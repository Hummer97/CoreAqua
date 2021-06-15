package com.xpertwebtech.coreaqua.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.paykun.sdk.PaykunApiCall
import com.paykun.sdk.eventbus.Events.PaymentMessage
import com.paykun.sdk.eventbus.GlobalBus
import com.paykun.sdk.helper.PaykunHelper
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.json.JSONException
import org.json.JSONObject


class DemoActivity : AppCompatActivity() {

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        var bundle :Bundle ?=intent.extras
        amount = bundle!!.getString("amount").toString()
        customerName = bundle.getString("name").toString()
        customerPhone = bundle.getString("phone").toString()
        customerEmail = bundle.getString("mail").toString()
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
        PaykunApiCall.Builder(this@DemoActivity)
            .sendJsonObject(`object`) // Paykun api to initialize your payment and send info.
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun getResults(message: PaymentMessage) {
        if (message.results.equals(PaykunHelper.MESSAGE_SUCCESS, ignoreCase = true)) {
            // do your stuff here
            // message.getTransactionId() will return your failed or succeed transaction id
            /* if you want to get your transaction detail call message.getTransactionDetail()
             *  getTransactionDetail return all the field from server and you can use it here as per your need
             *  For Example you want to get Order id from detail use message.getTransactionDetail().order.orderId */
            if (!TextUtils.isEmpty(message.transactionId)) {
                val transactionId = message.transactionDetail.paymentId
                val PaymentStatus = message.results
                //                buyPackage(transactionId,PaymentStatus);
                Toast.makeText(
                    this@DemoActivity,
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
            Toast.makeText(this@DemoActivity, "Your Transaction is failed", Toast.LENGTH_SHORT)
                .show()
        } else if (message.results.equals(PaykunHelper.MESSAGE_SERVER_ISSUE, ignoreCase = true)) {
            // do your stuff here
            Toast.makeText(
                this@DemoActivity,
                PaykunHelper.MESSAGE_SERVER_ISSUE,
                Toast.LENGTH_SHORT
            ).show()
        } else if (message.results.equals(
                PaykunHelper.MESSAGE_ACCESS_TOKEN_MISSING,
                ignoreCase = true
            )
        ) {
            // do your stuff here
            Toast.makeText(this@DemoActivity, "Access Token missing", Toast.LENGTH_SHORT).show()
        } else if (message.results.equals(
                PaykunHelper.MESSAGE_MERCHANT_ID_MISSING,
                ignoreCase = true
            )
        ) {
            // do your stuff here
            Toast.makeText(this@DemoActivity, "Merchant Id is missing", Toast.LENGTH_SHORT)
                .show()
        } else if (message.results.equals(
                PaykunHelper.MESSAGE_INVALID_REQUEST,
                ignoreCase = true
            )
        ) {
            Toast.makeText(this@DemoActivity, "Invalid Request", Toast.LENGTH_SHORT).show()
        } else if (message.results.equals(
                PaykunHelper.MESSAGE_NETWORK_NOT_AVAILABLE,
                ignoreCase = true
            )
        ) {
            Toast.makeText(this@DemoActivity, "Network is not available", Toast.LENGTH_SHORT)
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
}