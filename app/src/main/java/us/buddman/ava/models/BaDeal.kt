package us.buddman.ava.models

import java.util.ArrayList

/**
 * Created by Chad Park on 2018-01-20.
 */
data class BaDeal(
       var deal_token : String,
       var master_token : String,
       var slave_token : String,
       var master_name : String,
       var slave_name : String,
       var ba_master : Ba,
       var ba_slave : Ba,
       var state : Number,
       var master_delivery_number : String,
       var slave_delivery_number : String,
       var comment : ArrayList<Comment>?
)