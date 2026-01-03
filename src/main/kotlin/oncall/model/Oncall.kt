package oncall.model

import oncall.view.InputView
import oncall.view.OutputView

class Oncall(
    val input: InputView,
    val output: OutputView
) {
    var month = 0
    var startDayOfWeek = "일"
    var weekDayWorker = mutableListOf<String>()
    var weekEndWorker = mutableListOf<String> ()
    var day = mutableListOf<Day>()
    var worker = mutableListOf<String>()
    fun readMonth() {
        while(true) {
            try {
                val (inputmonth, inputdayOfWeek) = input.readMonth()
                month = inputmonth
                startDayOfWeek = inputdayOfWeek
                return
            }catch (e: IllegalArgumentException) {
                output.printError(e.message!!)
            }
        }
    }

    fun readWorker() {
        while(true) {
            try {
                weekDayWorker = input.readWeekDayWorker()
                weekEndWorker = input.readWeekEndWorker(weekDayWorker)
                return
            }catch (e: IllegalArgumentException) {
                output.printError(e.message!!)
            }
        }
    }

    fun replaceMonth() {
        val n = when {
            month == 2 -> 28
            month <= 7 && month % 2 == 1 -> 31
            month <= 7 && month % 2 == 0 -> 30
            month % 2 == 1 -> 30
            month % 2 == 0 -> 31
            else -> 30
        }
        var dayOfWeek = startDayOfWeek
        for (i in 1..n) {
            day.add(Day(i, month,dayOfWeek))
            dayOfWeek = when {
                dayOfWeek == "월" -> "화"
                dayOfWeek == "화" -> "수"
                dayOfWeek == "수" -> "목"
                dayOfWeek == "목" -> "금"
                dayOfWeek == "금" -> "토"
                dayOfWeek == "토" -> "일"
                dayOfWeek == "일" -> "월"
                else -> startDayOfWeek
            }
        }
    }

    fun checkWorker() {
        while(true) {
            if(placeWorker()) return
        }
    }

    fun placeWorker(): Boolean {
        worker = mutableListOf()
        var beforeWorker = ""
        var weekDayNum = 0
        var weekEndNum = 0
        day.forEach { d ->
            if(d.isHoliday) {
                if(replaceWeekEndWorker(weekEndNum, beforeWorker)) return false
                beforeWorker = weekEndWorker[weekEndNum]
                weekEndNum ++
                if(weekEndNum >= weekEndWorker.size) weekEndNum = 0
            }
            else {
                if(replaceWeekDayWorker(weekDayNum, beforeWorker)) return false
                beforeWorker = weekDayWorker[weekDayNum]
                weekDayNum ++
                if(weekDayNum >= weekDayWorker.size) weekDayNum = 0
            }

        }
        return true
    }

    fun replaceWeekDayWorker(num: Int, before: String) : Boolean {
        if(weekDayWorker[num] == before) {
            val switch = weekDayWorker[num]
            weekDayWorker[num] = weekDayWorker[
                if(num == weekDayWorker.size - 1) 0 else num+1
            ]
            weekDayWorker[num+1] = switch

            return true
        }
        worker.add(weekDayWorker[num])
        return false
    }

    fun replaceWeekEndWorker(num: Int, before: String): Boolean {
        if(weekEndWorker[num] == before) {
            val switch = weekEndWorker[num]
            weekEndWorker[num] = weekEndWorker[
                if(num == weekEndWorker.size - 1) 0 else num+1
            ]
            weekEndWorker[num+1] = switch

            return true
        }
        worker.add(weekEndWorker[num])
        return false
    }

    fun printResult() {
        output.printResult(day, worker)
    }
}