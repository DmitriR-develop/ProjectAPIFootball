package com.dmitri.projectapifootball.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitri.projectapifootball.R.layout.fragment_leagues
import com.dmitri.projectapifootball.abs.AbsFragment
import com.dmitri.projectapifootball.adapter.LeaguesRVAdapter
import com.dmitri.projectapifootball.databinding.FragmentLeaguesBinding
import com.dmitri.projectapifootball.navigation.IBackButtonListener
import com.dmitri.projectapifootball.presenter.LeaguesPresenter
import com.dmitri.projectapifootball.view.LeaguesView
import moxy.ktx.moxyPresenter

class LeaguesFragment : AbsFragment(fragment_leagues), LeaguesView, IBackButtonListener {

    companion object {
        fun newInstance() = LeaguesFragment()
    }

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