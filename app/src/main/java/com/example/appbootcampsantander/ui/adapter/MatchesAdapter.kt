package com.example.appbootcampsantander.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appbootcampsantander.databinding.MatchItemBinding
import com.example.appbootcampsantander.domain.Match

class MatchesAdapter(private val matches: List<Match>) : RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {


    private lateinit var binding: MatchItemBinding


    inner class ViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = matches[position]

        Glide.with(holder.itemView.context).load(match.homeTeam.image).circleCrop().into(holder.binding.ivHomeTeam)
        holder.binding.tvHomeTeamName.text = match.homeTeam.name
        holder.binding.tvHomeTeamScore.text = match.homeTeam.score.toString()

        Glide.with(holder.itemView.context).load(match.awayTeam.image).circleCrop().into(holder.binding.ivAwayTeam)
        holder.binding.tvAwayTeamName.text = match.awayTeam.name
        holder.binding.tvAwayTeamScore.text = match.awayTeam.score.toString()

    }

    override fun getItemCount(): Int {
        return matches.size
    }

    fun getMathches(): List<Match>{
        return matches
    }
}