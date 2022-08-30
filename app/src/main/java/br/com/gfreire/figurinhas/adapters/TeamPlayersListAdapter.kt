package br.com.gfreire.figurinhas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gfreire.figurinhas.R
import br.com.gfreire.figurinhas.models.Player
import com.google.android.material.card.MaterialCardView

class TeamPlayersListAdapter(
    private var context: Context,
    private var listener: OnTeamPlayerItemClickListener
) : RecyclerView.Adapter<TeamPlayersListAdapter.TeamPlayerItemViewHolder>() {

    private val teamPlayersList = ArrayList<Player>()

    interface OnTeamPlayerItemClickListener {
        fun onClick(player: Player)
    }

    inner class TeamPlayerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        val cvPlayerCard: MaterialCardView = itemView.findViewById(R.id.cv_team_player)
        val tvPlayerNumber: TextView = itemView.findViewById(R.id.tv_team_player_number)

        override fun onClick(view: View?) {
            listener.onClick(teamPlayersList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamPlayerItemViewHolder =
        TeamPlayerItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.team_players_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: TeamPlayerItemViewHolder, position: Int) {
        val player = teamPlayersList[position]

        if (player.alreadyHave) {
            checkPlayer(holder)
        } else {
            uncheckPlayer(holder)
        }

        holder.tvPlayerNumber.text = player.number
    }

    override fun getItemCount(): Int = teamPlayersList.size

    private fun checkPlayer(itemHolder: TeamPlayerItemViewHolder) {
        itemHolder.cvPlayerCard.setCardBackgroundColor(context.getColor(R.color.white))
        itemHolder.tvPlayerNumber.setTextColor(context.getColor(R.color.gray))
    }

    private fun uncheckPlayer(itemHolder: TeamPlayerItemViewHolder) {
        itemHolder.cvPlayerCard.setCardBackgroundColor(context.getColor(R.color.dark_scarlet))
        itemHolder.tvPlayerNumber.setTextColor(context.getColor(R.color.white))
    }

    fun addList(list: ArrayList<Player>) {
        if (teamPlayersList.size > 0) teamPlayersList.clear()
        teamPlayersList.addAll(list)
        notifyDataSetChanged()
    }

    fun itemChanged(player: Player) {
        if (teamPlayersList.contains(player)) {
            val index = teamPlayersList.indexOf(player)
            teamPlayersList[index] = player
            notifyItemChanged(index)
        }
    }
}