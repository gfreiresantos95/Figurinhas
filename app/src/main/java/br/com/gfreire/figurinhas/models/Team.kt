package br.com.gfreire.figurinhas.models

data class Team(
    var country: Country? = null,
    var players: ArrayList<Player>? = null
)