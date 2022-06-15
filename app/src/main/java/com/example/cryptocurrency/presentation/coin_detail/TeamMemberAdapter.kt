package com.example.cryptocurrency.presentation.coin_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.data.remote.dto.TeamMember
import com.example.cryptocurrency.databinding.CoinListItemBinding
import com.example.cryptocurrency.databinding.TagItemBinding
import com.example.cryptocurrency.databinding.TeamMemberItemBinding
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.presentation.coin_list.CoinListAction

class TeamMemberAdapter(var members:ArrayList<TeamMember>): RecyclerView.Adapter<TeamMemberAdapter.ViewHolder>() {

    fun updateTeamMember(newTags: List<TeamMember>){
        members.clear()
        members.addAll(newTags)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: TeamMemberItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(member: TeamMember){
            itemBinding.memberName.text = member.name
            itemBinding.designation.text = member.position

//            itemBinding.noteLayout.setOnClickListener {
//                action.onClick(coin.id)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TeamMemberItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(members[position])
    }

    override fun getItemCount(): Int {
        return members.size

    }
}