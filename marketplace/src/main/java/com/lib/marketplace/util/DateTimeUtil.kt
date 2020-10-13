package com.ipos.iposmanage.util

import android.content.Context
import com.lib.marketplace.app.Constants
import com.lib.marketplace.app.Constants.Date.Unit.MILLISECOND
import com.lib.marketplace.app.Constants.Date.Unit.SECOND
import com.lib.marketplace.R
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtil {

    companion object {

        @JvmStatic
        fun convertTimeStampToDate(timeStamp: Any?, outputFormat: String): String {
            if (timeStamp == 0) {
                return ""
            }
            val sdf = SimpleDateFormat(outputFormat, Locale.getDefault())
            var date = Date()
            try {
                timeStamp?.run {
                    date = when (this) {
                        is String -> Date(toLong() * 1000)
                        is Long -> Date(this * 1000)
                        is Double -> Date(toLong() * 1000)
                        is Float -> Date(toLong() * 1000)
                        else -> Date()
                    }
                }
            } catch (ex: NumberFormatException) {
                Timber.e(ex.localizedMessage)
            }

            return sdf.format(date)
        }

        @JvmStatic
        fun convertStringToTimeStamp(date: String, format: String): Any {
            val format = SimpleDateFormat(format)
            try {
                val date = format.parse(date)
                return (date.time / 1000L)
            } catch (e: ParseException) {
//            e.printStackTrace()
            }
            return 0
        }

        @JvmStatic
        fun calculateTimeLine(ctx: Context, targetTime: Long, currentTime: Long): String {
            val timeDiff = (currentTime / 1000 - targetTime) / 60
            return if (timeDiff >= 0) {
                when (timeDiff) {
                    0L -> ctx.getString(R.string.just_now)
                    in 1..60 -> String.format(
                            ctx.getString(
                                    R.string.value_minute_ago,
                                    timeDiff.toString()
                            )
                    )
                    in 60..(60 * 23 + 59) -> String.format(
                            ctx.getString(
                                    R.string.value_hour_ago,
                                    (timeDiff / 60).toInt().toString()
                            )
                    )
                    else -> convertTimeStampToDate(targetTime, Constants.Date.Format.DD_MM_YYYY)
                }
            } else {
                convertTimeStampToDate(targetTime, Constants.Date.Format.DD_MM_YYYY)
            }
        }

        @JvmStatic
        fun compareTimeGet(expriedAt: String, itemCallBack: (Long, Long, Long, Long, Long) -> Unit) {
            val dateStop: String =
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())
            // Custom date format
            // Custom date format
//        Timber.e("expriedAt: " + expriedAt)
//        Log.i("aa1 ", expriedAt)
//        Log.i("aa2 ", dateStop)
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            var d1: Date? = null
            var d2: Date? = null
            try {
                d1 = format.parse(expriedAt)
                d2 = format.parse(dateStop)
//            Log.i("bb1 ", "" + d2.time)
//            Log.i("bb2 ", "" + d1.time)

                // Get msec from each, and subtract.
                // Get msec from each, and subtract.
                val diff = d2!!.time - d1!!.time
                val diffSeconds = diff / 1000
                val diffMinutes = diff / (60 * 1000)
                val diffHours = diff / (60 * 60 * 1000)
                val diffDay = diffHours / 24
                val diffMonth = diffDay / 30
//            println("Số giây : $diffSeconds seconds.")
//            println("Số phút: $diffMinutes minutes.")
//            Timber.e("Số giờ: $diffHours hours.")
//            Timber.e("diffDay: $diffDay day.")
//            Timber.e("diffMonth $diffMonth motnh.")
                var gio = 0.0
                var phut = 0.0
                gio = diffMinutes.toInt() / 60.toDouble()
                phut = diffMinutes.toInt() - (diffMinutes / 60 * 60).toDouble()
                itemCallBack(diffMonth, diffDay, diffHours, diffMinutes, diffSeconds)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
    }


    /**
     * @param timeStamp As millisecond
     * @return timestamp of the date(00:00:00:000) from timeStamp
     */
    fun convertTimeStampToStartOfDate(
            timeStamp: Long,
            timeZone: String = TimeZone.getDefault().id,
            unit: String = MILLISECOND
    ): Long {
        val cal = Calendar.getInstance(TimeZone.getTimeZone(timeZone))
        cal.timeInMillis = timeStamp
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)

        return when (unit) {
            SECOND -> {
                val result = cal.timeInMillis.toString()
                result.substring(0, result.length - 3).toLong()
            }
            else -> cal.timeInMillis
        }
    }

    /**
     * @param timeStamp As millisecond
     * @return timestamp of the date(23:59:59:999) from timeStamp
     */
    fun convertTimeStampToEndOfDate(
            timeStamp: Long,
            timeZone: String = TimeZone.getDefault().id,
            unit: String = MILLISECOND
    ): Long {
        val cal = Calendar.getInstance(TimeZone.getTimeZone(timeZone))
        cal.timeInMillis = timeStamp
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)

        return when (unit) {
            SECOND -> {
                val result = cal.timeInMillis.toString()
                result.substring(0, result.length - 3).toLong()
            }
            else -> cal.timeInMillis
        }
    }

    /**
     * @param targetTime As second
     * @param currentTime As millisecond
     * @return "1 phut truoc" or "1 gio truoc" or "18/06/2019" or so on
     */




    fun compareTimeGet(
            dateStop: String,
            expriedAt: String,
            itemCallBack: (Long, Long, Long, Long) -> Unit
    ) {
        // Custom date format
        // Custom date format
//        Timber.e("expriedAt: " + expriedAt)
//        Log.i("aa1 ", expriedAt)
//        Log.i("aa2 ", dateStop)
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var d1: Date? = null
        var d2: Date? = null
        try {
            d1 = format.parse(expriedAt)
            d2 = format.parse(dateStop)
//            Log.i("bb1 ", "" + d2.time)
//            Log.i("bb2 ", "" + d1.time)

            // Get msec from each, and subtract.
            // Get msec from each, and subtract.
            val diff = d2!!.time - d1!!.time
            val diffSeconds = diff / 1000
            val diffMinutes = diff / (60 * 1000)
            val diffHours = diff / (60 * 60 * 1000)
            val diffDay = diffHours / 24
            val diffMonth = diffDay / 30
//            println("Số giây : $diffSeconds seconds.")
//            println("Số phút: $diffMinutes minutes.")
//            Timber.e("Số giờ: $diffHours hours.")
//            Timber.e("diffDay: $diffDay day.")
//            Timber.e("diffMonth $diffMonth motnh.")
            var gio = 0.0
            var phut = 0.0
            gio = diffMinutes.toInt() / 60.toDouble()
            phut = diffMinutes.toInt() - (diffMinutes / 60 * 60).toDouble()
            itemCallBack(diffMonth, diffDay, diffHours, diffMinutes)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    fun convertSeconds(seconds: Int): String? {
        val h = seconds / 3600
        val m = seconds % 3600 / 60
        val s = seconds % 60
        val sh = if (h > 0) "$h h" else ""
        val sm =
                (if (m < 10 && m > 0 && h > 0) "0 :" else "") + if (m > 0) if (h > 0 && s == 0) m.toString() else "$m :" else "0 : "
        val ss =
                if (s == 0 && (h > 0 || m > 0)) "00" else (if (s < 10 && (h > 0 || m > 0)) "0" else "") + s.toString()
        return sh + (if (h > 0) " " else "") + sm + (if (m > 0) " " else "") + ss
    }
}