package com.webproject.rubyonrailsandroidapp.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.SHARE_STATE_ON
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.graifstudio.learnrailsapp.MainActivity
import com.graifstudio.learnrailsapp.R
import com.webproject.rubyonrailsandroidapp.util.GUIDES_URL
import com.webproject.rubyonrailsandroidapp.util.STIMULUS_URL
import com.webproject.rubyonrailsandroidapp.util.TURBO_URL
import dev.hotwire.turbo.config.TurboPathConfigurationProperties
import dev.hotwire.turbo.config.context
import dev.hotwire.turbo.nav.TurboNavDestination
import dev.hotwire.turbo.nav.TurboNavPresentationContext.MODAL

interface NavDestination : TurboNavDestination {
    val menuProgress: MenuItem?
        get() = toolbarForNavigation()?.menu?.findItem(R.id.menu_progress)

    override fun shouldNavigateTo(newLocation: String): Boolean {

        return when (isNavigable(newLocation)) {
            true -> {
                // Example: If pressed stimulus button in turbo webpage, Do nothing
                if (!getUrlStart(location).equals(getUrlStart(newLocation))) {
                    when(getUrlStart(newLocation)) {
                        "turbo" -> println("go turbo")
                        "stimulus" ->println("go stimulus")
                        "guides" ->println("go guides")
                    }
                    false
                }else{
                    true
                }

            }
            else -> {
                launchCustomTab(newLocation)
                false
            }
        }
    }

    override fun getNavigationOptions(
        newLocation: String,
        newPathProperties: TurboPathConfigurationProperties
    ): NavOptions {
        return when (newPathProperties.context) {
            MODAL -> slideAnimation()
            else -> super.getNavigationOptions(newLocation, newPathProperties)
        }
    }

    private fun getUrlStart(location: String): String{
        return  location.split("://")[1].split(".")[0]
    }

    private fun isNavigable(location: String): Boolean {
        return location.startsWith(TURBO_URL) || location.startsWith(STIMULUS_URL) || location.startsWith(
            GUIDES_URL)
    }

    private fun launchCustomTab(location: String) {
        val context = fragment.context ?: return
        val color = context.getColor(R.color.color_surface)
        val colorParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(color)
            .setNavigationBarColor(color)
            .build()

        CustomTabsIntent.Builder()
            .setShowTitle(true)
            .setShareState(SHARE_STATE_ON)
            .setUrlBarHidingEnabled(false)
            .setDefaultColorSchemeParams(colorParams)
            .build()
            .launchUrl(context, Uri.parse(location))
    }

    private fun slideAnimation(): NavOptions {
        return navOptions {
            anim {
                enter = R.anim.nav_slide_enter
                exit = R.anim.nav_slide_exit
                popEnter = R.anim.nav_slide_pop_enter
                popExit = R.anim.nav_slide_pop_exit
            }
        }
    }
}
