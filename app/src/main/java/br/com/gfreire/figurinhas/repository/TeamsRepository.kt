package br.com.gfreire.figurinhas.repository

import androidx.lifecycle.MutableLiveData
import br.com.gfreire.figurinhas.models.Country
import br.com.gfreire.figurinhas.models.Player
import br.com.gfreire.figurinhas.models.Team

class TeamsRepository {

    private val teamsList = MutableLiveData<ArrayList<Team>>()

    fun setList() {
        val teamsList = ArrayList<Team>()

        teamsList.add(FIFA_LIST)

        for (country in COUNTRIES_LIST) {
            val playersList = ArrayList<Player>()

            for (number in PLAYERS_NUMBERS_LIST) {
                playersList.add(Player("${country.abr} $number"))
            }

            teamsList.add(Team(country, playersList))
        }

        this.teamsList.value = teamsList
    }

    fun getList() = teamsList

    private companion object {
        private val PLAYERS_NUMBERS_LIST =
            arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

        private val FIFA_LIST = Team(
            Country("Fifa World Cup", "FWC"),
            arrayListOf(
                Player("00"),
                Player("FWC 1"),
                Player("FWC 2"),
                Player("FWC 3"),
                Player("FWC 4"),
                Player("FWC 5"),
                Player("FWC 6"),
                Player("FWC 7"),
                Player("FWC 8"),
                Player("FWC 9"),
                Player("FWC 10"),
                Player("FWC 11"),
                Player("FWC 12"),
                Player("FWC 13"),
                Player("FWC 14"),
                Player("FWC 15"),
                Player("FWC 16"),
                Player("FWC 17"),
                Player("FWC 18"),
                Player("FWC 19"),
                Player("FWC 20"),
                Player("FWC 21"),
                Player("FWC 22"),
                Player("FWC 23"),
                Player("FWC 24"),
                Player("FWC 25"),
                Player("FWC 26"),
                Player("FWC 27"),
                Player("FWC 28"),
                Player("FWC 29"),
            )
        )

        private val COUNTRIES_LIST = arrayListOf(
            Country("Catar", "QAT"),
            Country("Equador", "ECU"),
            Country("Senegal", "SEN"),
            Country("Holanda", "NED"),
            Country("Inglaterra", "ENG"),
            Country("Ir??", "IRN"),
            Country("Estados Unidos", "USA"),
            Country("Pa??s de Gales", "WAL"),
            Country("Argentina", "ARG"),
            Country("Ar??bia Saudita", "KSA"),
            Country("M??xico", "MEX"),
            Country("Pol??nia", "POL"),
            Country("Fran??a", "FRA"),
            Country("Austr??lia", "AUS"),
            Country("Dinamarca", "DEN"),
            Country("Tun??sia", "TUN"),
            Country("Espanha", "ESP"),
            Country("Costa Rica", "CRC"),
            Country("Alemanha", "GER"),
            Country("Jap??o", "JPN"),
            Country("B??lgica", "BEL"),
            Country("Canad??", "CAN"),
            Country("Marrocos", "MAR"),
            Country("Cro??cia", "CRO"),
            Country("Brasil", "BRA"),
            Country("S??rvia", "SRB"),
            Country("Su????a", "SUI"),
            Country("Camar??es", "CMR"),
            Country("Portugal", "POR"),
            Country("Gana", "GHA"),
            Country("Uruguai", "URU"),
            Country("Coreia do Sul", "KOR")
        )
    }
}