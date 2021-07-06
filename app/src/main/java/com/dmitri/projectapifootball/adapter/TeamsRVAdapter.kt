package com.dmitri.projectapifootball.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitri.projectapifootball.databinding.ItemTeamBinding
import com.dmitri.projectapifootball.presenter.TeamsPresenter
import com.dmitri.projectapifootball.view.TeamsItemView

class TeamsRVAdapter(val presenter: TeamsPresenter.TeamsListPresenter) :
    RecyclerView.Adapter<TeamsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTeamBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }


    inner class ViewHolder(private val vb: ItemTeamBinding) : RecyclerView.ViewHolder(vb.root),
        TeamsItemView {
        override var pos = -1
        override fun setName(league: String) {
            vb.tvTeamName.text = league
        }
    }
}