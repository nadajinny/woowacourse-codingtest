package oncall.model

class Day(
    val date: Int,
    val month: Int,
    val dayOfWeek: String
){
    val isWeekend : Boolean = when {
        dayOfWeek == "토" -> true
        dayOfWeek == "일" -> true
        else -> false
    }

    val isHoliday : Boolean = when {
        month == 1 && date == 1 -> true
        month == 3 && date == 1 -> true
        month == 5 && date == 5 -> true
        month == 6 && date == 6 -> true
        month == 8 && date == 15 -> true
        month == 10 && date == 3 -> true
        month == 10 && date == 9 -> true
        month == 12 && date == 25 -> true
        isWeekend -> true
        else -> false
    }

}