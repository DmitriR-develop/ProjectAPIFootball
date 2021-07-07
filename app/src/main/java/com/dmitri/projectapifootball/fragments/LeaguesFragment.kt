package com.dmitri.projectapifootball.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitri.projectapifootball.adapter.LeaguesRVAdapter
import com.dmitri.projectapifootball.databinding.FragmentLeaguesBinding
import com.dmitri.projectapifootball.model.ILeaguesRepo
import com.dmitri.projectapifootball.navigation.IBackButtonListener
import com.dmitri.projectapifootball.presenter.LeaguesPresenter
import com.dmitri.projectapifootball.view.LeaguesView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class LeaguesFragment : MvpAppCompatFragment(), LeaguesView, IBackButtonListener {
    companion object {
        @JvmStatic
        fun newInstance() = LeaguesFragment()
    }

    @Inject
    lateinit var leaguesRepo: ILeaguesRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var scheduler: Scheduler

    val presenter by moxyPresenter {
        LeaguesPresenter(leaguesRepo, router, scheduler)
    }

    var adapter: LeaguesRVAdapter? = null

    private var vb: FragmentLeaguesBinding? = null
    private val binding get() = vb!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLeaguesBinding.inflate(inflater, container, false)
            .also { vb = it }.root
    }

    override fun backPressed() = presenter.backPressed()

    override fun init() {
        vb?.rvLeagues?.layoutManager = LinearLayoutManager(context)
        adapter = LeaguesRVAdapter(presenter.leaguesListPresenter)
        binding.rvLeagues.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        vb = null
        super.onDestroyView()
    }
}