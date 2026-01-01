package store.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Promotion(info : String) {
    val name : String
    val buy: Int
    val get: Int
    val start_date: Date
    val end_date: Date
    init {
        val splitedInfo = info.split(",")
        name = splitedInfo[0].trim()
        buy = splitedInfo[1].trim().toIntOrNull() ?: 0
        get = splitedInfo[2].trim().toIntOrNull() ?: 0
        var date = splitedInfo[3].trim()
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        start_date = format.parse(date)
        date = splitedInfo[4].trim()
        val cal = Calendar.getInstance()
        cal.time = format.parse(date)
        cal.add(Calendar.DAY_OF_MONTH,1)
        cal.add(Calendar.MILLISECOND, -1)
        end_date = cal.time
    }
}