package com.example.myopinion.helpers

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myopinion.R
import com.example.myopinion.fragments.CommentsFragment

class BundleSenderProvider : BundleSenderService {
    override fun sendBundle(fragment: Fragment, data: String) {

        val fragmentManager = fragment.requireActivity().supportFragmentManager
        val commentsFragment = CommentsFragment()
        fragmentManager.beginTransaction().replace(R.id.layout, commentsFragment)
            .addToBackStack(
                null
            ).commit()

        val bundle = Bundle()
        bundle.putString("postId", data)
        commentsFragment.arguments = bundle

    }

}