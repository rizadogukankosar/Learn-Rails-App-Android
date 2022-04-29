package com.graifstudio.learnrailsapp

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.graifstudio.learnrailsapp.Adapters.ViewPagerAdapter
import com.graifstudio.learnrailsapp.GuidesPage.GuidesPageFragment
import com.graifstudio.learnrailsapp.StimulusPage.StimulusPageFragment
import com.graifstudio.learnrailsapp.TurboPage.TurboPageFragment
import com.webproject.rubyonrailsandroidapp.features.web.WebFragment
import dev.hotwire.turbo.activities.TurboActivity
import dev.hotwire.turbo.delegates.TurboActivityDelegate

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var bottomNavigationView: BottomNavigationView
    var turboPageFragment: Fragment? = null
    var stimulusPageFragment: Fragment? = null
    var guidesPageFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        turboPageFragment = TurboPageFragment()
        stimulusPageFragment = StimulusPageFragment()
        guidesPageFragment = GuidesPageFragment()
        viewPager = findViewById(R.id.viewpager)
        viewPager.isUserInputEnabled = false
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.turboPageFragment -> viewPager.setCurrentItem(0, true)
                R.id.guidesPageFragment -> viewPager.setCurrentItem(1, true)
                R.id.stimulusPageFragment -> viewPager.setCurrentItem(2, true)
            }
            false
        })
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    0 -> bottomNavigationView.menu.findItem(R.id.turboPageFragment).isChecked = true
                    1 -> bottomNavigationView.menu.findItem(R.id.guidesPageFragment).isChecked = true
                    2 -> bottomNavigationView.menu.findItem(R.id.stimulusPageFragment).isChecked = true
                }

            }
        })
        setupViewPager(viewPager)
        viewPager.currentItem = 1

    }
    private fun setupViewPager(viewPager: ViewPager2) {
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        turboPageFragment?.let { adapter.addFragment(it) }
        guidesPageFragment?.let { adapter.addFragment(it) }
        stimulusPageFragment?.let { adapter.addFragment(it) }
        viewPager.adapter = adapter
    }





}