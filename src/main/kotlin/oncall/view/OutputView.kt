package oncall.view

import oncall.model.Day


class OutputView {
    fun printError(e: String) {
        println(e)
    }
    fun printResult(day: List<Day>, worker: List<String>) {
        var i = 0
        day.forEach { d->
            if(d.isHoliday && !d.isWeekend) println("${d.month}월 ${d.date}일 ${d.dayOfWeek}(휴일) ${worker[i]}")
            println("${d.month}월 ${d.date}일 ${d.dayOfWeek} ${worker[i]}")
            i++
        }
    }
}