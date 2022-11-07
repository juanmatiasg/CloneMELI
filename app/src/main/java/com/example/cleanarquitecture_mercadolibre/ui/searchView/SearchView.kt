package com.example.cleanarquitecture_mercadolibre.ui.searchView

import android.animation.Animator
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import com.example.cleanarquitecture_mercadolibre.databinding.ViewSearchBinding

class SearchView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    var rootSearchView:ViewSearchBinding

    init {
        rootSearchView = ViewSearchBinding.inflate(LayoutInflater.from(context),this,true)
        rootSearchView.openSearchButton.setOnClickListener{openSearch()}
        rootSearchView.closeSearchButton.setOnClickListener { closeSearch() }
    }

    private fun openSearch() {
        rootSearchView.searchOpenView.visibility = View.VISIBLE
        val circularReveal = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimationUtils.createCircularReveal(
                rootSearchView.searchOpenView,
                (rootSearchView.openSearchButton.right + rootSearchView.openSearchButton.left) / 2,
                (rootSearchView.openSearchButton.top + rootSearchView.openSearchButton.bottom) / 2,
                0f, width.toFloat()
            )
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
        circularReveal.duration = 300
        circularReveal.start()
    }

    private fun closeSearch() {
        val circularConceal = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimationUtils.createCircularReveal(
                rootSearchView.searchOpenView,
                (rootSearchView.openSearchButton.right + rootSearchView.openSearchButton.left) / 2,
                (rootSearchView.openSearchButton.top + rootSearchView.openSearchButton.bottom) / 2,
                width.toFloat(), 0f
            )
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }

        circularConceal.duration = 300
        circularConceal.start()
        circularConceal.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) = Unit
            override fun onAnimationCancel(animation: Animator?) = Unit
            override fun onAnimationStart(animation: Animator?) = Unit
            override fun onAnimationEnd(animation: Animator?) {
                rootSearchView.searchOpenView.visibility = View.INVISIBLE
                circularConceal.removeAllListeners()
            }
        })
    }
}
