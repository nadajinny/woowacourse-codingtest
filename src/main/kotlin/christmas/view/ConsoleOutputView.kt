package christmas.view

import christmas.model.Menu
import christmas.util.OutputMessages
import java.text.DecimalFormat

class ConsoleOutputView: OutputView {
    override fun printStartInformation() {
        println(OutputMessages.PROMT_START_INFORMATION)
    }

    override fun printRequestDate() {
        println(OutputMessages.PROMPT_REQUEST_DATE)
    }

    override fun printRequestOrder() {
        println(OutputMessages.PROMPT_REQUEST_ORDER)
    }

    override fun printEventInstructions(date: Int) {
        println(OutputMessages.PROMPT_EVENT_INSTRUCTION_FRONT+"${date}"+ OutputMessages.PROMPT_EVENT_INSTRUCTION_BACK)
        println()
    }

    override fun printStartPrintOrder() {
        println(OutputMessages.PROMPT_ORDER_MENU)
    }

    override fun printOrder(menu: Menu, count: Int) {
        println("${menu.menuName} ${count}개")
    }

    override fun printTotalPrice(totalPrice: Int) {
        println(OutputMessages.PROMPT_TOTAL_PRICE_BEFORE_DISCOUNT)
        println(DecimalFormat("#,###").format(totalPrice)+ OutputMessages.PROMPT_WON)
    }

    override fun printGift(isGift: Boolean) {
        println(OutputMessages.PROMPT_GIFT)
        if(isGift) println(OutputMessages.PROMPT_GIFT_YES)
        else println(OutputMessages.PROMPT_NONE)
    }
}