package christmas.view

import christmas.util.OutputMessages

class ConsoleOutputView: OutputView {
    override fun printStartInformation() {
        println(OutputMessages.PROMT_START_INFORMATION)
    }

    override fun printRequestDate() {
        println(OutputMessages.PROMPT_REQUEST_DATE)
    }
}