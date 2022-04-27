package com.webproject.rubyonrailsandroidapp.features.web

import android.os.Bundle
import android.view.View
import com.webproject.rubyonrailsandroidapp.util.displayBackButtonAsCloseIcon
import dev.hotwire.turbo.nav.TurboNavGraphDestination

@TurboNavGraphDestination(uri = "turbo://fragment/web/modal")
class WebModalFragment : WebFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        toolbarForNavigation()?.displayBackButtonAsCloseIcon()
    }
}
