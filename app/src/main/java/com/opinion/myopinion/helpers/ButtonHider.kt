package com.opinion.myopinion.helpers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.elasticviews.ElasticFloatingActionButton

interface ButtonHider {

    fun hideOnScroll(floatingActionButton: ElasticFloatingActionButton)

    class Base(private val recyclerView: RecyclerView) : ButtonHider {
        override fun hideOnScroll(floatingActionButton: ElasticFloatingActionButton) {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 && floatingActionButton.getVisibility() === View.VISIBLE) {
                        floatingActionButton.hide()
                    } else if (dy < 0 && floatingActionButton.visibility !== View.VISIBLE) {
                        floatingActionButton.show()
                    }
                }
            })
        }
    }
}