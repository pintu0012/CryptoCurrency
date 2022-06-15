package com.example.cryptocurrency.presentation.coin_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.databinding.FragmentCoinListBinding
import com.example.cryptocurrency.domain.model.Coin
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class CoinListFragment : Fragment() , CoinListAction{
    private lateinit var viewModel: CoinListViewModel
    private lateinit var binding :FragmentCoinListBinding
    private val coinListAdapter= CoinListAdapter(arrayListOf(),this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCoinListBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java)
        observeViewModel()
    }

    private fun initRecyclerView() {
        binding.coinListView.apply {
            layoutManager= LinearLayoutManager(context)
            adapter = coinListAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCoins()
    }

    private  fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let { render(it) }
        })

    }
    private fun render(event: CoinListState) {
        Log.e("CoinListFragment",event.toString())
        if (!event.isLoading && event.coins.isNotEmpty()) {
            binding.progressBar.visibility = View.GONE
            binding.coinListView.visibility=View.VISIBLE
            coinListAdapter.updateCoins(event.coins)
            return
        }
        if (event.isLoading) {
            binding.progressBar.visibility = View.VISIBLE
                    binding.coinListView.visibility=View.GONE
            return

        }
        if (event.error.isNotEmpty()) {
            binding.progressBar.visibility = View.GONE
                    Toast.makeText(context,event.error,Toast.LENGTH_SHORT)
            return

        }

    }

    override fun onClick(id: String) {
//        val action = .actionGotoNotes(id)


    }

}