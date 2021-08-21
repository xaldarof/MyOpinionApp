package com.opinion.myopinion.helpers

import android.app.Activity
import android.content.Context
import com.opinion.myopinion.R
import com.opinion.myopinion.repository.Password
import com.skydoves.elasticviews.ElasticImageView

interface PasswordCheck {
    class PasswordChecker(private val context: Context,private val elasticImageView: ElasticImageView) {
        fun init(){
            val sharedPreferences = context.getSharedPreferences("password",
                Activity.MODE_PRIVATE
            )
            val password = Password(sharedPreferences,context)
            if (password.getPassword().isNotEmpty()){
                elasticImageView.setImageResource(R.drawable.ic_baseline_lock_24)
            }else {
                elasticImageView.setImageResource(R.drawable.ic_baseline_lock_open_24)
            }
        }
    }
}