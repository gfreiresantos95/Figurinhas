package br.com.gfreire.figurinhas.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gfreire.figurinhas.R
import br.com.gfreire.figurinhas.adapters.TeamsListAdapter
import br.com.gfreire.figurinhas.models.Team
import br.com.gfreire.figurinhas.utils.Constants.ADAPTER_POSITION
import br.com.gfreire.figurinhas.utils.Constants.PREFERENCE_NAME
import br.com.gfreire.figurinhas.utils.Constants.TEAM_EXTRA
import br.com.gfreire.figurinhas.viewmodels.MainViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray

class MainActivity : AppCompatActivity(), TeamsListAdapter.OnTeamItemClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var teamsListAdapter: TeamsListAdapter
    private lateinit var rvTeamsList: RecyclerView
    private val teamList = ArrayList<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        teamsListAdapter = TeamsListAdapter(this, this)

        setupViews()
        setupObservables()
        checkListOnPrefs()
    }

    override fun onTeamItemClickListener(team: Team, position: Int) {
        val intent = Intent(this, TeamActivity::class.java)
        intent.putExtra(ADAPTER_POSITION, position)
        startActivity(intent)
    }

    private fun setupViews() {
        rvTeamsList = findViewById(R.id.rv_teams_list)

        setupRecycler()
    }

    private fun setupObservables() {
        mainViewModel.getTeamsList().observe(this) { countryList ->
            saveToPrefs(countryList)
            teamsListAdapter.addList(countryList)
        }
    }

    private fun checkListOnPrefs() {
        val prefs = getPreferences()
        if (prefs.contains(TEAM_EXTRA)) {
            val extraJson = getPreferences().getString(TEAM_EXTRA, "")
            val jsonList = Gson().fromJson(extraJson, JsonArray::class.java)
            jsonList.forEach {
                teamList.add(Gson().fromJson(it, Team::class.java))
            }
            teamsListAdapter.addList(teamList)
        } else {
            mainViewModel.setTeamsList()
        }
    }

    private fun setupRecycler() {
        rvTeamsList.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter = teamsListAdapter
        }
    }

    private fun saveToPrefs(countryList: ArrayList<Team>) {
        with(getPreferences().edit()) {
            putString(TEAM_EXTRA, Gson().toJson(countryList))
            apply()
        }
    }

    private fun getPreferences(): SharedPreferences =
        getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
}