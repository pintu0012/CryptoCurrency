package com.example.cryptocurrency.presentation.coin_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.databinding.CoinListItemBinding
import com.example.cryptocurrency.databinding.TagItemBinding
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.presentation.coin_list.CoinListAction

class TagAdapter(var tags:ArrayList<String>): RecyclerView.Adapter<TagAdapter.ViewHolder>() {

    fun updateTags(newTags: List<String>){
        tags.clear()
        tags.addAll(newTags)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: TagItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(tag: String){
            itemBinding.tagName.text = tag

//            itemBinding.noteLayout.setOnClickListener {
//                action.onClick(coin.id)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TagItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tags[position])
    }

    override fun getItemCount(): Int {
        return tags.size

    }
}