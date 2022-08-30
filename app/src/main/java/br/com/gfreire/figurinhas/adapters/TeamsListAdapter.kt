package br.com.gfreire.figurinhas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gfreire.figurinhas.R
import br.com.gfreire.figurinhas.models.Team

class TeamsListAdapter(
    private var context: Context,
    private var listener: OnTeamItemClickListener
) : RecyclerView.Adapter<TeamsListAdapter.TeamItemViewHolder>() {

    private val teamsList = ArrayList<Team>()

    interface OnTeamItemClickListener {
        fun onTeamItemClickListener(team: Team, position: Int)
    }

    inner class TeamItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        val tvTeamName: TextView = itemView.findViewById(R.id.tv_team_name)

        override fun onClick(view: View?) {
            listener.onTeamItemClickListener(teamsList[adapterPosition], adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamItemViewHolder =
        TeamItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.teams_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: TeamItemViewHolder, position: Int) {
        val team = teamsList[position]

        holder.tvTeamName.text = team.country?.name
    }

    override fun getItemCount(): Int = teamsList.size

    fun addList(list: ArrayList<Team>) {
        if (teamsList.size > 0) teamsList.clear()
        teamsList.addAll(list)
        notifyDataSetChanged()
    }
}