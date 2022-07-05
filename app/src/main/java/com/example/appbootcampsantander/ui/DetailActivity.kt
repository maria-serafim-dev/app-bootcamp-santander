package com.example.appbootcampsantander.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.appbootcampsantander.databinding.ActivityDetailBinding
import com.example.appbootcampsantander.domain.Match

class DetailActivity : AppCompatActivity() {

    object Extras{
        const val MATCH = "EXTRA_MATCH"
    }

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let {
            setupLayoutWithDataPlace(it)
            setupLayoutWithDataDescription(it)
            setupLayoutWithDataTeamHome(it)
            setupLayoutWithDataTeamAway(it)
        }
    }

    private fun setupLayoutWithDataDescription(match: Match) {
        binding.tvDescription.text = match.description
    }

    private fun setupLayoutWithDataPlace(match: Match) {
        Glide.with(this).load(match.place.image).into(binding.ivPlace)
        supportActionBar?.title = match.place.name
    }

    private fun setupLayoutWithDataTeamHome(match: Match) {
        with(binding) {
            with(match) {
                Glide.with(applicationContext).load(homeTeam.image).into(ivHomeTeam)
                tvHomeTeamName.text = homeTeam.name
                tvHomeTeamScore.text = homeTeam.score.toString()
                rbHomeTeamStars.rating = homeTeam.start.toFloat()
            }
        }
    }

    private fun setupLayoutWithDataTeamAway(match: Match) {
        with(binding) {
            with(match) {
                Glide.with(applicationContext).load(awayTeam.image).into(ivAwayTeam)
                tvAwayTeamName.text = awayTeam.name
                tvAwayTeamScore.text = awayTeam.score.toString()
                rbAwayTeamStars.rating = awayTeam.start.toFloat()
            }
        }

    }

}