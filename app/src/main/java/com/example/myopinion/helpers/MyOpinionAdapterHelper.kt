package com.example.myopinion.helpers

class MyOpinionAdapterHelper(private val myOpinionsAdapterHelperProvider: MyOpinionsAdapterHelperProvider) : MyAdapterService {
    override fun initAdapter() {
        myOpinionsAdapterHelperProvider.initAdapter()
    }

    override fun notifyDataSetChanged() {
       myOpinionsAdapterHelperProvider.notifyDataSetChanged()
    }
}