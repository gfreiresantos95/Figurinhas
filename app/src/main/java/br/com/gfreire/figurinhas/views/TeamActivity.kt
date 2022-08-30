package br.com.gfreire.figurinhas.views

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gfreire.figurinhas.R
import br.com.gfreire.figurinhas.adapters.TeamPlayersListAdapter
import br.com.gfreire.figurinhas.models.Player
import br.com.gfreire.figurinhas.models.Team
import br.com.gfreire.figurinhas.utils.Constants.ADAPTER_POSITION
import br.com.gfreire.figurinhas.utils.Constants.PREFERENCE_NAME
import br.com.gfreire.figurinhas.utils.Constants.TEAM_EXTRA
import com.google.android.material.appbar.MaterialToolbar
import com.google.gson.Gson
import com.google.gson.JsonArray

class TeamActivity : AppCompatActivity(), TeamPlayersListAdapter.OnTeamPlayerItemClickListener {

    private lateinit var tbTeamToolbar: MaterialToolbar
    private lateinit var teamPlayersListAdapter: TeamPlayersListAdapter
    private lateinit var rvTeamPlayersList: RecyclerView
    private var team = Team()
    private var teamList = ArrayList<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        teamPlayersListAdapter = TeamPlayersListAdapter(this, this)

        getExtraInformation()
        setupViews()
    }

    override fun onDestroy() {
        with(getPreferences().edit()) {
            putString(TEAM_EXTRA, Gson().toJson(teamList))
            apply()
        }

        super.onDestroy()
    }

    override fun onClick(player: Player) {
        player.alreadyHave = !player.alreadyHave
        teamPlayersListAdapter.itemChanged(player)
        saveNewItemInList(player)
    }

    private fun getExtraInformation() {
        val extraJson = getPreferences().getString(TEAM_EXTRA, "")
        val jsonList = Gson().fromJson(extraJson, JsonArray::class.java)

        jsonList.forEach {
            teamList.add(Gson().fromJson(it, Team::class.java))
        }

        if (intent.hasExtra(ADAPTER_POSITION)) {
            team = teamList[intent.getIntExtra(ADAPTER_POSITION, 0)]
        }
    }

    private fun setupViews() {
        tbTeamToolbar = findViewById(R.id.tb_team_toolbar)
        rvTeamPlayersList = findViewById(R.id.rv_team_players_list)

        setupToolbar()
        setupRecycler()
    }

    private fun setupToolbar() {
        tbTeamToolbar.title = team.country?.name

        tbTeamToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecycler() {
        rvTeamPlayersList.apply {
            layoutManager = GridLayoutManager(applicationContext, 4)
            setHasFixedSize(true)
            adapter = teamPlayersListAdapter
        }

        teamPlayersListAdapter.addList(team.players!!)
    }

    private fun saveNewItemInList(player: Player) {
        val teamIndex = teamList.indexOf(team)
        val playerIndex = team.players?.indexOf(player)!!

        team.players!![playerIndex] = player
        teamList[teamIndex] = team
    }

    private fun getPreferences(): SharedPreferences =
        getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
}