package com.xpertwebtech.coreaqua.ui.ProfileDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.base.BaseFragment
import kotlinx.android.synthetic.main.activity_signup.sign_up_isReferCode_exist
import kotlinx.android.synthetic.main.fragment_profile_details.*

class ProfileDetailsFragment : BaseFragment<ProfileDetailsView,ProfileDetailsPresenter>(),ProfileDetailsView {
    override fun getContentView(): Int {
        return R.layout.fragment_profile_details
    }

    override fun getPresenterClass(): Class<ProfileDetailsPresenter> {
        return ProfileDetailsPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        // set checkbox checked change listener
        profile_details_change_password_checkBox.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                profile_details_confirm_password.visibility = View.VISIBLE
                profile_details_old_password.visibility = View.VISIBLE
                profile_details_new_password.visibility = View.VISIBLE
                profile_details_update_password_btn.visibility = View.VISIBLE
            }else{
                profile_details_confirm_password.visibility = View.GONE
                profile_details_old_password.visibility = View.GONE
                profile_details_new_password.visibility = View.GONE
                profile_details_update_password_btn.visibility = View.GONE
            }
        }
    }

}