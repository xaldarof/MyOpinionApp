package com.example.myopinion.adapters.helpers

class MainFragmentAdapterHelper(private val mainFragmentAdapterProvider: MainFragmentAdapterProvider) :
    MainFragmentAdapterService {
    override fun initAdapter() {
        mainFragmentAdapterProvider.initAdapter()
    }

    override fun notifyDataSetChanged() {
        mainFragmentAdapterProvider.notifyDataSetChanged()
    }
}