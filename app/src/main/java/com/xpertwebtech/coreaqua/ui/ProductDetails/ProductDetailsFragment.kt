package com.xpertwebtech.coreaqua.ui.ProductDetails

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.xpertwebtech.coreaqua.Interface.RecyclerViewClickInterface
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import com.xpertwebtech.coreaqua.ui.AddressList.AddressListFragmentArgs
import com.xpertwebtech.coreaqua.ui.DashBoard.DeshboardAdapter
import com.xpertwebtech.coreaqua.ui.DashBoard.ProductListData
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import kotlinx.android.synthetic.main.fragment_address_list.*
import kotlinx.android.synthetic.main.fragment_deshboard.*
import kotlinx.android.synthetic.main.fragment_product_details.*
import kotlinx.android.synthetic.main.select_current_address_popup.view.*
import kotlinx.android.synthetic.main.select_location_popup.view.*
import kotlinx.android.synthetic.main.term_condition_popup.view.*
import java.io.IOException
import java.util.*

class ProductDetailsFragment : BaseFragment<ProductDetailsView, ProductDetailsPresenter>(),ProductDetailsView {

    private lateinit var navController: NavController
    private lateinit var mCurrentAddress: String
    private lateinit var geocoder: Geocoder
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var selected_Address: String
    private lateinit var mDialog: AlertDialog
    private lateinit var mDialog2: AlertDialog
    private lateinit var mDialog3: AlertDialog
    private lateinit var mDialogBuilder: AlertDialog.Builder
    private lateinit var mDialogBuilder2: AlertDialog.Builder
    private lateinit var mDialogBuilder3: AlertDialog.Builder
    private lateinit var mProduct_ID:String
    private lateinit var mProduct_Name:String
    private var mProduct_price:String = "null"
    private lateinit var mProduct_code:String
    private lateinit var mProduct_quantity:String
    private lateinit var mProduct_pic_url:String
    private lateinit var mCount:String
    private lateinit var sharedPrefmanager:SharedPrefManager
    private lateinit var mUser_ID:String
    private var sdate = "null"
    private var count: Int = 0
    private var mYear = 0
    private  var mMonth:Int = 0
    private  var mDay:Int = 0
    private var mPerItemPrice:Int =0
    private var ItemCount: Int =1
    private val mPosition_code:Int = 0;
    private var mStartTimeInMiliSeconds:Long = System.currentTimeMillis()

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var latitudeTextView: TextView
    private lateinit var longitTextView: TextView
    private var PERMISSION_ID = 44
    override fun getContentView(): Int {
        return R.layout.fragment_product_details
    }

