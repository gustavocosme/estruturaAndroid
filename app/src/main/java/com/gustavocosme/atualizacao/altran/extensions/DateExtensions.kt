package com.gustavocosme.atualizacao.altran.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil
import kotlin.math.floor

fun Date.getCalendar(): Calendar {
    val cal = Calendar.getInstance()
    cal.timeZone = TimeZone.getTimeZone("UTC")
    cal.time = this
    return cal
}

fun Date.validateBirthday(): Boolean {
    val a = this.getCalendar()
    val b = Date().getCalendar()

    var diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR)
    if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE)) {
        diff--
    }

    return diff >= 0
}

fun Date.toString(format : String) : String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this)
}

fun Date.dayMonthText() : String {
    val dateFormat = SimpleDateFormat("d 'de' MMMM", Locale.getDefault())
    return dateFormat.format(this)
}

fun Date.monthYearText() : String {
    val dateFormat = SimpleDateFormat("MMMM 'de' yyyy", Locale.getDefault())
    return dateFormat.format(this)
}

fun Date.subtract(days: Int? = null, week: Int? = null, month: Int? = null, minutes: Int? = null) : Date {
    val calendar = Calendar.getInstance()
    calendar.time = this

    when {
        days != null -> calendar.add(Calendar.DAY_OF_MONTH, -days)
        week != null -> calendar.add(Calendar.WEEK_OF_MONTH, -week)
        month != null -> calendar.add(Calendar.MONTH, -month)
        minutes != null -> calendar.add(Calendar.MINUTE, -minutes)
    }

    return calendar.time
}

fun Date.firstDayOfWeek() : Date {
    val calendar = Calendar.getInstance()
    calendar.time = this

    calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)

    return calendar.time
}

fun Date.beginToday(): Date {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.time
}

fun Date.endToday(): Date {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 23)
    calendar.set(Calendar.MINUTE, 59)
    calendar.set(Calendar.SECOND, 59)
    calendar.set(Calendar.MILLISECOND, 59)

    return calendar.time
}

fun Date.todayFromMillis(timeStamp: Long): Date {
    val calendar = Calendar.getInstance()
    calendar.setTimeInMillis(timeStamp)

    val minutesUnformatted = calendar.get(Calendar.MINUTE).toDouble()

    val hour = calendar.get(Calendar.HOUR_OF_DAY)

    var min = (ceil(minutesUnformatted/10)*10).toInt()
    if(hour == 23) {
        min = (floor(minutesUnformatted/10) *10).toInt()
    }

    calendar.set(Calendar.MINUTE, min)
    calendar.set(Calendar.SECOND, 0)

    return calendar.time
}

fun Date.increase(days: Int? = null, week: Int? = null, month: Int? = null) : Date {
    val calendar = Calendar.getInstance()
    calendar.time = this

    when {
        days != null -> calendar.add(Calendar.DAY_OF_MONTH, days)
        week != null -> calendar.add(Calendar.WEEK_OF_MONTH, week)
        month != null -> calendar.add(Calendar.MONTH, month)
    }

    return calendar.time
}

fun Date?.getAge(): Int{
    return if(this != null){
        val a = this.getCalendar()
        val b = Date().getCalendar()

        var diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR)
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE)) {
            diff--
        }

        diff
    } else {
        0
    }

}

fun Date.getTimeInMillis(): Long{
    return this.getCalendar().timeInMillis
}