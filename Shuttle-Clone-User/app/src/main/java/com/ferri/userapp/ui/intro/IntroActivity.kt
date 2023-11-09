package com.ferri.userapp.ui.intro

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.ferri.userapp.BaseActivity
import com.ferri.userapp.R
import com.ferri.userapp.ui.events.ActionEvents
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import org.greenrobot.eventbus.EventBus

class IntroActivity : BaseActivity() {

    private val TAG = "IntroActivity"

    var viewPager: ViewPager? = null
    var wormDotsIndicator: WormDotsIndicator? = null

    var introFragment: IntroFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = layoutParams
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_intro)


        viewPager = findViewById<View>(R.id.intro_viewpager) as ViewPager
        wormDotsIndicator =
                findViewById<View>(R.id.worm_dots_indicator) as WormDotsIndicator

        introFragment = IntroFragment()


        val pagerAdapter = IntroPagerAdapter(supportFragmentManager)

        viewPager!!.adapter = pagerAdapter
        viewPager!!.offscreenPageLimit = 0
        wormDotsIndicator!!.setViewPager(viewPager!!)



        viewPager!!.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {

//                Log.i(TAG, "onPageScrolled: position="+position);
            }

            override public fun onPageSelected(position: Int) {
                //   Log.i(MainActivity.TAG, "onPageSelected: position=$position")
                EventBus.getDefault().post(
                        ActionEvents(
                                position
                        )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {
//                Log.i(TAG, "onPageScrollStateChanged: state="+state);
            }
        })
    }
}

