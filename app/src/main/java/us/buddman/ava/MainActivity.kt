package us.buddman.ava

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import us.buddman.ava.fragment.AhFragment
import us.buddman.ava.utils.BaseActivity

class MainActivity : BaseActivity() {
    override val viewId: Int = R.layout.activity_main
    override val toolbarId: Int = R.id.toolbar

    lateinit var pagerAdapter: MainPagerAdapter
    override fun setDefault() {
        disableToggle()
        setToolbarTitle("")
        pagerAdapter = MainPagerAdapter(supportFragmentManager)
        mainPager.adapter = pagerAdapter
        mainPager.offscreenPageLimit = 4
        bottombar.setOnTabSelectListener { tabId: Int ->
            when (tabId) {
                R.id.main_ah -> mainPager.currentItem = 0
                R.id.main_naba -> mainPager.currentItem = 1
                R.id.main_da -> mainPager.currentItem = 2
                R.id.main_mypage -> mainPager.currentItem = 3
            }
        }
        mainPager.currentItem = 0
        bottombar.setDefaultTab(R.id.main_ah)
    }

    class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
//            return when (position) {
//                0 -> AhFragment()
//                else -> null
//            }
            return AhFragment()
        }

        override fun getCount(): Int {
            return 1
        }

        override fun getPageTitle(position: Int): CharSequence {
            return ""
        }
    }
}
