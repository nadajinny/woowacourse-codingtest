package christmas.controller

import christmas.model.Date
import christmas.model.Order
import christmas.util.ErrorMessages
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController(
    private val input : InputView,
    private val output : OutputView
) {
    fun run() {
        output.printOpening()
        val date = inputDate()
        val order = inputOrder()
    }

    private fun inputOrder(): Order {
        output.printOpeningOrder()
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
        output.printOpeningDate()
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