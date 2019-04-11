package com.ionnt.rocketpocket.ui.storeshome

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ionnt.rocketpocket.R
import com.ionnt.rocketpocket.commons.base.BaseFragment
import com.ionnt.rocketpocket.commons.extensions.invisible
import com.ionnt.rocketpocket.commons.extensions.visible
import com.ionnt.rocketpocket.commons.utils.Failure
import com.ionnt.rocketpocket.data.model.Store
import com.ionnt.rocketpocket.ui.storeshome.adapter.StoresAdapter
import kotlinx.android.synthetic.main.stores_fragment.*

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */
class StoresFragment: BaseFragment() {
    private lateinit var viewModel: StoresViewModel
    private lateinit var storesAdapter: StoresAdapter

    override fun layoutId(): Int = R.layout.stores_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = ViewModelProviders
            .of(requireActivity(),  viewModelFactory)
            .get(StoresViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.home_toolbar, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapters()
//        loadStores()
        storeSwipeRefresh.setOnRefreshListener { handleRefreshData() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        viewModel.storesGetted.observe(viewLifecycleOwner, Observer(::renderStores))
        viewModel.failure.observe(viewLifecycleOwner, Observer(::showError))

        return view
    }

    private fun initAdapters() {
        storesAdapter = StoresAdapter(ArrayList())
        storesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        storesRecyclerView.adapter = storesAdapter

        storesAdapter.onClickAction = { item -> itemStoreClicked(item) }
    }

    private fun itemStoreClicked(item: Store) {
        // Go to store detail screen passing arguments by Android Navigation component.
        val storeArg = StoresFragmentDirections.actionToStoreDetails(item)
        view?.let { Navigation.findNavController(it).navigate(storeArg) }
    }

    private fun handleRefreshData() {
        frameProgress.visible()
        progress_circular.invisible()
        viewModel.getStoresData(true)
    }

    private fun loadStores() {
        viewModel.getStoresData(false)
        frameProgress.visible()
    }

    private fun renderStores(stores: List<Store>){
        storeSwipeRefresh.isRefreshing = false
        frameProgress.invisible()
        placeholderImage.invisible()
        storesRecyclerView.visible()
        storesAdapter.setListData(stores)
    }

    private fun showError(failure: Failure){
        when(failure){
            is Failure.NetworkConnection -> renderFailure(R.string.connectionError)
            is Failure.ServerError -> renderFailure(R.string.server_error)
            is Failure.NoDataAvailable -> renderFailure(R.string.noDataAvailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        storesRecyclerView.invisible()
        placeholderImage.visible()
        frameProgress.invisible()
        storeSwipeRefresh.isRefreshing = false
        showToast(message, Toast.LENGTH_LONG)
    }
}
