package com.xpertwebtech.coreaqua.ui.Signup

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.xpertwebtech.coreaqua.Interface.RecyclerViewClickInterface
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.base.BaseActivity
import com.xpertwebtech.coreaqua.ui.MainActivity
import com.xpertwebtech.coreaqua.ui.SignIn.SignInActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : BaseActivity<SignupView,SignupPresenter>(),SignupView {
    private var mBlock_Id:String? = null
    private var mSector_Id:String? = null
    private lateinit var mAddress:String
    override fun getContentView(): Int {
        supportActionBar!!.hide()
        return R.layout.activity_signup
    }

    override fun getPresenterClass(): Class<SignupPresenter> {
        return SignupPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
//        presenter?.hitBlockListApi()
//        sign_up_block.setOnClickListener {
//            sign_up_block_dropdown_rc.visibility=View.VISIBLE
//            sign_up_sector_dropdown_rc.visibility = View.GONE
//
//        }
        sign_up_login_btn.setOnClickListener {

            var intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }
//        sign_up_sector.setOnClickListener {
//            sign_up_block_dropdown_rc.visibility = View.GONE
//            sign_up_sector_dropdown_rc.visibility = View.VISIBLE
//        }
        sign_up_submit_btn.setOnClickListener {
            val userName = sign_up_username.text.toString()
            val mobileNo = sign_up_mobile_no.text.toString()
            val email_id = sign_up_email.text.toString()
            val password = sign_up_password.text.toString()
            val confirm_password = sign_up_confirm_password.text.toString()
//            mAddress = sign_up_address.text.toString()
//            val block = sign_up_block.text.toString()
//            val sector = sign_up_sector.text.toString()
            val usedReferCode = sign_up_referCode.text.toString()
//            if(isValidationChecked(userName,mobileNo,email_id,password,confirm_password,mAddress,block,sector))
            if(isValidationChecked(userName,mobileNo,email_id,password,confirm_password))
            {
                presenter?.hitUserRegistrationPart1Api(userName,mobileNo,email_id,"2",password,usedReferCode)
            }
        }
        // set checkbox checked change listener
        sign_up_isReferCode_exist.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                sign_up_referCode.visibility = View.VISIBLE
            }else{
                sign_up_referCode.visibility = View.GONE
            }
        }
    }

    private fun isValidationChecked(userName: String, mobileNo: String, emailId: String, password: String, confirmPassword: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if(userName.isEmpty())
            {
                sign_up_username_error_txt.text = "**Please Enter username"
                sign_up_username_error_txt.visibility = View.VISIBLE
                return false
            }else if (mobileNo.isEmpty())
            {
                sign_up_mobile_no_error_txt.text = "**Please Enter Mobile number"
                sign_up_username_error_txt.visibility = View.GONE
                sign_up_mobile_no_error_txt.visibility = View.VISIBLE
                return false
            }else if (mobileNo.length < 10 || mobileNo.length > 10)
            {
                sign_up_mobile_no_error_txt.text = "**Mobile No should be 10 digits"
                sign_up_username_error_txt.visibility = View.GONE
                sign_up_mobile_no_error_txt.visibility = View.VISIBLE

                return false
            }else if (emailId.isEmpty())
            {
                sign_up_email_error_txt.text = "**Please Enter Email id"
                sign_up_mobile_no_error_txt.visibility = View.GONE
                sign_up_email_error_txt.visibility = View.VISIBLE

                return false
            }else if (!emailId.matches(emailPattern.toRegex()))
            {
                sign_up_email_error_txt.text = "**Please Enter Valid Mail ID"
                sign_up_mobile_no_error_txt.visibility = View.GONE
                sign_up_email_error_txt.visibility = View.VISIBLE
                return false
            }
//            else if (address.isEmpty())
//            {
//                sign_up_address_error_txt.text = "**Please Enter your address"
//                sign_up_confirm_password_error_txt.visibility = View.GONE
//                sign_up_address_error_txt.visibility = View.VISIBLE
//                return false
//            }else if (block.isEmpty())
//            {
//                sign_up_block_error_txt.text = "**Please select block"
//                sign_up_address_error_txt.visibility = View.GONE
//                sign_up_block_error_txt.visibility = View.VISIBLE
//                return false
//            }else if (sector.isEmpty())
//            {
//                sign_up_sector_error_txt.text = "**Please select sector"
//                sign_up_block_error_txt.visibility = View.GONE
//                sign_up_sector_error_txt.visibility = View.VISIBLE
//                return false
//            }
            else if (password.isEmpty())
            {
                sign_up_password_error_txt.text = "**Please Enter Password"
                sign_up_email_error_txt.visibility = View.GONE
                sign_up_password_error_txt.visibility = View.VISIBLE
                return false
            }else if (password.length < 8)
            {
                sign_up_password_error_txt.text = "**Password should be 8 digits or above"
                sign_up_email_error_txt.visibility = View.GONE
                sign_up_password_error_txt.visibility = View.VISIBLE
                return false
            }else if (confirmPassword.isEmpty())
            {
                sign_up_confirm_password_error_txt.text = "**Please Enter confirm password"
                sign_up_password_error_txt.visibility = View.GONE
                sign_up_confirm_password_error_txt.visibility = View.VISIBLE
                return false
            }else if (confirmPassword != password)
            {
                sign_up_email_error_txt.text = "**Password not match"
                sign_up_password_error_txt.visibility = View.GONE
                sign_up_confirm_password_error_txt.visibility = View.VISIBLE
                return false
            }else
            {
//                sign_up_sector_error_txt.visibility = View.GONE
                return true
            }
    }


