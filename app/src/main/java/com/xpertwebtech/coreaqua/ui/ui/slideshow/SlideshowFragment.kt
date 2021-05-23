package com.xpertwebtech.coreaqua.ui.ui.slideshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.xpertwebtech.coreaqua.R
import kotlinx.android.synthetic.main.fragment_slideshow.*
import kotlinx.android.synthetic.main.fragment_slideshow.view.*


class SlideshowFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View =  inflater.inflate(R.layout.fragment_slideshow, container, false)

        view.privacy_policy_btn.setOnClickListener {
            Navigation.findNavController(privacy_policy_btn).navigate(R.id.action_nav_slideshow_to_privacyPolicyFragment)
        }

        view.refer_and_earn_btn.setOnClickListener {
            Navigation.findNavController(privacy_policy_btn).navigate(R.id.action_nav_slideshow_to_referEranFragment)
        }

        return view
    }

}