    override fun getPresenterClass(): Class<ProductDetailsPresenter> {
        return ProductDetailsPresenter::class.java
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewReady(savedInstanceState: Bundle?) {
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navBar.visibility = View.VISIBLE

        sharedPrefmanager = SharedPrefManager.getInstance(requireContext())
        mUser_ID = sharedPrefmanager.user.id.toString()
        if(arguments !=null) {
            var qty:String = ProductDetailsFragmentArgs.fromBundle(requireArguments()).qty
            mCount = qty
        }
        presenter?.hitGetProductListApi()
        Log.d("OD", "ID$mUser_ID")
        presenter?.hitGetWalletBalanceApi(mUser_ID)
        navController = Navigation.findNavController(requireView())


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        // method to get the location

        // method to get the location






//        if(arguments!=null)
//        {
//            mProduct_ID = ProductDetailsFragmentArgs.fromBundle(requireArguments()).id
//            mProduct_Name = ProductDetailsFragmentArgs.fromBundle(requireArguments()).name
//            mProduct_price = ProductDetailsFragmentArgs.fromBundle(requireArguments()).amount
//            mProduct_code = ProductDetailsFragmentArgs.fromBundle(requireArguments()).code
//            mProduct_quantity = ProductDetailsFragmentArgs.fromBundle(requireArguments()).quantity
//            mProduct_pic_url = ProductDetailsFragmentArgs.fromBundle(requireArguments()).url
//
//        }
        product_details_term_condition.setOnClickListener {
            termConditionPopup()
        }
        product_details_qty_count.text = mCount
        product_details_amount_details_qty.text = mCount
        if(product_details_qty_count.text.toString() == mCount)
        {
            val price:Int = mCount.toInt()*mPerItemPrice
            product_details_total_price.text = price.toString()
            product_details_amount_details_total_amount.text = "$price.00"
        }

        product_details_wallet.setOnClickListener {
            Navigation.findNavController(product_details_wallet).navigate(R.id.action_orderDetailsFragment_to_walletHistoryFragment3)
        }


        ItemCount = product_details_qty_count.text.toString().toInt()

        product_details_qty_max.setOnClickListener {
            val countdata = product_details_qty_count.text
            count = countdata.toString().toInt()
            count++
            product_details_qty_count.text = count.toString()
            product_details_amount_details_qty.text = count.toString()
            val main_count:Int= product_details_qty_count.text.toString().toInt()
            getTotalPrice(main_count)
        }
        product_details_qty_min.setOnClickListener {
            val countdata = product_details_qty_count.text
            count = countdata.toString().toInt()
            if(count > 1)
            {
                count--

                product_details_qty_count.text = count.toString()
                product_details_amount_details_qty.text = count.toString()
                val main_count:Int= product_details_qty_count.text.toString().toInt()
                getTotalPrice(main_count)
            }
            else
            {
                Snackbar.make(
                    product_details_qty_count,
                    "Item Should not be negative",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
//        product_details_selected_btn.setOnClickListener {
//            selectedDaysPopup()
//        }
//        product_details_start_date.setOnClickListener {
//            val c = java.util.Calendar.getInstance()
//            mYear = c[java.util.Calendar.YEAR]
//            mMonth = c[java.util.Calendar.MONTH]
//            mDay = c[java.util.Calendar.DAY_OF_MONTH]
//
//            val datePickerDialog = DatePickerDialog(
//                requireContext(),
//                { view, year, monthOfYear, dayOfMonth ->
//                    sdate = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
//                    mStartTimeInMiliSeconds = getTimeInMillis(dayOfMonth+1,monthOfYear,year)
//                    product_details_start_date.text =
//                            year.toString()+"-"+(monthOfYear + 1)+"-"+dayOfMonth.toString()
//
//                    // startdatetxt.setText(day + "-" + month + "-" + year2);
//                    product_details_start_date.text =
//                            year.toString()+"-"+(monthOfYear + 1)+"-"+dayOfMonth.toString()
//
//                    // Toast.makeText(PaymentDeatilsFormActivity.this, "date="+date, LENGTH_SHORT).show();
//                }, mYear, mMonth, mDay
//            )
//            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000)
//            datePickerDialog.show()
//
//        }
//        product_details_end_date.setOnClickListener {
//            val c = java.util.Calendar.getInstance()
//            mYear = c[java.util.Calendar.YEAR]
//            mMonth = c[java.util.Calendar.MONTH]
//            mDay = c[java.util.Calendar.DAY_OF_MONTH]
//
//            val datePickerDialog = DatePickerDialog(
//                requireContext(),
//                { view, year, monthOfYear, dayOfMonth ->
//                    sdate = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
//                    product_details_end_date.text =
//                        year.toString()+"-"+(monthOfYear + 1)+"-"+dayOfMonth.toString()
//
//                    // startdatetxt.setText(day + "-" + month + "-" + year2);
//                    product_details_end_date.text =
//                        year.toString()+"-"+(monthOfYear + 1)+"-"+dayOfMonth.toString()
//
//                    // Toast.makeText(PaymentDeatilsFormActivity.this, "date="+date, LENGTH_SHORT).show();
//                }, mYear, mMonth, mDay
//            )
//            datePickerDialog.getDatePicker().setMinDate(mStartTimeInMiliSeconds - 1000)
//            datePickerDialog.show()
//        }

        product_details_product_order_btn.setOnClickListener {
            lateinit var day_type: String
            val quantity:String = product_details_qty_count.text.toString()

            selectAddressPopup(quantity)

//            val selectedId: Int = product_details_delivery_day_btn_group.getCheckedRadioButtonId()
//            day_type = if(selectedId==-1){
//                Log.d("OD", "Please Select Delivery Day ")
//                "null"
//            } else{
//                val selectedBtn:RadioButton= requireView().findViewById(selectedId)
//                selectedBtn.text.toString()
//            }

            Log.d("OD", "btn " + System.currentTimeMillis())


//            if(isValidationChecked(day_type, start_date, end_date))
//            {
//                    presenter?.hitGetUserSelectedPlanApi(mUser_ID,mProduct_ID,quantity,day_type, start_date, end_date)
//            }
        }

    }
    override fun getProductListResponse(response: ProductListData) {
        if(response.status == 200)
        {
            mProduct_Name = response.product?.get(mPosition_code)?.name.toString()
            mProduct_code = response.product?.get(mPosition_code)?.code.toString()
            mProduct_quantity = response.product?.get(mPosition_code)?.quantity.toString()
            mProduct_price = response.product?.get(mPosition_code)?.amount.toString()
            mProduct_pic_url = response.product?.get(mPosition_code)?.url.toString()
            mProduct_ID= response.product?.get(mPosition_code)?.id.toString()
            setProductDetalis()
        }
    }

    override fun getGeoLocationResponse(response: GeoLocationData) {
        Log.d("Geo Check", response.location.toString())
    }

    private fun isValidationChecked(userType: String, start_date: String, end_date: String): Boolean {
        when {
            userType.equals("null") -> {
                Snackbar.make(
                    product_details_qty_count,
                    "Please Select Delivery day first",
                    Snackbar.LENGTH_LONG
                ).show()
                return false
            }
            start_date.equals("00:00:00") -> {
                Snackbar.make(product_details_qty_count, "Please Select Start date", Snackbar.LENGTH_LONG).show()
                return false
            }
            end_date.equals("00:00:00") -> {
                Snackbar.make(product_details_qty_count, "Please Select End Date", Snackbar.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setProductDetalis() {
        Glide.with(requireContext()).load(mProduct_pic_url).into(product_details_product_img)
        product_details_product_code.text = mProduct_code
        product_details_product_price.text = "Rs. $mProduct_price"
        product_details_product_quantity.text = "$mProduct_quantity Liters"
        product_details_amount_details_price.text = "$mProduct_price.00"
        mPerItemPrice = mProduct_price.toInt()
        val price:Int = mCount.toInt()*mPerItemPrice
        product_details_total_price.text = price.toString()
        product_details_amount_details_total_amount.text = "$price.00"

    }

    private fun getTotalPrice(mainCount: Int) {
        val price:Int = mainCount*mProduct_price.toInt()
        product_details_total_price.text = price.toString()
        product_details_amount_details_total_amount.text = "$price.00"

    }

//    private fun selectedDaysPopup() {
//        mDialogBuilder = AlertDialog.Builder(requireContext())
//        val li = layoutInflater
//        val popupView: View = li.inflate(R.layout.item_selected_days, null)
//        mDialogBuilder.setView(popupView)
//        mDialog = mDialogBuilder.create()
//        mDialog.show()
//        popupView.item_selected_days_save_btn.setOnClickListener {
//            Snackbar.make(product_details_selected_btn,"saved data",Snackbar.LENGTH_LONG).show()
//        }
//        popupView.item_selected_days_cancel_btn.setOnClickListener {
//            mDialog.dismiss()
//        }
//    }

//    fun onRadioButtonClicked(view: View) {
//        if (view is RadioButton) {
//            // Is the button now checked?
//            val checked = view.isChecked
//
//            // Check which radio button was clicked
//            when (view.getId()) {
//                R.id.product_details_delivery_day_daily ->
//                    if (checked) {
//                        // Pirates are the best
//                    }
//                R.id.product_details_delivery_day_alternate ->
//                    if (checked) {
//                        // Ninjas rule
//                    }
//            }
//        }
//    }

    @SuppressLint("SetTextI18n")
    override fun getWalletBalanceResponse(response: WalletListData) {
        when (response.status) {
            200 -> {
                product_details_wallet_balance.text = "Rs. "+response.walletAmount?.amount
            }
            401 -> {
                Snackbar.make(product_details_wallet_balance,response.message.toString(),Snackbar.LENGTH_LONG).show()
            }
            else -> {
                Snackbar.make(product_details_wallet_balance, "Something went wrong!",Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun getOrderSelectedPlanResponse(response: ProductDetailsResponseData) {
        when (response.status) {
            200 -> {
                Snackbar.make(product_details_wallet_balance,response.message.toString(),Snackbar.LENGTH_LONG).show()
            }
            401 -> {
                Snackbar.make(product_details_wallet_balance,response.message.toString(),Snackbar.LENGTH_LONG).show()
            }
            402 -> {
                Snackbar.make(product_details_wallet_balance,response.message.toString(),Snackbar.LENGTH_LONG).show()
            }
            else -> {
                Snackbar.make(product_details_wallet_balance, "Something went wrong!",Snackbar.LENGTH_LONG).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getTimeInMillis(day: Int, month: Int, year: Int): Long {
        val calendar = Calendar.getInstance()
        calendar[year, month] = day
        return calendar.timeInMillis
    }

    private fun selectAddressPopup(qty:String) {
        mDialogBuilder = AlertDialog.Builder(requireContext())
        val li = layoutInflater
        val popupView: View = li.inflate(R.layout.select_location_popup, null)
        mDialogBuilder.setView(popupView)
        mDialog = mDialogBuilder.create()
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        popupView.select_location_submit_btn.setOnClickListener {
            var selectedOption: Int = popupView.select_location_radioGroup!!.checkedRadioButtonId
            selected_Address = if(selectedOption==-1){
                Log.d("OD", "Please Select Delivery Day ")
                "null"
            } else{
                var selectedBtn: RadioButton = popupView.findViewById(selectedOption)
                selectedBtn.text.toString()
            }
            Log.d("selected_Address",selected_Address)
            if (selected_Address.equals("Current Address"))
            {
//                presenter!!.hitGeoLocationApi("AIzaSyB3h-DQ2T_YnsMo7pIMtjRv42tsxlaAY-o")
                getLastLocation()
                mDialog.dismiss()
//                CurrentAddressPopup()
                Snackbar.make(popupView.select_location_radioGroup,"Current Address Selected",Snackbar.LENGTH_LONG).show()
            }
            else if (selected_Address.equals("Saved Address"))
            {
                mDialog.dismiss()
                var action: NavDirections = ProductDetailsFragmentDirections.actionOrderDetailsFragmentToAddressListFragment(qty,mProduct_price,"ProductDetailsPage")
                navController.navigate(action)
//                Navigation.findNavController(product_details_amount_details_qty).navigate(R.id.action_orderDetailsFragment_to_addressListFragment)
            }
            else
            {
                Snackbar.make(popupView.select_location_radioGroup,"Something went wrong!!",Snackbar.LENGTH_LONG).show()
            }
        }
//        mAddClassBtn.setOnClickListener(View.OnClickListener {
//            mProgressDialog.setMessage("Please Wait...")
//            mProgressDialog.show()
//            addClassApi()
//        })
        popupView.select_location_cancel.setOnClickListener {
            mDialog.dismiss()
        }
    }

//    private fun CurrentAddressPopup() {
//        mDialogBuilder3 = AlertDialog.Builder(requireContext())
//        val li = layoutInflater
//        val popupView: View = li.inflate(R.layout.select_location_popup, null)
//        mDialogBuilder3.setView(popupView)
//        mDialog3 = mDialogBuilder3.create()
//        mDialog3.setCanceledOnTouchOutside(false)
//        mDialog3.show()
//
//        popupView.select_current_location_submit_btn.setOnClickListener {
//            var selectedOption: Int = popupView.select_current_current_location_radioGroup!!.checkedRadioButtonId
//            selected_Address = if(selectedOption==-1){
//                Log.d("OD", "Please Select Delivery Day ")
//                "null"
//            } else{
//                var selectedBtn: RadioButton = popupView.findViewById(selectedOption)
//                selectedBtn.text.toString()
//            }
//            Log.d("selected_Address",selected_Address)
//            if (selected_Address.equals("Current Address"))
//            {
////                presenter!!.hitGeoLocationApi("AIzaSyB3h-DQ2T_YnsMo7pIMtjRv42tsxlaAY-o")
//                var address:String = mCurrentAddress
//                if(address != null)
//                {
//                    popupView.select_current_current_location.text = address
//                }
//
//            }
//            else if (selected_Address.equals("Saved Address"))
//            {
//                mDialog.dismiss()
//                Navigation.findNavController(product_details_amount_details_qty).navigate(R.id.action_orderDetailsFragment_to_addressListFragment)
//            }
//            else
//            {
//                Snackbar.make(popupView.select_location_radioGroup,"Something went wrong!!",Snackbar.LENGTH_LONG).show()
//            }
//        }
////        mAddClassBtn.setOnClickListener(View.OnClickListener {
////            mProgressDialog.setMessage("Please Wait...")
////            mProgressDialog.show()
////            addClassApi()
////        })
//        popupView.select_location_cancel.setOnClickListener {
//            mDialog.dismiss()
//        }
//    }


    private fun termConditionPopup() {
        mDialogBuilder2 = AlertDialog.Builder(requireContext())
        val li = layoutInflater
        val popupView: View = li.inflate(R.layout.term_condition_popup, null)
        mDialogBuilder2.setView(popupView)
        mDialog2 = mDialogBuilder2.create()
        mDialog2.show()

        popupView.tc_cancel.setOnClickListener {
            mDialog2.dismiss()
        }
    }
//    private fun getUserAddress(latitude: Double, longitude: Double) {
//
//        val addresses: List<android.location.Address>
//        geocoder = Geocoder(requireContext(), Locale.getDefault())
//
//        addresses = geocoder.getFromLocation(latitude, longitude, 1) // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//
//
//        val address: String = addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//
//        if(addresses[0]?.getSubLocality()!=null)
//        {
//            val socity:String = addresses[0]?.getSubLocality()
//            deshboard_location_subleclity.text = socity
//        }
//        if(addresses[0]?.getFeatureName()!=null)
//        {
//            val knownName: String = addresses[0]?.getFeatureName()
//            var trimData:String = knownName+", "
//            val separated: List<String> = address.split(trimData)
//            var demoAddress:String = separated[1];
//            deshboard_location_full_address.text = demoAddress
//        }
////        v
//
////        Log.d("location", address)
////        Log.d("location", socity)
////        Log.d("location", city)
////        Log.d("location", state)
////        Log.d("location", country)
////        Log.d("location", postalCode)
////        Log.d("location", knownName)
//
//
//
//
//
//    }
//    private fun checkLocationPermission() {
//        val task = fusedLocationProviderClient.lastLocation
//        if(ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED && ActivityCompat
//                .checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED){
//
//            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
//            return
//        }
//        task.addOnSuccessListener {
//            if (it != null)
//            {
////                Snackbar.make(dashboard_rc, "${it.latitude} ${it.longitude}", Snackbar.LENGTH_LONG).show()
//                Log.d("location", "${it.latitude} ${it.longitude}")
//                getUserAddress(it.latitude, it.longitude)
//
//            }
//        }
//    }


    //    -------------------------------------Current Location code--------------------



    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation()
                    .addOnCompleteListener(OnCompleteListener<Location?> { task ->
                        val location = task.result
                        if (location == null) {
                            requestNewLocationData()
                        } else {
//                            latitudeTextView.setText(location.latitude.toString() + "")
//                            longitTextView.setText(location.longitude.toString() + "")
                            try {
                                getUserAddress(location.latitude, location.longitude)
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    })
            } else {
                Snackbar.make(address_list_add_btn, "Please turn on" + " your location...", Snackbar.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 5
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        // setting LocationRequest
        // on FusedLocationClient

    }

    private val mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation = locationResult.lastLocation
//            latitudeTextView.setText("Latitude: " + mLastLocation.latitude + "")
//            longitTextView.setText("Longitude: " + mLastLocation.longitude + "")
        }
    }

    // method to check for permissions
    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), PERMISSION_ID
        )
    }

    // method to check
// if location is enabled
    private fun isLocationEnabled(): Boolean {
//    val locationManager: LocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE)
        val locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    // If everything is alright then
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            }
        }
    }



    @Throws(IOException::class)
    private fun getUserAddress(latitude: Double, longitude: Double) {
        val geocoder: Geocoder
        val addresses: List<Address>
        geocoder = Geocoder(requireContext(), Locale.getDefault())
        addresses = geocoder.getFromLocation(
            latitude,
            longitude,
            1
        ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        val address =
            addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        val city = addresses[0].locality
        val state = addresses[0].adminArea
        val country = addresses[0].countryName
        val postalCode = addresses[0].postalCode
        val knownName = addresses[0].featureName
        mCurrentAddress = "  $knownName"

//        try
//        {
//
//        }catch (e: Exception)
//        {
//
//        }
        Log.d("Address: ", "$address $city $state $country $postalCode $knownName")
        Snackbar.make(product_details_term_condition,"$address $city $state $country $postalCode $knownName",Snackbar.LENGTH_SHORT).show()
        Snackbar.make(product_details_term_condition,"Functionality coming soon",Snackbar.LENGTH_LONG).show()
    }
    override fun onResume() {
        super.onResume()
//        if (checkPermissions()) {
//            getLastLocation()
//        }
    }
//    -------------------------------------Current Location code--------------------
}