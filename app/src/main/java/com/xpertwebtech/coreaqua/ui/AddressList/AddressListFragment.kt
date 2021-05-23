package com.xpertwebtech.coreaqua.ui.AddressList

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xpertwebtech.coreaqua.Interface.RcClickInterfaceWithView
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_address_list.*
import kotlinx.android.synthetic.main.item_address_list.view.*


class AddressListFragment : BaseFragment<AddressListView,AddressListPresenter>(),AddressListView {
    private var checkedPosition:Int = 0
    private var data:ArrayList<AddressData> = ArrayList()
    private lateinit var mAdapter:AddressListAdapter
    override fun getContentView(): Int {
        return R.layout.fragment_address_list
    }

    override fun getPresenterClass(): Class<AddressListPresenter> {
        return AddressListPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navBar.visibility = View.GONE
        mAdapter = AddressListAdapter(requireContext(),object :RcClickInterfaceWithView{
            override fun OnItemClick(position: Int, itemView: View) {
                itemView.item_address_list_tick.visibility = View.VISIBLE
            }
        })
        address_list_rc.adapter = mAdapter
        address_list_rc.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))

//        createList()
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
}