package com.dmitri.projectapifootball.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitri.projectapifootball.R.layout.fragment_teams
import com.dmitri.projectapifootball.abs.AbsFragment
import com.dmitri.projectapifootball.adapter.TeamsRVAdapter
import com.dmitri.projectapifootball.databinding.FragmentTeamsBinding
import com.dmitri.projectapifootball.model.Leagues
import com.dmitri.projectapifootball.navigation.IBackButtonListener
import com.dmitri.projectapifootball.presenter.TeamsPresenter
import com.dmitri.projectapifootball.view.TeamsItemView
import com.dmitri.projectapifootball.view.TeamsView
import moxy.ktx.moxyPresenter

class TeamsFragment : AbsFragment(fragment_teams), TeamsView, TeamsItemView, IBackButtonListener {
    companion object {
        private const val TEAM_NAME = "TEAM_NAME"

        @JvmStatic
        fun newInstance(team: Leagues) =
            TeamsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(TEAM_NAME, team)
                }
            }
    }

    private var vb: FragmentTeamsBinding? = null
    private val binding get() = vb!!

    private val leagueId by lazy {
        arguments?.getInt(TEAM_NAME) ?: ""
    }

    private val presenter by moxyPresenter {
        TeamsPresenter(
            leagueId as Int,
            leaguesRepo,
            router,
            scheduler
        )
    }

    private var adapter: TeamsRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTeamsBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun setName(league: String) {
        binding.tvChooseLeague.text = league
    }

    override var pos: Int
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun init() {
        binding.rvTeams.layoutManager = LinearLayoutManager(context)
        adapter = TeamsRVAdapter(presenter.teamsListPresenter)
        binding.rvTeams.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroyView() {
        vb = null
        super.onDestroyView()
    }
}