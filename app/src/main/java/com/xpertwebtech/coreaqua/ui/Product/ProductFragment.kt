package com.xpertwebtech.coreaqua.ui.Product

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.base.BaseFragment
import com.xpertwebtech.coreaqua.ui.DashBoard.ProductListData
import com.xpertwebtech.coreaqua.ui.ProductDetails.ProductDetailsFragmentDirections
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.fragment_product_details.*


class ProductFragment : BaseFragment<ProductView,ProductPresenter>(),ProductView {
    private lateinit var navController: NavController
    private lateinit var mProduct_pic_url: String
    private var count: Int = 0
    override fun getContentView(): Int {
        return R.layout.fragment_product
    }

    override fun getPresenterClass(): Class<ProductPresenter> {
        return ProductPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(requireView())
        presenter!!.hitGetProductListApi()
        product_qty_max.setOnClickListener {
            val countdata = product_qty_count.text
            count = countdata.toString().toInt()
            count++
            product_qty_count.text = count.toString()
        }
        product_qty_min.setOnClickListener {
            val countdata = product_qty_count.text
            count = countdata.toString().toInt()
            if(count > 1)
            {
                count--

                product_qty_count.text = count.toString()
            }
            else
            {
                Snackbar.make(
                    product_qty_count,
                    "Item Should not be negative",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        product_btn.setOnClickListener {
            var qty:String = product_qty_count.text.toString()
            var action: NavDirections = ProductFragmentDirections.actionProductFragmentToOrderDetailsFragment(qty)
            navController.navigate(action)
        }
    }

    override fun getProductListResponse(response: ProductListData) {
        if(response.status == 200)
        {
            product_product_name.text = response.product?.get(0)?.name
            product_product_code.text = response.product?.get(0)?.code
            product_product_price.text = response.product?.get(0)?.amount+".00"
            mProduct_pic_url = response.product?.get(0)?.url.toString()
            Glide.with(requireContext()).load(mProduct_pic_url).into(product_product_img)
        }
    }

}