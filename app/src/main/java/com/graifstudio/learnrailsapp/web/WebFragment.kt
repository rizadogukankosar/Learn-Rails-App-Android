package com.webproject.rubyonrailsandroidapp.features.web

import android.os.Bundle
import android.view.View
import com.graifstudio.learnrailsapp.R
import com.webproject.rubyonrailsandroidapp.base.NavDestination

import dev.hotwire.turbo.fragments.TurboWebFragment
import dev.hotwire.turbo.nav.TurboNavGraphDestination
import dev.hotwire.turbo.visit.TurboVisitAction.REPLACE
import dev.hotwire.turbo.visit.TurboVisitOptions

@TurboNavGraphDestination(uri = "turbo://fragment/web")
open class WebFragment : TurboWebFragment(), NavDestination {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
    }

    override fun onFormSubmissionStarted(location: String) {
        menuProgress?.isVisible = true
    }

    override fun onFormSubmissionFinished(location: String) {
        menuProgress?.isVisible = false
    }

    override fun onVisitErrorReceived(location: String, errorCode: Int) {
        println("showErrorCode: "+ errorCode.toString())
        when (errorCode) {
            401 -> println("401")
            else -> super.onVisitErrorReceived(location, errorCode)
        }
    }

    private fun setupMenu() {
        toolbarForNavigation()?.inflateMenu(R.menu.web)
    }


}
