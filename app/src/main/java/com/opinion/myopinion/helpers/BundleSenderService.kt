package com.opinion.myopinion.helpers

import androidx.fragment.app.Fragment

interface BundleSenderService {

    fun sendBundle(fragment: Fragment, data:String)
}