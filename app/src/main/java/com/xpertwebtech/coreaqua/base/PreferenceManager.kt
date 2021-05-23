package com.rns.rnsecomapp.base

import android.content.Context
import android.content.SharedPreferences

open class PreferenceManager protected constructor(context: Context) {
    private val preference: SharedPreferences =
        context.getSharedPreferences("BigBrotherUser", Context.MODE_PRIVATE)

    companion object {
        private var preferenceManager: PreferenceManager? = null
        private const val LOGIN = "LOGIN"
        private const val CAMEBACK = "CAMEBACK"
        private const val SEARCH = "SEARCH"
        private const val TOKEN = "TOKEN"
        private const val EMAIL = "EMAIL"
        private const val USERNAME = "USERNAME"
        private const val ROLEID = "ROLEID"
        private const val PROFILEDETAILID = "PROFILEDETAILID"
        private const val COMPANYID = "COMPANYID"
        private const val USERID = "USERID"
        private const val PHONE = "PHONE"
        private const val DOB = "DOB"
        private const val MEMBER = "MEMBER"
        private const val ADVERTISING = "ADVERTISING"
        private const val HIRING = "HIRING"
        private const val DEPARTMENT = "DEPARTMENT"

        fun getInstance(context: Context): PreferenceManager {
            if (preferenceManager == null)
                preferenceManager =
                    PreferenceManager(context)
            return preferenceManager as PreferenceManager
        }
    }

    fun setToken(login: String) {
        preference.edit().putString(TOKEN, login).apply()
    }

    val getToken: String
        get() = preference.getString(TOKEN, "AR-AUG-ARST-BIZBR-2019OLLY")!!

    fun setLogin(login: Boolean) {
        preference.edit().putBoolean(LOGIN, login).apply()
    }

    val getLogin : Boolean
        get() = preference.getBoolean(LOGIN, false)

    fun setCameBack(check: Boolean) {
        preference.edit().putBoolean(CAMEBACK, check).apply()
    }

    val getCameBack : Boolean
        get() = preference.getBoolean(CAMEBACK, false)

    val searchBool: Boolean
        get() = preference.getBoolean(SEARCH, false)

    fun setsearchBool(search: Boolean) {
        preference.edit().putBoolean(SEARCH, search).apply()
    }


    val advertisingBool: Boolean
        get() = preference.getBoolean(ADVERTISING, false)

    fun setAdvertisingBool(advertising: Boolean) {
        preference.edit().putBoolean(ADVERTISING, advertising).apply()
    }


    val HiringBool: Boolean
        get() = preference.getBoolean(HIRING, false)

    fun setHiringBool(hiring: Boolean) {
        preference.edit().putBoolean(HIRING, hiring).apply()
    }


    val departmentBool: Boolean
        get() = preference.getBoolean(DEPARTMENT, false)

    fun setdepartmentBool(department: Boolean) {
        preference.edit().putBoolean(DEPARTMENT, department).apply()
    }

    fun setMemberCount(memberCount: Int) {
        preference.edit().putInt(MEMBER, memberCount).apply()
    }

    val getMemberCount: Int
        get() = preference.getInt(MEMBER, 0)

    val isLoggedIn: Boolean
        get() = preference.getBoolean(LOGIN, false)

    fun setAuthToken(authToken: String) {
        preference.edit().putString(TOKEN, authToken).apply()
    }

    val getAuthToken: String
        get() = preference.getString(TOKEN, "").toString()

    fun setEmail(email: String) {
        preference.edit().putString(EMAIL, email).apply()
    }

    val getEmail: String
        get() = preference.getString(EMAIL, "").toString()

    fun setUserName(username: String) {
        preference.edit().putString(USERNAME, username).apply()
    }

    val getUserName: String
        get() = preference.getString(USERNAME, "").toString()

    fun setDob(dob: String) {
        preference.edit().putString(DOB, dob).apply()
    }

    val getDob: String
        get() = preference.getString(DOB, "").toString()

    fun setPhone(phone: String) {
        preference.edit().putString(PHONE, phone.toString()).apply()
    }

    val getPhone: String
        get() = preference.getString(PHONE, "").toString()


    fun setRoleId(roleId: Int) {
        preference.edit().putInt(ROLEID, roleId).apply()
    }

    val getRoleId: Int
        get() = preference.getInt(ROLEID, 0)

    fun setProfileDetailId(profileDetailId: Int) {
        preference.edit().putInt(PROFILEDETAILID, profileDetailId).apply()
    }

    val getProfileDetailId: Int
        get() = preference.getInt(PROFILEDETAILID, 0)

    fun setCompanyId(companyId: Int) {
        preference.edit().putInt(COMPANYID, companyId).apply()
    }

    val getCompanyId: Int
        get() = preference.getInt(COMPANYID, 0)

    fun setUserId(userId: Int) {
        preference.edit().putInt(USERID, userId).apply()
    }

    val getUserId: Int
        get() = preference.getInt(USERID, 0)

    open fun clear() {
        preference.edit().clear().apply()
    }

}