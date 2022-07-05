package com.example.appbootcampsantander.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appbootcampsantander.R
import com.example.appbootcampsantander.data.MatchesApi
import com.example.appbootcampsantander.databinding.ActivityMainBinding
import com.example.appbootcampsantander.domain.Match
import com.example.appbootcampsantander.ui.adapter.MatchesAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var matchAdapter: MatchesAdapter
    private lateinit var matchesApi: MatchesApi
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHttpClient()
        setupMatchesList()
        setupMatchesRefresh()
        setupFloatingActionButton()
    }

    private fun setupHttpClient() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://maria-serafim-dev.github.io/matches-simulator-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

       matchesApi =  retrofit.create(MatchesApi::class.java)
    }

    private fun setupMatchesList() {
        binding.rvMatches.setHasFixedSize(true)
        binding.rvMatches.layoutManager = LinearLayoutManager(this)

        findMatchesFromApi()
    }


    private fun showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api, Snackbar.LENGTH_LONG).show()
    }

    private fun setupMatchesRefresh() {
        binding.srlMatches.setOnRefreshListener (this::findMatchesFromApi)
    }

    private fun setupFloatingActionButton() {
        binding.fabSimulate.setOnClickListener {
            it.animate().rotationBy(360F).setDuration(500).setListener(object : AnimatorListenerAdapter(){

                override fun onAnimationEnd(animation: Animator?) {
                    setupScoreMatches()
                }
            })
        }
    }

    private fun setupScoreMatches() {
        matchAdapter.getMatches().forEachIndexed { index, match ->
            match.homeTeam.score = Random.nextInt(match.homeTeam.start + 1)
            match.awayTeam.score = Random.nextInt(match.awayTeam.start + 1)
            matchAdapter.notifyItemChanged(index)
        }
    }

    private fun findMatchesFromApi() {
        binding.srlMatches.isRefreshing = true
        matchesApi.getMatches().enqueue(object : Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    val matches: List<Match>? = response.body()
                    matchAdapter = matches?.let { MatchesAdapter(it) }!!
                    binding.rvMatches.adapter = matchAdapter
                } else {
                    showErrorMessage()
                }

                binding.srlMatches.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                showErrorMessage()
            }
        })
    }
}