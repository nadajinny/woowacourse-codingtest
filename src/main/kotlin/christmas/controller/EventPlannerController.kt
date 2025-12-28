package christmas.controller

import christmas.model.Date
import christmas.model.Order
import christmas.util.ErrorMessages
import christmas.util.OutputMessages
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController(
    private val input : InputView,
    private val output : OutputView
) {
    fun run() {
        output.printOpening(OutputMessages.PROMPT_OPENING)
        val date = inputDate()
        val order = inputOrder()
        showEvent(date, order)
    }

    private fun showEvent(date: Date, order: Order) {
        output.printOpeningEvent(date.getDate())
        output.nextLine()
        MenuList(order)
        val beforediscount = beforeDiscount(order)
        output.nextLine()
        GiftMenu()
        output.nextLine()
        BenefitList()
        output.nextLine()
        BenefitPrice()
        output.nextLine()
        ExpectedPrice()
        output.nextLine()
        EventBadge()
    }

    private fun GiftMenu() {

    }
    private fun MenuList(order: Order) {
        output.printOpening(OutputMessages.PROMPT_OPENING_MENU_LIST)
        order.sheet.forEach { (menu, i) ->
            output.printEachMenu(menu.menuName, i)
        }
    }

    private fun beforeDiscount(order: Order): Int {
        output.printOpening(OutputMessages.PROMPT_OPENING_PRICE_BEFORE_DISCOUNT)
        var total = 0
        order.sheet.forEach { (menu, i) ->
            total += menu.price*i
        }
        output.printPrice(total)
        return total
    }

    private fun inputOrder(): Order {
        output.printOpening(OutputMessages.PROMPT_OPENING_ORDER)
        while(true) {
            try {
                val order = Order(input.readLine())
                return order
            }catch (e : IllegalArgumentException) {
                output.printError(ErrorMessages.INVALID_ORDER)
            }
        }
    }

    private fun inputDate(): Date {
        output.printOpening(OutputMessages.PROMPT_OPENING_DATE)
        while(true) {
            try {
                val date = Date(input.readLine().toIntOrNull() ?: 0)
                return date
            }catch (e : IllegalArgumentException) {
                output.printError(ErrorMessages.INVALID_DATE)
            }
        }
    }
}