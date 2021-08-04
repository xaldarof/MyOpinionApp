package com.example.myopinion.tools

import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

class Animator {

    companion object {
        fun View.startAnimating() {
            YoYo.with(Techniques.Swing)
                .duration(100)
                .repeat(1)
                .playOn(this)

        }
    }
}