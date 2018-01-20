package us.buddman.ava.models

import us.buddman.ava.utils.DateHelper
import java.io.Serializable
import java.util.*

/**
 * Created by Chad Park on 2018-01-20.
 */
data class Ah(
        var post_token: String,
        var author: String,
        var author_token: String,
        var title: String,
        var text: String,
        var date: Date,
        var photo: String,
        var profile_img : String,
        var like: Int
) : Serializable {
    var dateString: String = ""
        get() = DateHelper.getDateString(date)
}
