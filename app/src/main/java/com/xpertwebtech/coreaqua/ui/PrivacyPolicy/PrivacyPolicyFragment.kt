package com.xpertwebtech.coreaqua.ui.PrivacyPolicy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.base.BaseFragment


class PrivacyPolicyFragment : BaseFragment<PrivacyPolicyView,PrivacyPolicyPresenter>(),PrivacyPolicyView {
    override fun getContentView(): Int {
        return R.layout.fragment_privacy_policy
    }

    override fun getPresenterClass(): Class<PrivacyPolicyPresenter> {
        return PrivacyPolicyPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {

    }

}