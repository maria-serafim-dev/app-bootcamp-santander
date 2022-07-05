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
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let{
            Glide.with(this).load(it.place.image).into(binding.ivPlace)
            supportActionBar?.title = it.place.name

            binding.tvDescription.text = it.description
            Glide.with(this).load(it.homeTeam.image).into(binding.ivHomeTeam)
            binding.tvHomeTeamName.text = it.homeTeam.name
            binding.tvHomeTeamScore.text = it.homeTeam.score.toString()
            binding.rbHomeTeamStars.rating = it.homeTeam.start.toFloat()

            Glide.with(this).load(it.awayTeam.image).into(binding.ivAwayTeam)
            binding.tvAwayTeamName.text = it.awayTeam.name
            binding.tvAwayTeamScore.text = it.awayTeam.score.toString()
            binding.rbAwayTeamStars.rating = it.awayTeam.start.toFloat()
        }
    }

}