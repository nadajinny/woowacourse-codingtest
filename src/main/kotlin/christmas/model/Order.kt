package christmas.model

import christmas.util.ErrorMessages
import christmas.util.MenuCategory

class Order(input: String) {
    val sheet : Map<Menu, Int>
    init {
        var separatedOrder = input.trim().split(",")
        require(input.trim().isNotBlank()) { ErrorMessages.INVALID_ORDER }
        require(separatedOrder.isNotEmpty()) { ErrorMessages.INVALID_ORDER }
        sheet = parseSheet(separatedOrder)
    }

    private fun parseSheet(input: List<String>): MutableMap<Menu, Int> {
        val result = mutableMapOf<Menu, Int>()
        input.forEach {
            val (menu, i) = it.split("-")
            val count = i.toIntOrNull() ?: 0
            require(count >= 1) { ErrorMessages.INVALID_ORDER }
            require(Menu.from(menu)!= Menu.NONE) { ErrorMessages.INVALID_ORDER }
            require(!result.containsKey(Menu.from(menu))) { ErrorMessages.INVALID_ORDER }
            result[Menu.from(menu)]=count
        }
        require(result.values.sum()<21){ ErrorMessages.INVALID_ORDER }
        require(result.keys.all { it.category != MenuCategory.DRINK}) { ErrorMessages.INVALID_ORDER }
        return result
    }
}