package us.buddman.ava.models

import java.io.Serializable
import java.util.*

/**
 * Created by Chad Park on 2018-01-20.
 */
data class Na(
        var state: Int,
        var title: String,
        var text: String,
        var date: String,
        var quality_status: Int,
        var tag: Int,
        var author: String,
        var author_token: String,
        var post_token: String,
        var send_type: Int,
        var photo: String,
        var select_type : Int,
        var comment : ArrayList<Comment>
) : Serializable