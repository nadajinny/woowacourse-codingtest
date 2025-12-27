package christmas.controller

import christmas.model.Date
import christmas.model.EventPlanner
import christmas.model.Menu
import christmas.model.Order
import christmas.util.ErrorMessages
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerContoller(
    private val input: InputView,
    private val output: OutputView
) {
    fun run() {
        output.printStartInformation()
        val eventplanner = collectUserInput()
        output.printEventInstructions(eventplanner.date.date)

        output.printStartPrintOrder()
        eventplanner.order.getALLorder().forEach { (menu, i) ->
            output.printOrder(menu, i)
        }
        println()

        output.printTotalPrice(eventplanner.order.getTotalPrice())
        println()

        output.printGift(eventplanner.checkGift())
    }

    fun collectUserInput(): EventPlanner {
        output.printRequestDate()
        val read_date = input.readLine()
        if(read_date.trim().toIntOrNull() == null) throw IllegalArgumentException(ErrorMessages.WRONG_DATE_RANGE)
        val date = Date(read_date.trim().toInt())

        output.printRequestOrder()
        val read_order = input.readLine()
        val order = Order(read_order)

        return EventPlanner(date, order)
    }
}