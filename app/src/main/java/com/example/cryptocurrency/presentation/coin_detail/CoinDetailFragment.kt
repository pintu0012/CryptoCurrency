package com.example.cryptocurrency.presentation.coin_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.R
import com.example.cryptocurrency.databinding.FragmentCoinDetailBinding
import com.example.cryptocurrency.databinding.FragmentCoinListBinding
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.presentation.coin_list.CoinListAdapter
import com.example.cryptocurrency.presentation.coin_list.CoinListState
import com.example.cryptocurrency.presentation.coin_list.CoinListViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {
    private lateinit var viewModel: CoinDetailViewModel
    private var currentCoin = CoinDetail("","","","",0,false, emptyList(), emptyList())
    private lateinit var binding: FragmentCoinDetailBinding
    private var coinId = ""
    private val tagAdapter= TagAdapter(arrayListOf())
    private val teamMemberAdapter = TeamMemberAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel = ViewModelProviders.of(this).get(CoinDetailViewModel::class.java)
        arguments?.let {
            coinId = CoinDetailFragmentArgs.fromBundle(it).coinId
        }
        if (coinId.isNotEmpty()) {
            viewModel.getCoin(coinId)
        }
        observeViewModel()

    }

    private fun initRecyclerView() {
        val mlayoutManager = FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
        }
        binding.tagsRecyclerView.apply {
            layoutManager= mlayoutManager
            adapter = tagAdapter
        }
        binding.teamMembersRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        binding.teamMembersRecyclerView.apply {
            layoutManager= LinearLayoutManager(context)
            adapter = teamMemberAdapter
        }
    }

    private  fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let { render(it) }
        })

    }
    private fun render(event: CoinDetailState) {
//        Log.e("CoinDetailFragment",event.toString())
        if (!event.isLoading && event.coin!=null) {
            handleData(event.coin)
            return
        }
        if (event.isLoading) {
            binding.progressLayout.progressLayout.visibility             = View.VISIBLE
//            binding.coinListView.visibility=View.GONE
            return

        }
        if (event.error.isNotEmpty()) {
            binding.progressLayout.progressLayout.visibility = View.GONE
            Toast.makeText(context,event.error, Toast.LENGTH_SHORT)
            return

        }

    }

    private fun handleData(coin: CoinDetail) {
        binding.progressLayout.progressLayout.visibility = View.GONE
        binding.name.text = coin.name
        binding.symbol.text = "(${coin.symbol})"

        if(coin.active){
            binding.status.text="Active"
        }else{
            binding.status.text="In-Active"
        }
        binding.description.text = coin.description
        tagAdapter.updateTags(coin.tags)
        teamMemberAdapter.updateTeamMember(coin.team)
        binding.tagsRecyclerView.visibility=View.VISIBLE
    }

}