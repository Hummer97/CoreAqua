package com.xpertwebtech.coreaqua.ui.DashBoard

import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationServices
import com.xpertwebtech.coreaqua.Interface.RecyclerViewClickInterface
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseFragment
import com.xpertwebtech.coreaqua.ui.Wallet.WalletListData
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_deshboard.*
import java.util.*


class DeshboardFragment : BaseFragment<DeshboardView, DeshboardPresenter>(),DeshboardView {
    private lateinit var geocoder: Geocoder
    private var count: Int = 0
    private var mPrice: Int = 25
//    private lateinit var mLocationManager: LocationListener
//    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var navController: NavController
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var mAddress:String
    private lateinit var mBlock:String
    private lateinit var mSector:String

    override fun getContentView(): Int {
        return R.layout.fragment_deshboard
    }

    override fun getPresenterClass(): Class<DeshboardPresenter> {
        return DeshboardPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        sharedPrefManager = SharedPrefManager.getInstance(requireContext())
        mAddress = sharedPrefManager.user.address.toString()
        mBlock = sharedPrefManager.user.block.toString()
        mSector = sharedPrefManager.user.sector.toString()

        deshboard_location_subleclity.text = mBlock
        deshboard_location_full_address.text = mAddress+", "+mBlock+", "+mSector


        navController = Navigation.findNavController(requireView())

//         fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())




        banner_rc.adapter = DeshboardBannerAdapter(requireContext())

        presenter?.hitGetProductListApi()



//        val mLocationListener = LocationListener {
//
//
//        }

//        deshboard_qty_max.setOnClickListener {
//            var countdata = deshboard_qty_count.text
//            count = countdata.toString().toInt()
//            count++
//            deshboard_qty_count.text = count.toString()
//        }
//        deshboard_qty_min.setOnClickListener {
//            var countdata = deshboard_qty_count.text
//            count = countdata.toString().toInt()
//            if(count > 1)
//            {
//                count--
//                deshboard_qty_count.text = count.toString()
//            }
//            else
//            {
//                Snackbar.make(
//                    deshboard_qty_count,
//                    "Item Should not be negative",
//                    Snackbar.LENGTH_LONG
//                ).show()
//            }
//        }
//
//        deshboard_ordered_btn.setOnClickListener {
//            var count:String = deshboard_qty_count.text.toString()
//            var total_Price:Int = count.toInt()* mPrice
//            var action:NavDirections = DeshboardFragmentDirections.actionNavHomeToOrderDetailsFragment(count,total_Price.toString())
//            navController.navigate(action)
//        }
    }



//    private fun checkLocationPermission() {
//        val task = fusedLocationProviderClient.lastLocation
//        if(ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED && ActivityCompat
//                        .checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
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
////                getUserAddress(it.latitude, it.longitude)
//
//            }
//        }
//    }




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

//    override fun onResume() {
//        super.onResume()
//        checkLocationPermission()
//    }

    override fun getProductListResponse(response: ProductListData) {
        if(response.status == 200)
        {
            dashboard_product_list_rc.adapter = DeshboardAdapter(requireContext(),response,object :RecyclerViewClickInterface{
                override fun OnItemClick(position: Int) {
                    var name:String = response.product?.get(position)?.name.toString()
                    var code:String = response.product?.get(position)?.code.toString()
                    var quantity:String = response.product?.get(position)?.quantity.toString()
                    var amount:String = response.product?.get(position)?.amount.toString()
                    var url:String = response.product?.get(position)?.url.toString()
                    var id:String = response.product?.get(position)?.id.toString()
//                    Navigation.findNavController(deshboard_location_full_address).navigate(R.id.action_nav_home_to_orderDetailsFragment)
//                    var action: NavDirections = DeshboardFragmentDirections.actionNavHomeToOrderDetailsFragment(name,code,quantity,amount,url,id)
//                    navController.navigate(action)
                }

                override fun OnItemLongClick(position: Int) {

                }

            })
        }
    }

}
