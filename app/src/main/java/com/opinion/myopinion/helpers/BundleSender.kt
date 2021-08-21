package com.opinion.myopinion.helpers

import androidx.fragment.app.Fragment

class BundleSender(private val bundleSenderProvider: BundleSenderProvider) : BundleSenderService {
    override fun sendBundle(fragment: Fragment, data: String) {
        bundleSenderProvider.sendBundle(fragment,data)
    }
}