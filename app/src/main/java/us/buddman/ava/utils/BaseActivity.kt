package us.buddman.ava.utils


import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.os.Build
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View

/**
 * Created by Chad Park on 2017-10-27.
 */

abstract class BaseActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var baseBinding: ViewDataBinding
    internal var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewId)
        if (toolbarId != 0) {
            toolbar = findViewById<View>(toolbarId) as Toolbar
            setSupportActionBar(toolbar)
            toolbar.setTitleTextColor(Color.WHITE)
            toolbar.contentInsetStartWithNavigation = 0
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = Color.BLACK
        }
        setDefault()
    }

    protected abstract fun setDefault()

    protected abstract val viewId: Int

    protected abstract val toolbarId: Int


    fun disableToggle() {
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    fun enableToggle() {
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun setToolbarTitle(titleStr: String) {
        this.supportActionBar!!.title = titleStr
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}