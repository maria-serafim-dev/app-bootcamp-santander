package com.example.appbootcampsantander.ui

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

        matchesApi.getMatches().enqueue(object : Callback<List<Match>>{
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if(response.isSuccessful){
                    val matches : List<Match>? = response.body()
                    matchAdapter = matches?.let { MatchesAdapter(it) }!!
                    binding.rvMatches.adapter = matchAdapter

                }else{
                    showErrorMessage()
                }
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                showErrorMessage()
            }

        })

    }

    private fun showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api, Snackbar.LENGTH_LONG).show()
    }

    private fun setupMatchesRefresh() {
        //TODO Atualizar as partidas na ação de swipe
    }

    private fun setupFloatingActionButton() {
        //TODO Criar evento de click e simulação de partidas
    }

}