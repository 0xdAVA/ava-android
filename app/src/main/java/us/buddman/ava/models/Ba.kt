package us.buddman.ava.models

import java.io.Serializable
import java.util.*

/**
 * Created by Chad Park on 2018-01-20.
 */
data class Ba(
       var type : Number,
       var state : Number,
       var title : String,
       var text : String,
       var date : Date,
       var quality_status : Number,
       var tag : Number,
       var author : String,
       var author_token : String,
       var post_token : String,
       var send_type : Number ,
       var photo : String,
       var comment : ArrayList<Ba>?
) : Serializable