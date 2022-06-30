package com.example.appbootcampsantander.domain

data class Match(
    val description: String,
    val place: Place,
    val homeTeam: Team,
    val awayTeam: Team
)