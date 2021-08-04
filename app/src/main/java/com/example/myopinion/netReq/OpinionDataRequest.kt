package com.example.myopinion.netReq


class OpinionDataRequest(private val opinionDataRequestProvider: OpinionDataRequestProvider) {

    fun sendData() {
        opinionDataRequestProvider.sendData()
    }
}