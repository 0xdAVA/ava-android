package us.buddman.ava.utils

import android.app.Application
import android.content.Context
import com.facebook.FacebookSdk

/**
 * Created by Junseok Oh on 2017-07-09.
 */

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        FacebookSdk.sdkInitialize(this)
    }

    companion object {
        lateinit var context: Context
    }

}
