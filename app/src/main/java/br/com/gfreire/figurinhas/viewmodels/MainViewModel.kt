package br.com.gfreire.figurinhas.viewmodels

import androidx.lifecycle.ViewModel
import br.com.gfreire.figurinhas.repository.TeamsRepository

class MainViewModel : ViewModel() {

    private val repository = TeamsRepository()

    fun setTeamsList() {
        repository.setList()
    }

    fun getTeamsList() = repository.getList()
}