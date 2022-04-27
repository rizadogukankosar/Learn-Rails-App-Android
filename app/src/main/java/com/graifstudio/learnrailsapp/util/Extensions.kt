package com.webproject.rubyonrailsandroidapp.util

import android.content.Context
import android.content.res.Configuration
import android.webkit.WebView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.graifstudio.learnrailsapp.R
import dev.hotwire.turbo.config.TurboPathConfigurationProperties

val TurboPathConfigurationProperties.description: String?
    get() = get("description")

fun Toolbar.displayBackButtonAsCloseIcon() {
    navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_close)
}

fun WebView.initDayNightTheme() {
    if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK_STRATEGY)) {
        WebSettingsCompat.setForceDarkStrategy(settings, WebSettingsCompat.DARK_STRATEGY_WEB_THEME_DARKENING_ONLY)
    }

    if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
        when (isNightModeEnabled(context)) {
            true -> WebSettingsCompat.setForceDark(settings, WebSettingsCompat.FORCE_DARK_ON)
            else -> WebSettingsCompat.setForceDark(settings, WebSettingsCompat.FORCE_DARK_AUTO)
        }
    }
}

private fun isNightModeEnabled(context: Context): Boolean {
    val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return currentNightMode == Configuration.UI_MODE_NIGHT_YES
}