//    override fun getBlockListResponse(response: BlockListData) {
//        if (response.status == 200)
//        {
//            sign_up_block_dropdown_rc.adapter = BlockDropdownAdapter(applicationContext,response,object :RecyclerViewClickInterface{
//                override fun OnItemClick(position: Int) {
//                    var block:String = response.block!!.get(position).address.toString()
//                    mBlock_Id = response.block?.get(position).id.toString()
//                    sign_up_block.setText(block)
//                    sign_up_block_dropdown_rc.visibility = View.GONE
//                    presenter?.hitSectorListApi(mBlock_Id!!)
//                }
//
//                override fun OnItemLongClick(position: Int) {
//
//                }
//
//            })
//        }
//    }

//    override fun getSectorListResponse(response: SectorListData) {
//            if(response.status == 200)
//            {
//
//                sign_up_sector_dropdown_rc.adapter = SectorDropdownAdapter(applicationContext,response,object :RecyclerViewClickInterface{
//                    override fun OnItemClick(position: Int) {
//                        mSector_Id = response.sector?.get(position)?.id.toString()
//                        sign_up_sector.setText(response.sector?.get(position)?.sector)
//                        sign_up_sector_dropdown_rc.visibility = View.GONE
//                    }
//
//                    override fun OnItemLongClick(position: Int) {
//
//                    }
//
//                })
//            }
//    }

    override fun getUserRegistrationPart1Response(response: userRegisterpart1Data) {
        if (response.status == 200)
        {
            sign_up_username.setText("")
            sign_up_mobile_no.setText("")
            sign_up_email.setText("")
//            sign_up_address.setText("")
//            sign_up_block.setText("")
//            sign_up_sector.setText("")
            sign_up_password.setText("")
            sign_up_confirm_password.setText("")
            sign_up_referCode.setText("")
            Snackbar.make(sign_up_isReferCode_exist,"User Registered successfully",Snackbar.LENGTH_LONG).show()
            intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
//            presenter?.hitUserRegistrationPart2Api(response.signUpRespo?.id.toString(),mAddress,mBlock_Id.toString(),mSector_Id.toString())
        }else
        {
            Snackbar.make(sign_up_isReferCode_exist,"Something went wrong",Snackbar.LENGTH_LONG).show()
        }
    }

//    override fun getUserRegistrationPart2Response(response: userRegistrationPart2Data) {
//        if (response.status == 200)
//        {
//            sign_up_username.setText("")
//            sign_up_mobile_no.setText("")
//            sign_up_email.setText("")
////            sign_up_address.setText("")
////            sign_up_block.setText("")
////            sign_up_sector.setText("")
//            sign_up_password.setText("")
//            sign_up_confirm_password.setText("")
//            sign_up_referCode.setText("")
//            Snackbar.make(sign_up_isReferCode_exist,"User Registered successfully",Snackbar.LENGTH_LONG).show()
//            intent = Intent(applicationContext, SignInActivity::class.java)
//            startActivity(intent)
//        }
//        else
//        {
//            Snackbar.make(sign_up_isReferCode_exist,"Something went wrong",Snackbar.LENGTH_LONG).show()
//        }
//    }
}