package com.food_dev.dashboard.order.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.food_dev.dashboard.BR
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.food_dev.dashboard.R
import com.food_dev.dashboard.databinding.FragmentOrderBinding
import com.food_dev.dashboard.order.adapter.OrderAdapter
import com.food_dev.dashboard.order.listener.OrderClickListener
import com.food_dev.dashboard.order.viewmodel.OrderViewModel
import com.food_dev.domain.dto.local.model.order.Order
import com.food_dev.utils.base.BaseFragment
import com.food_dev.utils.ext.event.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import io.socket.client.Socket

@AndroidEntryPoint
@FragmentScoped
class OrderFragment : BaseFragment<FragmentOrderBinding, OrderViewModel>(), OrderClickListener {

    companion object{
        private val TAG = OrderFragment::class.java.simpleName
    }

    @FragmentScoped
    override val viewModel: OrderViewModel by viewModels()
    override val binding: FragmentOrderBinding by lazy { FragmentOrderBinding.inflate(layoutInflater) }
    override val bindingVariable: Int = BR.viewModel

    private var isPortrait: Boolean = true
    private val orderAdapter: OrderAdapter by lazy { OrderAdapter(this@OrderFragment) }
    private val orderList: MutableList<Order> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        setupDashboardSwipeRefresh()
        setupOrderObserver()
        setupOrderRv()
    }

    override fun onAcceptClicked(order: Order) {
        viewModel.acceptOrder(order.id)
    }

    private fun setupDashboardSwipeRefresh() {
        viewModel.getMerchantOrder()
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.getMerchantOrder()
        }
    }

    private fun setupOrderObserver() {
        viewModel.orderData.observe(viewLifecycleOwner, { items ->
            orderAdapter.orderItems = items
        })

        viewModel.confirmOrderResult.observe(viewLifecycleOwner, EventObserver{ isResultOk ->
            if (isResultOk){
                viewModel.getMerchantOrder()
            }
        })
    }

    private fun setupOrderRv() {
        isPortrait = requireContext().resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE
        val manager = if(isPortrait) {
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        } else {
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
        binding.viewCollectionRvOrder.apply {
            layoutManager = manager
            adapter       = orderAdapter
            itemAnimator  = DefaultItemAnimator()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        isPortrait = (newConfig.orientation != Configuration.ORIENTATION_LANDSCAPE)
        setupOrderRv()
    }

    override fun onPause() {
        super.onPause()
        orderAdapter.removeCallbacks()
    }

    override fun onDestroy() {
        super.onDestroy()
        orderAdapter.removeCallbacks()
    }


}