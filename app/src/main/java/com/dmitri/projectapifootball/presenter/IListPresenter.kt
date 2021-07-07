package com.dmitri.projectapifootball.presenter

import com.dmitri.projectapifootball.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(View: V)
    fun getCount(): Int
}