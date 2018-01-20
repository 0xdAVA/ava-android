package us.buddman.ava.models

import android.support.annotation.IntegerRes
import java.io.Serializable

/**
 * Created by Chad Park on 2018-01-20.
 */
data class User(
        var username: String,
        var id: String,
        var password: String,
        var user_token: String,
        var facebook_token: String,
        var profile_img: String,
        var userType: Int
) : Serializable