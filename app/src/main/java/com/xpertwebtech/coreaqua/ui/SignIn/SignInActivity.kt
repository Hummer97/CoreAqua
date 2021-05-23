package com.xpertwebtech.coreaqua.ui.SignIn

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseActivity
import com.xpertwebtech.coreaqua.dataModel.UserDataClass
import com.xpertwebtech.coreaqua.ui.MainActivity
import com.xpertwebtech.coreaqua.ui.Signup.SignupActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity<SignInView,SignInPresenter>(),SignInView {
    private lateinit var mEmail:String
    private lateinit var mPassword:String
    override fun getContentView(): Int {
        supportActionBar!!.hide()
        return R.layout.activity_sign_in
    }

    override fun getPresenterClass(): Class<SignInPresenter> {
        return SignInPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        sign_in_registration_btn.setOnClickListener {
            var intent = Intent(applicationContext, SignupActivity::class.java)
            startActivity(intent)
        }
        sign_in_btn.setOnClickListener {
            mEmail = sign_in_email_id.text.toString()
            mPassword = sign_in_password.text.toString()

            if(validateChecked())
            {
                presenter?.hitLogInApi(mEmail,"2",mPassword)
            }

        }
    }

    private fun validateChecked(): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (mEmail.isEmpty())
        {
            sign_in_email_error_txt.visibility = View.VISIBLE
            sign_in_email_error_txt.text = "**Please Enter Registered Email ID"
            return false
        }
        else if(!mEmail.matches(emailPattern.toRegex()))
        {
            sign_in_email_error_txt.visibility = View.VISIBLE
            sign_in_password_error_txt.visibility = View.GONE
            sign_in_email_error_txt.text = "**Please Enter Valid Mail ID"
            return false
        }
        else if(mPassword.isEmpty())
        {
            sign_in_email_error_txt.visibility = View.GONE
            sign_in_password_error_txt.visibility = View.VISIBLE
            sign_in_password_error_txt.text = "**Please Enter Password"
            return false
        }
        else{
            sign_in_email_error_txt.visibility = View.GONE
            sign_in_password_error_txt.visibility = View.GONE
            return true
        }

    }

    override fun getLogInResponse(response: UserDataClass) {
        if (response.status == 200)
        {
            Snackbar.make(sign_in_registration_btn, response.msg.toString(), Snackbar.LENGTH_LONG).show()
            SharedPrefManager.getInstance(applicationContext).userLogin(response.user)
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }else if(response.status == 400)
        {
            Snackbar.make(sign_in_registration_btn, response.msg.toString(), Snackbar.LENGTH_LONG).show()
        }
        else
        {
            Snackbar.make(sign_in_registration_btn, "Somethings went wrong!", Snackbar.LENGTH_LONG).show()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}