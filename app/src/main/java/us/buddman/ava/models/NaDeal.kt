package us.buddman.ava.models

import java.io.Serializable
import java.util.*

/**
 * Created by Chad Park on 2018-01-20.
 */
data class NaDeal(
       var deal_token : String,
       var master_token : String,
       var slave_token : String,
       var post_token : String,
       var master_name : String,
       var slave_name : String,
       var state : Number,
       var delivery_number : String,
       var item : Na,
       var comment : ArrayList<Comment>
) : Serializable