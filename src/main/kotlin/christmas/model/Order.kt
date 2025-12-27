package christmas.model

import christmas.util.ErrorMessages

class Order(
    input: String
) {
    val menus : Map<Menu, Int>
    init {
        if(input.trim().isBlank()) throw IllegalArgumentException(ErrorMessages.BLANK_MENU_LIST)
        menus = parse(input)
        validateTotalCount()
        validateDrinkOnly()
    }

    fun parse(input: String) : Map<Menu, Int> {
        val result = mutableMapOf<Menu, Int>()
        input.split(",").forEach { token ->
            val (menu, count) = parseMenu(token)
            if(result.containsKey(menu)) throw IllegalArgumentException(ErrorMessages.IN_ORDER_MENU_REPEAT)
            result[menu] = count
        }

        return result
    }

    fun parseMenu(token : String) : Pair<Menu, Int> {
        val parts = token.split("-")
        if(parts.size != 2) throw IllegalArgumentException(ErrorMessages.ORDER_INVALID)
        val menu = Menu.from(parts[0])
        val count = parseCount(parts[1])
        return menu to count
    }

    fun parseCount(value: String): Int {
        val count = value.toIntOrNull() ?: throw IllegalArgumentException(ErrorMessages.ORDER_INVALID)
        if(count < 1) throw IllegalArgumentException()
        return count
    }

    fun validateTotalCount() {
        if(menus.values.sum() > 20) throw IllegalArgumentException(ErrorMessages.ORDER_COUNT_MUST_BE_LESS_THAN_21)
    }

    fun validateDrinkOnly() {
        if(menus.keys.all { it.category == "음료" }) throw IllegalArgumentException(ErrorMessages.CANNOT_ORDER_ONLY_DRINK)
    }

    fun getALLorder() : Map<Menu, Int> {
        return menus
    }

    fun getTotalPrice() : Int {
        var totalprice = 0
        menus.forEach { (menu, i) ->
            totalprice += menu.price * i
        }

        return totalprice
    }
}