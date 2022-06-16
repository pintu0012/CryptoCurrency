package com.example.cryptocurrency.presentation.coin_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.databinding.FragmentCoinListBinding
import com.example.cryptocurrency.domain.model.Coin
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import androidx.navigation.Navigation
import com.example.cryptocurrency.presentation.base.BaseFragment
import com.example.cryptocurrency.presentation.base.BaseViewModel


@AndroidEntryPoint
class CoinListFragment : BaseFragment<FragmentCoinListBinding, BaseViewModel>(), CoinListAction {

    override val viewModel: CoinListViewModel by viewModels()

    private val coinListAdapter = CoinListAdapter(arrayListOf(), this)

    override fun getViewBinding(): FragmentCoinListBinding =
        FragmentCoinListBinding.inflate(layoutInflater)

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding= FragmentCoinListBinding.inflate(inflater,container,false)
//        val view = binding.root
//        return view
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeViewModel()
    }

    private fun initRecyclerView() {
        binding.coinListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = coinListAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCoins()
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let { render(it) }
        })

    }

    private fun render(event: CoinListState) {
//        Log.e("CoinListFragment", event.toString())
        if (!event.isLoading && event.coins.isNotEmpty()) {
            handleLoading(false)
//            binding.progressBar.visibility = GONE
//            binding.coinListView.visibility = View.VISIBLE
            coinListAdapter.updateCoins(event.coins)
            return
        }
        if (event.isLoading) {
//            binding.progressBar.visibility = View.VISIBLE
//                    binding.coinListView.visibility= GONE
            handleLoading(true)
            return

        }
        if (event.error.isNotEmpty()) {
//            binding.progressBar.visibility = GONE
            handleLoading(false)
            handleErrorMessage(event.error)
            return

        }

    }

    private fun goToCoinDetailScreen(coinId: String) {
        val action = CoinListFragmentDirections.actionGoToCoinDetail(coinId)
        Navigation.findNavController(binding.coinListView).navigate(action)
    }

    override fun onClick(id: String) {
        goToCoinDetailScreen(id)
    }


}