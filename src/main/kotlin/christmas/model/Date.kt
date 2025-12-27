package christmas.model

import christmas.util.ErrorMessages

// 입력된 날짜에 따른 해당되는 날짜별 혜택을 저장(받는 혜택이 어떤 것인지에 대한 것은 저장하지 않음)
class Date (public val date: Int) {
    val startDate = 1
    val endDate = 31
    val christmasDDays : List<Int> = (1..25).toList()
    val weekDays : List<Int> = listOf(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31)
    val weekendDays : List<Int> = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
    val specialDays : List<Int> = listOf(3, 10, 17, 24, 25, 31)

    public var isChristmas = false
    public var isweek = false
    public var isweekend = false
    public var isspecial = false

    init {
        if(date !in 1..31) throw IllegalArgumentException(ErrorMessages.WRONG_DATE_RANGE)
        if(date in christmasDDays) isChristmas = true
        if(date in weekDays) isweek = true
        if(date in weekendDays) isweekend = true
        if(date in specialDays) isspecial = true
    }


}