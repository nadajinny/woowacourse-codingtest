package christmas.model

import christmas.util.MenuCategory

class EventPlanner(
    private val date: Date,
    private val order: Order
) {
    val christmasDDay : IntRange = 1..25
    val weekDay: List<Int> = listOf(
        3,4,5,6,7,
        10,11,12,13,14,
        17, 18, 19, 20, 21,
        24, 25, 26, 27, 28,
        31
        )

    val staredDay: List<Int> = listOf(3,10, 17, 24, 25, 31)

    val weekEndDay: List<Int> = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
    val isChampange: Boolean = checkGift(order)
    val isChristmasDDay: Boolean = date.getDate() in christmasDDay
    val isWeekDay: Boolean = date.getDate() in weekDay
    val isWeekEndDay: Boolean = date.getDate() in weekEndDay
    val isStaredDay: Boolean = date.getDate() in staredDay


    fun checkGift(order: Order) : Boolean {
        var total = 0
        order.sheet.forEach { (menu, count) ->
            total += menu.price * count
        }
        return total>=120_000
    }

    fun ChristmasDDayBenefit():Int = (date.getDate()-1)*100+1000

    fun WeekDayBenefit(): Int {
        var total = 0
        order.sheet.forEach { (menu, i) ->
            if(menu.category == MenuCategory.DESSERT) total += 2_023*i
        }
        return total
    }

    fun WeekEndBenefit(): Int {
        var total = 0
        order.sheet.forEach { (menu, i) ->
            if(menu.category == MenuCategory.MAIN) total += 2_023*i
        }
        return total
    }

    fun SpecialBenefit(): Int {
        return 1000
    }

    fun GiftBenefit(): Int {
        return 25_000
    }
}