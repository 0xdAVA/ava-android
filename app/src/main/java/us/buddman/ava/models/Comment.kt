package us.buddman.ava.models

import java.io.Serializable
import java.util.*

/**
 * Created by Chad Park on 2018-01-20.
 */
data class Comment(
       var author : String,
       var author_token : String,
       var text :  String,
       var post_token : String,
       var comment_token : String,
       var date : String
) : Serializable