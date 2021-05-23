package com.xpertwebtech.coreaqua.ui.Refer_Earn

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_refer_eran.*


class ReferEranFragment : BaseFragment<ReferEranView, ReferEranPresenter>(),ReferEranView {
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var mUserID:String
    private lateinit var mUserReferCode:String
    override fun getContentView(): Int {
        return R.layout.fragment_refer_eran
    }

    override fun getPresenterClass(): Class<ReferEranPresenter> {
        return ReferEranPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        sharedPrefManager = SharedPrefManager.getInstance(requireContext())
        mUserReferCode = sharedPrefManager.user.referCode.toString()

        refer_and_earn_code.text = mUserReferCode


        refer_and_earn_share_btn.setOnClickListener(View.OnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Referal code:=" + mUserReferCode)
            val appPackageName: String = requireContext().getPackageName()
            val app_url = "https://play.google.com/store/apps/details?id=$appPackageName"
            shareIntent.putExtra(Intent.EXTRA_TEXT, app_url)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        })

    }


}