package us.buddman.ava.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Chad Park on 2018-01-21.
 */
class DateHelper{
    companion object {
        fun getDateString(date : Date) : String{
            var formatter = SimpleDateFormat("yyyy.MM.dd")
            return formatter.format(date)
        }
    }
}