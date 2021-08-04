package com.example.myopinion.helpers

import androidx.fragment.app.Fragment

interface BundleSenderService {

    fun sendBundle(fragment: Fragment, data:String)
}