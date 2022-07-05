package com.example.appbootcampsantander.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appbootcampsantander.databinding.MatchItemBinding
import com.example.appbootcampsantander.domain.Match
import com.example.appbootcampsantander.ui.DetailActivity

class MatchesAdapter(private val matches: List<Match>) : RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {


    private lateinit var binding: MatchItemBinding


    inner class ViewHolder(private val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(match: Match, context: Context) {
            with(binding) {
                Glide.with(context).load(match.homeTeam.image).circleCrop().into(ivHomeTeam)
                tvHomeTeamName.text = match.homeTeam.name
                tvHomeTeamScore.text = match.homeTeam.score.toString()

                Glide.with(context).load(match.awayTeam.image).circleCrop().into(ivAwayTeam)
                tvAwayTeamName.text = match.awayTeam.name
                tvAwayTeamScore.text = match.awayTeam.score.toString()

                itemView.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.Extras.MATCH, match)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = matches[position]
        val context = holder.itemView.context
        holder.onBind(match, context)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    fun getMatches(): List<Match> {
        return matches
    }
}