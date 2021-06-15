package com.xpertwebtech.coreaqua.ui.AddressList

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.xpertwebtech.coreaqua.Interface.RcClickInterfaceWithView
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.add_address_popup.view.*
import kotlinx.android.synthetic.main.alert_popup.*
import kotlinx.android.synthetic.main.alert_popup.view.*
import kotlinx.android.synthetic.main.fragment_address_list.*
import kotlinx.android.synthetic.main.item_address_list.view.*
import kotlinx.android.synthetic.main.term_condition_popup.view.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class AddressListFragment : BaseFragment<AddressListView, AddressListPresenter>(), AddressListView {

    private lateinit var maddress_id: String
    private lateinit var mDialogBuilder2: AlertDialog.Builder
    private var mSelected_address_id: String= "null"
    private lateinit var mUser_id: String
    private lateinit var mDialog: AlertDialog
    private lateinit var mDialog2: AlertDialog
    private lateinit var mDialogBuilder: AlertDialog.Builder
    private var checkedPosition: Int = 0
    private lateinit var sharedPrefManager: SharedPrefManager
    private var data: ArrayList<AddressData> = ArrayList()
    private lateinit var mAdapter: AddressListAdapter
    private lateinit var qty:String
    private lateinit var product_price:String
    private lateinit var rootPage: String
    private lateinit var navController: NavController

    override fun getContentView(): Int {
        return R.layout.fragment_address_list
    }

    override fun getPresenterClass(): Class<AddressListPresenter> {
        return AddressListPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        sharedPrefManager = SharedPrefManager.getInstance(requireContext())
        mUser_id = sharedPrefManager.user.id.toString()
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navBar.visibility = View.GONE

        navController = Navigation.findNavController(requireView())

        if(arguments !=null)
        {
            qty = AddressListFragmentArgs.fromBundle(requireArguments()).qty
            product_price = AddressListFragmentArgs.fromBundle(requireArguments()).price
            rootPage = AddressListFragmentArgs.fromBundle(requireArguments()).pageCallAddress
        }

        if(rootPage == "ProductDetailsPage")
        {
            address_list_select_address_btn.visibility = View.VISIBLE
        }else
        {
            address_list_select_address_btn.visibility = View.GONE
        }

        address_list_add_btn.setOnClickListener {
            addAddressPopup("Add")
        }

        presenter!!.hitSavedAddressListApi(mUser_id)

        address_list_select_address_btn.setOnClickListener {
            if(mSelected_address_id != "null")
            {
                Snackbar.make(address_list_add_btn, mSelected_address_id , Snackbar.LENGTH_LONG).show()
                var action: NavDirections = AddressListFragmentDirections.actionAddressListFragmentToPaymentDetailsFragment(mSelected_address_id,qty,product_price)
                navController.navigate(action)
            }else
            {
                Snackbar.make(address_list_add_btn, "**Please select a address", Snackbar.LENGTH_SHORT).show()
            }
        }
//        createList()


    }

    private fun addAddressPopup(tag: String) {
        mDialogBuilder = AlertDialog.Builder(requireContext())
        val li = layoutInflater
        val popupView: View = li.inflate(R.layout.add_address_popup, null)
        mDialogBuilder.setView(popupView)

        mDialog = mDialogBuilder.create()
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        if (tag.equals("update")) {
            popupView.add_address_save_btn.visibility = View.GONE
            popupView.add_address_update_btn.visibility = View.VISIBLE
        } else {
            popupView.add_address_save_btn.visibility = View.VISIBLE
            popupView.add_address_update_btn.visibility = View.GONE
        }

        popupView.add_address_save_btn.setOnClickListener {
            var name: String = popupView.add_address_name.text.toString()
            var mail: String = popupView.add_address_mail.text.toString()
            var mobile_no: String = popupView.add_address_mobile_no.text.toString()
            var address: String = popupView.add_address_address.text.toString()
            var city: String = popupView.add_address_city.text.toString()
            var state: String = popupView.add_address_state.text.toString()
            var pin: String = popupView.add_address_pin_code.text.toString()

            if (isvalidate(name, mail, mobile_no, address, city, state, pin)) {
                presenter!!.hitSetAddedAddressApi(
                    name,
                    mobile_no,
                    mail,
                    mUser_id,
                    address,
                    city,
                    state,
                    pin,
                    "address"
                )
            }
        }
        popupView.add_address_update_btn.setOnClickListener {
            var name: String = popupView.add_address_name.text.toString()
            var mail: String = popupView.add_address_mail.text.toString()
            var mobile_no: String = popupView.add_address_mobile_no.text.toString()
            var address: String = popupView.add_address_address.text.toString()
            var city: String = popupView.add_address_city.text.toString()
            var state: String = popupView.add_address_state.text.toString()
            var pin: String = popupView.add_address_pin_code.text.toString()

            if (isvalidate(name, mail, mobile_no, address, city, state, pin)) {
                if (maddress_id != null) {
                    presenter!!.hitUpdatedAddressApi(
                        name,
                        mobile_no,
                        mail,
                        mUser_id,
                        address,
                        city,
                        state,
                        pin,
                        "address",
                        maddress_id
                    )
                }
            }

        }

        popupView.add_address_cancel_btn.setOnClickListener {
            mDialog.dismiss()
        }
    }

    private fun isvalidate(
        name: String,
        mail: String,
        mobileNo: String,
        address: String,
        city: String,
        state: String,
        pin: String
    ): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (name.isEmpty()) {
            Snackbar.make(address_list_add_btn, "**Please Enter Name", Snackbar.LENGTH_LONG).show()
            return false
        } else if (mobileNo.isEmpty()) {
            Snackbar.make(
                address_list_add_btn,
                "**Please Enter Mobile number",
                Snackbar.LENGTH_LONG
            ).show()
            return false
        } else if (mobileNo.length < 10 || mobileNo.length > 10) {
            Snackbar.make(
                address_list_add_btn,
                "**Mobile No should be 10 digits",
                Snackbar.LENGTH_LONG
            ).show()

            return false
        } else if (mail.isEmpty()) {
            Snackbar.make(address_list_add_btn, "**Please Enter Email id", Snackbar.LENGTH_LONG)
                .show()

            return false
        } else if (!mail.matches(emailPattern.toRegex())) {
            Snackbar.make(
                address_list_add_btn,
                "**Please Enter Valid Mail ID",
                Snackbar.LENGTH_LONG
            ).show()
            return false
        } else if (address.isEmpty()) {
            Snackbar.make(address_list_add_btn, "**Please Enter address", Snackbar.LENGTH_LONG)
                .show()
            return false
        } else if (city.isEmpty()) {
            Snackbar.make(address_list_add_btn, "**Please Enter City", Snackbar.LENGTH_LONG).show()
            return false
        } else if (state.isEmpty()) {
            Snackbar.make(address_list_add_btn, "**Please Enter State", Snackbar.LENGTH_LONG).show()
            return false
        } else if (pin.isEmpty()) {
            Snackbar.make(address_list_add_btn, "**Please Enter Pin Code", Snackbar.LENGTH_LONG)
                .show()
            return false
        } else {
//                sign_up_sector_error_txt.visibility = View.GONE
            return true
        }
    }

    override fun getSavedAddressApiResponse(response: AddressData) {
        if (response.status == 200) {
            mAdapter =
                AddressListAdapter(requireContext(), response, object : RcClickInterfaceWithView {
                    override fun OnItemClick(
                        position: Int,
                        itemView: View,
                        adapterPosition: Int,
                        key: String
                    ) {
                        // Address list activity here
                        if (key.equals("mainLayout")) {
                            mSelected_address_id =
                                response.userAddress?.get(position)?.id.toString()

                        } else if (key.equals("edit")) {
                            maddress_id = response.userAddress?.get(position)?.id.toString()
                            AddressUpdateAlertDailog("edit")
                            Snackbar.make(address_list_add_btn, "edit", Snackbar.LENGTH_LONG).show()

                        } else if (key.equals("delete")) {
                            maddress_id = response.userAddress?.get(position)?.id.toString()
                            AddressUpdateAlertDailog("delete")
//                            Snackbar.make(address_list_add_btn,"delete",Snackbar.LENGTH_LONG).show()


                        } else {
                            Snackbar.make(address_list_add_btn, "else", Snackbar.LENGTH_LONG).show()
                        }

                    }
                })
            address_list_rc.adapter = mAdapter
            address_list_rc.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )

        } else {
            Snackbar.make(address_list_add_btn, "Something went wrong!!..", Snackbar.LENGTH_LONG)
                .show()
        }
    }

    override fun getAddedAddressResponse(response: AddedAddressData) {
        if (response.status == 200) {
            if (response.userAddress != null) {
                Snackbar.make(
                    address_list_add_btn,
                    "Address Added Successfully",
                    Snackbar.LENGTH_LONG
                ).show()
                presenter!!.hitSavedAddressListApi(mUser_id)
                mDialog.dismiss()
            }

        } else {
            Snackbar.make(address_list_add_btn, "Something went wrong!!", Snackbar.LENGTH_LONG)
                .show()
        }
    }

    override fun getDeletedAddressResponse(response: AddressDeleteResponseData) {
        if (response.status == 200) {
            presenter!!.hitSavedAddressListApi(mUser_id)
            Snackbar.make(address_list_add_btn, response.message.toString(), Snackbar.LENGTH_LONG)
                .show()
        }
    }

    override fun getUpdatedAddressResponse(response: AddedAddressData) {
        if (response.status == 200) {
            presenter!!.hitSavedAddressListApi(mUser_id)
            Snackbar.make(address_list_add_btn, response.message.toString(), Snackbar.LENGTH_LONG)
                .show()
        }
    }

    //    private fun createList() {
