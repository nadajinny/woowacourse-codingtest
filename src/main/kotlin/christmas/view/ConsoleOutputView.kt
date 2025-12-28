package christmas.view

import christmas.util.OutputMessages

class ConsoleOutputView: OutputView {
    override fun printOpening() {
        println(OutputMessages.PROMPT_OPENING)
    }

    override fun nextLine() {
        println()
    }

    override fun printOpeningDate() {
        println(OutputMessages.PROMPT_OPENING_DATE)
    }

    override fun printError(e: String) {
        println(e)
    }

    override fun printOpeningOrder() {
        println(OutputMessages.PROMPT_OPENING_ORDER)
    }
}