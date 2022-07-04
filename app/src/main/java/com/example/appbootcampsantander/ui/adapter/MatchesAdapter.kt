package com.example.appbootcampsantander.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        holder.binding.tvHomeTeamName.text = match.homeTeam.name
        holder.binding.tvAwayTeamName.text = match.awayTeam.name

    }

    override fun getItemCount(): Int {
        return matches.size
    }
}