//        data = ArrayList()
//        for (i in 1..20)
//        {
//            var Adata:AddressData = AddressData()
//            Adata.title("User Name $i")
//            data.add(Adata)
//        }
//        mAdapter.SetData(data)
//    }
    fun AddressUpdateAlertDailog(tag: String) {
        mDialogBuilder2 = AlertDialog.Builder(requireContext())
        val li = layoutInflater
        val popupView: View = li.inflate(R.layout.alert_popup, null)
        mDialogBuilder2.setView(popupView)
        mDialog2 = mDialogBuilder2.create()
        mDialog2.setCanceledOnTouchOutside(false)
        mDialog2.show()
        if (tag.equals("edit"))
        {
            popupView.alert_popup_title.text = "Update Alert"
            popupView.alert_popup_message.text = "Are you sure you want to update this address ?"
        }else if (tag.equals("delete"))
        {
            popupView.alert_popup_title.text = "Delete Alert"
            popupView.alert_popup_message.text = "Are you sure you want to Delete this address ?"
        }else
        {
            popupView.alert_popup_title.text = "Update Alert"
            popupView.alert_popup_message.text = "Are you sure you want to update this address ?"
        }



        popupView.alert_popup_yes_btn.setOnClickListener {

            if (tag.equals("edit"))
            {
                addAddressPopup("update")

            }else if (tag.equals("delete"))
            {
                if (maddress_id != null)
                {
                    presenter!!.hitAddressDeleteApi(maddress_id)
                }else
                {
                    Snackbar.make(address_list_add_btn,"Something went wrong!!",Snackbar.LENGTH_LONG).show()
                }

            }else
            {

            }
        }
        popupView.alert_popup_no_btn.setOnClickListener {
            mDialog2.dismiss()
        }
    }


}
