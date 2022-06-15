package com.example.cryptocurrency.presentation.coin_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.example.cryptocurrency.databinding.CoinListItemBinding
import com.example.cryptocurrency.domain.model.Coin

class CoinListAdapter(var coins: ArrayList<Coin>,val action:CoinListAction):RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

    fun updateCoins(newNotes:List<Coin>){
        coins.clear()
        coins.addAll(newNotes)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: CoinListItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(coin:Coin){
            itemBinding.title.text = coin.name
            itemBinding.symbol.text = "(${coin.symbol})"
            if(coin.is_active){
                itemBinding.status.text ="Active"

            }else{
                itemBinding.status.text ="In-Active"

            }
            itemBinding.noteLayout.setOnClickListener {
                action.onClick(coin.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CoinListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(coins[position])
    }

    override fun getItemCount(): Int {
        return coins.size

    }
}