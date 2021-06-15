package com.xpertwebtech.coreaqua.ui.ProfileDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import com.xpertwebtech.coreaqua.dataModel.UserDataClass
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_profile_details.*

class ProfileDetailsFragment : BaseFragment<ProfileDetailsView,ProfileDetailsPresenter>(),ProfileDetailsView {
    private lateinit var mUserID: String
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var mUserName:String
    private lateinit var mMobileNo:String
    private lateinit var mEmail:String
    override fun getContentView(): Int {
        return R.layout.fragment_profile_details
    }

    override fun getPresenterClass(): Class<ProfileDetailsPresenter> {
        return ProfileDetailsPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        // set checkbox checked change listener
        sharedPrefManager  = SharedPrefManager.getInstance(requireContext())
        mUserID = sharedPrefManager.user.id.toString()
        mUserName = sharedPrefManager.user.name.toString()
        mEmail = sharedPrefManager.user.email.toString()
        mMobileNo = sharedPrefManager.user.phone.toString()

        profile_details_username_txt.visibility = View.VISIBLE
        profile_details_mobile_no_txt.visibility = View.VISIBLE
        profile_details_email_id_txt.visibility = View.VISIBLE
        profile_details_cancel_btn.visibility = View.GONE
        profile_details_edit_btn.visibility = View.VISIBLE

        if(mUserName != null && mMobileNo != null && mEmail != null)
        {
            profile_details_username_txt.text = mUserName
            profile_details_mobile_no_txt.text = mMobileNo
            profile_details_email_id_txt.text = mEmail

            profile_details_username.setText(mUserName)
            profile_details_mobile_no.setText(mMobileNo)
            profile_details_email_id.setText(mEmail)

            profile_details_username.visibility = View.INVISIBLE
            profile_details_mobile_no.visibility = View.INVISIBLE
            profile_details_email_id.visibility = View.INVISIBLE
            profile_details_update_profile_btn.visibility = View.GONE
        }

        profile_details_edit_btn.setOnClickListener {
            profile_details_username_txt.visibility = View.INVISIBLE
            profile_details_mobile_no_txt.visibility = View.INVISIBLE
            profile_details_email_id_txt.visibility = View.INVISIBLE

            profile_details_cancel_btn.visibility = View.VISIBLE
            profile_details_edit_btn.visibility = View.GONE

            profile_details_username.visibility = View.VISIBLE
            profile_details_mobile_no.visibility = View.VISIBLE
            profile_details_email_id.visibility = View.VISIBLE
            profile_details_update_profile_btn.visibility = View.VISIBLE
        }

        profile_details_cancel_btn.setOnClickListener {
            profile_details_username_txt.visibility = View.VISIBLE
            profile_details_mobile_no_txt.visibility = View.VISIBLE
            profile_details_email_id_txt.visibility = View.VISIBLE
            profile_details_cancel_btn.visibility = View.GONE
            profile_details_edit_btn.visibility = View.VISIBLE
            profile_details_update_profile_btn.visibility = View.GONE
        }

        profile_details_change_password_checkBox.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                profile_details_confirm_password.visibility = View.VISIBLE
                profile_details_new_password.visibility = View.VISIBLE
                profile_details_update_password_btn.visibility = View.VISIBLE
            }else{
                profile_details_confirm_password.visibility = View.GONE
                profile_details_new_password.visibility = View.GONE
                profile_details_update_password_btn.visibility = View.GONE
            }
        }

        profile_details_update_profile_btn.setOnClickListener {
            var name:String = profile_details_username.text.toString()
            var mobile_no:String = profile_details_mobile_no.text.toString()

            if(isValidationChecked(name,mobile_no))
            {
                presenter?.hitUpdatedProfileApi(mUserID,name,mobile_no)
            }

        }

        profile_details_update_password_btn.setOnClickListener {
            var password:String = profile_details_new_password.text.toString()
            var confirm_password:String = profile_details_confirm_password.text.toString()
            if(isPasswordValidationChecked(password,confirm_password))
            {
                presenter?.hitUpdatePasswordApi(mUserID,password)
            }
        }
    }

    private fun isPasswordValidationChecked(password: String, confirmPassword: String): Boolean {
        if (password.isEmpty())
        {
            Snackbar.make(profile_details_update_password_btn,"**Please Enter Password",Snackbar.LENGTH_LONG).show()
            return false
        }else if (password.length < 8)
        {
            Snackbar.make(profile_details_update_password_btn,"**Password should be 8 digits or above",Snackbar.LENGTH_LONG).show()
            return false
        }else if (confirmPassword.isEmpty())
        {
            Snackbar.make(profile_details_update_password_btn,"**Please Enter confirm password",Snackbar.LENGTH_LONG).show()
            return false
        }else if (confirmPassword != password)
        {
            Snackbar.make(profile_details_update_password_btn,"**Password not match",Snackbar.LENGTH_LONG).show()
            return false
        }else
        {
//                sign_up_sector_error_txt.visibility = View.GONE
            return true
        }

    }

    override fun getUpdatedProfileResponse(response: UserDataClass) {
        if (response.status == 200)
        {
            if(response.user!=null) {
                SharedPrefManager.getInstance(requireContext()).userLogin(response.user)
                mUserID = sharedPrefManager.user.id.toString()
                mUserName = sharedPrefManager.user.name.toString()
                mEmail = sharedPrefManager.user.email.toString()
                mMobileNo = sharedPrefManager.user.phone.toString()
                profile_details_username_txt.text = mUserName
                profile_details_mobile_no_txt.text = mMobileNo
                profile_details_email_id_txt.text = mEmail

                profile_details_username.setText(mUserName)
                profile_details_mobile_no.setText(mMobileNo)
                profile_details_email_id.setText(mEmail)

                profile_details_username.visibility = View.INVISIBLE
                profile_details_mobile_no.visibility = View.INVISIBLE
                profile_details_email_id.visibility = View.INVISIBLE
                profile_details_update_profile_btn.visibility = View.GONE

                profile_details_username_txt.visibility = View.VISIBLE
                profile_details_mobile_no_txt.visibility = View.VISIBLE
                profile_details_email_id_txt.visibility = View.VISIBLE
                profile_details_cancel_btn.visibility = View.GONE
                profile_details_edit_btn.visibility = View.VISIBLE
                profile_details_update_profile_btn.visibility = View.GONE
            }
        }else
        {
            Snackbar.make(profile_details_cancel_btn,"Something went wrong!!",Snackbar.LENGTH_LONG).show()
        }
    }

    override fun getUpdatedPasswordResponse(response: UserDataClass) {
        if (response.status == 200)
        {
            if (response.user !=null)
            {
                profile_details_confirm_password.visibility = View.GONE
                profile_details_new_password.visibility = View.GONE
                profile_details_update_password_btn.visibility = View.GONE

                profile_details_confirm_password.text = null
                profile_details_new_password.text = null
                if (profile_details_change_password_checkBox.isChecked()) {
                    profile_details_change_password_checkBox.setChecked(false);
                }
                Snackbar.make(profile_details_cancel_btn,"Password updated successfully",Snackbar.LENGTH_LONG).show()
            }

        }else
        {
            Snackbar.make(profile_details_cancel_btn,"Something went wrong!!",Snackbar.LENGTH_LONG).show()
        }
    }

    private fun isValidationChecked(name: String, mobileNo: String): Boolean {
        if(name.isEmpty())
        {
            Snackbar.make(profile_details_cancel_btn,"Please Enter username",Snackbar.LENGTH_LONG).show()
            return false
        }else if (mobileNo.isEmpty())
        {
            Snackbar.make(profile_details_cancel_btn,"Please Enter Mobile number",Snackbar.LENGTH_LONG).show()
            return false
        }else if (mobileNo.length < 10 || mobileNo.length > 10)
        {
            Snackbar.make(profile_details_cancel_btn,"Mobile No should be 10 digits",Snackbar.LENGTH_LONG).show()
            return false
        }else
        {
            return true
        }
    }


}