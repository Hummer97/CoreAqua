package com.xpertwebtech.coreaqua.ui.AddressList

import java.io.Serializable

class AddressData: Serializable {
    private var isChecked = false
    private lateinit var title:String

    fun isChecked(): Boolean {
        return isChecked
    }

    fun setChecked(checked: Boolean) {
        isChecked = checked
    }

    fun title(): String {
        return title
    }

    fun title(mtitle: String) {
        title = mtitle
    }
}