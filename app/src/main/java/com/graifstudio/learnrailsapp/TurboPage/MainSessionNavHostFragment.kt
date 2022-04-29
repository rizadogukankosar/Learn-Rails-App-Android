package com.graifstudio.learnrailsapp.TurboPage

import android.content.Context
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.webproject.rubyonrailsandroidapp.features.web.WebBottomSheetFragment
import com.webproject.rubyonrailsandroidapp.features.web.WebFragment
import com.webproject.rubyonrailsandroidapp.features.web.WebHomeFragment
import com.webproject.rubyonrailsandroidapp.features.web.WebModalFragment

import com.webproject.rubyonrailsandroidapp.util.TURBO_URL
import com.webproject.rubyonrailsandroidapp.util.initDayNightTheme
import dev.hotwire.turbo.BuildConfig
import dev.hotwire.turbo.config.TurboPathConfiguration

import dev.hotwire.turbo.session.TurboSessionNavHostFragment
import kotlin.reflect.KClass


class MainSessionNavHostFragment : TurboSessionNavHostFragment(){

    override val sessionName = "main"

    override val startLocation = TURBO_URL

    override val registeredActivities: List<KClass<out AppCompatActivity>>
        get() = listOf()

    override val registeredFragments: List<KClass<out Fragment>>
        get() = listOf(
                WebFragment::class,
                WebHomeFragment::class,
                WebModalFragment::class,
                WebBottomSheetFragment::class,

        )

    override val pathConfigurationLocation: TurboPathConfiguration.Location
        get() = TurboPathConfiguration.Location(
                assetFilePath = "json/configuration.json"
        )

    override fun onSessionCreated() {
        super.onSessionCreated()

        session.webView.settings.userAgentString = customUserAgent(session.webView)
        session.webView.initDayNightTheme()
        if (BuildConfig.DEBUG) {
            session.setDebugLoggingEnabled(true)
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }

    private fun customUserAgent(webView: WebView): String {
        return "Turbo Native Android ${webView.settings.userAgentString}"
    }

}