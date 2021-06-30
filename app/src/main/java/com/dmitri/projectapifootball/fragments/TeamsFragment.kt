package com.dmitri.projectapifootball.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitri.projectapifootball.adapter.TeamsRVAdapter
import com.dmitri.projectapifootball.databinding.FragmentTeamsBinding
import com.dmitri.projectapifootball.model.ILeaguesRepo
import com.dmitri.projectapifootball.model.Teams
import com.dmitri.projectapifootball.navigation.IBackButtonListener
import com.dmitri.projectapifootball.presenter.TeamsPresenter
import com.dmitri.projectapifootball.view.TeamsItemView
import com.dmitri.projectapifootball.view.TeamsView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class TeamsFragment : MvpAppCompatFragment(), TeamsView, TeamsItemView, IBackButtonListener {
    companion object {
        private const val TEAM_NAME = "TEAM_NAME"

        @JvmStatic
        fun newInstance(team: Teams) =
            TeamsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(TEAM_NAME, team)
                }
            }
    }

    @Inject
    lateinit var leaguesRepo: ILeaguesRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var scheduler: Scheduler

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

    override fun setName(text: String) {
        binding.tvChooseLeague.text = text
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