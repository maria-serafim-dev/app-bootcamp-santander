package com.example.appbootcampsantander.data

import com.example.appbootcampsantander.domain.Match
import retrofit2.Call
import retrofit2.http.GET

interface MatchesApi {

    @GET("matches.json")
    fun getMatches() : Call<List<Match>>


}