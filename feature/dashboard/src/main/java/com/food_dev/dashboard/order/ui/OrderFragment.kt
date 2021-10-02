package com.food_dev.dashboard.order.ui

import android.content.res.Configuration
import android.view.Menu
import android.view.MenuInflater
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
import com.food_dev.utils.ext.log.MyLog
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.lang.Exception
import java.net.URISyntaxException

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

    private lateinit var mSocket: Socket
    private val orderAdapter: OrderAdapter by lazy { OrderAdapter(this@OrderFragment) }
    private val orderList: MutableList<Order> = mutableListOf()
    private var isPortrait  = true

    override fun setupOnCreate() {
        setHasOptionsMenu(true)
    }

    override fun setupSocket() {
        try {
            mSocket = IO.socket("https://io-idcoding.tech/food-delivery")
        } catch (e: URISyntaxException){
            MyLog.e(TAG, e.message.toString())
        }
        mSocket.on("incomingOrder", incomingOrder)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)
    }

    override fun onAcceptClicked(order: Order) {
        viewModel.acceptOrder(order.id)
    }

    override fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.getMerchantOrder()
        }
    }

    override fun setupObserver() {
        viewModel.getMerchantOrder()
        viewModel.orderData.observe(viewLifecycleOwner, { items ->
            orderList.clear()
            orderList.addAll(items)
        })

        viewModel.confirmOrderResult.observe(viewLifecycleOwner, EventObserver{ items ->
            orderList.remove(items)
        })
    }

    override fun setupRv() {
        orderAdapter.orderItems = orderList
        val manager = if(isPortrait) {
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        } else {
            LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        }
        binding.viewCollectionRvOrder.apply {
            adapter       = orderAdapter
            itemAnimator  = DefaultItemAnimator()
            layoutManager = manager
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        isPortrait = newConfig.orientation != Configuration.ORIENTATION_LANDSCAPE
        setupRv()
    }

    private var incomingOrder = Emitter.Listener {
        val order: Order = Gson().fromJson(it[0].toString(), Order::class.java)
        orderList.add(order)
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